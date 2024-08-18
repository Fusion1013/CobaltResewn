package se.fusion1013.networking.packet;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import se.fusion1013.networking.payload.UpdateMechanicSpectaclesPayloadS2C;
import se.fusion1013.util.FacilityStatus;

public class UpdateWaterFacilityStatusS2CPacket {
    public static void receive(UpdateMechanicSpectaclesPayloadS2C payload, ClientPlayNetworking.Context context) {
        context.client().execute(() -> {
            FacilityStatus.POWER_CURRENT = payload.power();
            FacilityStatus.PRESSURE_CURRENT = payload.pressure();
        });
    }
}
