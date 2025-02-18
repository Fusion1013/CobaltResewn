package se.fusion1013.items.armor;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import se.fusion1013.Main;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.CobaltRarity;
import se.fusion1013.items.materials.CobaltArmorMaterials;

import java.util.List;

public class DivingArmorItem extends CobaltArmorItem {

    public DivingArmorItem(Type type) {
        super(CobaltArmorMaterials.DIVE, type, (CobaltItem.Settings) new CobaltItem.Settings().rarity(CobaltRarity.Great), getAttributes(type));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
    }



    public static AttributeModifiersComponent getAttributes(Type type) {
        switch (type) {
            case HELMET -> {
                return AttributeModifiersComponent.builder()
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(Main.MOD_NAMESPACE, "helmet.speed"), -0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.HEAD).build();
            }
            case CHESTPLATE -> {
                return AttributeModifiersComponent.builder()
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(Main.MOD_NAMESPACE, "chestplate.speed"), -0.15, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.CHEST).build();
            }
            case LEGGINGS -> {
                return AttributeModifiersComponent.builder()
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(Main.MOD_NAMESPACE, "leggings.speed"), -0.15, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.LEGS).build();
            }
            case BOOTS -> {
                return AttributeModifiersComponent.builder()
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(Main.MOD_NAMESPACE, "boots.speed"), -0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.FEET).build();
            }
            default -> {
                return AttributeModifiersComponent.builder().build();
            }
        }
    }

}
