package se.fusion1013.networking.packet;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.item.ItemStack;
import se.fusion1013.gui.WalkieTalkieScreen;
import se.fusion1013.networking.payload.UpdateWalkieTalkiePayloadS2C;

public class UpdateWalkieTalkieS2CPacket {
    public static void receive(UpdateWalkieTalkiePayloadS2C payload, ClientPlayNetworking.Context context) {
        context.client().execute(() -> {
            ItemStack stack = payload.stack();
            WalkieTalkieScreen.getInstance().updateButtons(stack);
        });
    }
}
