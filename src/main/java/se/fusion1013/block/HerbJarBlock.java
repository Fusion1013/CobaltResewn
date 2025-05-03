package se.fusion1013.block;

import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class HerbJarBlock extends Block {

    private static final VoxelShape SHAPE = Block.createCuboidShape(4, 0, 4, 12, 15, 12);
    private static final Map<Block, Block> CONTENT_TO_POTTED = Maps.newHashMap();
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private final Block content;

    public HerbJarBlock(Block content) {
        super(AbstractBlock.Settings.copy(Blocks.FLOWER_POT));
        this.content = content;
        CONTENT_TO_POTTED.put(content, this);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getStackInHand(hand);
        Item item = heldItem.getItem();
        BlockState newState = (item instanceof BlockItem ? CONTENT_TO_POTTED.getOrDefault(((BlockItem)item).getBlock(), Blocks.AIR) : Blocks.AIR).getDefaultState();
        boolean isStateAir = newState.isOf(Blocks.AIR);

        if (isStateAir != isEmpty()) {
            // Insert item into the jar
            world.setBlockState(pos, newState.with(FACING, state.get(FACING)), Block.NOTIFY_ALL);
            player.incrementStat(Stats.POT_FLOWER);
            if (!player.getAbilities().creativeMode) heldItem.decrement(1);
        } else {
            // Extract item from the jar
            ItemStack contentStack = new ItemStack(this.content);
            if (heldItem.isEmpty()) player.setStackInHand(hand, contentStack);
            else if (!player.giveItemStack(contentStack)) player.dropItem(contentStack, false);
            world.setBlockState(pos, CobaltBlocks.HERB_JAR.getDefaultState().with(FACING, state.get(FACING)), Block.NOTIFY_ALL);
        }
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        return ActionResult.success(world.isClient);
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        if (this.isEmpty()) return super.getPickStack(world, pos, state);
        return new ItemStack(this.content);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.DOWN && !state.canPlaceAt(world, pos)) return Blocks.AIR.getDefaultState();
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public Block getContent() {
        return content;
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    private boolean isEmpty() {
        return this.content == Blocks.AIR;
    }
}
