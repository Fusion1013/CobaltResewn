package se.fusion1013.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class SculkSummonerBlock extends Block {

    public SculkSummonerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {

        if (random.nextInt(2) != 0) return;

        // Display particles and play sound
        world.addParticle(ParticleTypes.SCULK_SOUL, pos.getX()+random.nextFloat(), pos.getY()+1.2, pos.getZ()+random.nextFloat(), 0, 0, 0);
        world.playSound(pos.getX()+.5, pos.getY()+.5, pos.getZ()+.5, SoundEvents.PARTICLE_SOUL_ESCAPE.value(), SoundCategory.BLOCKS, 1, 1, false);

    }
}
