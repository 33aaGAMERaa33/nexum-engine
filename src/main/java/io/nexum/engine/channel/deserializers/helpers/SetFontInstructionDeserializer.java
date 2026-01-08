package io.nexum.engine.channel.deserializers.helpers;

import io.nexum.engine.services.HelperDeserializerService;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperDeserializer;
import io.nexum.engine.render.instructions.SetFontInstruction;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class SetFontInstructionDeserializer implements HelperDeserializer<SetFontInstruction> {
    @Override
    public @NotNull SetFontInstruction deserialize(@NotNull FriendlyBuffer friendlyBuffer) {
        final Font font = HelperDeserializerService.getInstance().deserialize(friendlyBuffer);
        return new SetFontInstruction(font);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "set_font";
    }

    @Override
    public @NotNull Class<SetFontInstruction> getObjectClazz() {
        return SetFontInstruction.class;
    }
}
