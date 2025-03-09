package se.fusion1013.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractPlantBlock;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

public class SculkVinesPlantBlock extends AbstractPlantBlock {

    public static final MapCodec<SculkVinesPlantBlock> CODEC = createCodec(SculkVinesPlantBlock::new);
    protected static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public SculkVinesPlantBlock(Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false);
    }

    @Override
    protected MapCodec<? extends AbstractPlantBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return (AbstractPlantStemBlock) CobaltBlocks.SCULK_VINES;
    }
}
