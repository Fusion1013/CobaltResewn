package se.fusion1013.items.tools;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import se.fusion1013.items.CobaltItem;

import java.util.List;

public class CobaltAxeItem extends AxeItem {

    private final CobaltItem.Settings settings;

    public CobaltAxeItem(float attackDamage, float attackSpeed, CobaltItem.Settings settings) {
        super(ToolMaterials.STONE, settings);
        settings.attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.STONE, attackDamage-2, attackSpeed-4));
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
}
