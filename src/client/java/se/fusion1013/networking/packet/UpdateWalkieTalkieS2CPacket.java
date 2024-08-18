package se.fusion1013.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import se.fusion1013.Main;
import se.fusion1013.gui.WalkieTalkieScreen;

public class UpdateWalkieTalkieS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender responseSender) {

        // ItemStack stack = buffer.readItemStack();
        // WalkieTalkieScreen.getInstance().updateButtons(stack);
    }
}
