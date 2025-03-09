package se.fusion1013.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.block.entity.SpotlightBlockEntity;

public class SpotlightBlock extends WallMountedBlock implements BlockEntityProvider {

    public static final MapCodec<SpotlightBlock> CODEC = createCodec(SpotlightBlock::new);

    protected static final VoxelShape FLOOR_SHAPE;
    protected static final VoxelShape CEILING_SHAPE;
    protected static final VoxelShape NORTH_SHAPE;
    protected static final VoxelShape SOUTH_SHAPE;
    protected static final VoxelShape EAST_SHAPE;
    protected static final VoxelShape WEST_SHAPE;

    protected SpotlightBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(FACE, BlockFace.WALL));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(FACE);
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
    protected MapCodec<? extends WallMountedBlock> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SpotlightBlockEntity(pos, state);
    }

    static {
        CEILING_SHAPE = Block.createCuboidShape(3.0D, 14.0D, 3.0D, 13.0D, 16.0D, 13.0D);
        FLOOR_SHAPE = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 2.0D, 13.0D);

        NORTH_SHAPE = Block.createCuboidShape(3.0D, 3.0D, 14.0D, 13.0D, 13.0D, 16.0D);
        SOUTH_SHAPE = Block.createCuboidShape(3.0D, 3.0D, 0.0D, 13.0D, 13.0D, 2.0D);

        EAST_SHAPE = Block.createCuboidShape(0.0D, 3.0D, 3.0D, 2.0D, 13.0D, 13.0D);
        WEST_SHAPE = Block.createCuboidShape(14.0D, 3.0D, 3.0D, 16.0D, 13.0D, 13.0D);
    }
}
