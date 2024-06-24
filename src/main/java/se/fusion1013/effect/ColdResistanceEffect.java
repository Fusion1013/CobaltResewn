package se.fusion1013.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

/**
 * Makes the {@link LivingEntity} immune to various cold related effects.
 */
public class ColdResistanceEffect extends StatusEffect {

    public ColdResistanceEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x98D982);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        // Remove cold-related effects on the entity
        entity.removeStatusEffect(CobaltEffects.FREEZING_EFFECT);
        entity.setInPowderSnow(false);
        entity.setFrozenTicks(0);
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
