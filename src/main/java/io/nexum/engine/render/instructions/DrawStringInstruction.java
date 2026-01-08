package io.nexum.engine.render.instructions;

import io.nexum.engine.render.RenderContext;
import io.nexum.engine.render.RenderInstruction;
import org.jetbrains.annotations.NotNull;

public class DrawStringInstruction extends RenderInstruction {
    private final @NotNull String value;

    public DrawStringInstruction(@NotNull String value) {
        this.value = value;
    }

    @Override
    public void execute(@NotNull RenderContext renderContext) {
        renderContext.drawString(value);
    }
}
