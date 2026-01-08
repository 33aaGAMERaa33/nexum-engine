package io.nexum.engine.render.instructions;

import io.nexum.engine.render.RenderContext;
import io.nexum.engine.render.RenderInstruction;
import io.nexum.engine.render.RenderContextConsumer;
import org.jetbrains.annotations.NotNull;

public class CreateSubContextInstruction extends RenderInstruction {
    private final @NotNull RenderContextConsumer consumer;

    public CreateSubContextInstruction(@NotNull RenderContextConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void execute(@NotNull RenderContext renderContext) {
        final RenderContext subContext = renderContext.create();

        consumer.consume(subContext);
        subContext.endFrame();
    }
}
