package io.nexum.channel.handlers;

import io.nexum.Engine;
import io.nexum.channel.PacketHandler;
import io.nexum.channel.packets.SendRenderContextPacket;
import org.jetbrains.annotations.NotNull;

public class SendGraphicsPacketHandler implements PacketHandler<SendRenderContextPacket> {
    @Override
    public void handle(@NotNull SendRenderContextPacket packet) {
        Engine.getInstance().render(packet.getConsumer());
    }

    @Override
    public @NotNull Class<SendRenderContextPacket> getPacketClazz() {
        return SendRenderContextPacket.class;
    }
}
