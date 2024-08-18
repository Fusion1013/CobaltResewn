package se.fusion1013.items.armor;

import net.minecraft.util.Identifier;
import se.fusion1013.Main;
import se.fusion1013.items.*;

import java.util.function.BiFunction;

public class CobaltArmorSet {

    public final CobaltArmorItem helmet;
    public final CobaltArmorItem chestplate;
    public final CobaltArmorItem leggings;
    public final CobaltArmorItem boots;

    public ItemSet set;

    public CobaltArmorSet(CobaltArmorItem helmet, CobaltArmorItem chestplate, CobaltArmorItem leggings, CobaltArmorItem boots) {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }

    public static class Builder {

        private final CobaltArmorItem helmet;
        private final CobaltArmorItem chestplate;
        private final CobaltArmorItem leggings;
        private final CobaltArmorItem boots;

        private IItemSetMethods setMethods;

        public Builder(CobaltArmorItem helmet, CobaltArmorItem chestplate, CobaltArmorItem leggings, CobaltArmorItem boots) {
            this.helmet = helmet;
            this.chestplate = chestplate;
            this.leggings = leggings;
            this.boots = boots;
        }

        public Builder setBonus(IItemSetMethods methods) {
            setMethods = methods;
            return this;
        }

        public CobaltArmorSet register(String setId, BiFunction<String, CobaltArmorItem, CobaltArmorItem> registerFunction) {
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
