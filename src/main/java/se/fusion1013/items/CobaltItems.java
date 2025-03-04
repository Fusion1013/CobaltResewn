package se.fusion1013.items;

import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.TrinketItem;
import io.wispforest.lavender.book.LavenderBookItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import se.fusion1013.entity.*;
import se.fusion1013.items.armor.CobaltArmorItem;
import se.fusion1013.items.armor.CobaltArmorSet;
import se.fusion1013.items.armor.DivingArmorItem;
import se.fusion1013.items.armor.sets.AdvancedExoskeletonArmorSet;
import se.fusion1013.items.armor.sets.DivingArmorSet;
import se.fusion1013.items.armor.sets.ExoskeletonArmorSet;
import se.fusion1013.items.armor.sets.ThermalGearArmorSet;
import se.fusion1013.items.consumable.CobaltDrinkItem;
import se.fusion1013.items.consumable.CobaltHealingItem;
import se.fusion1013.items.consumable.LiquidCourageItem;
import se.fusion1013.items.consumable.MysteryMedicineItem;
import se.fusion1013.items.crossbow.CobaltCrossbowItem;
import se.fusion1013.items.materials.CobaltArmorMaterials;
import se.fusion1013.items.tools.FlashlightItem;
import se.fusion1013.items.trinket.CobaltTrinketItem;
import se.fusion1013.items.trinket.MechanicSpectaclesTrinket;
import se.fusion1013.util.item.ItemSetUtil;

import java.text.Format;
import java.util.List;
import se.fusion1013.items.misc.CorruptedPearlItem;
import se.fusion1013.items.misc.WalkieTalkieItem;
import se.fusion1013.items.sword.*;
import se.fusion1013.items.tools.BasicDrillItem;
import se.fusion1013.items.tools.CobaltAxeItem;
import se.fusion1013.items.tools.CobaltPickaxeItem;
import se.fusion1013.items.trinket.*;
import se.fusion1013.sounds.CobaltJukeboxSongs;

import static se.fusion1013.Main.MOD_NAMESPACE;
import static se.fusion1013.items.CustomItemGroupRegistry.COBALT_GROUP_KEY;

public class CobaltItems {


    public static final Item ICON_ITEM = new Item(new Item.Settings());

    public static final LavenderBookItem WF_INSTRUCTION_MANUAL;
    public static final LavenderBookItem ADVENTURER_JOURNAL;

    // -- ARMOR
    public static final CobaltArmorSet ADVENTURE_ARMOR_SET;
    public static final CobaltArmorSet DIVING_ARMOR_SET;
    /*
    public static final ArmorItem DIVING_HELMET;
    public static final ArmorItem DIVING_CHESTPLATE;
    public static final ArmorItem DIVING_LEGGINGS;
    public static final ArmorItem DIVING_BOOTS;
     */
    public static final CobaltArmorSet LUMBERJACK_ARMOR_SET;
    public static final CobaltArmorSet GUARD_ARMOR_SET;
    public static final CobaltArmorSet HUNTER_ARMOR_SET;
    public static final CobaltArmorSet MECHANIC_ARMOR_SET;
    public static final CobaltArmorSet REINFORCED_MECHANIC_ARMOR_SET;
    public static final CobaltArmorSet MINER_ARMOR_SET;
    public static final CobaltArmorSet PROSPECTOR_ARMOR_SET;
    public static final CobaltArmorSet TINKER_ARMOR_SET;
    public static final CobaltArmorSet REINFORCED_TINKER_ARMOR_SET;
    public static final CobaltArmorSet EXOSKELETON;
    public static final CobaltArmorSet THERMAL_GEAR;
    public static final CobaltArmorSet ADVANCED_EXOSKELETON;

    public static final Item ADVENTURE_SWORD;
    // public static final Item INFECTED_ADVENTURE_SWORD;
    public static final Item HEAVY_WRENCH;
    public static final Item BASIC_DRILL;
    public static final Item SAMPLE_DRILL;
    public static final Item GUARD_SWORD;
    public static final Item PROSPECTOR_PICKAXE;
    public static final Item DAGGER;
    public static final Item SCREWDRIVER;
    public static final Item CROWBAR;
    public static final Item RATCHETING_SCREWDRIVER;
    public static final Item BIONIC_FIST;
    public static final Item ADVANCED_BIONIC_FIST;
    public static final Item FORGE_HAMMER;
    public static final Item VOIDREND;

    public static final Item MINER_PICKAXE;

