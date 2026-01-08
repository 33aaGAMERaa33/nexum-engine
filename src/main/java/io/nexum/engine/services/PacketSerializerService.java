package io.nexum.engine.services;

import io.nexum.engine.storages.PacketSerializerRegistry;
import io.nexum.engine.exceptions.PacketSerializationException;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.Packet;
import io.nexum.engine.channel.PacketSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class PacketSerializerService {
    private static @NotNull PacketSerializerService instance = new PacketSerializerService();

    private PacketSerializerService() {

    }

    public <T extends Packet> @NotNull FriendlyBuffer serializePacket(@NotNull T packet) {
        final FriendlyBuffer friendlyBuffer = new FriendlyBuffer();

        @Nullable
        final PacketSerializer<Packet> packetSerializer = PacketSerializerRegistry.getInstance().get(packet.getClass());

        if(packetSerializer == null) throw new PacketSerializationException(String.format(
                "O serializador para pacote %s não foi encontrado",
                packet.getClass().getSimpleName()
        ));

        friendlyBuffer.writeString(packetSerializer.getIdentifier());
        friendlyBuffer.writeString(Objects.requireNonNull(packet.getUUID()).toString());
        packetSerializer.serialize(packet, friendlyBuffer);

        return friendlyBuffer;
    }

    public static @NotNull PacketSerializerService getInstance() {
        return instance;
    }
}
