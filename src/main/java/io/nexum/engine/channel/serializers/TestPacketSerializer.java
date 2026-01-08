package io.nexum.engine.channel.serializers;

import io.nexum.engine.services.HelperSerializerService;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.PacketSerializer;
import io.nexum.engine.channel.packets.TestPacket;
import org.jetbrains.annotations.NotNull;

public class TestPacketSerializer implements PacketSerializer<TestPacket> {
    @Override
    public void serialize(@NotNull TestPacket packet, @NotNull FriendlyBuffer friendlyBuffer) {
        HelperSerializerService.getInstance().serialize(packet.getObject(), friendlyBuffer);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "test";
    }

    @Override
    public @NotNull Class<TestPacket> getPacketClazz() {
        return TestPacket.class;
    }
}
