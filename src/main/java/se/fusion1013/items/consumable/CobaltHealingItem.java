package se.fusion1013.items.consumable;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import se.fusion1013.items.CobaltItem;

public class CobaltHealingItem extends CobaltDrinkItem {

    private final int healAmount;

    public CobaltHealingItem(CobaltItem.Settings settings, int amount) {
        super((CobaltItem.Settings)settings.food(new FoodComponent.Builder()
                .nutrition(0)
                .saturationModifier(0f)
                .snack()
                .build())
        );

        healAmount = amount;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient()) return super.finishUsing(stack, world, user);

        if (user instanceof PlayerEntity player) applyHeal(player, stack);

        return super.finishUsing(stack, world, user);
    }

    private void applyHeal(PlayerEntity user, ItemStack stack) {
        // Do not heal if user is already at max health
        // if (user.getHealth() >= user.getMaxHealth()) return;

        // Try to reduce the held itemstack
        // stack.decrement(1);
        // user.getInventory().getMainHandStack().decrement(1);

        // Heal the user
        user.heal(healAmount);
        user.playSoundToPlayer(SoundEvents.ENTITY_WITCH_DRINK, SoundCategory.PLAYERS, 1, 1);
    }
}
