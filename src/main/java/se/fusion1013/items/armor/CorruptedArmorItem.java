package se.fusion1013.items.armor;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.CobaltRarity;
import se.fusion1013.items.materials.CobaltArmorMaterials;

public class CorruptedArmorItem extends CobaltArmorItem {

    public CorruptedArmorItem(Type type) {
        super(CobaltArmorMaterials.CORRUPTED, type, new CobaltItem.Settings().rarity(CobaltRarity.Outstanding), getAttributes(type));
    }

    public static AttributeModifiersComponent getAttributes(Type type) {
        switch (type) {
            case HELMET -> {
                return AttributeModifiersComponent.builder()
                        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of(Main.MOD_NAMESPACE, "helmet.damage"), 1, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD)
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(Identifier.of(Main.MOD_NAMESPACE, "helmet.max_health"), -0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.HEAD)
                        .build();
            }
            case CHESTPLATE -> {
                return AttributeModifiersComponent.builder()
                        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of(Main.MOD_NAMESPACE, "chestplate.damage"), 1, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.CHEST)
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(Identifier.of(Main.MOD_NAMESPACE, "chestplate.max_health"), -0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.CHEST)
                        .build();
            }
            case LEGGINGS -> {
                return AttributeModifiersComponent.builder()
                        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of(Main.MOD_NAMESPACE, "leggings.damage"), 1, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.LEGS)
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(Identifier.of(Main.MOD_NAMESPACE, "leggings.max_health"), -0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.LEGS)
                        .build();
            }
            case BOOTS -> {
                return AttributeModifiersComponent.builder()
                        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of(Main.MOD_NAMESPACE, "boots.damage"), 1, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.FEET)
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(Identifier.of(Main.MOD_NAMESPACE, "boots.max_health"), -0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.FEET)
                        .build();
            }
            case BODY -> {
                return AttributeModifiersComponent.builder().build();
            }
            default -> {
                return AttributeModifiersComponent.builder().build();
            }
        }
    }

}
