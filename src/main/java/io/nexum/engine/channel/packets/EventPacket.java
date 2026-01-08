package io.nexum.engine.channel.packets;

import io.nexum.engine.events.Event;
import io.nexum.engine.channel.Packet;
import org.jetbrains.annotations.NotNull;

public class EventPacket extends Packet {
    private final @NotNull Event event;

    public EventPacket(@NotNull Event event) {
        this.event = event;
    }

    public @NotNull Event getEvent() {
        return event;
    }
}
