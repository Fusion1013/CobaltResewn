package se.fusion1013.networking.payload;

import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import se.fusion1013.networking.CobaltNetworkingConstants;

public record UpdateWalkieTalkiePayloadS2C(ItemStack stack) implements CustomPayload {

    public static final CustomPayload.Id<UpdateWalkieTalkiePayloadS2C> ID = new CustomPayload.Id<>(CobaltNetworkingConstants.UPDATE_WALKIETALKIE_S2C);
    public static final PacketCodec<RegistryByteBuf, UpdateWalkieTalkiePayloadS2C> CODEC = PacketCodec.tuple(ItemStack.PACKET_CODEC, UpdateWalkieTalkiePayloadS2C::stack, UpdateWalkieTalkiePayloadS2C::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
