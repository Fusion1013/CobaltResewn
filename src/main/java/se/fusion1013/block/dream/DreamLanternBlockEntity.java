package se.fusion1013.block.dream;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import se.fusion1013.block.entity.CobaltBlockEntityTypes;

public class DreamLanternBlockEntity extends AbstractDreamLightBlockEntity {

    public DreamLanternBlockEntity(BlockPos pos, BlockState state) {
        super(CobaltBlockEntityTypes.DREAM_LANTERN_BLOCK_ENTITY, pos, state, true);
    }

}
