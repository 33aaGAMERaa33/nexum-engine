package io.nexum.engine;

import io.nexum.engine.channel.Channel;
import io.nexum.engine.events.Event;
import io.nexum.engine.exceptions.AlreadyInitialized;
import io.nexum.engine.helpers.Logger;
import io.nexum.engine.models.Size;
import io.nexum.engine.channel.PacketManager;
import io.nexum.engine.channel.packets.EventPacket;
import io.nexum.engine.render.RenderContext;
import io.nexum.engine.render.RenderContextConsumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Engine {
    private final int fpsLimit;
    private boolean started = false;
    private @Nullable Runnable onRender;
    private final @NotNull Size screenSize;
    private @Nullable RenderContext renderContext;

    private static @Nullable Engine instance;
    public static final Logger LOGGER = new Logger();

    protected Engine(int fpsLimit, @NotNull Size screenSize) {
        this.fpsLimit = Math.min(300, fpsLimit);
        this.screenSize = screenSize;
    }

    public void render(@NotNull RenderContextConsumer consumer) {
        if(this.renderContext == null) return;
        final long start = System.nanoTime();

        this.renderContext.beginFrame();
        consumer.consume(this.renderContext);
        this.renderContext.endFrame();
        if(this.onRender != null) this.onRender.run();

        final long end = System.nanoTime();
        final long elapsed = (end - start) / 100_000_000;

        this.log("Tempo de renderização %sms", elapsed);
    }

    public <T extends Event> void emitEvent(@NotNull T event) {
        PacketManager.getInstance().sendPacket(new EventPacket(event));
    }

    public void start(@NotNull ProcessBuilder processBuilder) {
        if(this.started) throw new RuntimeException("Já foi iniciado");
        this.started = true;

        try {
            final Process applicationProcess = processBuilder.start();
            final PacketManager packetManager = PacketManager.initialize();

            final Channel channel = Channel.initialize(
                    applicationProcess,
                    packetManager
            );

            channel.listen();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if(!this.started) return;
        System.exit(0);
    }

    public void setRenderContext(@Nullable RenderContext renderContext) {
        this.renderContext = renderContext;
    }

    public void setOnRender(@Nullable Runnable onRender) {
        this.onRender = onRender;
    }

    public int getFpsLimit() {
        return this.fpsLimit;
    }

    public @NotNull Size getScreenSize() {
        return this.screenSize;
    }

    public static Engine initialize(int fpsLimit, @NotNull Size screenSize) {
        if(Engine.instance != null) throw new AlreadyInitialized("Nexum já foi inicializado");
        final Engine nexum = new Engine(fpsLimit, screenSize);
        Engine.instance = nexum;

        return nexum;
    }

    public static @NotNull Engine getInstance() {
        return Objects.requireNonNull(instance);
    }

    public static boolean isInitialized() {
        return instance != null;
    }

    protected void log(@NotNull String log, Object ... args) {
        LOGGER.log("Nexum", log, args);
    }
}
