package se.fusion1013.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.util.math.BlockPos;
import org.joml.Vector3f;
import se.fusion1013.networking.packet.*;
import se.fusion1013.networking.payload.*;

public class CobaltClientNetworking {

    public static void register() {
        PayloadTypeRegistry.playS2C().register(UpdateMechanicSpectaclesPayloadS2C.ID, UpdateMechanicSpectaclesPayloadS2C.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(UpdateMechanicSpectaclesPayloadS2C.ID, UpdateWaterFacilityStatusS2CPacket::receive);

        // Walkie talkie networking
        PayloadTypeRegistry.playS2C().register(OpenWalkieTalkieScreenPayloadS2C.ID, OpenWalkieTalkieScreenPayloadS2C.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(OpenWalkieTalkieScreenPayloadS2C.ID, OpenWalkieTalkieScreenS2CPacket::receive);
        PayloadTypeRegistry.playS2C().register(UpdateWalkieTalkiePayloadS2C.ID, UpdateWalkieTalkiePayloadS2C.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(UpdateWalkieTalkiePayloadS2C.ID, UpdateWalkieTalkieS2CPacket::receive);

        // TODO: ClientPlayNetworking.registerGlobalReceiver(CobaltNetworkingConstants.SET_FOG_VALUE_S2C, SetFogValueS2CPacket::receive);

        // Item display screen networking
        // TODO: ClientPlayNetworking.registerGlobalReceiver(CobaltNetworkingConstants.OPEN_ITEM_DISPLAY_SCREEN_S2C, OpenItemDisplayScreenS2CPacket::receive);
    }

    // Send methods

    public static void sendUpdateWalkieTalkie(int index, boolean status) {
        ClientPlayNetworking.send(new UpdateWalkieTalkiePayloadC2S(index, status));
    }

    public static void sendUpdateItemDisplayBlock(BlockPos blockPos, Vector3f offset, Vector3f offsetFrequency, Vector3f offsetAmplitude, Vector3f scale, Vector3f scaleFrequency, Vector3f scaleAmplitude, Vector3f rotation, Vector3f rotationSpeed) {
        ClientPlayNetworking.send(new UpdateItemDisplayPayloadC2S(
                blockPos,
                offset, offsetFrequency, offsetAmplitude,
                scale, scaleFrequency, scaleAmplitude,
                rotation, rotationSpeed
            )
        );
    }

}
