package se.fusion1013.items.trinket;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
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
import se.fusion1013.items.CobaltItems;
import se.fusion1013.items.CobaltRarity;

import java.util.UUID;

public class HealthRuneItem extends CobaltTrinketItem {

    public static final int ATTACK_DURATION = 20 * 8;
    public static final int HEAL_COOLDOWN = 40;
    public static final int HEAL_AMOUNT = 1;

    public static final float FIRE_RUNE_MULTIPLIER = 2;

    private int cooldown;

    public HealthRuneItem() {
        super(new CobaltItem.Settings().rarity(CobaltRarity.Perfect), (modifiers, stack, slot, entity, uuid) -> modifiers);
    }

    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);
        modifiers.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(Identifier.of(Main.MOD_NAMESPACE, "health_rune.health"), 0.1f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        return modifiers;
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity, TrinketComponent trinketComponent) {
        super.tick(stack, slot, entity, trinketComponent);

        if (entity.getWorld().isClient) return;

        cooldown++;

        // Try to heal the entity
        if (entity.age - entity.getLastAttackedTime() > ATTACK_DURATION) {
            if (cooldown < HEAL_COOLDOWN) return;
            cooldown = 0;

            if (entity.getHealth() >= entity.getMaxHealth()) return;

            // Calculate final heal amount
            float healAmount = HEAL_AMOUNT *
                    (trinketComponent.isEquipped(CobaltItems.FIRE_RUNE) ? FIRE_RUNE_MULTIPLIER : 1);

            entity.heal(healAmount);
        }
    }
}
