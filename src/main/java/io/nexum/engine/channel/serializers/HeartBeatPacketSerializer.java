package io.nexum.engine.channel.serializers;

import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.PacketSerializer;
import io.nexum.engine.channel.packets.HeartBeatPacket;
import org.jetbrains.annotations.NotNull;

public class HeartBeatPacketSerializer implements PacketSerializer<HeartBeatPacket> {
    @Override
    public void serialize(@NotNull HeartBeatPacket packet, @NotNull FriendlyBuffer friendlyBuffer) {

    }

    @Override
    public @NotNull String getIdentifier() {
        return "heart_beat";
    }

    @Override
    public @NotNull Class<HeartBeatPacket> getPacketClazz() {
        return HeartBeatPacket.class;
    }
}
