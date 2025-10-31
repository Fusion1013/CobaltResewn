package se.fusion1013.block.dream;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.Util;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.sounds.CobaltSoundEvents;

import java.util.List;
import java.util.function.ToIntFunction;

public class DreamCandleBlock extends AbstractDreamLightBlock {

    public static final MapCodec<DreamCandleBlock> CODEC = createCodec(DreamCandleBlock::new);
    public static final IntProperty CANDLES = Properties.CANDLES;
    public static final ToIntFunction<BlockState> STATE_TO_LUMINANCE = state -> 3 * state.get(CANDLES);

    private static final VoxelShape ONE_CANDLE_SHAPE = Block.createCuboidShape(7.0, 0.0, 7.0, 9.0, 6.0, 9.0);
    private static final VoxelShape TWO_CANDLES_SHAPE = Block.createCuboidShape(5.0, 0.0, 6.0, 11.0, 6.0, 9.0);
    private static final VoxelShape THREE_CANDLES_SHAPE = Block.createCuboidShape(5.0, 0.0, 6.0, 10.0, 6.0, 11.0);
    private static final VoxelShape FOUR_CANDLES_SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 6.0, 10.0);

    private static final Int2ObjectMap<List<Vec3d>> CANDLES_TO_PARTICLE_OFFSETS = Util.make(
            () -> {
                Int2ObjectMap<List<Vec3d>> int2ObjectMap = new Int2ObjectOpenHashMap<>();
                int2ObjectMap.defaultReturnValue(ImmutableList.of());
                int2ObjectMap.put(1, ImmutableList.of(new Vec3d(0.5, 0.5, 0.5)));
                int2ObjectMap.put(2, ImmutableList.of(new Vec3d(0.375, 0.44, 0.5), new Vec3d(0.625, 0.5, 0.44)));
                int2ObjectMap.put(3, ImmutableList.of(new Vec3d(0.5, 0.313, 0.625), new Vec3d(0.375, 0.44, 0.5), new Vec3d(0.56, 0.5, 0.44)));
                int2ObjectMap.put(
                        4, ImmutableList.of(new Vec3d(0.44, 0.313, 0.56), new Vec3d(0.625, 0.44, 0.56), new Vec3d(0.375, 0.44, 0.375), new Vec3d(0.56, 0.5, 0.375))
                );
                return Int2ObjectMaps.unmodifiable(int2ObjectMap);
            }
    );

    public DreamCandleBlock(Settings settings) {
        super(settings, true);
        setDefaultState(getDefaultState().with(LIT, false).with(CANDLES, 1));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(CANDLES);
    }

    @Override
    protected int getLuminance(BlockState state) {
        return STATE_TO_LUMINANCE.applyAsInt(state);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            this.getParticleOffsets(state).forEach(offset -> spawnCandleParticles(world, offset.add(pos.getX(), pos.getY(), pos.getZ()), random));
        }
    }

    protected Iterable<Vec3d> getParticleOffsets(BlockState state) {
        return CANDLES_TO_PARTICLE_OFFSETS.get(state.get(CANDLES).intValue());
    }

    private static void spawnCandleParticles(World world, Vec3d vec3d, Random random) {
        float f = random.nextFloat();
        if (f < 0.3F) {
            world.addParticle(ParticleTypes.SMOKE, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0);
            if (f < 0.17F) {
                world.playSound(
                        vec3d.x + 0.5,
                        vec3d.y + 0.5,
                        vec3d.z + 0.5,
                        SoundEvents.BLOCK_CANDLE_AMBIENT,
                        SoundCategory.BLOCKS,
                        1.0F + random.nextFloat(),
                        random.nextFloat() * 0.7F + 0.3F,
                        false
                );
            }
        }

        world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.isEmpty()) {
            world.setBlockState(pos, state.with(LIT, false), Block.NOTIFY_ALL_AND_REDRAW);
            world.playSound(null, pos, CobaltSoundEvents.DREAM_CANDLE_EXTINGUISH, SoundCategory.BLOCKS, .9f, 0.9f + world.random.nextFloat() * 0.2f);
            return ItemActionResult.SUCCESS;
        } else if (stack.getItem() == Items.FLINT_AND_STEEL) {
            lightBlock(world, pos, state);
            return ItemActionResult.SUCCESS;
        }

        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (blockState.isOf(this)) {
            return blockState.cycle(CANDLES);
        } else {
            return super.getPlacementState(ctx);
        }
    }

    @Override
    protected boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.shouldCancelInteraction() && context.getStack().getItem() == this.asItem() && state.get(CANDLES) < 4 || super.canReplace(state, context);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(CANDLES)) {
            default -> ONE_CANDLE_SHAPE;
            case 2 -> TWO_CANDLES_SHAPE;
            case 3 -> THREE_CANDLES_SHAPE;
            case 4 -> FOUR_CANDLES_SHAPE;
        };
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DreamCandleBlockEntity(pos, state);
    }
}
