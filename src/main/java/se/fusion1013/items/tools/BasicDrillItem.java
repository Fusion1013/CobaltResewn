package se.fusion1013.items.tools;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.items.CobaltItemConfiguration;

import java.util.List;

public class BasicDrillItem extends CobaltPickaxeItem {

    public BasicDrillItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, CobaltItemConfiguration.create(Formatting.DARK_GRAY), settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.getInventory().containsAny(itemStack -> itemStack.getItem() == Items.COAL) && !user.hasStatusEffect(StatusEffects.HASTE)) {
            user.getInventory().remove(itemStack -> itemStack.getItem() == Items.COAL, 1, user.getInventory());
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 200, 0));
            user.playSound(SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 1, 1);
        }

        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("item.cobalt.basic_drill.ability").formatted(Formatting.GOLD));
    }
}
