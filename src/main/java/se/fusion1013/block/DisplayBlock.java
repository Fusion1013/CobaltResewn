package se.fusion1013.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.block.entity.DisplayBlockEntity;

public class DisplayBlock extends BlockWithEntity {

    public static final MapCodec<DisplayBlock> CODEC = createCodec(DisplayBlock::new);

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty TOP = BooleanProperty.of("top");
    public static final BooleanProperty BOTTOM = BooleanProperty.of("bottom");
    public static final BooleanProperty LEFT = BooleanProperty.of("left");
    public static final BooleanProperty RIGHT = BooleanProperty.of("right");

    public DisplayBlock(Settings settings) {
        super(settings);
        BlockState state = stateManager.getDefaultState();
        setDefaultState(state.with(FACING, Direction.NORTH).with(TOP, false).with(BOTTOM, false).with(LEFT, false).with(RIGHT, false));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        Direction facing = state.get(FACING);
        Direction opposite = facing.getOpposite();

        Direction left = facing.rotateClockwise(Direction.Axis.Y);
        Direction right = facing.rotateCounterclockwise(Direction.Axis.Y);

        if (direction != opposite && neighborState.getBlock() == CobaltBlocks.DISPLAY_BLOCK) {
            if (direction == right) {
                state = state.with(RIGHT, true);
            } else if (direction == left) {
                state = state.with(LEFT, true);
            } else if (direction == Direction.UP) {
                state = state.with(TOP, true);
            } else if (direction == Direction.DOWN) {
                state = state.with(BOTTOM, true);
            }
        } else {
            if (direction == right) {
                state = state.with(RIGHT, false);
            } else if (direction == left) {
                state = state.with(LEFT, false);
            } else if (direction == Direction.UP) {
                state = state.with(TOP, false);
            } else if (direction == Direction.DOWN) {
                state = state.with(BOTTOM, false);
            }
        }

        return state;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        Direction facing = state.get(FACING);
        switch (facing) {
            case NORTH:
                return VoxelShapes.cuboid(0f, 0f, 0.5f, 1f, 1f, 1f);
            case SOUTH:
                return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1f, 0.5f);
            case EAST:
                return VoxelShapes.cuboid(0f, 0f, 0f, 0.5f, 1f, 1f);
            case WEST:
                return VoxelShapes.cuboid(0.5f, 0f, 0f, 1f, 1f, 1f);
            default:
                return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 0.5f, 1f);
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DisplayBlockEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, TOP, BOTTOM, LEFT, RIGHT);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }
}
