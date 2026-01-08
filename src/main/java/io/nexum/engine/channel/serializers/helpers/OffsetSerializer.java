package io.nexum.engine.channel.serializers.helpers;

import io.nexum.engine.models.Offset;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperSerializer;
import org.jetbrains.annotations.NotNull;

public class OffsetSerializer implements HelperSerializer<Offset> {
    @Override
    public void serialize(@NotNull Offset object, @NotNull FriendlyBuffer friendlyBuffer) {
        friendlyBuffer.writeInt(object.getLeftPos());
        friendlyBuffer.writeInt(object.getTopPos());
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
