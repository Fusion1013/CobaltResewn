package se.fusion1013.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.block.entity.CobaltBlockEntityTypes;
import se.fusion1013.block.entity.SculkSpreaderBlockEntity;

public class SculkSpreaderBlock extends BlockWithEntity {

    private static final MapCodec<SculkSpreaderBlock> CODEC = createCodec(SculkSpreaderBlock::new);

    public SculkSpreaderBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SculkSpreaderBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, CobaltBlockEntityTypes.SCULK_SPREADER, SculkSpreaderBlockEntity::tick);
    }
}
