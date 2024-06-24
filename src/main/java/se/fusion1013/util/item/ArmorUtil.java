package se.fusion1013.util.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;
import se.fusion1013.items.CobaltEquipmentItem;

import static net.minecraft.item.ArmorItem.Type.*;

public class ArmorUtil {

    public static ArmorItem.Type toArmorType(EquipmentSlot slot) {
        switch (slot) {
            case FEET -> {
                return BOOTS;
            }
            case LEGS -> {
                return LEGGINGS;
            }
            case CHEST -> {
                return CHESTPLATE;
            }
            case HEAD -> {
                return HELMET;
            }
        }
        return null;
    }

    public static boolean isWearingArmorSet(PlayerEntity player, RegistryEntry<ArmorMaterial> material) {
        var boots = getArmorMaterial(player.getInventory().getArmorStack(0).getItem());
        var leggings = getArmorMaterial(player.getInventory().getArmorStack(1).getItem());
        var chestplate = getArmorMaterial(player.getInventory().getArmorStack(2).getItem());
        var helmet = getArmorMaterial(player.getInventory().getArmorStack(3).getItem());

        return  boots == material &&
                leggings == material &&
                chestplate == material &&
                helmet == material;
    }

    public static boolean isWearingArmorItem(PlayerEntity player, Item item) {
        var boots = player.getInventory().getArmorStack(0).getItem();
        var leggings = player.getInventory().getArmorStack(1).getItem();
        var chestplate = player.getInventory().getArmorStack(2).getItem();
        var helmet = player.getInventory().getArmorStack(3).getItem();

        return boots == item || leggings == item || chestplate == item || helmet == item;
    }

    public static RegistryEntry<ArmorMaterial> getArmorMaterial(Item item) {
        if (item instanceof ArmorItem armorItem) return armorItem.getMaterial();
        if (item instanceof CobaltEquipmentItem equipmentItem) return equipmentItem.material;
        return null;
    }

}
