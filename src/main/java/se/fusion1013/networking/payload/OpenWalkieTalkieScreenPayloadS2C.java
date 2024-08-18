package se.fusion1013.networking.payload;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import se.fusion1013.networking.CobaltNetworkingConstants;

public record OpenWalkieTalkieScreenPayloadS2C() implements CustomPayload {

    public static final CustomPayload.Id<OpenWalkieTalkieScreenPayloadS2C> ID = new CustomPayload.Id<>(CobaltNetworkingConstants.OPEN_WALKIE_TALKIE_SCREEN_S2C);
    public static final PacketCodec<RegistryByteBuf, OpenWalkieTalkieScreenPayloadS2C> CODEC = PacketCodec.unit(new OpenWalkieTalkieScreenPayloadS2C());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
