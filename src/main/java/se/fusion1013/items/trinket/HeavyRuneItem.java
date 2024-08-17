package se.fusion1013.items.trinket;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Formatting;
import se.fusion1013.items.CobaltItemConfiguration;

import java.util.UUID;

public class HeavyRuneItem extends CobaltTrinketItem {

    public HeavyRuneItem() {
        super(new Item.Settings(), new CobaltItemConfiguration().nameFormatting(Formatting.GRAY), HeavyRuneItem::getModifiers);
    }

    private static Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(Multimap<EntityAttribute, EntityAttributeModifier> modifiers, ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(uuid, "cobalt.heavy_rune.damage", 0.1f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifiers;
    }
}
