package se.fusion1013.items.armor;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.ICobaltArmorItem;
import se.fusion1013.util.item.ItemUtil;

import java.util.List;

public class CobaltArmorItem extends ArmorItem implements ICobaltArmorItem {

    private final CobaltItem.Settings settings;

    public CobaltArmorItem(RegistryEntry<ArmorMaterial> material, ArmorItem.Type type, CobaltItem.Settings settings) {
        this(material, type, settings, AttributeModifiersComponent.builder().build());
    }

    public CobaltArmorItem(RegistryEntry<ArmorMaterial> material, ArmorItem.Type type, CobaltItem.Settings settings, AttributeModifiersComponent attributeModifiersComponent) {
        super(material, type, settings.maxCount(1).attributeModifiers(ItemUtil.combine(attributeModifiersComponent, createArmorAttributes(material, type))));
        this.settings = settings;
    }

    public static AttributeModifiersComponent createArmorAttributes(RegistryEntry<ArmorMaterial> material, Type type) {
        int i = material.value().getProtection(type);
        float f = material.value().toughness();
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
        AttributeModifierSlot attributeModifierSlot = AttributeModifierSlot.forEquipmentSlot(type.getEquipmentSlot());
        Identifier identifier = Identifier.ofVanilla("armor." + type.getName());
        builder.add(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(identifier, i, EntityAttributeModifier.Operation.ADD_VALUE), attributeModifierSlot);
        builder.add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(identifier, f, EntityAttributeModifier.Operation.ADD_VALUE), attributeModifierSlot);
        float g = material.value().knockbackResistance();
        if (g > 0.0F) {
            builder.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(identifier, g, EntityAttributeModifier.Operation.ADD_VALUE), attributeModifierSlot);
        }

        return builder.build();
    }

    @Override
    public Text getName(ItemStack stack) {
        return settings.applyNameFormatting(super.getName(stack));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        settings.appendTooltip(stack, context, tooltip, type);
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public Item getItem() {
        return this;
    }
}
