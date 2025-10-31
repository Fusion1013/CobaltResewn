package se.fusion1013.items.tools;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.components.CobaltComponents;

import java.util.List;

public class FlashlightItem extends CobaltItem {

    public FlashlightItem(CobaltItem.Settings settings) {
        super((CobaltItem.Settings) settings
                .component(CobaltComponents.LIGHT_INTENSITY, 1f)
                .component(CobaltComponents.LIGHT_ANGLE, 0.8f)
                .component(CobaltComponents.LIGHT_DISTANCE, 15f));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.cobalt.flashlight.ability").formatted(Formatting.GOLD));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (stack.get(CobaltComponents.IS_ON) == null && !world.isClient) {
            stack.set(CobaltComponents.IS_ON, true);
        } else if (!world.isClient) {
            if (stack.get(CobaltComponents.IS_ON)) {
                stack.set(CobaltComponents.IS_ON, false);
            } else {
                stack.set(CobaltComponents.IS_ON, true);
            }
        }

        return super.use(world, user, hand);
    }
}
