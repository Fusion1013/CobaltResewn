package se.fusion1013.items.consumable;

import se.fusion1013.items.CobaltItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import se.fusion1013.items.CobaltItemConfiguration;

public class CobaltHealingItem extends CobaltItem {

    private final int healAmount;

    public CobaltHealingItem(CobaltItemConfiguration configuration, Settings settings, int amount) {
        super(configuration, settings);

        healAmount = amount;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // Do not heal if user is already at max health
        if (user.getHealth() >= user.getMaxHealth()) return super.use(world, user, hand);

        // Try to reduce the held itemstack
        if (hand == Hand.MAIN_HAND) user.getInventory().getMainHandStack().decrement(1);
        else user.getInventory().removeStack(PlayerInventory.OFF_HAND_SLOT, 1);

        // Heal the user
        user.heal(healAmount);
        user.playSound(SoundEvents.ENTITY_WITCH_DRINK, SoundCategory.PLAYERS, 1, 1);

        return super.use(world, user, hand);
    }
}
