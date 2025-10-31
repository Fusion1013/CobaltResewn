package se.fusion1013.block.dream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import se.fusion1013.items.CobaltItems;
import se.fusion1013.sounds.CobaltSoundEvents;

public abstract class AbstractLightContainerBlock extends AbstractDreamLightBlock {

    // --- SETUP

    private static final VoxelShape SHAPE;

    protected AbstractLightContainerBlock(Settings settings) {
        super(settings, false);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    // ---

    // --- DISPLAY

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);

        if (state.get(LIT)) {
            Vec3d center = pos.toCenterPos();
            world.addParticle(ParticleTypes.SOUL_FIRE_FLAME,
                    getParticleOffset(random) + center.x,
                    getParticleOffset(random) + pos.getY() + getMiddleOffset(),
                    getParticleOffset(random) + center.z,
                    0, 0, 0
            );
        }
    }

    // ---

    // --- USE (Light Soul Insert)


    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.getItem() == CobaltItems.LIGHT_SOUL) {
            return tryInsertSoul(world, pos, player, hand);
        }
        else return tryTakeSoul(world, pos, player, hand, this);
    }

    private ItemActionResult tryInsertSoul(World world, BlockPos pos, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        BlockState state = world.getBlockState(pos);

        if (stack.getItem() != CobaltItems.LIGHT_SOUL) return ItemActionResult.FAIL;
        if (state.get(LIT).booleanValue()) return ItemActionResult.FAIL;

        lightBlock(world, pos, state);
        player.getStackInHand(hand).setCount(0);

        if (!world.isClient) {
            world.playSound(null, pos, CobaltSoundEvents.LIGHT_HOLDER_SOUL_INSERT, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }

        return ItemActionResult.SUCCESS;
    }

    public static ItemActionResult tryTakeSoul(World world, BlockPos pos, PlayerEntity player, Hand hand, Block sourceBlock) {
        ItemStack stack = player.getStackInHand(hand);
        BlockState state = world.getBlockState(pos);

        if (!stack.isEmpty()) return ItemActionResult.FAIL;
        if (!state.get(LIT).booleanValue()) return ItemActionResult.FAIL;

        world.setBlockState(pos, state.with(LIT, false));
        player.setStackInHand(hand, CobaltItems.LIGHT_SOUL.getDefaultStack());

        // Update all neighbors
        for (Direction dir : Direction.values()) {
            world.updateNeighborsAlways(pos.offset(dir), sourceBlock);
        }

        if (!world.isClient) {
            world.playSound(null, pos, CobaltSoundEvents.LIGHT_HOLDER_SOUL_REMOVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }

        return ItemActionResult.SUCCESS;
    }

    // ---

    // --- REDSTONE

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(LIT) ? 15 : 0;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(LIT) ? 15 : 0;
    }

    // ---

    // --- UTILITY

    protected double getMiddleOffset() { return 6/16f; }

    private double getParticleOffset(Random random) {
        return (random.nextDouble() - 0.5) * 0.5;
    }

    // ---

    static {
        SHAPE = Block.createCuboidShape(2, 0, 2, 14, 14, 14);
    }
}
