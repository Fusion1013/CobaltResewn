package se.fusion1013.items.trinket;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.CobaltItemConfiguration;
import se.fusion1013.items.CobaltRarity;

import java.util.UUID;

public class FastRuneItem extends CobaltTrinketItem {

    public FastRuneItem() {
        super(new CobaltItem.Settings().rarity(CobaltRarity.Perfect), FastRuneItem::getModifiers);
    }

    private static Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifiers, ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(Main.MOD_NAMESPACE, "fast_rune.speed"), 0.1f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        return modifiers;
    }
}
