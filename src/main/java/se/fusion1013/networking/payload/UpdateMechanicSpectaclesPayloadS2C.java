package se.fusion1013.networking.payload;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import se.fusion1013.networking.CobaltNetworkingConstants;

public record UpdateMechanicSpectaclesPayloadS2C(int power, int pressure) implements CustomPayload {

    public static final CustomPayload.Id<UpdateMechanicSpectaclesPayloadS2C> ID = new CustomPayload.Id<>(CobaltNetworkingConstants.UPDATE_MECHANIC_SPECTACLES_S2C);
    public static final PacketCodec<RegistryByteBuf, UpdateMechanicSpectaclesPayloadS2C> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, UpdateMechanicSpectaclesPayloadS2C::power,
            PacketCodecs.INTEGER, UpdateMechanicSpectaclesPayloadS2C::pressure,
            UpdateMechanicSpectaclesPayloadS2C::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
