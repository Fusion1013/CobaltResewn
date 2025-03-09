package se.fusion1013.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;

public class SculkVinesBlock extends AbstractPlantStemBlock {

    public static final MapCodec<SculkVinesBlock> CODEC = createCodec(SculkVinesBlock::new);
    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0, 9.0, 4.0, 12.0, 16.0, 12.0);

    protected SculkVinesBlock(Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false, 0.1);
    }

    @Override
    protected MapCodec<? extends AbstractPlantStemBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected Block getPlant() {
        return CobaltBlocks.SCULK_VINES_PLANT;
    }

    @Override
    protected int getGrowthLength(Random random) {
        return VineLogic.getGrowthLength(random);
    }

    @Override
    protected boolean chooseStemState(BlockState state) {
        return VineLogic.isValidForWeepingStem(state);
    }
}
