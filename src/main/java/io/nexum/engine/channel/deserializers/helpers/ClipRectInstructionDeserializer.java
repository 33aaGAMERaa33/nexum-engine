package io.nexum.engine.channel.deserializers.helpers;

import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperDeserializer;
import io.nexum.engine.services.HelperDeserializerService;
import io.nexum.engine.models.Offset;
import io.nexum.engine.models.Size;
import io.nexum.engine.render.instructions.ClipRectInstruction;
import org.jetbrains.annotations.NotNull;

public class ClipRectInstructionDeserializer implements HelperDeserializer<ClipRectInstruction> {
    @Override
    public @NotNull ClipRectInstruction deserialize(@NotNull FriendlyBuffer friendlyBuffer) {
        final Offset offset = HelperDeserializerService.getInstance().deserialize(friendlyBuffer);
        final Size size = HelperDeserializerService.getInstance().deserialize(friendlyBuffer);

        return new ClipRectInstruction(offset, size);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "clip_rect";
    }

    @Override
    public @NotNull Class<ClipRectInstruction> getObjectClazz() {
        return ClipRectInstruction.class;
    }
}
