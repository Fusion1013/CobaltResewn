package se.fusion1013.items.trinket;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.CobaltItems;
import se.fusion1013.items.CobaltRarity;
import se.fusion1013.util.item.ItemSetUtil;

public class FireRuneItem extends CobaltTrinketItem {

    private int healCooldown;

    public FireRuneItem() {
        super(new CobaltItem.Settings().rarity(CobaltRarity.Perfect), (modifiers, stack, slot, entity, uuid) -> modifiers);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity, TrinketComponent trinketComponent) {
        super.tick(stack, slot, entity, trinketComponent);

        // Tick depending on which base rune is equipped
        if (trinketComponent.isEquipped(CobaltItems.HEALTH_RUNE)) healthRuneTick(stack, slot, entity);
        if (trinketComponent.isEquipped(CobaltItems.HEAVY_RUNE)) heavyRuneTick(stack, slot, entity);
        if (trinketComponent.isEquipped(CobaltItems.FAST_RUNE)) fastRuneTick(stack, slot, entity);
    }

    private void healthRuneTick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        // Does nothing, applied from health rune
    }

    private void heavyRuneTick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        ItemSetUtil.addSetBonusStatusEffect(entity, new StatusEffectInstance(StatusEffects.RESISTANCE, 20, 0));
    }

    private void fastRuneTick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        // TODO
    }
}
