package io.nexum.engine.channel.packets;

import io.nexum.engine.material.TestObject;
import io.nexum.engine.channel.Packet;
import org.jetbrains.annotations.NotNull;

public class TestPacket extends Packet {
    private final @NotNull TestObject test;

    public TestPacket(@NotNull TestObject test) {
        this.test = test;
    }

    public @NotNull TestObject getObject() {
        return this.test;
    }
}
