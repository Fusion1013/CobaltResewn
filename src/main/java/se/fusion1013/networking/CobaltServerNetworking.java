package se.fusion1013.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import se.fusion1013.networking.packet.ItemSetTriggerAbilityC2S;
import se.fusion1013.networking.packet.UpdateItemDisplayC2SPacket;
import se.fusion1013.networking.packet.UpdateSpeakerC2SPacket;
import se.fusion1013.networking.packet.UpdateWalkieTalkieC2SPacket;
import se.fusion1013.networking.payload.UpdateItemDisplayPayloadC2S;

import static se.fusion1013.networking.CobaltNetworkingConstants.*;

/**
 * Registers all the global receivers on the server side.
 */
public class CobaltServerNetworking {

    public static void register() {
        // Register global receivers
        ServerPlayNetworking.registerGlobalReceiver(ITEM_SET_TRIGGER_ABILITY_C2S, new ItemSetTriggerAbilityC2S()::receive);
        ServerPlayNetworking.registerGlobalReceiver(UPDATE_WALKIETALKIE_C2S, UpdateWalkieTalkieC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(UPDATE_SPEAKER_C2S, UpdateSpeakerC2SPacket::receive);


        PayloadTypeRegistry.playC2S().register(UpdateItemDisplayPayloadC2S.ID, UpdateItemDisplayPayloadC2S.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(UpdateItemDisplayPayloadC2S.ID, UpdateItemDisplayC2SPacket::receive);
    }

}
