package se.fusion1013.networking.payload;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import se.fusion1013.networking.CobaltNetworkingConstants;

public record UpdateWalkieTalkiePayloadC2S(int index, boolean status) implements CustomPayload {

    public static final CustomPayload.Id<UpdateWalkieTalkiePayloadC2S> ID = new CustomPayload.Id<>(CobaltNetworkingConstants.UPDATE_WALKIETALKIE_C2S);
    public static final PacketCodec<RegistryByteBuf, UpdateWalkieTalkiePayloadC2S> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, UpdateWalkieTalkiePayloadC2S::index,
            PacketCodecs.BOOL, UpdateWalkieTalkiePayloadC2S::status,
            UpdateWalkieTalkiePayloadC2S::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
