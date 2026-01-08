package io.nexum.engine.channel.deserializers;

import io.nexum.engine.services.HelperDeserializerService;
import io.nexum.engine.material.TestObject;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.PacketDeserializer;
import io.nexum.engine.channel.packets.TestPacket;
import org.jetbrains.annotations.NotNull;

public class TestPacketDeserializer implements PacketDeserializer<TestPacket> {
    @Override
    public @NotNull TestPacket deserialize(@NotNull FriendlyBuffer friendlyBuffer) {
        final TestObject testObject = HelperDeserializerService.getInstance().deserialize(friendlyBuffer);
        return new TestPacket(testObject);
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
