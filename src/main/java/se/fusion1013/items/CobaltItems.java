package se.fusion1013.items;

import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import io.wispforest.lavender.book.LavenderBookItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.datafixer.fix.StatusEffectFix;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Arm;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.Main;
import se.fusion1013.effect.CobaltEffects;
import se.fusion1013.entity.ExplosiveArrowEntity;
import se.fusion1013.entity.LightningArrowEntity;
import se.fusion1013.items.armor.CobaltArmorItem;
import se.fusion1013.items.armor.CobaltArmorSet;
import se.fusion1013.items.consumable.CobaltHealingItem;
import se.fusion1013.items.consumable.LiquidCourageItem;
import se.fusion1013.items.consumable.MysteryMedicineItem;
import se.fusion1013.items.crossbow.CobaltCrossbowItem;
import se.fusion1013.items.materials.CobaltArmorMaterial;
import se.fusion1013.items.misc.CorruptedPearlItem;
import se.fusion1013.items.misc.WalkieTalkieItem;
import se.fusion1013.items.sword.InfectedSwordItem;
import se.fusion1013.items.sword.SampleDrillItem;
import se.fusion1013.items.tools.BasicDrillItem;
import se.fusion1013.items.tools.CobaltAxeItem;
import se.fusion1013.items.sword.CobaltSwordItem;
import se.fusion1013.items.tools.CobaltPickaxeItem;
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
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import se.fusion1013.items.materials.CobaltArmorMaterials;
import se.fusion1013.items.tools.FlashlightItem;
import se.fusion1013.items.trinket.CobaltTrinketItem;
import se.fusion1013.items.trinket.MechanicSpectaclesTrinket;
import se.fusion1013.util.item.ItemSetUtil;

import java.text.Format;
import java.util.List;

import static se.fusion1013.Main.MOD_NAMESPACE;
import static se.fusion1013.items.CustomItemGroupRegistry.*;

public class CobaltItems {


    public static final Item ICON_ITEM = new Item(new Item.Settings());
    public static final LavenderBookItem WF_INSTRUCTION_MANUAL = LavenderBookItem.registerForBook(new Identifier("cobalt", "wf_instruction_manual"), new FabricItemSettings());

    // --- ITEMS

    public static class ArmorItems {

        public static void registerAll() {}

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

        // Outstanding Tier
        public static final CobaltArmorSet EXOSKELETON;
        public static final CobaltArmorSet THERMAL_GEAR;

        // Perfect Tier
        public static final CobaltArmorSet ADVANCED_EXOSKELETON;


