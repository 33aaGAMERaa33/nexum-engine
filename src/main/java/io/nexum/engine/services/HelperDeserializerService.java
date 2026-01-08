package io.nexum.engine.services;

import io.nexum.engine.storages.HelperDeserializerRegistry;
import io.nexum.engine.exceptions.HelperDeserializationException;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperDeserializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HelperDeserializerService {
    private static @NotNull HelperDeserializerService instance = new HelperDeserializerService();

    private HelperDeserializerService() {

    }

    public <T> @NotNull T deserialize(@NotNull FriendlyBuffer friendlyBuffer) {
        final String identifier = friendlyBuffer.readString();

        @Nullable
        final HelperDeserializer<Object> helperDeserializer = HelperDeserializerRegistry.getInstance().get(identifier);

        if(helperDeserializer == null) throw new HelperDeserializationException(
                String.format("O desserializador de identificador %s não foi encontrado", identifier)
        );

        @SuppressWarnings("unchecked")
        final T object = (T) helperDeserializer.deserialize(friendlyBuffer);
        return object;
    }

    public static @NotNull HelperDeserializerService getInstance() {
        return instance;
    }
}
