package se.fusion1013.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class JarBlock extends Block {

    private static final VoxelShape SHAPE = Block.createCuboidShape(3, 0, 3, 13, 14, 13);
    public static BooleanProperty HANGING = Properties.HANGING;

    public JarBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(HANGING, false));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        for (Direction direction : ctx.getPlacementDirections()) {
            BlockState blockState = getDefaultState().with(HANGING, direction == Direction.UP);
            if (direction.getAxis() != Direction.Axis.Y || !(blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos()))) continue;
            return blockState;
        }
        return null;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HANGING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
