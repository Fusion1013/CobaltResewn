package se.fusion1013.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class SpotlightBlockEntity extends BlockEntity {

    public SpotlightBlockEntity(BlockPos pos, BlockState state) {
        super(CobaltBlockEntityTypes.SPOTLIGHT_BLOCK_ENTITY, pos, state);
    }
}
