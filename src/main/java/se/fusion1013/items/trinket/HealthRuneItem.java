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
import net.minecraft.util.Formatting;
import se.fusion1013.items.CobaltItemConfiguration;
import se.fusion1013.items.CobaltItems;

import java.util.UUID;

public class HealthRuneItem extends CobaltTrinketItem {

    public static final int ATTACK_DURATION = 20 * 8;
    public static final int HEAL_COOLDOWN = 40;
    public static final int HEAL_AMOUNT = 1;

    public static final float FIRE_RUNE_MULTIPLIER = 2;

    private int cooldown;

    public HealthRuneItem() {
        super(new Item.Settings(),
                new CobaltItemConfiguration()
                        .nameFormatting(Formatting.RED),
                (modifiers, stack, slot, entity, uuid) -> modifiers);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        modifiers.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(uuid, "cobalt.health_rune.health", 0.1f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
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
                    (trinketComponent.isEquipped(CobaltItems.TrinketItems.FIRE_RUNE) ? FIRE_RUNE_MULTIPLIER : 1);

            entity.heal(healAmount);
        }
    }
}
