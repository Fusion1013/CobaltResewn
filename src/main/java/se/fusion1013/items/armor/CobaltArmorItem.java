package se.fusion1013.items.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.ICobaltArmorItem;

import java.util.List;

public class CobaltArmorItem extends ArmorItem implements ICobaltArmorItem {

    private final CobaltItem.Settings settings;

    public CobaltArmorItem(RegistryEntry<ArmorMaterial> material, ArmorItem.Type type, CobaltItem.Settings settings) {
        super(material, type, settings);
        this.settings = settings;
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
