package se.fusion1013.networking.packet;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import se.fusion1013.items.CobaltItems;
import se.fusion1013.items.misc.WalkieTalkieItem;
import se.fusion1013.networking.CobaltServerNetworking;
import se.fusion1013.networking.payload.UpdateWalkieTalkiePayloadC2S;
import se.fusion1013.networking.payload.UpdateWalkieTalkiePayloadS2C;
import se.fusion1013.util.item.ItemUtil;
import se.fusion1013.util.math.MathUtil;

/**
 * Sent from the client to the server when the client makes changes to a walkie talkie item.
 * This updates the walkie talkie item on the server side.
 */
public class UpdateWalkieTalkieC2SPacket {
    public static void receive(UpdateWalkieTalkiePayloadC2S payload, ServerPlayNetworking.Context context) {

        ServerPlayerEntity player = context.player();

        // Gets the held walkie talkie item
        ItemStack stack = ItemUtil.getHeldItemOfType(player, CobaltItems.WALKIE_TALKIE);

        // If it is not a valid item, do not try to edit it
        // TODO: if (!(stack.getItem() instanceof WalkieTalkieItem) && !stack.hasNbt()) return;

        // Read the buffer. Index is the id of the value changed
        int index = payload.index();
        boolean status = payload.status();

        // Get the current values
        boolean activate = WalkieTalkieItem.isActivate(stack);
        boolean mute = WalkieTalkieItem.isMute(stack);
        int canal = WalkieTalkieItem.getCanal(stack);

        switch (index) {
            case 0 -> activate = !activate;
            case 1 -> {
                // Loop the canal value
                if (status) canal = MathUtil.loopValue(canal + 1, 1, 8);
                else canal = MathUtil.loopValue(canal - 1, 1, 8);
            }
            case 2 -> mute = !mute;
        }

        // Set the new values on the item
        WalkieTalkieItem.setActivate(stack, activate);
        WalkieTalkieItem.setMute(stack, mute);
        WalkieTalkieItem.setCanal(stack, canal);

        // Send the updated item stack to the client
        CobaltServerNetworking.sendUpdateWalkieTalkie(player, stack);
    }
}
