package io.nexum.engine.channel.deserializers.helpers;

import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperDeserializer;
import io.nexum.engine.render.instructions.SetCompositeInstruction;
import org.jetbrains.annotations.NotNull;

public class SetCompositeInstructionDeserializer implements HelperDeserializer<SetCompositeInstruction> {
    @Override
    public @NotNull SetCompositeInstruction deserialize(@NotNull FriendlyBuffer friendlyBuffer) {
        final float alpha = friendlyBuffer.readFloat();
        return new SetCompositeInstruction(alpha);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "set_composite";
    }

    @Override
    public @NotNull Class<SetCompositeInstruction> getObjectClazz() {
        return SetCompositeInstruction.class;
    }
}
