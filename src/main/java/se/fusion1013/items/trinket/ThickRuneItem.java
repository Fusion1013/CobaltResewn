package se.fusion1013.items.trinket;

import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Formatting;
import se.fusion1013.items.CobaltItemConfiguration;
import se.fusion1013.util.item.ItemSetUtil;

public class ThickRuneItem extends CobaltTrinketItem {

    public ThickRuneItem() {
        super(new Item.Settings(),
                new CobaltItemConfiguration()
                        .nameFormatting(Formatting.GRAY)
                        .setBonusTooltip("thick_rune_health"),
                (modifiers, stack, slot, entity, uuid) -> modifiers);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.tick(stack, slot, entity);
        ItemSetUtil.addSetBonusStatusEffect(entity, new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 20, 9, false, false, true));
    }
}
