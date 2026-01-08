package io.nexum.engine.channel.serializers.helpers;

import io.nexum.engine.services.HelperSerializerService;
import io.nexum.engine.material.TextMetrics;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.HelperSerializer;
import org.jetbrains.annotations.NotNull;

public class TextMetricsSerializer implements HelperSerializer<TextMetrics> {
    @Override
    public void serialize(@NotNull TextMetrics object, @NotNull FriendlyBuffer friendlyBuffer) {
        HelperSerializerService.getInstance().serialize(object.getSize(), friendlyBuffer);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "text_metrics";
    }

    @Override
    public @NotNull Class<TextMetrics> getObjectClazz() {
        return TextMetrics.class;
    }
}
