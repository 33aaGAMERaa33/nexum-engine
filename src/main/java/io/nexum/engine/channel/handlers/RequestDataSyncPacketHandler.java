package io.nexum.engine.channel.handlers;

import io.nexum.engine.Engine;
import io.nexum.engine.channel.PacketHandler;
import io.nexum.engine.channel.PacketManager;
import io.nexum.engine.channel.packets.RequestDataSyncPacket;
import io.nexum.engine.channel.packets.SyncDataPacket;
import org.jetbrains.annotations.NotNull;

public class RequestDataSyncPacketHandler implements PacketHandler<RequestDataSyncPacket> {
    @Override
    public void handle(@NotNull RequestDataSyncPacket packet) {
        final Engine nexumInstance = Engine.getInstance();

        final SyncDataPacket responsePacket = new SyncDataPacket(
                ProcessHandle.current().pid(),
                nexumInstance.getFpsLimit(),
                false,
                nexumInstance.getScreenSize()
        );

        PacketManager.getInstance().sendResponsePacket(
                packet, responsePacket
        );
    }

    @Override
    public @NotNull Class<RequestDataSyncPacket> getPacketClazz() {
        return RequestDataSyncPacket.class;
    }
}
