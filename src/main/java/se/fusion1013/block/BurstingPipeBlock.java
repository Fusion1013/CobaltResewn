package se.fusion1013.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import se.fusion1013.Main;

public class BurstingPipeBlock extends Block {

    private static final BooleanProperty BURSTING = BooleanProperty.of("bursting");

    private static final float PARTICLE_VELOCITY_LOW = 0.05f;
    private static final float PARTICLE_VELOCITY_HIGH = 0.2f;
    private static final float ENTITY_VELOCITY = 1.5f;

    public BurstingPipeBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(BURSTING, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BURSTING);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        activate(world, pos);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.get(BURSTING)) return;
        world.setBlockState(pos, state.with(BURSTING, false), 2);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
        if (!isBursting(state)) return;
        entity.addVelocity(0, ENTITY_VELOCITY, 0);
    }

    private boolean isBursting(BlockState state) {
        return state.get(BURSTING);
    }

    private void activate(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (!state.contains(BURSTING)) return;
        if (isBursting(state)) return;

        world.setBlockState(pos, state.with(BURSTING, true), 2);
        world.scheduleBlockTick(pos, state.getBlock(), 200 + world.getRandom().nextInt(200));
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(6) == 0) {
            world.playSound(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, .3f, 1.2f, false);
        }

        world.addParticle(ParticleTypes.CLOUD, pos.getX() + .25 + 0.5 * random.nextFloat(), pos.getY() + 1, pos.getZ() + .25 + 0.5 * random.nextFloat(), 0, PARTICLE_VELOCITY_LOW, 0);

        if (isBursting(state)) {
            for (int i = 0; i < 10; i++) {
                world.addParticle(ParticleTypes.CLOUD, pos.getX() + .25 + 0.5 * random.nextFloat(), pos.getY() + 1, pos.getZ() + .25 + 0.5 * random.nextFloat(), 0, PARTICLE_VELOCITY_HIGH, 0);
                world.playSound(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, .3f, 1.2f, false);
            }
        }
    }
}
