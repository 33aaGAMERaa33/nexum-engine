package io.nexum.engine.channel.packets;

import io.nexum.engine.channel.Packet;

public class RequestTextMetricsPacket extends Packet {
    private final String value;
    private final int fontSize;

    public RequestTextMetricsPacket(String value, int fontSize) {
        this.value = value;
        this.fontSize = fontSize;
    }

    public String getValue() {
        return value;
    }

    public int getFontSize() {
        return fontSize;
    }
}
