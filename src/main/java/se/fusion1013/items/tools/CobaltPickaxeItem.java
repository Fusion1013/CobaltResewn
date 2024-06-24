package se.fusion1013.items.tools;

import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import se.fusion1013.items.CobaltItem;

import java.util.List;

public class CobaltPickaxeItem extends PickaxeItem {

    private final CobaltItem.Settings settings;

    public CobaltPickaxeItem(int attackDamage, float attackSpeed, CobaltItem.Settings settings) {
        super(ToolMaterials.STONE, settings);
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
