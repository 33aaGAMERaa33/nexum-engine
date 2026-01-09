package io.nexum.engine.channel.packets;

import io.nexum.engine.models.Size;
import io.nexum.engine.channel.Packet;
import org.jetbrains.annotations.NotNull;

public class SyncDataPacket extends Packet {
    private final int fpsLimit;
    private final long enginePID;
    private final boolean release;
    private final @NotNull Size screenSize;

    public SyncDataPacket(
            long enginePID,
            int fpsLimit,
            boolean release,
            @NotNull Size screenSize
    ) {
        this.enginePID = enginePID;
        this.release = release;
        this.fpsLimit = fpsLimit;
        this.screenSize = screenSize;
    }

    public long getEnginePID() {
        return this.enginePID;
    }

    public boolean isRelease() {
        return release;
    }

    public int getFpsLimit() {
        return fpsLimit;
    }

    public @NotNull Size getScreenSize() {
        return screenSize;
    }
}
