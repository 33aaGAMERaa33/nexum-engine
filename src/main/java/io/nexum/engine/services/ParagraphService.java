package io.nexum.engine.services;

import io.nexum.engine.material.TextMetrics;
import org.jetbrains.annotations.NotNull;

public class ParagraphService {
    private static final @NotNull ParagraphService instance = new ParagraphService();

    private ParagraphService() {

    }

    public @NotNull TextMetrics buildTextMetrics() {
        return null;
    }

    public static @NotNull ParagraphService getInstance() {
        return instance;
    }
}
