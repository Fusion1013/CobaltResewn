package se.fusion1013.items;

import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.TrinketItem;
import io.wispforest.lavender.book.LavenderBookItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ProjectileDispenserBehavior;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import se.fusion1013.entity.*;
import se.fusion1013.items.armor.CobaltArmorSet;
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

import static se.fusion1013.Main.MOD_NAMESPACE;
import static se.fusion1013.items.CustomItemGroupRegistry.*;

public class CobaltItems {


    public static final Item ICON_ITEM = new Item(new Item.Settings());

    public static final LavenderBookItem WF_INSTRUCTION_MANUAL;
    public static final LavenderBookItem ADVENTURER_JOURNAL;

    public static final CobaltArmorSet ADVENTURE_ARMOR_SET;
    public static final CobaltArmorSet DIVING_ARMOR_SET;
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
    public static final Item INFECTED_ADVENTURE_SWORD;
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
        WF_INSTRUCTION_MANUAL = LavenderBookItem.registerForBook(new Identifier("cobalt", "wf_instruction_manual"), new FabricItemSettings());
        ADVENTURER_JOURNAL = LavenderBookItem.registerForBook(new Identifier("cobalt", "adventure_journal"), new FabricItemSettings());

        ADVENTURE_ARMOR_SET = registerSet("adventure", new CobaltArmorSet.Builder(CobaltArmorMaterials.ADVENTURE, CobaltItemConfiguration.create(Formatting.DARK_GREEN)).withAll().build());
        DIVING_ARMOR_SET = registerSet("diving", new CobaltArmorSet.Builder(CobaltArmorMaterials.DIVE, CobaltItemConfiguration.create(Formatting.GOLD)).withAll().withHelmet(true).withSetBonus(new DivingArmorSet()).build());
        LUMBERJACK_ARMOR_SET = registerSet("lumberjack", new CobaltArmorSet.Builder(CobaltArmorMaterials.LUMBERJACK, CobaltItemConfiguration.create(Formatting.DARK_GREEN)).withAll().build());
        GUARD_ARMOR_SET = registerSet("guard", new CobaltArmorSet.Builder(CobaltArmorMaterials.GUARD, CobaltItemConfiguration.create(Formatting.GRAY)).withAll().build());
        HUNTER_ARMOR_SET = registerSet("hunter", new CobaltArmorSet.Builder(CobaltArmorMaterials.HUNTER, CobaltItemConfiguration.create(Formatting.GRAY)).withAll().build());
        MECHANIC_ARMOR_SET = registerSet("mechanic", new CobaltArmorSet.Builder(CobaltArmorMaterials.MECHANIC, CobaltItemConfiguration.create(Formatting.DARK_GRAY)).withAll().build());
        REINFORCED_MECHANIC_ARMOR_SET = registerSet("reinforced_mechanic", new CobaltArmorSet.Builder(CobaltArmorMaterials.REINFORCED_MECHANIC, CobaltItemConfiguration.create(Formatting.DARK_GRAY)).withAll().build());
        MINER_ARMOR_SET = registerSet("miner", new CobaltArmorSet.Builder(CobaltArmorMaterials.MINER, CobaltItemConfiguration.create(Formatting.DARK_GRAY)).withAll().withHelmet(true).build());
        PROSPECTOR_ARMOR_SET = registerSet("prospector", new CobaltArmorSet.Builder(CobaltArmorMaterials.PROSPECTOR, CobaltItemConfiguration.create(Formatting.GOLD)).withAll().withHelmet(true).build());
        TINKER_ARMOR_SET = registerSet("tinker", new CobaltArmorSet.Builder(CobaltArmorMaterials.TINKER, CobaltItemConfiguration.create(Formatting.GOLD)).withAll().build());
        REINFORCED_TINKER_ARMOR_SET = registerSet("reinforced_tinker", new CobaltArmorSet.Builder(CobaltArmorMaterials.REINFORCED_TINKER, CobaltItemConfiguration.create(Formatting.GOLD)).withAll().build());
        EXOSKELETON = registerSet("exoskeleton", new CobaltArmorSet.Builder(CobaltArmorMaterials.EXOSKELETON, CobaltItemConfiguration.create(Formatting.GOLD)).withAll().withSetBonus(new ExoskeletonArmorSet()).build());
        THERMAL_GEAR = registerSet("thermal_gear", new CobaltArmorSet.Builder(CobaltArmorMaterials.THERMAL_GEAR, CobaltItemConfiguration.create(Formatting.YELLOW)).withAll().withHelmet(true).withSetBonus(new ThermalGearArmorSet()).build());
        ADVANCED_EXOSKELETON = registerSet("advanced_exoskeleton", new CobaltArmorSet.Builder(CobaltArmorMaterials.ADVANCED_EXOSKELETON, CobaltItemConfiguration.create(Formatting.GOLD)).withAll().withSetBonus(new AdvancedExoskeletonArmorSet()).build());

