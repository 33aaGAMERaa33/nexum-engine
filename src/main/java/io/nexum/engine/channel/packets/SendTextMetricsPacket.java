package io.nexum.engine.channel.packets;

import io.nexum.engine.material.TextMetrics;
import io.nexum.engine.channel.Packet;
import org.jetbrains.annotations.NotNull;

public class SendTextMetricsPacket extends Packet {
    private final @NotNull TextMetrics textMetrics;

    public SendTextMetricsPacket(@NotNull TextMetrics textMetrics) {
        this.textMetrics = textMetrics;
    }

    public @NotNull TextMetrics getTextMetrics() {
        return textMetrics;
    }
}
