package se.fusion1013.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CandlestickBlock extends Block {

    private static final VoxelShape SHAPE = Block.createCuboidShape(6, 0, 6, 10, 13, 10);

    public CandlestickBlock(Settings settings) {
        super(settings);
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
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        world.addParticle(ParticleTypes.FLAME, pos.getX() + 0.5f, pos.getY() + 13f/16f, pos.getZ() + 0.5f, 0, 0, 0);
        world.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5f, pos.getY() + 13f/16f, pos.getZ() + 0.5f, 0, 0, 0);
    }
}
