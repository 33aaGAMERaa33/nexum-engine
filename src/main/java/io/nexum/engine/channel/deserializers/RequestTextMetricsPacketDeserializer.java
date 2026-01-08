package io.nexum.engine.channel.deserializers;

import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.PacketDeserializer;
import io.nexum.engine.channel.packets.RequestTextMetricsPacket;
import org.jetbrains.annotations.NotNull;

public class RequestTextMetricsPacketDeserializer implements PacketDeserializer<RequestTextMetricsPacket> {
    @Override
    public @NotNull RequestTextMetricsPacket deserialize(@NotNull FriendlyBuffer friendlyBuffer) {
        final String value = friendlyBuffer.readString();
        final int fontSize = friendlyBuffer.readInt();

        return new RequestTextMetricsPacket(value, fontSize);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "request_text_metrics";
    }

    @Override
    public @NotNull Class<RequestTextMetricsPacket> getPacketClazz() {
        return RequestTextMetricsPacket.class;
    }
}
