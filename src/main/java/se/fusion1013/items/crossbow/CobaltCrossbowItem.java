package se.fusion1013.items.crossbow;

import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import se.fusion1013.items.CobaltItem;

import java.util.List;

public class CobaltCrossbowItem extends CrossbowItem {

    private final CobaltItem.Settings settings;

    public CobaltCrossbowItem(CobaltItem.Settings settings) {
        super(settings.maxCount(1));
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
