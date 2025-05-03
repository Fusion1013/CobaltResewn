package se.fusion1013.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;

public class DreamingEffect extends StatusEffect {

    public DreamingEffect() {
        super(StatusEffectCategory.NEUTRAL, 0x7534ed);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.addStatusEffect(new StatusEffectInstance(CobaltEffects.DARK_SHADOWS, 20*6, 0, true, false));
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
