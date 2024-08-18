package se.fusion1013.items.sword;

import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import se.fusion1013.items.CobaltItem;

import java.util.List;

public class CobaltSwordItem extends SwordItem {

    private final CobaltItem.Settings settings;

    public CobaltSwordItem(int attackDamage, float attackSpeed, CobaltItem.Settings settings) {
        super(ToolMaterials.STONE, settings
                .attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.STONE, attackDamage-2, attackSpeed-4)));
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
