package io.nexum.engine.channel.serializers;

import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.PacketSerializer;
import io.nexum.engine.channel.packets.SyncDataPacket;
import org.jetbrains.annotations.NotNull;

public class SyncDataPacketSerializer implements PacketSerializer<SyncDataPacket> {
    @Override
    public void serialize(@NotNull SyncDataPacket packet, @NotNull FriendlyBuffer friendlyBuffer) {
        friendlyBuffer.writeInt(packet.getScreenSize().getWidth());
        friendlyBuffer.writeInt(packet.getScreenSize().getHeight());
        friendlyBuffer.writeInt(packet.getFpsLimit());
        friendlyBuffer.writeBool(packet.isRelease());
        friendlyBuffer.writeLong(packet.getEnginePID());
    }

    @Override
    public @NotNull String getIdentifier() {
        return "sync_data";
    }

    @Override
    public @NotNull Class<SyncDataPacket> getPacketClazz() {
        return SyncDataPacket.class;
    }
}
