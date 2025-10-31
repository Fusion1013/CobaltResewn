package se.fusion1013.block.dream;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import se.fusion1013.block.entity.CobaltBlockEntityTypes;

public class DreamCandleBlockEntity extends AbstractDreamLightBlockEntity {

    public DreamCandleBlockEntity(BlockPos pos, BlockState state) {
        super(CobaltBlockEntityTypes.DREAM_CANDLE_BLOCK_ENTITY, pos, state, true);
    }

}
