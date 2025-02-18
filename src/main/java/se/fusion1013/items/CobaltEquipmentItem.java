package se.fusion1013.items;

import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import se.fusion1013.items.armor.CobaltArmorItem;
import se.fusion1013.util.item.ArmorUtil;
import se.fusion1013.util.item.ItemUtil;

import java.util.List;

public class CobaltEquipmentItem extends Item implements Equipment, ICobaltArmorItem {

    private final EquipmentSlot slotType;
    private final CobaltItem.Settings settings;
    public final RegistryEntry<ArmorMaterial> material;

    public CobaltEquipmentItem(RegistryEntry<ArmorMaterial> material, CobaltItem.Settings settings, EquipmentSlot slotType) {
        this(material, settings, slotType, AttributeModifiersComponent.builder().build());
    }

    public CobaltEquipmentItem(RegistryEntry<ArmorMaterial> material, CobaltItem.Settings settings, EquipmentSlot slotType, AttributeModifiersComponent attributeModifiersComponent) {
        super(settings.maxCount(1).attributeModifiers(ItemUtil.combine(CobaltArmorItem.createArmorAttributes(material, ArmorUtil.toArmorType(slotType)), attributeModifiersComponent)));

        this.slotType = slotType;
        this.settings = settings;
        this.material = material;
    }

    @Override
    public EquipmentSlot getSlotType() {
        return slotType;
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
