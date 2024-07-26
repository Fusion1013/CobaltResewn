package se.fusion1013.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class DirectionalLightHolderBlockEntity extends LightHolderBlockEntity {

    public DirectionalLightHolderBlockEntity(BlockPos pos, BlockState state) {
        super(CobaltBlockEntityTypes.DIRECTIONAL_LIGHT_HOLDER_BLOCK_ENTITY, pos, state);
    }
}
