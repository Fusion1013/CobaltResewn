package se.fusion1013.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.block.entity.AncientHealerBlockEntity;
import se.fusion1013.block.entity.CobaltBlockEntityTypes;
import se.fusion1013.items.CobaltItems;

import static se.fusion1013.block.AbstractLightContainerBlock.tryInsertSoul;
import static se.fusion1013.block.AbstractLightContainerBlock.tryTakeSoul;

public class AncientHealerBlock extends BlockWithEntity {

    public static final MapCodec<AncientHealerBlock> CODEC = createCodec(AncientHealerBlock::new);
    private static final VoxelShape SHAPE;
    public static final BooleanProperty LIT;

    protected AncientHealerBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(LIT, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem() == CobaltItems.LIGHT_SOUL) return tryInsertSoul(world, pos, player, hand);
        else return tryTakeSoul(world, pos, player, hand, this);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AncientHealerBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return AncientHealerBlock.validateTicker(type, CobaltBlockEntityTypes.ANCIENT_HEALER_BLOCK_ENTITY, world.isClient ? AncientHealerBlockEntity::clientTick : AncientHealerBlockEntity::serverTick);
    }

    static {
        LIT = BooleanProperty.of("lit");
        SHAPE = Block.createCuboidShape(2, 0, 2, 14, 4, 14);
    }
}
