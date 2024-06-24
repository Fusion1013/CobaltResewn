package se.fusion1013.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.MathHelper;

/**
 * Simulates the effect of powdered snow on the entity.
 */
public class FreezingEffect extends StatusEffect {
    public FreezingEffect() {
        super(StatusEffectCategory.HARMFUL, 0x818da1);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        var world = entity.getWorld();
        var pos = entity.getPos();

        if (world.isClient) {
            var random = world.getRandom();
            world.addParticle(ParticleTypes.SNOWFLAKE, entity.getX(), (double)(pos.getY() + 1), entity.getZ(), (double)(MathHelper.nextBetween(random, -1.0F, 1.0F) * 0.083333336F), 0.05000000074505806D, (double)(MathHelper.nextBetween(random, -1.0F, 1.0F) * 0.083333336F));
        }
        entity.setInPowderSnow(true);
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
