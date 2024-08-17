package se.fusion1013.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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

public abstract class AbstractLightContainerBlock extends BlockWithEntity {

    // --- SETUP

    public static final BooleanProperty LIT;
    private static final VoxelShape SHAPE;

    protected AbstractLightContainerBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(LIT, false));
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
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem() == CobaltItems.LIGHT_SOUL) return tryInsertSoul(world, pos, player, hand);
        else return tryTakeSoul(world, pos, player, hand);
    }

    private ActionResult tryInsertSoul(World world, BlockPos pos, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        BlockState state = world.getBlockState(pos);

        if (stack.getItem() != CobaltItems.LIGHT_SOUL) return ActionResult.FAIL;
        if (state.get(LIT).booleanValue()) return ActionResult.FAIL;

        world.setBlockState(pos, state.with(LIT, true));
        player.getStackInHand(hand).setCount(0);

        if (!world.isClient) {
            world.playSound(null, pos, CobaltSoundEvents.LIGHT_HOLDER_SOUL_INSERT, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }

        return ActionResult.SUCCESS;
    }

    private ActionResult tryTakeSoul(World world, BlockPos pos, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        BlockState state = world.getBlockState(pos);

        if (!stack.isEmpty()) return ActionResult.FAIL;
        if (!state.get(LIT).booleanValue()) return ActionResult.FAIL;

        world.setBlockState(pos, state.with(LIT, false));
        player.setStackInHand(hand, CobaltItems.LIGHT_SOUL.getDefaultStack());

        // Update all neighbors
        for (Direction dir : Direction.values()) {
            world.updateNeighborsAlways(pos.offset(dir), this);
        }

        if (!world.isClient) {
            world.playSound(null, pos, CobaltSoundEvents.LIGHT_HOLDER_SOUL_REMOVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }

        return ActionResult.SUCCESS;
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
        LIT = BooleanProperty.of("lit");
        SHAPE = Block.createCuboidShape(2, 0, 2, 14, 14, 14);
    }
}
