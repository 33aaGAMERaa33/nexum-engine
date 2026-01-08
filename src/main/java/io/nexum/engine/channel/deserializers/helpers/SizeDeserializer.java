package io.nexum.engine.channel.deserializers.helpers;

import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperDeserializer;
import io.nexum.engine.models.Size;
import org.jetbrains.annotations.NotNull;

public class SizeDeserializer implements HelperDeserializer<Size> {
    @Override
    public @NotNull Size deserialize(@NotNull FriendlyBuffer friendlyBuffer) {
        final int width = friendlyBuffer.readInt();
        final int height = friendlyBuffer.readInt();

        return new Size(width, height);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "size";
    }

    @Override
    public @NotNull Class<Size> getObjectClazz() {
        return Size.class;
    }
}
