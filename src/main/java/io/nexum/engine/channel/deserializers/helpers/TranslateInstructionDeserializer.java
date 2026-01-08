package io.nexum.engine.channel.deserializers.helpers;

import io.nexum.engine.services.HelperDeserializerService;
import io.nexum.engine.models.Offset;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperDeserializer;
import io.nexum.engine.render.instructions.TranslateInstruction;
import org.jetbrains.annotations.NotNull;

public class TranslateInstructionDeserializer implements HelperDeserializer<TranslateInstruction> {
    @Override
    public @NotNull TranslateInstruction deserialize(@NotNull FriendlyBuffer friendlyBuffer) {
        final @NotNull Offset offset = HelperDeserializerService.getInstance().deserialize(friendlyBuffer);
        return new TranslateInstruction(offset);
    }


    @Override
    public @NotNull String getIdentifier() {
        return "translate";
    }

    @Override
    public @NotNull Class<TranslateInstruction> getObjectClazz() {
        return TranslateInstruction.class;
    }
}
