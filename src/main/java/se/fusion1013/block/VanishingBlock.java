package se.fusion1013.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.Main;

public class VanishingBlock extends Block {

    public static final BooleanProperty VANISHED = BooleanProperty.of("vanished");
    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");
    private static final VoxelShape VANISHED_SHAPE = createCuboidShape(6, 6, 6, 10, 10, 10);

    public VanishingBlock(Settings settings) {
        super(settings);
        getDefaultState().with(VANISHED, false).with(ACTIVE, true);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(VANISHED);
        builder.add(ACTIVE);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(VANISHED, false).with(ACTIVE, true);
    }

    private boolean isVanished(BlockState state) {
        return state.get(VANISHED);
    }

    private boolean isActive(BlockState state) {
        return state.get(ACTIVE);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return isVanished(state) ? VANISHED_SHAPE : super.getOutlineShape(state, world, pos, context);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return isVanished(state) ? VoxelShapes.empty() : super.getCollisionShape(state, world, pos, context);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!player.getMainHandStack().isEmpty()) return super.onUse(state, world, pos, player, hit);

        if (!isVanished(state)) {
            activate(world, pos);
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isClient) return;

        if (isVanished(state)) {
            world.setBlockState(pos, state.with(VANISHED, false).with(ACTIVE, true), 2);
        } else {
            world.setBlockState(pos, state.with(VANISHED, true), 2);
            world.scheduleBlockTick(pos, this, 80);

            for (Direction d : Direction.values()) {
                this.activate(world, pos.offset(d));
            }
        }
    }

    private void activate(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (!state.contains(VANISHED)) return;
        if (!isActive(state)) return;

        if (!state.get(VANISHED)) {
            world.setBlockState(pos, state.with(ACTIVE, false), 2);
            world.scheduleBlockTick(pos, state.getBlock(), 1 + world.getRandom().nextInt(3));
        }
    }
}
