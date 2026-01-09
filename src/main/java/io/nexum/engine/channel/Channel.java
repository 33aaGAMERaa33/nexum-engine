package io.nexum.engine.channel;

import io.nexum.engine.exceptions.AlreadyInitialized;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.*;

import static io.nexum.engine.Engine.LOGGER;

public class Channel {
    private static @Nullable Channel instance = null;

    private @Nullable Future<?> senderTask;
    private final PacketManager packetManager;
    private final DataInputStream inputStream;
    private final DataInputStream errorStream;
    private final DataOutputStream outputStream;
    private final Process applicationProcess;
    private final BlockingQueue<byte[]> sendQueue = new LinkedBlockingQueue<>();
    private final ExecutorService sendQueueExecutor = Executors.newFixedThreadPool(1);
    private final ExecutorService incomingDataExecutors = Executors.newFixedThreadPool(2);

    private boolean isRunning = false;

    private Channel(
            Process applicationProcess,
            PacketManager packetManager
    ) {
        System.out.println(applicationProcess.pid());
        this.packetManager = packetManager;
        this.applicationProcess = applicationProcess;

        this.inputStream = new DataInputStream(applicationProcess.getInputStream());
        this.errorStream = new DataInputStream(applicationProcess.getErrorStream());
        this.outputStream = new DataOutputStream(applicationProcess.getOutputStream());

        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
    }

    public void listen() {
        if(this.isRunning) return;
        this.isRunning = true;

        handlePendingQueuePackets();

        this.incomingDataExecutors.submit(() -> {
            try {
                while (this.isRunning) {
                    final byte[] lenBytes = this.inputStream.readNBytes(4);
                    final int size = ByteBuffer.wrap(lenBytes)
                            .order(ByteOrder.LITTLE_ENDIAN)
                            .getInt();

                    final byte[] payload = this.inputStream.readNBytes(size);
                    final FriendlyBuffer friendlyBuffer = new FriendlyBuffer(payload);

                    this.packetManager.handleReceivedData(friendlyBuffer);
                }
            }catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }

            this.stop();
        });

        // STDERR (logs / erros do Dart)
        this.incomingDataExecutors.submit(() -> {
            try (
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(this.errorStream, StandardCharsets.UTF_8)
                    )
            ) {
                String line;
                while (this.isRunning && (line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                this.error("Erro STDERR: %s", e.getMessage());
            }
        });
    }

    public void stop() {
        if(!this.isRunning) return;
        this.isRunning = false;

        if(this.senderTask != null) {
            this.senderTask.cancel(true);
        }

        if(this.applicationProcess.isAlive()) {
            this.applicationProcess.destroy();
        }

        this.sendQueueExecutor.shutdownNow();
        this.incomingDataExecutors.shutdownNow();

        try {
            this.inputStream.close();
            this.outputStream.close();
        }catch(Exception ignored) {}
    }

    protected void handlePendingQueuePackets() {
        this.senderTask = this.sendQueueExecutor.submit(() -> {
            while(this.isRunning) {
                try {
                    final byte[] payload = this.sendQueue.take();

                    this.outputStream.writeInt(payload.length);
                    this.outputStream.write(payload);
                    this.outputStream.flush();
                }catch (InterruptedException ignored) {
                }catch (Exception e) {
                    this.error(e.toString());
                }
            }
        });
    }

    public void send(@NotNull byte[] bytes) {
        this.sendQueue.offer(bytes);
    }

    private void log(@NotNull String log, Object ... args) {
        LOGGER.log("Channel", log, args);
    }

    private void debug(@NotNull String log, Object ... args) {
        LOGGER.debug("Channel", log, args);
    }

    private void warn(@NotNull String log, Object ... args) {
        LOGGER.warn("Channel", log, args);
    }

    private void error(@NotNull String log, Object ... args) {
        LOGGER.error("Channel", log, args);
    }

    public static @NotNull Channel getInstance() {
        return Objects.requireNonNull(instance);
    }

    public static Channel initialize(
            Process applicationProcess,
            PacketManager packetManager
    ) {
        if(instance != null) throw new AlreadyInitialized("Channel já foi inicializado");

        final Channel channel = new Channel(applicationProcess, packetManager);
        instance = channel;

        return channel;
    }

    public static boolean isInitialized() {
        return instance != null;
    }
}