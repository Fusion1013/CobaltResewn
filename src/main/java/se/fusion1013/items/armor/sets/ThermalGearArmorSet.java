package se.fusion1013.items.armor.sets;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import se.fusion1013.items.IItemSetMethods;

public class ThermalGearArmorSet implements IItemSetMethods {

    @Override
    public StatusEffectInstance[] withActiveEffects() {
        return new StatusEffectInstance[] { new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 20, 0) };
    }

    @Override
    public String[] appendTooltipStrings() {
        return new String[] {
                "item.cobalt.thermal_gear_set_bonus.tooltip"
        };
    }

}
