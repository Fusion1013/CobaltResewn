package se.fusion1013.util.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class ItemUtil {

    /***
     * Gets the held item if it is of the specified type. First checks the mainhand for the item, then the offhand.
     * @param player the player to check the hands of.
     * @param item the item to look for.
     * @return the itemstack of the held item. Null if an item of the specified type was not found.
     */
    public static ItemStack getHeldItemOfType(PlayerEntity player, Item item) {
        ItemStack mainHand = player.getStackInHand(Hand.MAIN_HAND);
        ItemStack offHand = player.getStackInHand(Hand.OFF_HAND);

        if (mainHand.getItem() == item) return mainHand;
        if (offHand.getItem() == item) return offHand;
        return null;
    }

}
