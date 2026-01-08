package io.nexum.engine.channel.packets;

import io.nexum.engine.channel.Packet;
import io.nexum.engine.render.RenderContextConsumer;
import org.jetbrains.annotations.NotNull;

public class SendRenderContextPacket extends Packet {
    private final @NotNull RenderContextConsumer consumer;

    public SendRenderContextPacket(@NotNull RenderContextConsumer consumer) {
        this.consumer = consumer;
    }

    public @NotNull RenderContextConsumer getConsumer() {
        return consumer;
    }
}