    public static final Item HARPOON_GUN;
    public static final Item HUNTER_CROSSBOW;

    public static final Item LUMBERJACK_AXE;

    public static final Item HUNTER_GLOVE;
    public static final Item MECHANICAL_HAND;
    public static final Item MECHANIC_GLOVES;
    public static final Item MECHANIC_SPECTACLES;
    public static final Item GEARSTRAP;
    public static final Item RUNE_GLOVE;
    public static final Item HEALTH_RUNE;
    public static final Item HEAVY_RUNE;
    public static final Item FAST_RUNE;
    public static final Item FIRE_RUNE;
    public static final Item ICE_RUNE;
    public static final Item LIGHTNING_RUNE;
    public static final Item THICK_RUNE;

    public static final Item LIGHTNING_ARROW;
    public static final Item EXPLOSIVE_ARROW;

    public static final Item PAINKILLERS;
    public static final Item BANDAGE;
    public static final Item FIRST_AID_KIT;
    public static final Item PNEUMATIC_NEEDLE;
    public static final Item MYSTERY_MEDICINE;
    public static final Item LIQUID_COURAGE;
    public static final Item RUINED_GEAR;
    public static final Item TARNISHED_GEAR;
    public static final Item AVERAGE_GEAR;
    public static final Item REMARKABLE_GEAR;
    public static final Item BATTERY;
    public static final Item CORRUPTED_PEARL;
    public static final Item WALKIE_TALKIE;
    public static final Item HAND_HELD_LANTERN;
    public static final Item RUNE_MODIFIER;
    public static final Item FORGE_SIDE_CRYSTAL;
    public static final Item LIGHT_SOUL;
    public static final Item LENS;
    public static final Item RED_LENS;
    public static final Item GREEN_LENS;
    public static final Item BLUE_LENS;
    public static final Item PRESSURE_GAUGE;

    public static final Item SMOKE_BOMB;
    public static final Item DYNAMITE;

    public static final Item FLASHLIGHT;

    public static final Item CORRUPTED_ZOMBIE_SPAWN_EGG;
    public static final Item CORRUPTED_SKELETON_SPAWN_EGG;
    public static final Item CORRUPTED_SPIDER_SPAWN_EGG;
    public static final Item AUTOMATON_SPAWN_EGG;
    public static final Item RAT_SPAWN_EGG;

    public static final Item MUSIC_DISC_ANCIENT_GATEWAY;
    public static final Item MUSIC_DISC_ERA_OF_PEACE;
    public static final Item MUSIC_DISC_HYDROANGEA;
    public static final Item MUSIC_DISC_OUTPOST;
    public static final Item MUSIC_DISC_SHOOTING_FOR_THE_STARS;
    public static final Item MUSIC_DISC_AKALI;


