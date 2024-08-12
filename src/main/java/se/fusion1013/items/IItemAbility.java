package se.fusion1013.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public interface IItemAbility {
    TypedActionResult<ItemStack> trigger(World world, PlayerEntity user, Hand hand);
}
