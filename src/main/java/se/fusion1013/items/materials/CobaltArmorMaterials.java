package se.fusion1013.items.materials;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class CobaltArmorMaterials {

    // POST-1.21

    public static final int GLOBAL_DURABILITY_MULTIPLIER = 15;

    public static final RegistryEntry<ArmorMaterial> ADVENTURE = registerMaterial("adventure", 1, 2, 3, 1, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER);
    public static final RegistryEntry<ArmorMaterial> GUARD = registerMaterial("guard", 2, 3, 5, 2, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN);
    public static final RegistryEntry<ArmorMaterial> HUNTER = registerMaterial("hunter", 1, 2, 3, 1, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER);
    public static final RegistryEntry<ArmorMaterial> MECHANIC = registerMaterial("mechanic", 2, 3, 4, 2, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN);
    public static final RegistryEntry<ArmorMaterial> REINFORCED_MECHANIC = registerMaterial("reinforced_mechanic", 2, 3, 4, 2, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN);
    public static final RegistryEntry<ArmorMaterial> TINKER = registerMaterial("tinker", 2, 4, 5, 1, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN);
    public static final RegistryEntry<ArmorMaterial> REINFORCED_TINKER = registerMaterial("reinforced_tinker", 2, 4, 5, 1, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN);
    public static final RegistryEntry<ArmorMaterial> LUMBERJACK = registerMaterial("lumberjack", 1, 2, 3, 1, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER);
    public static final RegistryEntry<ArmorMaterial> MINER = registerMaterial("miner", 1, 2, 5, 1, SoundEvents.ITEM_ARMOR_EQUIP_IRON);
    public static final RegistryEntry<ArmorMaterial> PROSPECTOR = registerMaterial("prospector", 1, 2, 3, 2, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER);
    public static final RegistryEntry<ArmorMaterial> DIVE = registerMaterial("dive", 3, 6, 7, 3, SoundEvents.ITEM_ARMOR_EQUIP_IRON);
    public static final RegistryEntry<ArmorMaterial> CORRUPTED = registerMaterial("corrupted", 1, 2, 3, 1, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER);
    public static final RegistryEntry<ArmorMaterial> EXOSKELETON = registerMaterial("exoskeleton", 3, 6, 7, 3, SoundEvents.ITEM_ARMOR_EQUIP_IRON);
    public static final RegistryEntry<ArmorMaterial> ADVANCED_EXOSKELETON = registerMaterial("advanced_exoskeleton", 3, 6, 7, 3, SoundEvents.ITEM_ARMOR_EQUIP_IRON);
    public static final RegistryEntry<ArmorMaterial> THERMAL_GEAR = registerMaterial("thermal_gear", 2, 5, 6, 2, SoundEvents.ITEM_ARMOR_EQUIP_IRON);


    public static RegistryEntry<ArmorMaterial> registerMaterial(String id, int defenseHelmet, int defenseChestplate, int defenseLeggings, int defenseBoots, RegistryEntry<SoundEvent> equipSound) {
        return registerMaterial(id, defenseHelmet, defenseChestplate, defenseLeggings, defenseBoots, 0, equipSound);
    }

    public static RegistryEntry<ArmorMaterial> registerMaterial(String id, int defenseHelmet, int defenseChestplate, int defenseLeggings, int defenseBoots, int toughness, RegistryEntry<SoundEvent> equipSound) {
        return registerMaterial(id, Map.of(
                ArmorItem.Type.HELMET, defenseHelmet,
                ArmorItem.Type.CHESTPLATE, defenseChestplate,
                ArmorItem.Type.LEGGINGS, defenseLeggings,
                ArmorItem.Type.BOOTS, defenseBoots
        ), 0, equipSound, () -> Ingredient.ofItems(Items.BEDROCK), 0.0f, 0.0f, false);
    }

    public static RegistryEntry<ArmorMaterial> registerMaterial(String id, Map<ArmorItem.Type, Integer> defensePoints, int enchantability, RegistryEntry<SoundEvent> equipSound, Supplier<Ingredient> repairIngredientSupplier, float toughness, float knockbackResistance, boolean dyeable) {
        List<ArmorMaterial.Layer> layers = List.of(
                new ArmorMaterial.Layer(Identifier.of(Main.MOD_NAMESPACE, id), "", dyeable)
        );

        ArmorMaterial material = new ArmorMaterial(defensePoints, enchantability, equipSound, repairIngredientSupplier, layers, toughness, knockbackResistance);
        material = Registry.register(Registries.ARMOR_MATERIAL, Identifier.of(Main.MOD_NAMESPACE, id), material);
        return RegistryEntry.of(material);
    }

    // PRE-1.21

    /*
    public static final CobaltArmorMaterial ADVENTURE = new CobaltArmorMaterial("adventure", new int[] { 20, 20, 20, 20 }, new int[] { 1, 2, 3, 1 }, 5);
    public static final CobaltArmorMaterial GUARD = new CobaltArmorMaterial.Builder("guard", new int[] { 20, 20, 20, 20 }, new int[] { 2, 3, 5, 2 }, 5)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("guard.chestplate.move_speed", -0.02f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.CHEST)
            .equipSound(SoundEvents.ITEM_ARMOR_EQUIP_CHAIN)
            .build();

    public static final CobaltArmorMaterial HUNTER = new CobaltArmorMaterial.Builder("hunter", new int[] { 20, 20, 20, 20 }, new int[] { 1, 2, 3, 1 }, 5)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("hunter.boots.move_speed", 0.02f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.FEET)
            .build();


    public static final CobaltArmorMaterial MECHANIC = new CobaltArmorMaterial.Builder("mechanic", new int[] { 20, 20, 20, 20 }, new int[] { 2, 3, 4, 2 }, 5)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("mechanic.boots.move_speed", -.07f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.FEET)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("mechanic.leggings.move_speed", -.05f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.LEGS)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("mechanic.chestplate.move_speed", -.06f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.CHEST)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("mechanic.helmet.move_speed", -.05f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.HEAD)
            .toughness(1)
            .build();

    public static final CobaltArmorMaterial REINFORCED_MECHANIC = new CobaltArmorMaterial.Builder("reinforced_mechanic", new int[] { 20, 20, 20, 20 }, new int[] { 2, 3, 4, 2 }, 5)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("mechanic.boots.move_speed", -.10f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.FEET)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("mechanic.leggings.move_speed", -.075f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.LEGS)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("mechanic.chestplate.move_speed", -.10f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.CHEST)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("mechanic.helmet.move_speed", -.075f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.HEAD)
            .toughness(2)
            .build();

    public static final CobaltArmorMaterial TINKER = new CobaltArmorMaterial.Builder("tinker", new int[] { 20, 20, 20, 20 }, new int[] { 2, 4, 5, 1 }, 5)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("tinker.helmet.move_speed", .05f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.HEAD)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("tinker.chestplate.move_speed", .05f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.CHEST)
            .attribute(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier("tinker.chestplate.knockback_resistance", .1f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.CHEST)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("tinker.leggings.move_speed", .07f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.LEGS)
            .attribute(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier("tinker.leggings.knockback_resistance", .07f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.LEGS)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("tinker.boots.move_speed", .1f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.FEET)
            .build();

    public static final CobaltArmorMaterial REINFORCED_TINKER = new CobaltArmorMaterial.Builder("reinforced_tinker", new int[] { 20, 20, 20, 20 }, new int[] { 2, 4, 5, 1 }, 5)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("reinforced_tinker.helmet.move_speed", .08f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.HEAD)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("reinforced_tinker.chestplate.move_speed", .075f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.CHEST)
            .attribute(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier("reinforced_tinker.chestplate.knockback_resistance", .075f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.CHEST)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("reinforced_tinker.leggings.move_speed", .08f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.LEGS)
            .attribute(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier("reinforced_tinker.leggings.knockback_resistance", .08f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.LEGS)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("reinforced_tinker.boots.move_speed", .15f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.FEET)
            .toughness(1)
            .build();

    public static final CobaltArmorMaterial LUMBERJACK = new CobaltArmorMaterial.Builder("lumberjack", new int[] { 20, 20, 20, 20 }, new int[] { 1, 2, 3, 1 }, 5)
            .attribute(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier("lumberjack.chestplate.attack_damage", 1, EntityAttributeModifier.Operation.ADDITION), EquipmentSlot.CHEST)
            .build();

    public static final CobaltArmorMaterial MINER = new CobaltArmorMaterial.Builder("miner", new int[] { 20, 20, 20, 20 }, new int[] { 1, 2, 5, 1 }, 5)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("miner.chestplate.move_speed", -0.05, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.CHEST)
            .build();

    public static final CobaltArmorMaterial PROSPECTOR = new CobaltArmorMaterial.Builder("prospector", new int[] { 20, 20, 20, 20 }, new int[] { 1, 2, 3, 2 }, 5)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("prospector.helmet.move_speed", .01f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.HEAD)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("prospector.chestplate.move_speed", .01f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.CHEST)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("prospector.leggings.move_speed", .02f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.LEGS)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("prospector.boots.move_speed", .02f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.FEET)
            .build();

    public static final CobaltArmorMaterial DIVE = new CobaltArmorMaterial.Builder("dive", new int[] { 20, 20, 20, 20 }, new int[] { 3, 6, 7, 3 }, 5)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("dive.helmet.move_speed", -0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.HEAD)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("dive.chestplate.move_speed", -0.15, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.CHEST)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("dive.leggings.move_speed", -0.15, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.LEGS)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("dive.boots.move_speed", -0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.FEET)
            .build();
    public static final CobaltArmorMaterial CORRUPTED = new CobaltArmorMaterial("corrupted", new int[] { 20, 20, 20, 20 }, new int[] { 1, 2, 3, 1 }, 5);

    public static final CobaltArmorMaterial EXOSKELETON = new CobaltArmorMaterial.Builder("exoskeleton", new int[] { 20, 20, 20, 20 }, new int[] { 3, 6, 7, 3 }, 5)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("exoskeleton.helmet.move_speed", -0.01, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.HEAD)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("exoskeleton.chestplate.move_speed", -0.04, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.CHEST)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("exoskeleton.leggings.move_speed", -0.03, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.LEGS)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("exoskeleton.boots.move_speed", -0.02, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.FEET)
            .build();
    public static final CobaltArmorMaterial ADVANCED_EXOSKELETON = new CobaltArmorMaterial.Builder("advanced_exoskeleton", new int[] { 20, 20, 20, 20 }, new int[] { 3, 6, 7, 3 }, 5)
            .toughness(1)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("advanced_exoskeleton.helmet.move_speed", -0.01, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.HEAD)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("advanced_exoskeleton.chestplate.move_speed", -0.04, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.CHEST)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("advanced_exoskeleton.leggings.move_speed", -0.03, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.LEGS)
            .attribute(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("advanced_exoskeleton.boots.move_speed", -0.02, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.FEET)
            .build();
    public static final CobaltArmorMaterial THERMAL_GEAR = new CobaltArmorMaterial.Builder("thermal_gear", new int[] { 20, 20, 20, 20 }, new int[] { 2, 5, 6, 2 }, 5)
            .build();
     */

}