    static {
        WF_INSTRUCTION_MANUAL = LavenderBookItem.registerForBook(Identifier.of("cobalt", "wf_instruction_manual"), new Item.Settings());
        ADVENTURER_JOURNAL = LavenderBookItem.registerForBook(Identifier.of("cobalt", "adventure_journal"), new Item.Settings());

        ADVENTURE_ARMOR_SET = registerSet("adventure", CobaltArmorMaterials.ADVENTURE, CobaltRarity.Average, CobaltArmorMaterials.GLOBAL_DURABILITY_MULTIPLIER);
        DIVING_ARMOR_SET = registerSet("diving",
                new CobaltEquipmentItem(CobaltArmorMaterials.DIVE, new CobaltItem.Settings().rarity(CobaltRarity.Great), EquipmentSlot.HEAD, DivingArmorItem.getAttributes(ArmorItem.Type.HELMET)),
                new DivingArmorItem(ArmorItem.Type.CHESTPLATE),
                new DivingArmorItem(ArmorItem.Type.LEGGINGS),
                new DivingArmorItem(ArmorItem.Type.BOOTS),
                new DivingArmorSet()
        );
        LUMBERJACK_ARMOR_SET = registerSet("lumberjack",
                new CobaltArmorItem(CobaltArmorMaterials.LUMBERJACK, ArmorItem.Type.HELMET, new CobaltItem.Settings().rarity(CobaltRarity.Average)),
                new CobaltArmorItem(CobaltArmorMaterials.LUMBERJACK, ArmorItem.Type.CHESTPLATE, new CobaltItem.Settings().rarity(CobaltRarity.Average), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "lumberjack.damage"), 1, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.CHEST).build()),
                new CobaltArmorItem(CobaltArmorMaterials.LUMBERJACK, ArmorItem.Type.LEGGINGS, new CobaltItem.Settings().rarity(CobaltRarity.Average)),
                new CobaltArmorItem(CobaltArmorMaterials.LUMBERJACK, ArmorItem.Type.BOOTS, new CobaltItem.Settings().rarity(CobaltRarity.Average)),
                null
        );
        GUARD_ARMOR_SET = registerSet("guard",
                new CobaltArmorItem(CobaltArmorMaterials.GUARD, ArmorItem.Type.HELMET, new CobaltItem.Settings().rarity(CobaltRarity.Good)),
                new CobaltArmorItem(CobaltArmorMaterials.GUARD, ArmorItem.Type.CHESTPLATE, new CobaltItem.Settings().rarity(CobaltRarity.Good), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "guard.speed"), -0.02, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.CHEST).build()),
                new CobaltArmorItem(CobaltArmorMaterials.GUARD, ArmorItem.Type.LEGGINGS, new CobaltItem.Settings().rarity(CobaltRarity.Good)),
                new CobaltArmorItem(CobaltArmorMaterials.GUARD, ArmorItem.Type.BOOTS, new CobaltItem.Settings().rarity(CobaltRarity.Good)),
                null
        );
        HUNTER_ARMOR_SET = registerSet("hunter",
                new CobaltArmorItem(CobaltArmorMaterials.HUNTER, ArmorItem.Type.HELMET, new CobaltItem.Settings().rarity(CobaltRarity.Average)),
                new CobaltArmorItem(CobaltArmorMaterials.HUNTER, ArmorItem.Type.CHESTPLATE, new CobaltItem.Settings().rarity(CobaltRarity.Average)),
                new CobaltArmorItem(CobaltArmorMaterials.HUNTER, ArmorItem.Type.LEGGINGS, new CobaltItem.Settings().rarity(CobaltRarity.Average)),
                new CobaltArmorItem(CobaltArmorMaterials.HUNTER, ArmorItem.Type.BOOTS, new CobaltItem.Settings().rarity(CobaltRarity.Average), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "boots.speed"), 0.02, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.FEET).build()),
                null
        );
        MECHANIC_ARMOR_SET = registerSet("mechanic",
                new CobaltArmorItem(CobaltArmorMaterials.MECHANIC, ArmorItem.Type.HELMET, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "helmet.speed"), -0.05, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.HEAD).build()),
                new CobaltArmorItem(CobaltArmorMaterials.MECHANIC, ArmorItem.Type.CHESTPLATE, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "chestplate.speed"), -0.06, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.CHEST).build()),
                new CobaltArmorItem(CobaltArmorMaterials.MECHANIC, ArmorItem.Type.LEGGINGS, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "leggings.speed"), -0.05, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.LEGS).build()),
                new CobaltArmorItem(CobaltArmorMaterials.MECHANIC, ArmorItem.Type.BOOTS, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "boots.speed"), -0.07, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.FEET).build()),
                null
        );
        REINFORCED_MECHANIC_ARMOR_SET = registerSet("reinforced_mechanic",
                new CobaltArmorItem(CobaltArmorMaterials.REINFORCED_MECHANIC, ArmorItem.Type.HELMET, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "helmet.speed"), -0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.HEAD).build()),
                new CobaltArmorItem(CobaltArmorMaterials.REINFORCED_MECHANIC, ArmorItem.Type.CHESTPLATE, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "chestplate.speed"), -0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.CHEST).build()),
                new CobaltArmorItem(CobaltArmorMaterials.REINFORCED_MECHANIC, ArmorItem.Type.LEGGINGS, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "leggings.speed"), -0.075, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.LEGS).build()),
                new CobaltArmorItem(CobaltArmorMaterials.REINFORCED_MECHANIC, ArmorItem.Type.BOOTS, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "boots.speed"), -0.075, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.FEET).build()),
                null
        );
        MINER_ARMOR_SET = registerSet("miner",
                new CobaltEquipmentItem(CobaltArmorMaterials.MINER, new CobaltItem.Settings().rarity(CobaltRarity.Good), EquipmentSlot.HEAD),
                new CobaltArmorItem(CobaltArmorMaterials.MINER, ArmorItem.Type.CHESTPLATE, new CobaltItem.Settings().rarity(CobaltRarity.Good), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "chestplate.speed"), -0.05, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.CHEST).build()),
                new CobaltArmorItem(CobaltArmorMaterials.MINER, ArmorItem.Type.LEGGINGS, new CobaltItem.Settings().rarity(CobaltRarity.Good)),
                new CobaltArmorItem(CobaltArmorMaterials.MINER, ArmorItem.Type.BOOTS, new CobaltItem.Settings().rarity(CobaltRarity.Good)),
                null
        );
        PROSPECTOR_ARMOR_SET = registerSet("prospector",
                new CobaltEquipmentItem(CobaltArmorMaterials.PROSPECTOR, new CobaltItem.Settings().rarity(CobaltRarity.Great), EquipmentSlot.HEAD, AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "helmet.speed"), 0.01, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.HEAD).build()),
                new CobaltArmorItem(CobaltArmorMaterials.PROSPECTOR, ArmorItem.Type.CHESTPLATE, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "chestplate.speed"), 0.01, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.CHEST).build()),
                new CobaltArmorItem(CobaltArmorMaterials.PROSPECTOR, ArmorItem.Type.LEGGINGS, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "leggings.speed"), 0.02, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.LEGS).build()),
                new CobaltArmorItem(CobaltArmorMaterials.PROSPECTOR, ArmorItem.Type.BOOTS, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "boots.speed"), 0.02, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.FEET).build()),
                null
        );
        TINKER_ARMOR_SET = registerSet("tinker",
                new CobaltArmorItem(CobaltArmorMaterials.TINKER, ArmorItem.Type.HELMET, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "helmet.speed"), 0.05, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.HEAD).build()),
                new CobaltArmorItem(CobaltArmorMaterials.TINKER, ArmorItem.Type.CHESTPLATE, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "chestplate.speed"), 0.05, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.CHEST).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "chestplate.knockback_resistance"), 0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.CHEST).build()),
                new CobaltArmorItem(CobaltArmorMaterials.TINKER, ArmorItem.Type.LEGGINGS, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "leggings.speed"), 0.07, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.LEGS).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "leggings.knockback_resistance"), 0.07, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.LEGS).build()),
                new CobaltArmorItem(CobaltArmorMaterials.TINKER, ArmorItem.Type.BOOTS, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "boots.speed"), 0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.FEET).build()),
                null
        );
        REINFORCED_TINKER_ARMOR_SET = registerSet("reinforced_tinker",
                new CobaltArmorItem(CobaltArmorMaterials.REINFORCED_TINKER, ArmorItem.Type.HELMET, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "helmet.speed"), 0.08, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.HEAD).build()),
                new CobaltArmorItem(CobaltArmorMaterials.REINFORCED_TINKER, ArmorItem.Type.CHESTPLATE, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "chestplate.speed"), 0.075, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.CHEST).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "chestplate.knockback_resistance"), 0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.CHEST).build()),
                new CobaltArmorItem(CobaltArmorMaterials.REINFORCED_TINKER, ArmorItem.Type.LEGGINGS, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "leggings.speed"), 0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.LEGS).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "leggings.knockback_resistance"), 0.08, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.LEGS).build()),
                new CobaltArmorItem(CobaltArmorMaterials.REINFORCED_TINKER, ArmorItem.Type.BOOTS, new CobaltItem.Settings().rarity(CobaltRarity.Great), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "boots.speed"), 0.15, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.FEET).build()),
                null
        );
        EXOSKELETON = registerSet("exoskeleton", CobaltArmorMaterials.EXOSKELETON, CobaltRarity.Outstanding, CobaltArmorMaterials.GLOBAL_DURABILITY_MULTIPLIER, new ExoskeletonArmorSet());
        THERMAL_GEAR = registerSet("thermal_gear", CobaltArmorMaterials.THERMAL_GEAR, CobaltRarity.Outstanding, CobaltArmorMaterials.GLOBAL_DURABILITY_MULTIPLIER, new ThermalGearArmorSet());
        ADVANCED_EXOSKELETON = registerSet("advanced_exoskeleton", CobaltArmorMaterials.ADVANCED_EXOSKELETON, CobaltRarity.Perfect, CobaltArmorMaterials.GLOBAL_DURABILITY_MULTIPLIER, new AdvancedExoskeletonArmorSet());

        DAGGER = register("dagger", new CobaltSwordItem(1, 3, new CobaltItem.Settings().rarity(CobaltRarity.Good).maxCount(1), AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "dagger.attack_damage"), .06, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.OFFHAND).build()));
        ADVENTURE_SWORD = register("adventure_sword", new CobaltSwordItem(3, 1.6f, new CobaltItem.Settings().rarity(CobaltRarity.Average)));
        /*
        INFECTED_ADVENTURE_SWORD = register("infected_adventure_sword", new InfectedSwordItem(ToolMaterials.STONE, -2+4, -4+1.6f, new CobaltItem.Settings(Formatting.DARK_PURPLE), Formatting.DARK_PURPLE, 10, 60*20, ((world, user, hand) -> {
            user.(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.PLAYERS, 1, 1);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20*60, 0));
        })));
         */
        HEAVY_WRENCH = register("heavy_wrench", new CobaltSwordItem(9, 1.0f, (CobaltItem.Settings) new CobaltItem.Settings().rarity(CobaltRarity.Great).component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "heavy_wrench.speed"), -0.05, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.MAINHAND).build())));
        BASIC_DRILL = register("basic_drill", new BasicDrillItem(5, 1.4f, new CobaltItem.Settings().rarity(CobaltRarity.Good)));
        SAMPLE_DRILL = register("sample_drill", new SampleDrillItem(2, 2, new CobaltItem.Settings().rarity(CobaltRarity.Great)));
        GUARD_SWORD = register("guard_sword", new CobaltSwordItem(4, 1.6f, new CobaltItem.Settings().rarity(CobaltRarity.Good)));
        PROSPECTOR_PICKAXE = register("prospector_pickaxe", new CobaltSwordItem(4, 2.3f, new CobaltItem.Settings().rarity(CobaltRarity.Good)));
        SCREWDRIVER = register("screwdriver", new CobaltSwordItem(6, 2.3f, new CobaltItem.Settings().rarity(CobaltRarity.Great)));
        CROWBAR = register("crowbar", new CobaltSwordItem(7, 0.9f, new CobaltItem.Settings().rarity(CobaltRarity.Great)));
        RATCHETING_SCREWDRIVER = register("ratcheting_screwdriver", new CobaltSwordItem(7, 2.3f, new CobaltItem.Settings().rarity(CobaltRarity.Outstanding)));
        BIONIC_FIST = register("bionic_fist", new CobaltSwordItem(9, 1.6f, new CobaltItem.Settings().rarity(CobaltRarity.Outstanding)));
        ADVANCED_BIONIC_FIST = register("advanced_bionic_fist", new AdvancedBionicFistItem());
        FORGE_HAMMER = register("forge_hammer", new CobaltSwordItem(9, 1.6f, new CobaltItem.Settings().rarity(CobaltRarity.Perfect)));
        VOIDREND = register("voidrend", new VoidRendSwordItem());

        MINER_PICKAXE = register("miner_pickaxe", new CobaltPickaxeItem(9, 0.9f, new CobaltItem.Settings().rarity(CobaltRarity.Good)));

        HARPOON_GUN = register("harpoon_gun", new CobaltCrossbowItem(new CobaltItem.Settings().rarity(CobaltRarity.Great)));
        HUNTER_CROSSBOW = register("hunter_crossbow", new CobaltCrossbowItem(new CobaltItem.Settings().rarity(CobaltRarity.Good)));

        LUMBERJACK_AXE = register("lumberjack_axe", new CobaltAxeItem(7, 0.8f, new CobaltItem.Settings().rarity(CobaltRarity.Average)));

        HUNTER_GLOVE = register("hunter_gloves", new CobaltTrinketItem(
                new CobaltItem.Settings().rarity(CobaltRarity.Average), (modifiers, stack, slot, entity, uuid) -> {
                    modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "hunter_gloves.armor"), 1, EntityAttributeModifier.Operation.ADD_VALUE));
                    return modifiers;
                }));
        MECHANICAL_HAND = register("mechanical_hand", new CobaltTrinketItem(
                new CobaltItem.Settings().rarity(CobaltRarity.Great), (modifiers, stack, slot, entity, uuid) -> {
                    modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "mechanical_hand.damage"), 0.05, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                    return modifiers;
        }));
        MECHANIC_GLOVES = register("mechanic_gloves", new CobaltTrinketItem(
                new CobaltItem.Settings().rarity(CobaltRarity.Great),
                (modifiers, stack, slot, entity, uuid) -> {
                    modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "mechanic_gloves.move_speed"), -0.025, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                    modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "mechanic_gloves.damage"), 0.05, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                    modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "mechanic_gloves.armor"), 2, EntityAttributeModifier.Operation.ADD_VALUE));
                    modifiers.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "mechanic_gloves.toughness"), 1, EntityAttributeModifier.Operation.ADD_VALUE));
                    return modifiers;
                }));
        MECHANIC_SPECTACLES = register("mechanic_spectacles", new MechanicSpectaclesTrinket(new CobaltItem.Settings(), (modifiers, stack, slot, entity, uuid) -> modifiers));
        GEARSTRAP = register("gearstrap", new CobaltTrinketItem(
                new CobaltItem.Settings().rarity(CobaltRarity.Great),
                (modifiers, stack, slot, entity, uuid) -> {
                    modifiers.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "gearstrap.knockback_resistance"), .05f, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                    modifiers.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "gearstrap.health"), 10, EntityAttributeModifier.Operation.ADD_VALUE));
                    return modifiers;
                }));
        RUNE_GLOVE = register("rune_glove", new CobaltTrinketItem(
                new CobaltItem.Settings().rarity(CobaltRarity.Perfect),
                (modifiers, stack, slot, entity, uuid) -> {
                    modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of(MOD_NAMESPACE, "rune_glove.damage"), -0.25, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                    SlotAttributes.addSlotModifier(modifiers, "hand/rune", uuid, 5, EntityAttributeModifier.Operation.ADD_VALUE);
                    return modifiers;
                }
        ));
        HEALTH_RUNE = register("health_rune", new HealthRuneItem());
        HEAVY_RUNE = register("heavy_rune", new HeavyRuneItem());
        FAST_RUNE = register("fast_rune", new FastRuneItem());
        FIRE_RUNE = register("fire_rune", new FireRuneItem());
        ICE_RUNE = register("ice_rune", new IceRuneItem());
        LIGHTNING_RUNE = register("lightning_rune", new LightningRuneItem());
        THICK_RUNE = register("thick_rune", new ThickRuneItem());

        LIGHTNING_ARROW = register("lightning_arrow", new CobaltArrowItem(new CobaltItem.Settings().rarity(CobaltRarity.Great), LightningArrowEntity::new));
        EXPLOSIVE_ARROW = register("explosive_arrow", new CobaltArrowItem(new CobaltItem.Settings().rarity(CobaltRarity.Good), ExplosiveArrowEntity::new));

        PAINKILLERS = register("painkillers", new CobaltHealingItem(new CobaltItem.Settings().rarity(CobaltRarity.Average).maxCount(4), 5));
        BANDAGE = register("bandage", new CobaltHealingItem(new CobaltItem.Settings().rarity(CobaltRarity.Good).maxCount(4), 10));
        FIRST_AID_KIT = register("first_aid_kit", new CobaltHealingItem(new CobaltItem.Settings().rarity(CobaltRarity.Great).maxCount(2), 20));
        PNEUMATIC_NEEDLE = register("pneumatic_needle", new CobaltHealingItem(new CobaltItem.Settings().rarity(CobaltRarity.Outstanding).maxCount(1), 40));
        MYSTERY_MEDICINE = register("mystery_medicine", new MysteryMedicineItem(new CobaltItem.Settings().rarity(CobaltRarity.Great)));
        LIQUID_COURAGE = register("liquid_courage", new LiquidCourageItem(new CobaltItem.Settings().rarity(CobaltRarity.Good)));
        RUINED_GEAR = register("ruined_gear", new CobaltItem(new CobaltItem.Settings().rarity(CobaltRarity.Average)));
        TARNISHED_GEAR = register("tarnished_gear", new CobaltItem(new CobaltItem.Settings().rarity(CobaltRarity.Good)));
        AVERAGE_GEAR = register("average_gear", new CobaltItem(new CobaltItem.Settings().rarity(CobaltRarity.Great)));
        REMARKABLE_GEAR = register("remarkable_gear", new CobaltItem(new CobaltItem.Settings().rarity(CobaltRarity.Outstanding)));
        BATTERY = register("battery", new CobaltItem(new CobaltItem.Settings().rarity(CobaltRarity.Quest).maxCount(24)));
        CORRUPTED_PEARL = register("corrupted_pearl", new CorruptedPearlItem());
        WALKIE_TALKIE = register("walkie_talkie", new WalkieTalkieItem(9999));
        HAND_HELD_LANTERN = register("hand_held_lantern", new CobaltItem(new CobaltItem.Settings().rarity(CobaltRarity.Good)));
        RUNE_MODIFIER = register("rune_modifier", new CobaltItem(new CobaltItem.Settings()));
        FORGE_SIDE_CRYSTAL = register("forge_side_crystal", new CobaltItem(new CobaltItem.Settings().maxCount(1)));
        LIGHT_SOUL = register("light_soul", new CobaltItem(new CobaltItem.Settings().rarity(CobaltRarity.Quest).maxCount(1)));
        LENS = register("lens", new CobaltItem(new CobaltItem.Settings().rarity(CobaltRarity.Quest).maxCount(1)));
        RED_LENS = register("red_lens", new CobaltItem(new CobaltItem.Settings().rarity(CobaltRarity.Quest).maxCount(1)));
        GREEN_LENS = register("green_lens", new CobaltItem(new CobaltItem.Settings().rarity(CobaltRarity.Quest).maxCount(1)));
        BLUE_LENS = register("blue_lens", new CobaltItem(new CobaltItem.Settings().rarity(CobaltRarity.Quest).maxCount(1)));
        PRESSURE_GAUGE = register("pressure_gauge", new CobaltItem(new CobaltItem.Settings()));

        SMOKE_BOMB = register("smoke_bomb", new ThrownItem(new CobaltItem.Settings().rarity(CobaltRarity.Good).maxCount(4), SmokeBombEntity::new, SmokeBombEntity::new));
        DYNAMITE = register("dynamite", new ThrownItem(new CobaltItem.Settings().rarity(CobaltRarity.Great).maxCount(4), DynamiteEntity::new, DynamiteEntity::new));

        CORRUPTED_ZOMBIE_SPAWN_EGG = register("corrupted_zombie_spawn_egg", new SpawnEggItem(CobaltEntities.CORRUPTED_ZOMBIE, 44975, 3790560, new CobaltItem.Settings()));
        CORRUPTED_SKELETON_SPAWN_EGG = register("corrupted_skeleton_spawn_egg", new SpawnEggItem(CobaltEntities.CORRUPTED_SKELETON, 0xC1C1C1, 3790560, new CobaltItem.Settings()));
        CORRUPTED_SPIDER_SPAWN_EGG = register("corrupted_spider_spawn_egg", new SpawnEggItem(CobaltEntities.CORRUPTED_SPIDER, 3419431, 3790560, new CobaltItem.Settings()));
        AUTOMATON_SPAWN_EGG = register("automaton_spawn_egg", new SpawnEggItem(CobaltEntities.AUTOMATON, 0x909c3a, 0xcfd4a9, new CobaltItem.Settings()));
        RAT_SPAWN_EGG = register("rat_spawn_egg", new SpawnEggItem(CobaltEntities.RAT, 4996656, 986895, new CobaltItem.Settings()));

        MUSIC_DISC_ANCIENT_GATEWAY = register("music_disc_ancient_gateway", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(CobaltJukeboxSongs.ANCIENT_GETAWAY)));
        MUSIC_DISC_ERA_OF_PEACE = register("music_disc_era_of_peace", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(CobaltJukeboxSongs.ERA_OF_PEACE)));
        MUSIC_DISC_HYDROANGEA = register("music_disc_hydroangea", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(CobaltJukeboxSongs.HYDROANGEA)));
        MUSIC_DISC_OUTPOST = register("music_disc_outpost", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(CobaltJukeboxSongs.OUTPOST)));
        MUSIC_DISC_SHOOTING_FOR_THE_STARS = register("music_disc_shooting_for_the_stars", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(CobaltJukeboxSongs.SHOOTING_FOR_THE_STARS)));
        MUSIC_DISC_AKALI = register("music_disc_akali", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(CobaltJukeboxSongs.AKALI)));
        FLASHLIGHT = register("flashlight", new FlashlightItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(1)));
    }

    // -- REGISTER

    public static void register() {
        register("icon_item", ICON_ITEM);

        registerDispenserBlockBehaviour(LIGHTNING_ARROW);
        registerDispenserBlockBehaviour(EXPLOSIVE_ARROW);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            content.addAfter(Items.MUSIC_DISC_PIGSTEP, MUSIC_DISC_AKALI);
            content.addAfter(Items.MUSIC_DISC_PIGSTEP, MUSIC_DISC_ANCIENT_GATEWAY);
            content.addAfter(Items.MUSIC_DISC_PIGSTEP, MUSIC_DISC_ERA_OF_PEACE);
            content.addAfter(Items.MUSIC_DISC_PIGSTEP, MUSIC_DISC_HYDROANGEA);
            content.addAfter(Items.MUSIC_DISC_PIGSTEP, MUSIC_DISC_OUTPOST);
            content.addAfter(Items.MUSIC_DISC_PIGSTEP, MUSIC_DISC_SHOOTING_FOR_THE_STARS);
        });

        DispenserBlock.registerBehavior(SMOKE_BOMB, new ProjectileDispenserBehavior(SMOKE_BOMB));
    }

    private static Item register(String itemId, Item item) {
        Registry.register(Registries.ITEM, Identifier.of(MOD_NAMESPACE, itemId), item);

        // Add to appropriate item group depending on item type
        if (item instanceof SwordItem) ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(Items.NETHERITE_SWORD, item));
        else if (item instanceof AxeItem) ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(Items.NETHERITE_AXE, item));
        else if (item instanceof CrossbowItem) ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(Items.CROSSBOW, item));
        else if (item instanceof ArrowItem) ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(Items.TIPPED_ARROW, item));
        else if (item instanceof SpawnEggItem) ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> content.add(item));
        else if (item instanceof CobaltDrinkItem) ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> content.addAfter(Items.SPIDER_EYE, item));
        else if (item instanceof ThrownItem) ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(Items.EGG, item));
        else if (item instanceof TrinketItem) ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.add(item));

        ItemGroupEvents.modifyEntriesEvent(COBALT_GROUP_KEY).register(content -> content.add(item));

        return item;
    }

    private static CobaltArmorSet registerSet(String id, Item helmet, Item chestplate, Item leggings, Item boots, IItemSetMethods setBonus) {
        return new CobaltArmorSet.Builder(helmet, chestplate, leggings, boots)
                .setBonus(setBonus).register(id, CobaltItems::registerArmorItem);
    }

    private static CobaltArmorSet registerSet(String id, IArmorTemplate template) {
        return registerSet(id, template, null);
    }

    private static CobaltArmorSet registerSet(String id, IArmorTemplate template, IItemSetMethods itemSetMethods) {
        return new CobaltArmorSet.Builder(
                template.create(ArmorItem.Type.HELMET),
                template.create(ArmorItem.Type.CHESTPLATE),
                template.create(ArmorItem.Type.LEGGINGS),
                template.create(ArmorItem.Type.BOOTS)
        ).setBonus(itemSetMethods).register(id, CobaltItems::registerArmorItem);
    }

    private static CobaltArmorSet registerSet(String id, RegistryEntry<ArmorMaterial> material, CobaltRarity rarity, int damageMultiplier) {
        return registerSet(id, material, rarity, damageMultiplier, null);
    }

    private static CobaltArmorSet registerSet(String id, RegistryEntry<ArmorMaterial> material, CobaltRarity rarity, int damageMultiplier, IItemSetMethods setBonus) {
        return new CobaltArmorSet.Builder(
                new CobaltArmorItem(material, ArmorItem.Type.HELMET, (CobaltItem.Settings) new CobaltItem.Settings().rarity(rarity).maxDamage(ArmorItem.Type.HELMET.getMaxDamage(damageMultiplier))),
                new CobaltArmorItem(material, ArmorItem.Type.CHESTPLATE, (CobaltItem.Settings) new CobaltItem.Settings().rarity(rarity).maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(damageMultiplier))),
                new CobaltArmorItem(material, ArmorItem.Type.LEGGINGS, (CobaltItem.Settings) new CobaltItem.Settings().rarity(rarity).maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(damageMultiplier))),
                new CobaltArmorItem(material, ArmorItem.Type.BOOTS, (CobaltItem.Settings) new CobaltItem.Settings().rarity(rarity).maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(damageMultiplier)))
        ).register(id, CobaltItems::registerArmorItem);
    }

    private static Item registerArmorItem(String itemId, Item item) {
        Registry.register(Registries.ITEM, Identifier.of(MOD_NAMESPACE, itemId), item);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(Items.NETHERITE_BOOTS, item));
        ItemGroupEvents.modifyEntriesEvent(COBALT_GROUP_KEY).register(content -> content.add(item));
        return item;
    }

    private interface IArmorTemplate {
        CobaltArmorItem create(ArmorItem.Type type);
    }

    private static void registerDispenserBlockBehaviour(Item item) {
        DispenserBlock.registerBehavior(item, new ProjectileDispenserBehavior(item));
    }
}
