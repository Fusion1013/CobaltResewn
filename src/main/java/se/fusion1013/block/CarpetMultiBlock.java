package se.fusion1013.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarpetBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

public class CarpetMultiBlock extends CarpetBlock {

    public static final MapCodec<CarpetMultiBlock> CODEC = createCodec(CarpetMultiBlock::new);

    public static final BooleanProperty NORTH = BooleanProperty.of("north");
    public static final BooleanProperty SOUTH = BooleanProperty.of("south");
    public static final BooleanProperty EAST = BooleanProperty.of("east");
    public static final BooleanProperty WEST = BooleanProperty.of("west");

    public CarpetMultiBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(NORTH, false).with(SOUTH, false).with(EAST, false).with(WEST, false));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP) return state;
        if (direction == Direction.DOWN) return state;

        if (neighborState.getBlock() instanceof CarpetMultiBlock) {
            if (direction == Direction.NORTH) state = state.with(NORTH, true);
            else if (direction == Direction.SOUTH) state = state.with(SOUTH, true);
            else if (direction == Direction.EAST) state = state.with(EAST, true);
            else if (direction == Direction.WEST) state = state.with(WEST, true);
        } else {
            if (direction == Direction.NORTH) state = state.with(NORTH, false);
            else if (direction == Direction.SOUTH) state = state.with(SOUTH, false);
            else if (direction == Direction.EAST) state = state.with(EAST, false);
            else if (direction == Direction.WEST) state = state.with(WEST, false);
        }
        return state;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST);
    }

    @Override
    public MapCodec<? extends CarpetBlock> getCodec() {
        return CODEC;
    }
}
