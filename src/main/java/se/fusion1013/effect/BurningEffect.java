package se.fusion1013.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;

public class BurningEffect extends StatusEffect {

    public BurningEffect() {
        super(StatusEffectCategory.HARMFUL, 16750848);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {

        entity.damage(entity.getDamageSources().onFire(), 1f);

        if (!entity.isFireImmune() && !entity.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
            if (entity.getFireTicks() == 0) {
                entity.setOnFireFor(8.0f);
            }
        }
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
