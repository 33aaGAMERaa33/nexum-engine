package io.nexum.engine.events;

import io.nexum.engine.models.Offset;
import org.jetbrains.annotations.NotNull;

public abstract class PointerEvent extends InputEvent {
    private final @NotNull Offset position;

    public PointerEvent(@NotNull Offset position) {
        this.position = position;
    }

    public @NotNull Offset getPosition() {
        return position;
    }
}
