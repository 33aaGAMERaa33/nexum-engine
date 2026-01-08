package io.nexum.engine.channel.deserializers.helpers;

import io.nexum.engine.models.Offset;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperDeserializer;
import org.jetbrains.annotations.NotNull;

public class OffsetDeserializer implements HelperDeserializer<Offset> {
    @Override
    public @NotNull Offset deserialize(@NotNull FriendlyBuffer friendlyBuffer) {
        final int leftPos = friendlyBuffer.readInt();
        final int topPos = friendlyBuffer.readInt();

        return new Offset(leftPos, topPos);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "offset";
    }

    @Override
    public @NotNull Class<Offset> getObjectClazz() {
        return Offset.class;
    }
}
