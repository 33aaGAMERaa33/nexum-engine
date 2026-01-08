package io.nexum.engine.events;

import io.nexum.engine.models.Offset;
import org.jetbrains.annotations.NotNull;

public class PointerMoveEvent extends PointerEvent {
    public PointerMoveEvent(@NotNull Offset position) {
        super(position);
    }
}
