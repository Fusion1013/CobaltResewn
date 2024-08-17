package se.fusion1013.items.armor.sets;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import se.fusion1013.effect.CobaltEffects;
import se.fusion1013.items.IItemSetMethods;

public class DivingArmorSet implements IItemSetMethods {

    @Override
    public StatusEffectInstance[] withActiveEffects() {
        return new StatusEffectInstance[] {
                new StatusEffectInstance(StatusEffects.WATER_BREATHING, 20, 0),
                new StatusEffectInstance(CobaltEffects.COLD_RESISTANCE_EFFECT, 20, 0)
        };
    }

    @Override
    public String[] appendTooltipStrings() {
        return new String[] {
                "item_set.cobalt.diving_armor.tooltip.breathing",
                "item_set.cobalt.diving_armor.tooltip.cold"
        };
    }
}
