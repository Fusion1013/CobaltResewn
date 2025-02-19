package se.fusion1013.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import se.fusion1013.gui.WalkieTalkieScreen;
import se.fusion1013.items.CobaltItems;
import se.fusion1013.util.item.ItemUtil;

public class OpenWalkieTalkieScreenS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender responseSender) {
        client.execute(() -> {
            if (client.player == null) return;
            var item = ItemUtil.getHeldItemOfType(client.player, CobaltItems.WALKIE_TALKIE);
            if (item == null) return;
            new WalkieTalkieScreen(item);
        });
    }
}
