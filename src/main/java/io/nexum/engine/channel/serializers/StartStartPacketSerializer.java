package io.nexum.engine.channel.serializers;

import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.PacketSerializer;
import io.nexum.engine.channel.packets.StartPacket;
import org.jetbrains.annotations.NotNull;

public class StartStartPacketSerializer implements PacketSerializer<StartPacket> {
    @Override
    public void serialize(@NotNull StartPacket packet, @NotNull FriendlyBuffer friendlyBuffer) {

    }

    @Override
    public @NotNull String getIdentifier() {
        return "start";
    }

    @Override
    public @NotNull Class<StartPacket> getPacketClazz() {
        return StartPacket.class;
    }
}
