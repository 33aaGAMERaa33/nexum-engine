package io.nexum.engine.channel.deserializers.helpers;

import io.nexum.engine.services.HelperDeserializerService;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperDeserializer;
import io.nexum.engine.render.RenderContextConsumer;
import io.nexum.engine.render.instructions.CreateSubContextInstruction;
import org.jetbrains.annotations.NotNull;

public class CreateGraphicsInstructionDeserializer implements HelperDeserializer<CreateSubContextInstruction> {
    @Override
    public @NotNull CreateSubContextInstruction deserialize(@NotNull FriendlyBuffer friendlyBuffer) {
        final RenderContextConsumer consumer = HelperDeserializerService.getInstance().deserialize(friendlyBuffer);
        return new CreateSubContextInstruction(consumer);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "create_sub_context";
    }

    @Override
    public @NotNull Class<CreateSubContextInstruction> getObjectClazz() {
        return CreateSubContextInstruction.class;
    }
}
