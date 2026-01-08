package io.nexum.engine.channel.deserializers.helpers;

import io.nexum.engine.services.HelperDeserializerService;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperDeserializer;
import io.nexum.engine.render.RenderInstruction;
import io.nexum.engine.render.RenderContextConsumer;
import org.jetbrains.annotations.NotNull;

public class RenderContextConsumerDeserializer implements HelperDeserializer<RenderContextConsumer> {
    @Override
    public @NotNull RenderContextConsumer deserialize(@NotNull FriendlyBuffer friendlyBuffer) {
        final RenderContextConsumer consumer = new RenderContextConsumer();
        final int instructionsSize = friendlyBuffer.readInt();

        for(int i = 0; i < instructionsSize; i++) {
            final RenderInstruction instruction = HelperDeserializerService.getInstance().deserialize(friendlyBuffer);
            consumer.addInstruction(instruction);
        }

        return consumer;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "render_context";
    }

    @Override
    public @NotNull Class<RenderContextConsumer> getObjectClazz() {
        return RenderContextConsumer.class;
    }
}
