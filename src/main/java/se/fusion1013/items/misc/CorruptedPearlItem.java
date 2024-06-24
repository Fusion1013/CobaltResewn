package se.fusion1013.items.misc;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import se.fusion1013.effect.CobaltEffects;
import se.fusion1013.items.CobaltItem;

import java.util.List;

public class CorruptedPearlItem extends CobaltItem {

    public CorruptedPearlItem() {
        super(new CobaltItem.Settings(Formatting.DARK_PURPLE));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.addStatusEffect(new StatusEffectInstance(CobaltEffects.CORRUPTION_SPREAD, 10, 0, true, false, false));
        user.playSoundToPlayer(SoundEvents.ENTITY_ILLUSIONER_PREPARE_MIRROR, SoundCategory.PLAYERS, 1, 1);

        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("item.cobalt.item_use.header").formatted(Formatting.GOLD));
        tooltip.add(Text.translatable("item.cobalt.corrupted_pearl.ability").formatted(Formatting.GRAY));
    }
}
