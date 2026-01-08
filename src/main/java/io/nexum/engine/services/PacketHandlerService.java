package io.nexum.engine.services;

import io.nexum.engine.storages.PacketHandlerRegistry;
import io.nexum.engine.exceptions.PacketHandleException;
import io.nexum.engine.channel.Packet;
import io.nexum.engine.channel.PacketHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PacketHandlerService {
    private static @NotNull PacketHandlerService instance = new PacketHandlerService();

    private PacketHandlerService() {

    }

    public <T extends Packet> void handlePacket(@NotNull T packet) {
        @Nullable
        final PacketHandler<Packet> packetHandler = PacketHandlerRegistry.getInstance().get(packet.getClass());

        if(packetHandler == null) throw new PacketHandleException(
                String.format("O pacote %s não possui um handler", packet.getClass().getSimpleName())
        );

        packetHandler.handle(packet);
    }

    public static @NotNull PacketHandlerService getInstance() {
        return instance;
    }
}
