package io.nexum.engine.channel.serializers.helpers;

import io.nexum.engine.services.HelperSerializerService;
import io.nexum.engine.events.PointerClickEvent;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperSerializer;
import org.jetbrains.annotations.NotNull;

public class PointerClickEventSerializer implements HelperSerializer<PointerClickEvent> {
    @Override
    public void serialize(@NotNull PointerClickEvent object, @NotNull FriendlyBuffer friendlyBuffer) {
        HelperSerializerService.getInstance().serialize(object.getPosition(), friendlyBuffer);
        friendlyBuffer.writeBool(object.isReleased());
    }

    @Override
    public @NotNull String getIdentifier() {
        return "pointer_click";
    }

    @Override
    public @NotNull Class<PointerClickEvent> getObjectClazz() {
        return PointerClickEvent.class;
    }
}
