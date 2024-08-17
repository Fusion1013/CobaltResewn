package se.fusion1013.networking.packet;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import se.fusion1013.items.CobaltItems;
import se.fusion1013.items.misc.WalkieTalkieItem;
import se.fusion1013.networking.CobaltNetworkingConstants;
import se.fusion1013.util.item.ItemUtil;
import se.fusion1013.util.math.MathUtil;

/**
 * Sent from the client to the server when the client makes changes to a walkie talkie item.
 * This updates the walkie talkie item on the server side.
 */
public class UpdateWalkieTalkieC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buffer, PacketSender responseSender) {

        // Gets the held walkie talkie item
        ItemStack stack = ItemUtil.getHeldItemOfType(player, CobaltItems.WALKIE_TALKIE);

        // If it is not a valid item, do not try to edit it
        if (!(stack.getItem() instanceof WalkieTalkieItem) && !stack.hasNbt()) return;

        // Read the buffer. Index is the id of the value changed
        int index = buffer.readInt();
        boolean status = buffer.readBoolean();

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
        PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());
        packet.writeItemStack(stack);
        ServerPlayNetworking.send(player, CobaltNetworkingConstants.UPDATE_WALKIETALKIE_S2C, packet);
    }
}
