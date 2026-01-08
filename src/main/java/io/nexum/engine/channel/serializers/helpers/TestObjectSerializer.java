package io.nexum.engine.channel.serializers.helpers;

import io.nexum.engine.material.TestObject;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperSerializer;
import org.jetbrains.annotations.NotNull;

public class TestObjectSerializer implements HelperSerializer<TestObject> {
    @Override
    public void serialize(@NotNull TestObject object, @NotNull FriendlyBuffer friendlyBuffer) {
        friendlyBuffer.writeString(object.getMessage());
    }

    @Override
    public @NotNull String getIdentifier() {
        return "test_object";
    }

    @Override
    public @NotNull Class<TestObject> getObjectClazz() {
        return TestObject.class;
    }
}
