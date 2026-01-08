package io.nexum.engine.channel.deserializers.helpers;

import io.nexum.engine.material.TestObject;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperDeserializer;
import org.jetbrains.annotations.NotNull;

public class TestObjectDeserializer implements HelperDeserializer<TestObject> {
    @Override
    public @NotNull TestObject deserialize(@NotNull FriendlyBuffer friendlyBuffer) {
        return new TestObject(friendlyBuffer.readString());
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
