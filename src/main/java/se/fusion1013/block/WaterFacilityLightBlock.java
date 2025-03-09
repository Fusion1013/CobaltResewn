package se.fusion1013.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.Main;
import se.fusion1013.sounds.CobaltSoundEvents;

import java.util.function.ToIntFunction;

import static net.minecraft.block.RedstoneLampBlock.LIT;

public class WaterFacilityLightBlock extends Block {

    public static final MapCodec<WaterFacilityLightBlock> CODEC = createCodec(WaterFacilityLightBlock::new);
    public static IntProperty LIGHT_LEVEL = Properties.LEVEL_15;
    public static final ToIntFunction<BlockState> STATE_TO_LUMINANCE = (state) -> state.get(LIT) ? state.get(LIGHT_LEVEL) : 0;

    protected WaterFacilityLightBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(LIGHT_LEVEL, 15).with(LIT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIGHT_LEVEL);
        builder.add(LIT);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(LIT, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()));
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.isClient) return;

        boolean isLit = state.get(LIT);
        if (isLit == world.isReceivingRedstonePower(pos)) return;

        if (isLit) {
            world.scheduleBlockTick(pos, this, 4);
        } else {
            world.setBlockState(pos, state.cycle(LIT), 2);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(LIT) && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.with(LIT, false), 2);
        }
    }

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isReceivingRedstonePower(pos)) return;
        world.setBlockState(pos, state.with(LIT, true), 2);
        world.scheduleBlockTick(pos, this, 6);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient && player.isCreativeLevelTwoOp() && player.getInventory().getMainHandStack().isEmpty()) {
            world.setBlockState(pos, state.cycle(LIGHT_LEVEL), 2);
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.CONSUME;
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (world.isReceivingRedstonePower(pos)) return;
        if (state.get(LIT)) world.playSound(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, CobaltSoundEvents.ELECTRIC_SPARK, SoundCategory.BLOCKS, 2.0f, 1.0f, false);
    }
}
