package se.fusion1013.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import se.fusion1013.block.CobaltBlocks;
import se.fusion1013.block.RuneBlock;
import se.fusion1013.items.CobaltItems;
import se.fusion1013.tags.CobaltTags;

public class RuneBlockEntity extends BlockEntity {

    private static int tick;
    private static int lastTick;

    public RuneBlockEntity(BlockPos pos, BlockState state) {
        super(CobaltBlockEntityTypes.RUNE_BLOCK, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, RuneBlockEntity blockEntity) {
        if (world.isClient) return;

        boolean playerInRange = false;

        // Find all nearby players
        var players = world.getPlayers();
        for (PlayerEntity player : players) {

            // Get the held items of the player
            var mainHandItem = player.getMainHandStack();
            var offhandItem = player.getOffHandStack();

            // Check if player is holding an item that reveals runes (check if the item has the reveal runes tag)
            if (!mainHandItem.isIn(CobaltTags.REVEALS_RUNES) && !offhandItem.isIn(CobaltTags.REVEALS_RUNES)) continue;

            // Player is holding lantern, check distance
            if (player.getPos().distanceTo(pos.toCenterPos()) < 16) playerInRange = true;
        }

        // Set the visible state of the block
        world.setBlockState(pos, state.with(RuneBlock.VISIBLE, playerInRange));
    }

    public static void clientTick(World world, BlockPos pos, BlockState blockState, RuneBlockEntity pedestalBlockEntity) {
        lastTick = tick;
        tick++;
    }

    public static void serverTick(World world, BlockPos pos, BlockState blockState, RuneBlockEntity pedestalBlockEntity) {
        tick(world, pos, blockState, pedestalBlockEntity);
    }

    public static int getTick() {
        return tick;
    }

    public static int getLastTick() {
        return lastTick;
    }
}
