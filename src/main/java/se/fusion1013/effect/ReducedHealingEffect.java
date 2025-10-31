package se.fusion1013.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ReducedHealingEffect extends StatusEffect {

    protected ReducedHealingEffect() {
        super(StatusEffectCategory.HARMFUL, 0x818da1);
    }
}
