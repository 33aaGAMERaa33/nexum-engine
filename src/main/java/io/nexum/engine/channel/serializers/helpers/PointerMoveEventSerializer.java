package io.nexum.engine.channel.serializers.helpers;

import io.nexum.engine.services.HelperSerializerService;
import io.nexum.engine.events.PointerMoveEvent;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperSerializer;
import org.jetbrains.annotations.NotNull;

public class PointerMoveEventSerializer implements HelperSerializer<PointerMoveEvent> {
    @Override
    public void serialize(@NotNull PointerMoveEvent object, @NotNull FriendlyBuffer friendlyBuffer) {
        HelperSerializerService.getInstance().serialize(object.getPosition(), friendlyBuffer);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "pointer_move";
    }

    @Override
    public @NotNull Class<PointerMoveEvent> getObjectClazz() {
        return PointerMoveEvent.class;
    }
}
