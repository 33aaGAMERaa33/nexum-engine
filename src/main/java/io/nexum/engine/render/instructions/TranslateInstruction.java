package io.nexum.engine.render.instructions;

import io.nexum.engine.models.Offset;
import io.nexum.engine.render.RenderContext;
import io.nexum.engine.render.RenderInstruction;
import org.jetbrains.annotations.NotNull;

public class TranslateInstruction extends RenderInstruction {
    private final @NotNull Offset offset;

    public TranslateInstruction(@NotNull Offset offset) {
        this.offset = offset;
    }

    @Override
    public void execute(@NotNull RenderContext renderContext) {
        renderContext.translate(offset);
    }
}
