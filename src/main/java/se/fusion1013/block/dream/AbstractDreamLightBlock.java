package se.fusion1013.block.dream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import se.fusion1013.sounds.CobaltSoundEvents;

import java.util.List;

public abstract class AbstractDreamLightBlock extends BlockWithEntity {

    public static final BooleanProperty LIGHTING_UP = BooleanProperty.of("lighting_up");
    public static final BooleanProperty LIT = Properties.LIT;

    private final boolean lightFromProximity;

    // region SETUP

    public AbstractDreamLightBlock(Settings settings, boolean lightFromProximity) {
        super(settings);
        setDefaultState(getDefaultState().with(LIT, false).with(LIGHTING_UP, false));
        this.lightFromProximity = lightFromProximity;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(LIT);
        builder.add(LIGHTING_UP);
    }

    // endregion

    protected int getLuminance(BlockState state) {
        return 0;
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.scheduledTick(state, world, pos, random);
        if (state.get(LIGHTING_UP) && lightFromProximity) lightBlock(world, pos, state);
    }

    public void lightBlock(World world, BlockPos pos, BlockState state) {
        if (state.get(LIT)) {
            world.setBlockState(pos, state.with(LIGHTING_UP, false), NOTIFY_ALL_AND_REDRAW);
            return; // Do not light the block if it is already lit
        }

        world.setBlockState(pos, state.with(LIT, true).with(LIGHTING_UP, false), NOTIFY_ALL_AND_REDRAW);
        world.playSound(null, pos, CobaltSoundEvents.DREAM_CANDLE_LIGHT, SoundCategory.BLOCKS, 0.35f, 0.9f + world.random.nextFloat() * 0.2f);

        // Schedule update for nearby dream lights
        int targetRadius = getLuminance(state);
        List<BlockEntity> list = AbstractDreamLightBlockEntity.getDreamLightBlockEntitiesInRange(new Vec3d(pos.getX(), pos.getY(), pos.getZ()), targetRadius);
        for (BlockEntity dreamLightBlockEntity : list) {
            int distance = (int) dreamLightBlockEntity.getPos().toCenterPos().distanceTo(pos.toCenterPos());
            BlockState otherState = world.getBlockState(dreamLightBlockEntity.getPos());
            if (otherState.get(LIGHTING_UP)) continue;

            world.setBlockState(dreamLightBlockEntity.getPos(), otherState.with(LIGHTING_UP, true), NOTIFY_ALL_AND_REDRAW);
            world.scheduleBlockTick(dreamLightBlockEntity.getPos(), world.getBlockState(dreamLightBlockEntity.getPos()).getBlock(), distance * 3);
        }

        DreamLightLitCallback.EVENT.invoker().dreamLightLit((AbstractDreamLightBlockEntity) world.getBlockEntity(pos));
    }
}
