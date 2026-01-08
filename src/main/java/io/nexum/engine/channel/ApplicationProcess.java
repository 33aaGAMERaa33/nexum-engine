package io.nexum.engine.channel;

import io.nexum.ApplicationFile;
import io.nexum.RunInfo;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// Não tem suporte a linux / macOS
public class ApplicationProcess {
    private ApplicationProcess() {

    }

    public static ProcessBuilder build() {
        return build(new File(ApplicationFile.APPLICATION_PATH));
    }

    public static ProcessBuilder build(@NotNull File file) {
        if(RunInfo.IS_RELEASE) return buildRelease(file);
        else return buildDebug(file);
    }

    public static ProcessBuilder buildRelease(@NotNull File file) {
        final ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(file.getAbsolutePath());

        return processBuilder;
    }

    public static ProcessBuilder buildDebug(@NotNull File file) {
        final ProcessBuilder processBuilder = new ProcessBuilder();
        final List<String> command = new ArrayList<>();

        command.add("dart");
        command.add("run");
        command.add("--enable-asserts");
        command.add(file.getAbsolutePath());

        processBuilder.command(command);
        processBuilder.redirectErrorStream(false);
        return processBuilder;
    }
}
