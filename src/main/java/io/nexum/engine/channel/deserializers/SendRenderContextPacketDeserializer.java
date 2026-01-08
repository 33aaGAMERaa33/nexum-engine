package io.nexum.engine.channel.deserializers;

import io.nexum.engine.render.RenderInstruction;
import io.nexum.engine.services.HelperDeserializerService;
import io.nexum.engine.channel.FriendlyBuffer;
import io.nexum.engine.channel.PacketDeserializer;
import io.nexum.engine.channel.packets.SendRenderContextPacket;
import io.nexum.engine.render.RenderContextConsumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class SendRenderContextPacketDeserializer implements PacketDeserializer<SendRenderContextPacket> {
    @Override
    public @NotNull SendRenderContextPacket deserialize(@NotNull FriendlyBuffer friendlyBuffer) {
        final int instructionLength = friendlyBuffer.readInt();

        for(int i = 0; i < instructionLength; i ++) {
            final UUID uuid = UUID.fromString(friendlyBuffer.readString());
            @Nullable RenderInstruction instruction = RenderInstruction.fromUUID(uuid);

            if(instruction != null) {
                continue;
            }

            instruction = HelperDeserializerService.getInstance().deserialize(friendlyBuffer);
            instruction.attach(uuid);
        }

        final RenderContextConsumer consumer = HelperDeserializerService.getInstance().deserialize(friendlyBuffer);
        return new SendRenderContextPacket(consumer);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "send_render_context";
    }

    @Override
    public @NotNull Class<SendRenderContextPacket> getPacketClazz() {
        return SendRenderContextPacket.class;
    }
}
