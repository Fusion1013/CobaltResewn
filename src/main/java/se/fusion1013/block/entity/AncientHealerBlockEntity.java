package se.fusion1013.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;

public class AncientHealerBlockEntity extends BlockEntity {

    public int ticks;

    public AncientHealerBlockEntity(BlockPos pos, BlockState state) {
        super(CobaltBlockEntityTypes.ANCIENT_HEALER_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, AncientHealerBlockEntity blockEntity) {
        if (world.getTime() % 80L != 0) return;
        applyPlayerEffects(world, blockPos, StatusEffects.REGENERATION);
    }

    private static void applyPlayerEffects(World world, BlockPos blockPos, StatusEffect effect) {
        if (world.isClient) return;

        Box box = new Box(blockPos).expand(4);
        List<PlayerEntity> players = world.getNonSpectatingEntities(PlayerEntity.class, box);
        for (PlayerEntity player : players) {
            player.addStatusEffect(new StatusEffectInstance(effect, 16*20, 1, true, true));
        }
    }

    public static void clientTick(World world, BlockPos blockPos, BlockState blockState, AncientHealerBlockEntity blockEntity) {
        tick(world, blockPos, blockState, blockEntity);

        blockEntity.ticks++;
        spawnHealingParticles(world, blockPos, blockEntity.ticks);
    }

    public static void serverTick(World world, BlockPos blockPos, BlockState blockState, AncientHealerBlockEntity blockEntity) {
        tick(world, blockPos, blockState, blockEntity);
    }

    private static void spawnHealingParticles(World world, BlockPos blockPos, int ticks) {
        Vec3d centerPos = new Vec3d(blockPos.toCenterPos().x, blockPos.toCenterPos().y, blockPos.toCenterPos().z);
        Random rnd = world.getRandom();

        float xOffset = (rnd.nextFloat() - 0.5f) * 5;
        float yOffset = (rnd.nextFloat() - 0.5f) * 5;
        float zOffset = (rnd.nextFloat() - 0.5f) * 5;

        world.addParticle(ParticleTypes.ENCHANT,
                centerPos.x, centerPos.y, centerPos.z,
                xOffset + rnd.nextFloat() - 0.5, yOffset + rnd.nextFloat() - 0.5, zOffset + rnd.nextFloat() - 0.5
        );
    }
}
