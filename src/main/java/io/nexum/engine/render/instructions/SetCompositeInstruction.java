package io.nexum.engine.render.instructions;

import io.nexum.engine.render.RenderContext;
import io.nexum.engine.render.RenderInstruction;
import org.jetbrains.annotations.NotNull;

public class SetCompositeInstruction extends RenderInstruction {
    private final float alpha;

    public SetCompositeInstruction(float alpha) {
        this.alpha = alpha;
    }

    @Override
    public void execute(@NotNull RenderContext renderContext) {
        renderContext.setComposite(this.alpha);
    }

    public float getAlpha() {
        return alpha;
    }
}
