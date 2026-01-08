package io.nexum.engine.channel.deserializers.helpers;

import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperDeserializer;
import io.nexum.engine.render.RenderInstruction;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class RenderInstructionDeserializer implements HelperDeserializer<RenderInstruction> {
    @Override
    public @NotNull RenderInstruction deserialize(@NotNull FriendlyBuffer friendlyBuffer) {
        final UUID uuid = UUID.fromString(friendlyBuffer.readString());
        final RenderInstruction instruction = RenderInstruction.fromUUID(uuid);
        assert instruction != null;

        return instruction;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "render_instruction";
    }

    @Override
    public @NotNull Class<RenderInstruction> getObjectClazz() {
        return RenderInstruction.class;
    }
}
