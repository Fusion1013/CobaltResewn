package se.fusion1013.items.armor.sets;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import se.fusion1013.effect.CobaltEffects;
import se.fusion1013.items.IItemSetMethods;

public class CorruptedArmorSet implements IItemSetMethods {

    @Override
    public StatusEffectInstance[] withActiveEffects() {
        return new StatusEffectInstance[] {
                new StatusEffectInstance(StatusEffects.STRENGTH, 20, 1),
                new StatusEffectInstance(CobaltEffects.REDUCED_HEALING, 20, 4)
        };
    }

    @Override
    public String[] appendTooltipStrings() {
        return new String[] {
                "item_set.cobalt.corrupted_armor.tooltip.damage",
                "item_set.cobalt.corrupted_armor.tooltip.healing"
        };
    }
}
