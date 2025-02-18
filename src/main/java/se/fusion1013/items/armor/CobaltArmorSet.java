package se.fusion1013.items.armor;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;
import se.fusion1013.items.*;

import java.util.function.BiFunction;

public class CobaltArmorSet {

    public final Item helmet;
    public final Item chestplate;
    public final Item leggings;
    public final Item boots;

    public ItemSet set;

    public CobaltArmorSet(Item helmet, Item chestplate, Item leggings, Item boots) {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }

    public static class Builder {

        private final Item helmet;
        private final Item chestplate;
        private final Item leggings;
        private final Item boots;

        private IItemSetMethods setMethods;

        public Builder(Item helmet, Item chestplate, Item leggings, Item boots) {
            this.helmet = helmet;
            this.chestplate = chestplate;
            this.leggings = leggings;
            this.boots = boots;
        }

        public Builder setBonus(IItemSetMethods methods) {
            setMethods = methods;
            return this;
        }

        public CobaltArmorSet register(String setId, BiFunction<String, Item, Item> registerFunction) {
            // Register the parts
            var registeredBoots = registerFunction.apply(setId + "_boots", boots);
            var registeredLeggings = registerFunction.apply(setId + "_leggings", leggings);
            var registeredChestplate = registerFunction.apply(setId + "_chestplate", chestplate);
            var registeredHelmet = registerFunction.apply(setId + "_helmet", helmet);

            // Create armor set
            var armorSet = new CobaltArmorSet(registeredHelmet, registeredChestplate, registeredLeggings, registeredBoots);

            // Create item set if methods are present
            if (setMethods != null) {
                Main.LOGGER.info("Registered armor set '" + setId + "' with item set methods");
                armorSet.set = ItemSet.register(Identifier.of(setId), new ItemSet.ItemSetItem[] {
                        new ItemSet.ItemSetItem(registeredHelmet, ItemSet.ItemLocation.Armor),
                        new ItemSet.ItemSetItem(registeredChestplate, ItemSet.ItemLocation.Armor),
                        new ItemSet.ItemSetItem(registeredLeggings, ItemSet.ItemLocation.Armor),
                        new ItemSet.ItemSetItem(registeredBoots, ItemSet.ItemLocation.Armor)
                }, setMethods);
            }

            return armorSet;
        }

    }
}
