package se.fusion1013.items.misc;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.networking.CobaltNetworkingConstants;

import java.util.Objects;

public class WalkieTalkieItem extends CobaltItem {

    private final int MAX_RANGE;

    private static final String NBT_KEY_CANAL = "cobalt.walkietalkie.canal";
    private static final String NBT_KEY_MUTE = "cobalt.walkietalkie.mute";
    private static final String NBT_KEY_ACTIVATE = "cobalt.walkietalkie.activate";

    public WalkieTalkieItem(int maxRange) {
        super((CobaltItem.Settings) new CobaltItem.Settings(Formatting.GOLD).maxCount(1));
        MAX_RANGE = maxRange;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        if (world.isClient()) return super.use(world, player, hand);
        if (!player.getStackInHand(hand).hasNbt()) return super.use(world, player, hand);

        ItemStack stack = player.getStackInHand(hand);

        if (player.isSneaking()) {
            // Open the walkie talkie configuration screen
            ServerPlayNetworking.send((ServerPlayerEntity) player, CobaltNetworkingConstants.OPEN_WALKIE_TALKIE_SCREEN_S2C, PacketByteBufs.empty());

        } else {
            // Ping all other walkie talkies on this channel

            // TODO
        }

        return TypedActionResult.success(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient()) return;

        if (stack.hasNbt()) return;

        // Set custom nbt for item
        NbtCompound nbtCompound = new NbtCompound();
        nbtCompound.putBoolean(NBT_KEY_ACTIVATE, false);
        nbtCompound.putBoolean(NBT_KEY_MUTE, false);
        nbtCompound.putInt(NBT_KEY_CANAL, 1);
        stack.setNbt(nbtCompound);
    }

    public static int getCanal(ItemStack stack) {
        return Objects.requireNonNull(stack.getNbt()).getInt(NBT_KEY_CANAL);
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
        return Objects.requireNonNull(stack.getNbt()).getBoolean(NBT_KEY_ACTIVATE);
    }

    public static boolean isMute(ItemStack stack) {
        return Objects.requireNonNull(stack.getNbt()).getBoolean(NBT_KEY_MUTE);
    }

    public static void setCanal(ItemStack stack, int canal) {
        stack.getNbt().putInt(NBT_KEY_CANAL, canal);
    }

    public static void setActivate(ItemStack stack, boolean activate) {
        stack.getNbt().putBoolean(NBT_KEY_ACTIVATE, activate);
    }

    public static void setMute(ItemStack stack, boolean mute) {
        stack.getNbt().putBoolean(NBT_KEY_MUTE, mute);
    }
}
