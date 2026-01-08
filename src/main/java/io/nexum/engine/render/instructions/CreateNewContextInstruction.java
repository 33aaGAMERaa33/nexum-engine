package io.nexum.engine.render.instructions;

import io.nexum.engine.render.RenderContext;
import io.nexum.engine.render.RenderContextConsumer;
import io.nexum.engine.render.RenderInstruction;
import org.jetbrains.annotations.NotNull;

public class CreateNewContextInstruction extends RenderInstruction {
    private final @NotNull RenderContextConsumer consumer;

    public CreateNewContextInstruction(@NotNull RenderContextConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void execute(@NotNull RenderContext renderContext) {
        final RenderContext newContext = renderContext.createWithoutContext();
        consumer.consume(newContext);

        newContext.endFrame();
    }
}
