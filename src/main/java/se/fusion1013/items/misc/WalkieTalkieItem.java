package se.fusion1013.items.misc;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.CobaltRarity;
import se.fusion1013.items.components.CobaltComponents;
import se.fusion1013.networking.CobaltServerNetworking;
import se.fusion1013.networking.payload.OpenWalkieTalkieScreenPayloadS2C;

public class WalkieTalkieItem extends CobaltItem {

    private final int MAX_RANGE;

    public WalkieTalkieItem(int maxRange) {
        super(
                (Settings) new Settings()
                        .rarity(CobaltRarity.Great)
                        .maxCount(1)
                        .component(CobaltComponents.ACTIVATE, false)
                        .component(CobaltComponents.MUTE, false)
                        .component(CobaltComponents.CANAL, 1)
        );
        MAX_RANGE = maxRange;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        if (world.isClient()) return super.use(world, player, hand);
        // TODO: if (!player.getStackInHand(hand).hasNbt()) return super.use(world, player, hand);

        ItemStack stack = player.getStackInHand(hand);

        if (player.isSneaking()) {
            // Open the walkie talkie configuration screen
            CobaltServerNetworking.sendOpenWalkieTalkieScreen(player);
        } else {
            // Ping all other walkie talkies on this channel

            // TODO
        }

        return TypedActionResult.success(stack);
    }

    public static int getCanal(ItemStack stack) {
        return stack.getOrDefault(CobaltComponents.CANAL, 0);
    }

    public static int getRange(ItemStack stack) {
        if (stack.getItem() instanceof WalkieTalkieItem item) {
            return item.getRange();
        }
        return -1;
    }

    private int getRange() {
        return MAX_RANGE;
    }


    public static boolean isActivate(ItemStack stack) {
        return stack.getOrDefault(CobaltComponents.ACTIVATE, false);
    }

    public static boolean isMute(ItemStack stack) {
        return stack.getOrDefault(CobaltComponents.MUTE, false);
    }

    public static void setCanal(ItemStack stack, int canal) {
        stack.set(CobaltComponents.CANAL, canal);
    }

    public static void setActivate(ItemStack stack, boolean activate) {
        stack.set(CobaltComponents.ACTIVATE, activate);
    }

    public static void setMute(ItemStack stack, boolean mute) {
        stack.set(CobaltComponents.MUTE, mute);
    }
}
