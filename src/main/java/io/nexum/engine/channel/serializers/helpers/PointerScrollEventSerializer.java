package io.nexum.engine.channel.serializers.helpers;

import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperSerializer;
import io.nexum.engine.events.PointerScrollEvent;
import io.nexum.engine.services.HelperSerializerService;
import org.jetbrains.annotations.NotNull;

public class PointerScrollEventSerializer implements HelperSerializer<PointerScrollEvent> {
    @Override
    public void serialize(@NotNull PointerScrollEvent object, @NotNull FriendlyBuffer friendlyBuffer) {
        HelperSerializerService.getInstance().serialize(object.getPosition(), friendlyBuffer);
        friendlyBuffer.writeDouble(object.getScrollDelta());
    }

    @Override
    public @NotNull String getIdentifier() {
        return "pointer_scroll";
    }

    @Override
    public @NotNull Class<PointerScrollEvent> getObjectClazz() {
        return PointerScrollEvent.class;
    }
}
