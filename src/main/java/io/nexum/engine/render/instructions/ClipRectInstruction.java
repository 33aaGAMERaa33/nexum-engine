package io.nexum.engine.render.instructions;

import io.nexum.engine.models.Offset;
import io.nexum.engine.models.Size;
import io.nexum.engine.render.RenderContext;
import io.nexum.engine.render.RenderInstruction;
import org.jetbrains.annotations.NotNull;

public class ClipRectInstruction extends RenderInstruction {
    private final @NotNull Offset offset;
    private final @NotNull Size size;

    public ClipRectInstruction(@NotNull Offset offset, @NotNull Size size) {
        this.offset = offset;
        this.size = size;
    }

    @Override
    public void execute(@NotNull RenderContext renderContext) {
        renderContext.clipRect(offset, size);
    }
}
