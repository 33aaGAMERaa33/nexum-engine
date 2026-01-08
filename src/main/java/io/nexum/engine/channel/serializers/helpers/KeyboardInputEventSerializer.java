package io.nexum.engine.channel.serializers.helpers;

import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperSerializer;
import io.nexum.engine.events.KeyboardInputEvent;
import org.jetbrains.annotations.NotNull;

public class KeyboardInputEventSerializer implements HelperSerializer<KeyboardInputEvent> {
    @Override
    public void serialize(@NotNull KeyboardInputEvent object, @NotNull FriendlyBuffer friendlyBuffer) {
        friendlyBuffer.writeInt(object.getKeyCode());
        friendlyBuffer.writeBool(object.isReleased());
    }

    @Override
    public @NotNull String getIdentifier() {
        return "keyboard";
    }

    @Override
    public @NotNull Class<KeyboardInputEvent> getObjectClazz() {
        return KeyboardInputEvent.class;
    }
}
