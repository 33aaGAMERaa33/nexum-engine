package io.nexum.engine.channel.serializers;

import io.nexum.engine.services.HelperSerializerService;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.PacketSerializer;
import io.nexum.engine.channel.packets.EventPacket;
import org.jetbrains.annotations.NotNull;

public class EventPacketSerializer implements PacketSerializer<EventPacket> {
    @Override
    public void serialize(@NotNull EventPacket packet, @NotNull FriendlyBuffer friendlyBuffer) {
        HelperSerializerService.getInstance().serialize(packet.getEvent(), friendlyBuffer);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "event";
    }

    @Override
    public @NotNull Class<EventPacket> getPacketClazz() {
        return EventPacket.class;
    }
}
