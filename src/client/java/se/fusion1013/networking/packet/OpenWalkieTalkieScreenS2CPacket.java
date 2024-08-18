package se.fusion1013.networking.packet;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.text.Text;
import se.fusion1013.Main;
import se.fusion1013.gui.WalkieTalkieScreen;
import se.fusion1013.items.CobaltItems;
import se.fusion1013.networking.payload.OpenWalkieTalkieScreenPayloadS2C;
import se.fusion1013.util.item.ItemUtil;

public class OpenWalkieTalkieScreenS2CPacket {
    public static void receive(OpenWalkieTalkieScreenPayloadS2C payload, ClientPlayNetworking.Context context) {
        context.client().execute(() -> {
            if (context.player() == null) return;
            var item = ItemUtil.getHeldItemOfType(context.player(), CobaltItems.WALKIE_TALKIE);
            if (item == null) return;
            new WalkieTalkieScreen(item);
        });
    }
}
