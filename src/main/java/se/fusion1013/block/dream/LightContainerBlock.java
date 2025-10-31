package se.fusion1013.block.dream;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.block.CobaltBlocks;
import se.fusion1013.block.entity.CobaltBlockEntityTypes;

import java.util.List;

public class LightContainerBlock extends AbstractLightContainerBlock {

    public static final MapCodec<LightContainerBlock> CODEC = createCodec(LightContainerBlock::new);

    public LightContainerBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, CobaltBlockEntityTypes.LIGHT_HOLDER_BLOCK_ENTITY, world.isClient ? LightHolderBlockEntity::clientTick : LightHolderBlockEntity::serverTick);
    }

    @Override
    protected int getLuminance(BlockState state) {
        return 8;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LightHolderBlockEntity(pos, state);
    }
}
