package io.nexum.engine.events;

import io.nexum.engine.models.Offset;
import org.jetbrains.annotations.NotNull;

public class PointerScrollEvent extends PointerEvent {
    private final double scrollDelta;

    public PointerScrollEvent(@NotNull Offset position, double scrollDelta) {
        super(position);
        this.scrollDelta = scrollDelta;
    }

    public double getScrollDelta() {
        return scrollDelta;
    }
}