        DAGGER = register("dagger", new CobaltSwordItem(ToolMaterials.STONE, -2+1, -4+3, CobaltItemConfiguration.create(Formatting.GRAY).attributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier("cobalt.dagger.attack_damage", .06, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.OFFHAND), new FabricItemSettings().maxCount(1)));
        ADVENTURE_SWORD = register("adventure_sword", new CobaltSwordItem(ToolMaterials.STONE, -2+3, -4+1.6f, CobaltItemConfiguration.create(Formatting.DARK_GREEN), new FabricItemSettings()));
        INFECTED_ADVENTURE_SWORD = register("infected_adventure_sword", new InfectedSwordItem(ToolMaterials.STONE, -2+4, -4+1.6f, new FabricItemSettings(), Formatting.DARK_PURPLE, 10, 60*20, ((world, user, hand) -> {
            user.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.PLAYERS, 1, 1);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20*60, 0));
        })));
        HEAVY_WRENCH = register("heavy_wrench", new CobaltSwordItem(ToolMaterials.STONE, -2+9, -4+1.0f, CobaltItemConfiguration.create(Formatting.DARK_GRAY).attributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("cobalt.heavy_wrench.speed", -.05, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.MAINHAND), new FabricItemSettings()));
        BASIC_DRILL = register("basic_drill", new BasicDrillItem(ToolMaterials.STONE, -2+5, -4+1.4f, new Item.Settings()));
        SAMPLE_DRILL = register("sample_drill", new SampleDrillItem(ToolMaterials.STONE, -2+2, -4+2, new FabricItemSettings(), Formatting.LIGHT_PURPLE));
        GUARD_SWORD = register("guard_sword", new CobaltSwordItem(ToolMaterials.STONE, -2+4, -4+1.6f, CobaltItemConfiguration.create(Formatting.DARK_GRAY), new FabricItemSettings()));
        PROSPECTOR_PICKAXE = register("prospector_pickaxe", new CobaltSwordItem(ToolMaterials.STONE, -2+4, -4+2.3f, CobaltItemConfiguration.create(Formatting.GOLD).attributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("cobalt.prospector_pickaxe.speed", 0.1f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.MAINHAND), new FabricItemSettings()));
        SCREWDRIVER = register("screwdriver", new CobaltSwordItem(ToolMaterials.STONE, -2+6, -4+2.3f, CobaltItemConfiguration.create(Formatting.GRAY).attributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("cobalt.screwdriver.speed", 0.1f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.MAINHAND), new FabricItemSettings()));
        CROWBAR = register("crowbar", new CobaltSwordItem(ToolMaterials.STONE, -2+7, -4+0.9f, CobaltItemConfiguration.create(Formatting.DARK_GRAY).attributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("cobalt.crowbar.speed", -.01, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.MAINHAND), new FabricItemSettings()));
        RATCHETING_SCREWDRIVER = register("ratcheting_screwdriver", new CobaltSwordItem(ToolMaterials.STONE, -2+7, -4+2.3f, CobaltItemConfiguration.create(Formatting.GRAY).attributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("cobalt.ratcheting_screwdriver.speed", 0.12f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.MAINHAND), new FabricItemSettings()));
        BIONIC_FIST = register("bionic_fist", new CobaltSwordItem(ToolMaterials.STONE, -2+9, -4+1.6f, CobaltItemConfiguration.create(Formatting.GOLD).attributeModifier(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, new EntityAttributeModifier("cobalt.bionic_fist.knockback", 1.6f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.MAINHAND), new FabricItemSettings()));
        ADVANCED_BIONIC_FIST = register("advanced_bionic_fist", new AdvancedBionicFistItem());
        FORGE_HAMMER = register("forge_hammer", new CobaltSwordItem(ToolMaterials.STONE, -2+9, -4+1.6f, CobaltItemConfiguration.create(Formatting.GOLD), new FabricItemSettings()));
        VOIDREND = register("voidrend", new VoidRendSwordItem());

        MINER_PICKAXE = register("miner_pickaxe", new CobaltPickaxeItem(ToolMaterials.STONE, -2+9, -4+0.9f, CobaltItemConfiguration.create(Formatting.DARK_GRAY).attributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("cobalt.miner_pickaxe.speed", -.025, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.MAINHAND), new FabricItemSettings()));

        HARPOON_GUN = register("harpoon_gun", new CobaltCrossbowItem(CobaltItemConfiguration.create(Formatting.GOLD), new FabricItemSettings()));
        HUNTER_CROSSBOW = register("hunter_crossbow", new CobaltCrossbowItem(CobaltItemConfiguration.create(Formatting.GRAY), new FabricItemSettings()));

        LUMBERJACK_AXE = register("lumberjack_axe", new CobaltAxeItem(ToolMaterials.STONE, -2+7, -4+0.8f, CobaltItemConfiguration.create(Formatting.DARK_GREEN).attributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("cobalt.lumberjack.axe", -.015, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.MAINHAND), new FabricItemSettings()));

        HUNTER_GLOVE = register("hunter_gloves", new CobaltTrinketItem(
                new FabricItemSettings(),
                new CobaltItemConfiguration()
                        .nameFormatting(Formatting.GRAY),
                (modifiers, stack, slot, entity, uuid) -> {
                    modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier("cobalt.hunter_glove.armor", 1, EntityAttributeModifier.Operation.ADDITION));
                    return modifiers;
                }));
        MECHANICAL_HAND = register("mechanical_hand", new CobaltTrinketItem(
                new Item.Settings(),
                new CobaltItemConfiguration()
                        .nameFormatting(Formatting.DARK_GRAY),
                (modifiers, stack, slot, entity, uuid) -> {
                    modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(uuid, "cobalt.mechanical_hand.attack_damage", .05f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
                    return modifiers;
                }));
        MECHANIC_GLOVES = register("mechanic_gloves", new CobaltTrinketItem(
                new FabricItemSettings(),
                new CobaltItemConfiguration()
                        .nameFormatting(Formatting.DARK_GRAY),
                (modifiers, stack, slot, entity, uuid) -> {
                    modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("cobalt.mechanic_gloves.move_speed", -0.025, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
                    modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier("cobalt.mechanic_gloves.damage", 0.05, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
                    modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier("cobalt.mechanic_gloves.armor", 2, EntityAttributeModifier.Operation.ADDITION));
                    modifiers.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier("cobalt.mechanic_gloves.toughness", 1, EntityAttributeModifier.Operation.ADDITION));
                    return modifiers;
                }));
        MECHANIC_SPECTACLES = register("mechanic_spectacles", new MechanicSpectaclesTrinket(
                new FabricItemSettings(),
                new CobaltItemConfiguration()
                        .nameFormatting(Formatting.DARK_GRAY),
                (modifiers, stack, slot, entity, uuid) -> modifiers));
        GEARSTRAP = register("gearstrap", new CobaltTrinketItem(
                new Item.Settings(),
                new CobaltItemConfiguration()
                        .nameFormatting(Formatting.GOLD),
                (modifiers, stack, slot, entity, uuid) -> {
                    modifiers.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(uuid, "cobalt:knockback_resistance", .05f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
                    modifiers.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(uuid, "cobalt:health", 10, EntityAttributeModifier.Operation.ADDITION));
                    return modifiers;
                }));
        RUNE_GLOVE = register("rune_glove", new CobaltTrinketItem(
                new Item.Settings(),
                new CobaltItemConfiguration()
                        .nameFormatting(Formatting.GOLD),
                (modifiers, stack, slot, entity, uuid) -> {
                    modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(uuid, "cobalt.rune_glove.damage", -0.25, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
                    SlotAttributes.addSlotModifier(modifiers, "hand/rune", uuid, 5, EntityAttributeModifier.Operation.ADDITION);
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

        LIGHTNING_ARROW = register("lightning_arrow", new CobaltArrowItem(CobaltItemConfiguration.create(Formatting.DARK_AQUA), new FabricItemSettings(), LightningArrowEntity::new));
        EXPLOSIVE_ARROW = register("explosive_arrow", new CobaltArrowItem(CobaltItemConfiguration.create(Formatting.DARK_AQUA), new FabricItemSettings(), ExplosiveArrowEntity::new));

        PAINKILLERS = register("painkillers", new CobaltHealingItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(4), 5));
        BANDAGE = register("bandage", new CobaltHealingItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(4), 10));
        FIRST_AID_KIT = register("first_aid_kit", new CobaltHealingItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(2), 20));
        PNEUMATIC_NEEDLE = register("pneumatic_needle", new CobaltHealingItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(1), 40));
        MYSTERY_MEDICINE = register("mystery_medicine", new MysteryMedicineItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings()));
        LIQUID_COURAGE = register("liquid_courage", new LiquidCourageItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings()));
        RUINED_GEAR = register("ruined_gear", new CobaltItem(CobaltItemConfiguration.create(Formatting.GRAY), new FabricItemSettings()));
        TARNISHED_GEAR = register("tarnished_gear", new CobaltItem(CobaltItemConfiguration.create(Formatting.GRAY), new FabricItemSettings()));
        AVERAGE_GEAR = register("average_gear", new CobaltItem(CobaltItemConfiguration.create(Formatting.GRAY), new FabricItemSettings()));
        REMARKABLE_GEAR = register("remarkable_gear", new CobaltItem(CobaltItemConfiguration.create(Formatting.GRAY), new FabricItemSettings()));
        BATTERY = register("battery", new CobaltItem(CobaltItemConfiguration.create(Formatting.DARK_AQUA), new FabricItemSettings().maxCount(24)));
        CORRUPTED_PEARL = register("corrupted_pearl", new CorruptedPearlItem());
        WALKIE_TALKIE = register("walkie_talkie", new WalkieTalkieItem(9999));
        HAND_HELD_LANTERN = register("hand_held_lantern", new CobaltItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings()));
        RUNE_MODIFIER = register("rune_modifier", new CobaltItem(CobaltItemConfiguration.create(Formatting.DARK_PURPLE), new FabricItemSettings()));
        FORGE_SIDE_CRYSTAL = register("forge_side_crystal", new CobaltItem(CobaltItemConfiguration.create(Formatting.LIGHT_PURPLE), new FabricItemSettings().maxCount(1)));
        LIGHT_SOUL = register("light_soul", new CobaltItem(CobaltItemConfiguration.create(Formatting.AQUA), new FabricItemSettings().maxCount(1)));
        LENS = register("lens", new CobaltItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(1)));
        RED_LENS = register("red_lens", new CobaltItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(1)));
        GREEN_LENS = register("green_lens", new CobaltItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(1)));
        BLUE_LENS = register("blue_lens", new CobaltItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(1)));
        PRESSURE_GAUGE = register("pressure_gauge", new CobaltItem(CobaltItemConfiguration.create(Formatting.GOLD), new FabricItemSettings()));

        SMOKE_BOMB = register("smoke_bomb", new ThrownItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(4), SmokeBombEntity::new));
        DYNAMITE = register("dynamite", new ThrownItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(4), DynamiteEntity::new));

        FLASHLIGHT = register("flashlight", new FlashlightItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(1)));
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
    }

    // -- REGISTER

    public static void register() {
        register("icon_item", ICON_ITEM);

        registerDispenserBlockBehaviour(LIGHTNING_ARROW);
        registerDispenserBlockBehaviour(EXPLOSIVE_ARROW);

        DispenserBlock.registerBehavior(SMOKE_BOMB, new ProjectileDispenserBehavior() {
            @Override
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                return new SmokeBombEntity(world, position.getX(), position.getY(), position.getZ());
            }
        });

        // Add spawn eggs
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
            content.add(CORRUPTED_ZOMBIE_SPAWN_EGG);
            content.add(CORRUPTED_SKELETON_SPAWN_EGG);
            content.add(CORRUPTED_SPIDER_SPAWN_EGG);
            content.add(AUTOMATON_SPAWN_EGG);
            content.add(RAT_SPAWN_EGG);
        });
    }

    private static Item register(String itemId, Item item) {
        Registry.register(Registries.ITEM, new Identifier(MOD_NAMESPACE, itemId), item);

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

    private static CobaltArmorSet registerSet(String setId, CobaltArmorSet set) {
        // Register the armor set
        set.register(setId, CobaltItems::register);

        // Add to vanilla combat item group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.addAfter(Items.TURTLE_HELMET, set.registeredHelmet);
            content.addAfter(Items.TURTLE_HELMET, set.registeredChestplate);
            content.addAfter(Items.TURTLE_HELMET, set.registeredLeggings);
            content.addAfter(Items.TURTLE_HELMET, set.registeredBoots);
        });

        return set;
    }

    private static void registerDispenserBlockBehaviour(Item item) {
        DispenserBlock.registerBehavior(item, new ProjectileDispenserBehavior() {
            @Override
            protected ProjectileEntity createProjectile(World world, Position position, ItemStack stack) {
                if (item instanceof CobaltArrowItem cobaltArrowItem) {
                    var arrowEntity = cobaltArrowItem.getEntityFactory().create(position.getX(), position.getY(), position.getZ(), world, stack);
                    arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
                    return arrowEntity;
                }

                return null;
            }
        });
    }
}
