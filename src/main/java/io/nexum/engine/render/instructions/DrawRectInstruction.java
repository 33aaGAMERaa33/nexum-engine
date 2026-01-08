package io.nexum.engine.render.instructions;

import io.nexum.engine.models.Size;
import io.nexum.engine.render.RenderContext;
import io.nexum.engine.render.RenderInstruction;
import org.jetbrains.annotations.NotNull;

public class DrawRectInstruction extends RenderInstruction {
    private final @NotNull Size size;

    public DrawRectInstruction(@NotNull Size size) {
        this.size = size;
    }

    @Override
    public void execute(@NotNull RenderContext renderContext) {
        renderContext.drawRect(size);
    }

    public @NotNull Size getSize() {
        return size;
    }
}
