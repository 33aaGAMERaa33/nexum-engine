package io.nexum.engine.channel.handlers;

import io.nexum.engine.Engine;
import io.nexum.engine.channel.PacketHandler;
import io.nexum.engine.channel.packets.SendRenderContextPacket;
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