        static {
            ADVENTURE_ARMOR_SET = registerSet("adventure", new CobaltArmorSet.Builder(CobaltArmorMaterials.ADVENTURE, CobaltItemConfiguration.create(Formatting.DARK_GREEN)).withAll().build());

            DIVING_ARMOR_SET = registerSet("diving", new CobaltArmorSet.Builder(CobaltArmorMaterials.DIVE, CobaltItemConfiguration.create(Formatting.GOLD)).withAll().withHelmet(true)
                    .withSetBonus(new IItemSetMethods() {
                        @Override
                        public StatusEffectInstance[] withActiveEffects() {
                            return new StatusEffectInstance[] {
                                    new StatusEffectInstance(StatusEffects.WATER_BREATHING, 20, 0),
                                    new StatusEffectInstance(CobaltEffects.COLD_RESISTANCE_EFFECT, 20, 0)
                            };
                        }
                        @Override
                        public String[] appendTooltipStrings() { return new String[] { "item_set.cobalt.diving_armor.tooltip.breathing", "item_set.cobalt.diving_armor.tooltip.cold" }; }
                    })
                    .build());

            LUMBERJACK_ARMOR_SET = registerSet("lumberjack", new CobaltArmorSet.Builder(CobaltArmorMaterials.LUMBERJACK, CobaltItemConfiguration.create(Formatting.DARK_GREEN)).withAll().build());
            GUARD_ARMOR_SET = registerSet("guard", new CobaltArmorSet.Builder(CobaltArmorMaterials.GUARD, CobaltItemConfiguration.create(Formatting.GRAY)).withAll().build());
            HUNTER_ARMOR_SET = registerSet("hunter", new CobaltArmorSet.Builder(CobaltArmorMaterials.HUNTER, CobaltItemConfiguration.create(Formatting.GRAY)).withAll().build());
            MECHANIC_ARMOR_SET = registerSet("mechanic", new CobaltArmorSet.Builder(CobaltArmorMaterials.MECHANIC, CobaltItemConfiguration.create(Formatting.DARK_GRAY)).withAll().build());
            REINFORCED_MECHANIC_ARMOR_SET = registerSet("reinforced_mechanic", new CobaltArmorSet.Builder(CobaltArmorMaterials.REINFORCED_MECHANIC, CobaltItemConfiguration.create(Formatting.DARK_GRAY)).withAll().build());
            MINER_ARMOR_SET = registerSet("miner", new CobaltArmorSet.Builder(CobaltArmorMaterials.MINER, CobaltItemConfiguration.create(Formatting.DARK_GRAY)).withAll().withHelmet(true).build());
            PROSPECTOR_ARMOR_SET = registerSet("prospector", new CobaltArmorSet.Builder(CobaltArmorMaterials.PROSPECTOR, CobaltItemConfiguration.create(Formatting.GOLD)).withAll().withHelmet(true).build());
            TINKER_ARMOR_SET = registerSet("tinker", new CobaltArmorSet.Builder(CobaltArmorMaterials.TINKER, CobaltItemConfiguration.create(Formatting.GOLD)).withAll().build());
            REINFORCED_TINKER_ARMOR_SET = registerSet("reinforced_tinker", new CobaltArmorSet.Builder(CobaltArmorMaterials.REINFORCED_TINKER, CobaltItemConfiguration.create(Formatting.GOLD)).withAll().build());

            // Outstanding Tier
            EXOSKELETON = registerSet("exoskeleton", new CobaltArmorSet.Builder(CobaltArmorMaterials.EXOSKELETON, CobaltItemConfiguration.create(Formatting.GOLD)).withAll()
                    .withSetBonus(new IItemSetMethods() {
                        @Override
                        public void triggerSetAbility(Entity entity) {
                            IItemSetMethods.super.triggerSetAbility(entity);
                            if (entity instanceof PlayerEntity playerEntity) {
                                if (playerEntity.getInventory().containsAny(itemStack -> itemStack.getItem() == Items.COAL) && !playerEntity.hasStatusEffect(CobaltEffects.IMMOVABLE_EFFECT)) {
                                    // Remove coal
                                    playerEntity.getInventory().remove(itemStack -> itemStack.getItem() == Items.COAL, 2, playerEntity.getInventory());
                                    playerEntity.playSound(SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 1, 1);

                                    // Add status effects
                                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10*20, 1));
                                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 10*20, 1));
                                    playerEntity.addStatusEffect(new StatusEffectInstance(CobaltEffects.IMMOVABLE_EFFECT, 10*20, 0));
                                }
                            }
                        }
                        @Override
                        public String[] setAbilityTooltipString() {
                            return new String[] { "item.cobalt.exoskeleton.trigger_ability.tooltip" };
                        }
                    })
                    .build());
            THERMAL_GEAR = registerSet("thermal_gear", new CobaltArmorSet.Builder(CobaltArmorMaterials.THERMAL_GEAR, CobaltItemConfiguration.create(Formatting.YELLOW)).withAll().withHelmet(true)
                    .withSetBonus(new IItemSetMethods() {
                        @Override
                        public StatusEffectInstance[] withActiveEffects() {
                            return new StatusEffectInstance[] { new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 20, 0) };
                        }
                        @Override
                        public String[] appendTooltipStrings() {
                            return new String[] { "item.cobalt.thermal_gear_set_bonus.tooltip" };
                        }
                    })
                    .build());

            // Perfect Tier
            ADVANCED_EXOSKELETON = registerSet("advanced_exoskeleton", new CobaltArmorSet.Builder(CobaltArmorMaterials.ADVANCED_EXOSKELETON, CobaltItemConfiguration.create(Formatting.GOLD)).withAll()
                    .withSetBonus(new IItemSetMethods() {
                        @Override
                        public void triggerSetAbility(Entity entity) {
                            if (entity instanceof PlayerEntity playerEntity) {
                                if (playerEntity.getInventory().containsAny(itemStack -> itemStack.getItem() == Items.COAL) && !playerEntity.hasStatusEffect(CobaltEffects.IMMOVABLE_EFFECT)) {
                                    // Remove coal
                                    playerEntity.getInventory().remove(itemStack -> itemStack.getItem() == Items.COAL, 4, playerEntity.getInventory());
                                    playerEntity.playSound(SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 1, 1);

                                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10*20, 2));
                                    playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 10*20, 2));
                                    playerEntity.addStatusEffect(new StatusEffectInstance(CobaltEffects.IMMOVABLE_EFFECT, 10*20, 0));
                                }
                            }
                        }
                        @Override
                        public String[] setAbilityTooltipString() {
                            return new String[] { "item.cobalt.advanced_exoskeleton.trigger_ability.tooltip" };
                        }
                    })
                    .build());
        }

    }

    public static class SwordItems {

        public static void registerAll() {}

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
        public static final Item FORGE_HAMMER;

        static {
            DAGGER = register("dagger", new CobaltSwordItem(ToolMaterials.STONE, -2+1, -4+3,
                    CobaltItemConfiguration.create(Formatting.GRAY)
                            .attributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier("cobalt.dagger.attack_damage", .06, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.OFFHAND),
                    new FabricItemSettings().maxCount(1)));
            ADVENTURE_SWORD = register("adventure_sword", new CobaltSwordItem(ToolMaterials.STONE, -2+3, -4+1.6f, CobaltItemConfiguration.create(Formatting.DARK_GREEN), new FabricItemSettings()));
            INFECTED_ADVENTURE_SWORD = register("infected_adventure_sword", new InfectedSwordItem(ToolMaterials.STONE, -2+4, -4+1.6f, new FabricItemSettings(), Formatting.DARK_PURPLE, 10, 60*20, ((world, user, hand) -> {
                user.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.PLAYERS, 1, 1);
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20*60, 0));
            })));
            HEAVY_WRENCH = register("heavy_wrench", new CobaltSwordItem(ToolMaterials.STONE, -2+9, -4+1.0f, CobaltItemConfiguration.create(Formatting.DARK_GRAY).attributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("cobalt.heavy_wrench.speed", -.05, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.MAINHAND), new FabricItemSettings()));
            BASIC_DRILL = register("basic_drill", new BasicDrillItem(ToolMaterials.STONE, -2+5, -4+1.4f, new Item.Settings()));
            SAMPLE_DRILL = register("sample_drill", new SampleDrillItem(ToolMaterials.STONE, -2+2, -4+2, new FabricItemSettings(), Formatting.LIGHT_PURPLE));
            GUARD_SWORD = register("guard_sword", new CobaltSwordItem(ToolMaterials.STONE, -2+4, -4+1.6f, CobaltItemConfiguration.create(Formatting.DARK_GRAY), new FabricItemSettings()));
            PROSPECTOR_PICKAXE = register("prospector_pickaxe", new CobaltSwordItem(ToolMaterials.STONE, -2+4, -4+2.3f, CobaltItemConfiguration.create(Formatting.GOLD)
                    .attributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("cobalt.prospector_pickaxe.speed", 0.1f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.MAINHAND), new FabricItemSettings()));
            SCREWDRIVER = register("screwdriver", new CobaltSwordItem(ToolMaterials.STONE, -2+6, -4+2.3f, CobaltItemConfiguration.create(Formatting.GRAY)
                    .attributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("cobalt.screwdriver.speed", 0.1f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.MAINHAND), new FabricItemSettings()));
            CROWBAR = register("crowbar", new CobaltSwordItem(ToolMaterials.STONE, -2+7, -4+0.9f, CobaltItemConfiguration.create(Formatting.DARK_GRAY).attributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("cobalt.crowbar.speed", -.01, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.MAINHAND), new FabricItemSettings()));
            RATCHETING_SCREWDRIVER = register("ratcheting_screwdriver", new CobaltSwordItem(ToolMaterials.STONE, -2+7, -4+2.3f, CobaltItemConfiguration.create(Formatting.GRAY)
                    .attributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("cobalt.ratcheting_screwdriver.speed", 0.12f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.MAINHAND), new FabricItemSettings()));
            BIONIC_FIST = register("bionic_fist", new CobaltSwordItem(ToolMaterials.STONE, -2+9, -4+1.6f, CobaltItemConfiguration.create(Formatting.GOLD)
                    .attributeModifier(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, new EntityAttributeModifier("cobalt.bionic_fist.knockback", 1.6f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.MAINHAND), new FabricItemSettings()));
            FORGE_HAMMER = register("forge_hammer", new CobaltSwordItem(ToolMaterials.STONE, -2+9, -4+1.6f, CobaltItemConfiguration.create(Formatting.GOLD), new FabricItemSettings()));
        }

    }

    public static class PickaxeItems {

        public static void registerAll() {}

        public static final Item MINER_PICKAXE;

        static {
            MINER_PICKAXE = register("miner_pickaxe", new CobaltPickaxeItem(ToolMaterials.STONE, -2+9, -4+0.9f, CobaltItemConfiguration.create(Formatting.DARK_GRAY)
                    .attributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("cobalt.miner_pickaxe.speed", -.025, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.MAINHAND), new FabricItemSettings()));
        }
    }

    public static class CrossbowItems {

        public static void registerAll() {}

        public static final Item HARPOON_GUN;
        public static final Item HUNTER_CROSSBOW;

        static {
            HARPOON_GUN = register("harpoon_gun", new CobaltCrossbowItem(CobaltItemConfiguration.create(Formatting.GOLD), new FabricItemSettings()));
            HUNTER_CROSSBOW = register("hunter_crossbow", new CobaltCrossbowItem(CobaltItemConfiguration.create(Formatting.GRAY), new FabricItemSettings()));
        }

    }

    public static class AxeItems {

        public static void registerAll() {}

        public static final Item LUMBERJACK_AXE;

        static {
            LUMBERJACK_AXE = register("lumberjack_axe", new CobaltAxeItem(ToolMaterials.STONE, -2+7, -4+0.8f, CobaltItemConfiguration.create(Formatting.DARK_GREEN)
                    .attributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("cobalt.lumberjack.axe", -.015, EntityAttributeModifier.Operation.MULTIPLY_TOTAL), EquipmentSlot.MAINHAND), new FabricItemSettings()));
        }

    }

    public static class TrinketItems {

        public static void registerAll() {}

        public static final Item HUNTER_GLOVE;
        public static final Item MECHANICAL_HAND;
        public static final Item MECHANIC_GLOVES;
        public static final Item MECHANIC_SPECTACLES;
        public static final Item GEARSTRAP;

        public static final Item RUNE_GLOVE;
        public static final Item HEALTH_RUNE;
        public static final Item HEAVY_RUNE;

        public static final Item FIRE_RUNE;

        static {
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

            HEALTH_RUNE = register("health_rune", new CobaltTrinketItem(
                    new Item.Settings(),
                    new CobaltItemConfiguration()
                            .nameFormatting(Formatting.RED),
                    (modifiers, stack, slot, entity, uuid) -> {
                        modifiers.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(uuid, "cobalt.health_rune.health", 0.1f, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
                        return modifiers;
                    }
            ));
            HEAVY_RUNE = register("heavy_rune", new CobaltTrinketItem(
                    new Item.Settings(),
                    new CobaltItemConfiguration()
                            .nameFormatting(Formatting.GRAY),
                    (modifiers, stack, slot, entity, uuid) -> {
                        modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uuid, "cobalt.heavy_rune.armor", 2, EntityAttributeModifier.Operation.ADDITION));
                        return modifiers;
                    }
            ));

            FIRE_RUNE = register("fire_rune", new CobaltTrinketItem(
                    new Item.Settings(),
                    new CobaltItemConfiguration()
                            .nameFormatting(Formatting.RED),
                    (modifiers, stack, slot, entity, uuid) -> modifiers
            ));
        }

    }

    public static class ArrowItems {

        public static void registerAll() {}

        public static final Item LIGHTNING_ARROW;
        public static final Item EXPLOSIVE_ARROW;

        static {
            LIGHTNING_ARROW = register("lightning_arrow", new CobaltArrowItem(CobaltItemConfiguration.create(Formatting.DARK_AQUA), new FabricItemSettings(), LightningArrowEntity::new));
            EXPLOSIVE_ARROW = register("explosive_arrow", new CobaltArrowItem(CobaltItemConfiguration.create(Formatting.DARK_AQUA), new FabricItemSettings(), ExplosiveArrowEntity::new));
        }

    }

    public static class MiscItems {

        public static void registerAll() {}

        // Consumables
        public static final Item PAINKILLERS;
        public static final Item BANDAGE;
        public static final Item FIRST_AID_KIT;
        public static final Item PNEUMATIC_NEEDLE;
        public static final Item MYSTERY_MEDICINE;
        public static final Item LIQUID_COURAGE;

        // Gears
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

        public static final Item FLASHLIGHT;

        static {
            // Healing
            PAINKILLERS = register("painkillers", new CobaltHealingItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(4), 5));
            BANDAGE = register("bandage", new CobaltHealingItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(4), 10));
            FIRST_AID_KIT = register("first_aid_kit", new CobaltHealingItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(2), 20));
            PNEUMATIC_NEEDLE = register("pneumatic_needle", new CobaltHealingItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(1), 40));
            MYSTERY_MEDICINE = register("mystery_medicine", new MysteryMedicineItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings()));
            LIQUID_COURAGE = register("liquid_courage", new LiquidCourageItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings()));

            // Gears
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

            FLASHLIGHT = register("flashlight", new FlashlightItem(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(1)));
        }

    }

    public static final Item PRESSURE_GAUGE = register("pressure_gauge", new CobaltItem(CobaltItemConfiguration.create(Formatting.GOLD), new FabricItemSettings()));

    // --- ITEM SETS

    public static class ItemSets {

        // Runes
        public static final ItemSet FIRE_RUNE_HEALTH;
        public static final ItemSet FIRE_RUNE_HEAVY;

        static {
            FIRE_RUNE_HEALTH = ItemSet.register(new Identifier("fire_rune_health"), new ItemSet.ItemSetItem[]{
                    new ItemSet.ItemSetItem(TrinketItems.HEALTH_RUNE, ItemSet.ItemLocation.Trinket, false),
                    new ItemSet.ItemSetItem(TrinketItems.FIRE_RUNE, ItemSet.ItemLocation.Trinket)
            }, new IItemSetMethods() {
                @Override
                public void trinketTick(ItemStack stack, SlotReference slot, LivingEntity entity) {
                    IItemSetMethods.super.trinketTick(stack, slot, entity);
                    if (!entity.hasStatusEffect(StatusEffects.REGENERATION)) ItemSetUtil.addSetBonusStatusEffect(entity, new StatusEffectInstance(StatusEffects.REGENERATION, 60, 0));
                }
                @Override
                public Text[] appendTooltipText() {
                    return new Text[] {
                            Text.translatable("item_set.cobalt.fire_rune_health.tooltip.header").formatted(Formatting.GRAY),
                            Text.translatable("item_set.cobalt.fire_rune_health.tooltip").formatted(Formatting.GRAY)
                    };
                }
            });

            FIRE_RUNE_HEAVY = ItemSet.register(new Identifier("fire_rune_heavy"), new ItemSet.ItemSetItem[]{
                    new ItemSet.ItemSetItem(TrinketItems.HEAVY_RUNE, ItemSet.ItemLocation.Trinket, false),
                    new ItemSet.ItemSetItem(TrinketItems.FIRE_RUNE, ItemSet.ItemLocation.Trinket)
            }, new IItemSetMethods() {
                @Override
                public void trinketTick(ItemStack stack, SlotReference slot, LivingEntity entity) {
                    IItemSetMethods.super.trinketTick(stack, slot, entity);
                    ItemSetUtil.addSetBonusStatusEffect(entity, new StatusEffectInstance(StatusEffects.RESISTANCE, 20, 0));
                }
                @Override
                public Text[] appendTooltipText() {
                    return new Text[] {
                            Text.translatable("item_set.cobalt.fire_rune_heavy.tooltip.header").formatted(Formatting.GRAY),
                            Text.translatable("item_set.cobalt.fire_rune_heavy.tooltip").formatted(Formatting.GRAY)
                    };
                }
            });
        }

        public static void registerAll() {}

    }

    // -- REGISTER

    public static void register() {
        register("icon_item", ICON_ITEM);

        ArmorItems.registerAll();
        SwordItems.registerAll();
        PickaxeItems.registerAll();
        CrossbowItems.registerAll();
        AxeItems.registerAll();
        TrinketItems.registerAll();
        ArrowItems.registerAll();
        MiscItems.registerAll();

        ItemSets.registerAll();

        registerDispenserBlockBehaviour(ArrowItems.LIGHTNING_ARROW);
        registerDispenserBlockBehaviour(ArrowItems.EXPLOSIVE_ARROW);

        // Weapon group
        ItemGroupEvents.modifyEntriesEvent(COBALT_WEAPON_GROUP_KEY).register(content -> {
            content.add(SwordItems.ADVENTURE_SWORD);
            content.add(CrossbowItems.HARPOON_GUN);
            content.add(AxeItems.LUMBERJACK_AXE);
            content.add(SwordItems.INFECTED_ADVENTURE_SWORD);
            content.add(CrossbowItems.HUNTER_CROSSBOW);
            content.add(SwordItems.HEAVY_WRENCH);
            content.add(PickaxeItems.MINER_PICKAXE);
            content.add(SwordItems.PROSPECTOR_PICKAXE);
            content.add(SwordItems.SAMPLE_DRILL);
            content.add(SwordItems.BASIC_DRILL);
            content.add(SwordItems.GUARD_SWORD);
            content.add(SwordItems.DAGGER);
            content.add(SwordItems.SCREWDRIVER);
        });

        // Trinket group
        ItemGroupEvents.modifyEntriesEvent(COBALT_TRINKET_GROUP_KEY).register(content -> {
            content.add(TrinketItems.HUNTER_GLOVE);
            content.add(TrinketItems.MECHANICAL_HAND);
            content.add(TrinketItems.MECHANIC_GLOVES);
            content.add(TrinketItems.MECHANIC_SPECTACLES);
            content.add(TrinketItems.GEARSTRAP);
        });
    }

    private static Item register(String itemId, Item item) {
        Registry.register(Registries.ITEM, new Identifier(MOD_NAMESPACE, itemId), item);
        ItemGroupEvents.modifyEntriesEvent(COBALT_GROUP_KEY).register(content -> {
            content.add(item);
        });
        return item;
    }

    private static CobaltArmorSet registerSet(String setId, CobaltArmorSet set) {
        // Register the armor set
        set.register(setId, CobaltItems::register);

        // Add to armor item group
        ItemGroupEvents.modifyEntriesEvent(COBALT_ARMOR_GROUP_KEY).register(content -> {
            content.add(set.registeredHelmet);
            content.add(set.registeredChestplate);
            content.add(set.registeredLeggings);
            content.add(set.registeredBoots);
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
