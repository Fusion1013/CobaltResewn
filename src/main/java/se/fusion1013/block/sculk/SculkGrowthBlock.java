package se.fusion1013.block.sculk;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class SculkGrowthBlock extends Block {

    public static final DirectionProperty FACING;
    public static final EnumProperty<BlockFace> FACE;

    protected static final VoxelShape FLOOR_SHAPE;
    protected static final VoxelShape CEILING_SHAPE;
    protected static final VoxelShape NORTH_SHAPE;
    protected static final VoxelShape SOUTH_SHAPE;
    protected static final VoxelShape EAST_SHAPE;
    protected static final VoxelShape WEST_SHAPE;

    public SculkGrowthBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(FACE, BlockFace.FLOOR));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return true;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        switch (state.get(FACE)) {
            case FLOOR: return FLOOR_SHAPE;
            case CEILING: return CEILING_SHAPE;
            case WALL:
                switch (direction) {
                    case NORTH: return NORTH_SHAPE;
                    case SOUTH: return SOUTH_SHAPE;
                    case EAST: return EAST_SHAPE;
                    case WEST: return WEST_SHAPE;
                }
                break;
        }

        return NORTH_SHAPE; // Should never happen?
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
        builder.add(FACE);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction[] var2 = ctx.getPlacementDirections();

        for (Direction direction : var2) {
            BlockState blockState;
            if (direction.getAxis() == Direction.Axis.Y) {
                blockState = this.getDefaultState().with(FACE, direction == Direction.UP ? BlockFace.CEILING : BlockFace.FLOOR).with(FACING, ctx.getHorizontalPlayerFacing());
            } else {
                blockState = this.getDefaultState().with(FACE, BlockFace.WALL).with(FACING, direction.getOpposite());
            }

            if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                return blockState;
            }
        }

        return null;
    }

    static {
        FACING = Properties.FACING;
        FACE = Properties.BLOCK_FACE;
    }

    static {
        CEILING_SHAPE = Block.createCuboidShape(3.0D, 11.0D, 3.0D, 13.0D, 16.0D, 13.0D);
        FLOOR_SHAPE = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 5.0D, 13.0D);

        NORTH_SHAPE = Block.createCuboidShape(3.0D, 3.0D, 11.0D, 13.0D, 13.0D, 16.0D);
        SOUTH_SHAPE = Block.createCuboidShape(3.0D, 3.0D, 0.0D, 13.0D, 13.0D, 5.0D);

        EAST_SHAPE = Block.createCuboidShape(0.0D, 3.0D, 3.0D, 5.0D, 13.0D, 13.0D);
        WEST_SHAPE = Block.createCuboidShape(11.0D, 3.0D, 3.0D, 16.0D, 13.0D, 13.0D);
    }
}
