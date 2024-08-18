package se.fusion1013.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import se.fusion1013.networking.packet.UpdateItemDisplayC2SPacket;
import se.fusion1013.networking.packet.UpdateWalkieTalkieC2SPacket;
import se.fusion1013.networking.payload.*;

/**
 * Registers all the global receivers on the server side.
 */
public class CobaltServerNetworking {

    public static void register() {
        // Register global receivers
        // TODO: ServerPlayNetworking.registerGlobalReceiver(ITEM_SET_TRIGGER_ABILITY_C2S, new ItemSetTriggerAbilityC2S()::receive);
        PayloadTypeRegistry.playC2S().register(UpdateWalkieTalkiePayloadC2S.ID, UpdateWalkieTalkiePayloadC2S.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(UpdateWalkieTalkiePayloadC2S.ID, UpdateWalkieTalkieC2SPacket::receive);
        // TODO: ServerPlayNetworking.registerGlobalReceiver(UPDATE_SPEAKER_C2S, UpdateSpeakerC2SPacket::receive);


        PayloadTypeRegistry.playC2S().register(UpdateItemDisplayPayloadC2S.ID, UpdateItemDisplayPayloadC2S.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(UpdateItemDisplayPayloadC2S.ID, UpdateItemDisplayC2SPacket::receive);
    }

    // Send methods

    /**
     * Sends an update to the mechanic goggles.
     * @param player the player to send it to.
     * @param power the power value.
     * @param pressure the pressure value.
     */
    public static void sendUpdateMechanicSpectacles(PlayerEntity player, int power, int pressure) {
        ServerPlayNetworking.send((ServerPlayerEntity) player, new UpdateMechanicSpectaclesPayloadS2C(power, pressure));
    }

    /**
     * Tells the <code>Player</code> to open the WalkieTalkie screen.
     *
     * @param player the <code>Player</code> that should open the screen.
     */
    public static void sendOpenWalkieTalkieScreen(PlayerEntity player) {
        ServerPlayNetworking.send((ServerPlayerEntity) player, new OpenWalkieTalkieScreenPayloadS2C());
    }

    /**
     * Sends the updated WalkieTalkie <code>ItemStack</code> to the given <code>Player</code>.
     *
     * @param player the <code>Player</code> to send the update to.
     * @param itemStack the modified WalkieTalkie <code>ItemStack</code>.
     */
    public static void sendUpdateWalkieTalkie(PlayerEntity player, ItemStack itemStack) {
        ServerPlayNetworking.send((ServerPlayerEntity) player, new UpdateWalkieTalkiePayloadS2C(itemStack));
    }
}
