package se.fusion1013.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;

import java.util.function.ToIntFunction;

import static se.fusion1013.items.CustomItemGroupRegistry.COBALT_BLOCK_GROUP_KEY;
import static se.fusion1013.items.CustomItemGroupRegistry.COBALT_GROUP_KEY;

/**
 * Handles registering custom {@link Block}s.
 */
public class CobaltBlocks {

    // -- Chiseled Copper Blocks
    public static final Block WHITE_CHISELED_COPPER =                register("white_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block LIGHT_GRAY_CHISELED_COPPER =           register("light_gray_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block GRAY_CHISELED_COPPER =                 register("gray_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block BLACK_CHISELED_COPPER =                register("black_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block BROWN_CHISELED_COPPER =                register("brown_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block RED_CHISELED_COPPER =                  register("red_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block ORANGE_CHISELED_COPPER =               register("orange_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block YELLOW_CHISELED_COPPER =               register("yellow_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block LIME_CHISELED_COPPER =                 register("lime_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block GREEN_CHISELED_COPPER =                register("green_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block CYAN_CHISELED_COPPER =                 register("cyan_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block LIGHT_BLUE_CHISELED_COPPER =           register("light_blue_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block BLUE_CHISELED_COPPER =                 register("blue_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block PURPLE_CHISELED_COPPER =               register("purple_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block MAGENTA_CHISELED_COPPER =              register("magenta_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block PINK_CHISELED_COPPER =                 register("pink_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));

    public static final Block EXPOSED_WHITE_CHISELED_COPPER =        register("exposed_white_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block EXPOSED_LIGHT_GRAY_CHISELED_COPPER =   register("exposed_light_gray_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block EXPOSED_GRAY_CHISELED_COPPER =         register("exposed_gray_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block EXPOSED_BLACK_CHISELED_COPPER =        register("exposed_black_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block EXPOSED_BROWN_CHISELED_COPPER =        register("exposed_brown_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block EXPOSED_RED_CHISELED_COPPER =          register("exposed_red_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block EXPOSED_ORANGE_CHISELED_COPPER =       register("exposed_orange_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block EXPOSED_YELLOW_CHISELED_COPPER =       register("exposed_yellow_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block EXPOSED_LIME_CHISELED_COPPER =         register("exposed_lime_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block EXPOSED_GREEN_CHISELED_COPPER =        register("exposed_green_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block EXPOSED_CYAN_CHISELED_COPPER =         register("exposed_cyan_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block EXPOSED_LIGHT_BLUE_CHISELED_COPPER =   register("exposed_light_blue_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block EXPOSED_BLUE_CHISELED_COPPER =         register("exposed_blue_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block EXPOSED_PURPLE_CHISELED_COPPER =       register("exposed_purple_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block EXPOSED_MAGENTA_CHISELED_COPPER =      register("exposed_magenta_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block EXPOSED_PINK_CHISELED_COPPER =         register("exposed_pink_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));

    public static final Block WEATHERED_WHITE_CHISELED_COPPER =      register("weathered_white_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block WEATHERED_LIGHT_GRAY_CHISELED_COPPER = register("weathered_light_gray_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block WEATHERED_GRAY_CHISELED_COPPER =       register("weathered_gray_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block WEATHERED_BLACK_CHISELED_COPPER =      register("weathered_black_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block WEATHERED_BROWN_CHISELED_COPPER =      register("weathered_brown_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block WEATHERED_RED_CHISELED_COPPER =        register("weathered_red_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block WEATHERED_ORANGE_CHISELED_COPPER =     register("weathered_orange_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block WEATHERED_YELLOW_CHISELED_COPPER =     register("weathered_yellow_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block WEATHERED_LIME_CHISELED_COPPER =       register("weathered_lime_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block WEATHERED_GREEN_CHISELED_COPPER =      register("weathered_green_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block WEATHERED_CYAN_CHISELED_COPPER =       register("weathered_cyan_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block WEATHERED_LIGHT_BLUE_CHISELED_COPPER = register("weathered_light_blue_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block WEATHERED_BLUE_CHISELED_COPPER =       register("weathered_blue_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block WEATHERED_PURPLE_CHISELED_COPPER =     register("weathered_purple_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block WEATHERED_MAGENTA_CHISELED_COPPER =    register("weathered_magenta_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block WEATHERED_PINK_CHISELED_COPPER =       register("weathered_pink_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));

    public static final Block OXIDIZED_WHITE_CHISELED_COPPER =      register("oxidized_white_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block OXIDIZED_LIGHT_GRAY_CHISELED_COPPER = register("oxidized_light_gray_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block OXIDIZED_GRAY_CHISELED_COPPER =       register("oxidized_gray_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block OXIDIZED_BLACK_CHISELED_COPPER =      register("oxidized_black_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block OXIDIZED_BROWN_CHISELED_COPPER =      register("oxidized_brown_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block OXIDIZED_RED_CHISELED_COPPER =        register("oxidized_red_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block OXIDIZED_ORANGE_CHISELED_COPPER =     register("oxidized_orange_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block OXIDIZED_YELLOW_CHISELED_COPPER =     register("oxidized_yellow_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block OXIDIZED_LIME_CHISELED_COPPER =       register("oxidized_lime_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block OXIDIZED_GREEN_CHISELED_COPPER =      register("oxidized_green_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block OXIDIZED_CYAN_CHISELED_COPPER =       register("oxidized_cyan_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block OXIDIZED_LIGHT_BLUE_CHISELED_COPPER = register("oxidized_light_blue_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block OXIDIZED_BLUE_CHISELED_COPPER =       register("oxidized_blue_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block OXIDIZED_PURPLE_CHISELED_COPPER =     register("oxidized_purple_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block OXIDIZED_MAGENTA_CHISELED_COPPER =    register("oxidized_magenta_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block OXIDIZED_PINK_CHISELED_COPPER =       register("oxidized_pink_chiseled_copper", new Block(AbstractBlock.Settings.create().strength(3, 6)));

    public static final Block BURSTING_PIPE = register("bursting_pipe", new BurstingPipeBlock(AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK)));
    public static final Block EXPOSED_BURSTING_PIPE = register("exposed_bursting_pipe", new BurstingPipeBlock(AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK)));
    public static final Block WEATHERED_BURSTING_PIPE = register("weathered_bursting_pipe", new BurstingPipeBlock(AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK)));
    public static final Block OXIDIZED_BURSTING_PIPE = register("oxidized_bursting_pipe", new BurstingPipeBlock(AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK)));

    // -- Copper Decoration Blocks
    public static final Block EXPOSED_COPPER_VENT = register("exposed_copper_vent", new CopperVentBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));
    public static final Block EXPOSED_COPPER_CRATE = register("exposed_copper_crate", new CopperCrateBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));

    // -- Industrial Decoration Blocks
    public static final Block STEEL_BLOCK = register("steel_block", new Block(AbstractBlock.Settings.create().strength(3, 6)));

    // -- Ancient Blocks
    public static final Block ANCIENT_POT_1 = register("ancient_pot_1", new AncientPot1Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block ANCIENT_POT_2 = register("ancient_pot_2", new AncientPot1Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block ANCIENT_POT_3 = register("ancient_pot_3", new AncientPot1Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block ANCIENT_POT_4_BOTTOM = register("ancient_pot_4_bottom", new AncientPot4BottomBlock(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block ANCIENT_POT_4_MIDDLE = register("tall_ancient_pot_middle", new AncientPot4BottomBlock(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block ANCIENT_POT_4_TOP = register("ancient_pot_4_top", new AncientPot4TopBlock(AbstractBlock.Settings.create().strength(3, 6)));

    public static final Block ANCIENT_PEDESTAL = register("ancient_pedestal", new AncientPillarBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
    public static final Block ANCIENT_PILLAR = register("ancient_pillar", new AncientPillarBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
    public static final Block LIGHT_HOLDER = register("light_holder", new LightContainerBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS).luminance(createLightLevelFromBooleanProperty(2, LightContainerBlock.LIT))));
    public static final Block DIRECTIONAL_LIGHT_HOLDER = register("directional_light_holder", new DirectionalLightContainerBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS).luminance(createLightLevelFromBooleanProperty(2, LightContainerBlock.LIT))));


    // -- Sculk Blocks
    public static final Block SCULK_STEM = register("sculk_stem", new PillarBlock(FabricBlockSettings.copyOf(Blocks.WARPED_STEM)));
    public static final Block SHORT_SCULK_GRASS = register("short_sculk_grass", new SculkPlantBlock(FabricBlockSettings.create().noCollision()));
    public static final Block SCULK_GRASS = register("sculk_grass", new SculkPlantBlock(FabricBlockSettings.create().noCollision()));
    public static final Block SCULK_SUMMONER = register("sculk_summoner", new SculkSummonerBlock(FabricBlockSettings.copyOf(Blocks.SCULK)));
    public static final Block SCULK_SPREADER = register("sculk_spreader", new SculkSpreaderBlock(FabricBlockSettings.copyOf(Blocks.SCULK)));
    public static final Block SCULK_GROWTH = register("sculk_growth", new SculkGrowthBlock(FabricBlockSettings.copyOf(Blocks.SCULK).nonOpaque().luminance(8)));
    public static final Block SCULK_BUBBLE = register("sculk_bubble", new SculkBubbleBlock(FabricBlockSettings.copyOf(Blocks.SCULK).noCollision()));
    public static final Block SCULK_VINES = register("sculk_vines", new SculkVinesBlock(AbstractBlock.Settings.copy(Blocks.WEEPING_VINES)));
    public static final Block SCULK_VINES_PLANT = register("sculk_vines_plant", new SculkVinesPlantBlock(AbstractBlock.Settings.copy(Blocks.WEEPING_VINES_PLANT)), false);
    public static final Block SCULK_ROSE = register("sculk_rose", new WitherRoseBlock(StatusEffects.WITHER, 8, AbstractBlock.Settings.copy(Blocks.WITHER_ROSE)));

    public static final Block UMBRAN_LOG = register("umbran_log", new PillarBlock(FabricBlockSettings.copyOf(Blocks.WARPED_STEM)));
    public static final Block UMBRAN_LEAVES = register("umbran_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)));

    // -- Speakers
    public static final Block OXIDIZED_COPPER_SPEAKER = register("oxidized_copper_speaker", new SpeakerBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));
    public static final Block WEATHERED_COPPER_SPEAKER = register("weathered_copper_speaker", new SpeakerBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));
    public static final Block EXPOSED_COPPER_SPEAKER = register("exposed_copper_speaker", new SpeakerBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));
    public static final Block COPPER_SPEAKER = register("copper_speaker", new SpeakerBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));

    // -- Walls
    public static final Block AMETHYST_WALL = register("amethyst_wall", new WallBlock(FabricBlockSettings.copyOf(Blocks.AMETHYST_BLOCK).solid()));
    public static final Block PACKED_ICE_WALL = register("packed_ice_wall", new WallBlock(FabricBlockSettings.copyOf(Blocks.PACKED_ICE).solid()));

    // -- Carpets
    public static final Block ORNAMENTAL_BLACK_CARPET = register("ornamental_black_carpet", new CarpetMultiBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CARPET)));
    public static final Block ORNAMENTAL_BLUE_CARPET = register("ornamental_blue_carpet", new CarpetMultiBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CARPET)));
    public static final Block ORNAMENTAL_BROWN_CARPET = register("ornamental_brown_carpet", new CarpetMultiBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CARPET)));
    public static final Block ORNAMENTAL_CYAN_CARPET = register("ornamental_cyan_carpet", new CarpetMultiBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CARPET)));
    public static final Block ORNAMENTAL_GRAY_CARPET = register("ornamental_gray_carpet", new CarpetMultiBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CARPET)));
    public static final Block ORNAMENTAL_GREEN_CARPET = register("ornamental_green_carpet", new CarpetMultiBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CARPET)));
    public static final Block ORNAMENTAL_LIGHT_BLUE_CARPET = register("ornamental_light_blue_carpet", new CarpetMultiBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CARPET)));
    public static final Block ORNAMENTAL_LIGHT_GRAY_CARPET = register("ornamental_light_gray_carpet", new CarpetMultiBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CARPET)));
    public static final Block ORNAMENTAL_LIME_CARPET = register("ornamental_lime_carpet", new CarpetMultiBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CARPET)));
    public static final Block ORNAMENTAL_MAGENTA_CARPET = register("ornamental_magenta_carpet", new CarpetMultiBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CARPET)));
    public static final Block ORNAMENTAL_ORANGE_CARPET = register("ornamental_orange_carpet", new CarpetMultiBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CARPET)));
    public static final Block ORNAMENTAL_PINK_CARPET = register("ornamental_pink_carpet", new CarpetMultiBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CARPET)));
    public static final Block ORNAMENTAL_PURPLE_CARPET = register("ornamental_purple_carpet", new CarpetMultiBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CARPET)));
    public static final Block ORNAMENTAL_WHITE_CARPET = register("ornamental_white_carpet", new CarpetMultiBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CARPET)));
    public static final Block ORNAMENTAL_YELLOW_CARPET = register("ornamental_yellow_carpet", new CarpetMultiBlock(FabricBlockSettings.copyOf(Blocks.BLUE_CARPET)));

    // -- Misc
    public static final Block ICICLE_BLOCK = register("icicle_block", new IcicleBlock(FabricBlockSettings.copyOf(Blocks.POINTED_DRIPSTONE).slipperiness(0.98F).sounds(BlockSoundGroup.GLASS)));
    public static final Block PARTICLE_COMMAND_BLOCK = register("particle_command_block", new ParticleBlock(FabricBlockSettings.copyOf(Blocks.COMMAND_BLOCK)));
    // public static final Block RUNE_BLOCK = register("rune_block", new RuneBlock(FabricBlockSettings.copyOf(Blocks.BAMBOO_PLANKS).nonOpaque().luminance(createLightLevelFromBooleanProperty(4, RuneBlock.VISIBLE))));
    public static final Block PEDESTAL_BLOCK = register("pedestal_block", new PedestalBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
    public static final Block ITEM_DISPLAY = register("item_display", new ItemDisplayBlock(FabricBlockSettings.copyOf(Blocks.COMMAND_BLOCK).nonOpaque()));
    public static final Block FORGE_BLOCK = register("forge_block", new ForgeBlock(FabricBlockSettings.copyOf(Blocks.ANVIL)));
    public static final Block COMPUTER = register("computer", new ComputerBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final Block PURE_BLACK = register("pure_black", new Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block DISPLAY_BLOCK = register("display_block", new DisplayBlock(AbstractBlock.Settings.create().strength(4.0f).nonOpaque().solid()));
    public static final Block ELECTRIC_LIGHT = register("electric_light", new WaterFacilityLightBlock(AbstractBlock.Settings.create().strength(4.0f, 6).luminance(WaterFacilityLightBlock.STATE_TO_LUMINANCE)));
    public static final Block SPOTLIGHT = register("spotlight", new SpotlightBlock(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block VANISHING_BLOCK = register("vanishing_block", new VanishingBlock(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block DIM_LANTERN = register("dim_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.SOUL_LANTERN)));
    public static final Block PROJECTOR_BLOCK = register("projector_block", new ProjectorBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_BRICKS)));
    public static final Block ANCIENT_HEALER = register("ancient_healer", new AncientHealerBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_BRICKS).luminance(state -> 15)));
    public static final Block GOBLET = register("goblet", new GobletBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block CANDLESTICK = register("candlestick", new CandlestickBlock(AbstractBlock.Settings.copy(Blocks.LANTERN).luminance(state -> 10)));
    public static final Block JAR = register("jar", new JarBlock(AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final Block JAR_BRAIN = register("jar_brain", new JarBlock(AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));

    public static final Block HERB_JAR = register("herb_jar", new HerbJarBlock(Blocks.AIR));
    public static final Block HERB_JAR_TORCHFLOWER = register("herb_jar_torchflower", new HerbJarBlock(Blocks.TORCHFLOWER), false);
    public static final Block HERB_JAR_OAK_SAPLING = register("herb_jar_oak_sapling", new HerbJarBlock(Blocks.OAK_SAPLING), false);
    public static final Block HERB_JAR_SPRUCE_SAPLING = register("herb_jar_spruce_sapling", new HerbJarBlock(Blocks.SPRUCE_SAPLING), false);
    public static final Block HERB_JAR_BIRCH_SAPLING = register("herb_jar_birch_sapling", new HerbJarBlock(Blocks.BIRCH_SAPLING), false);
    public static final Block HERB_JAR_JUNGLE_SAPLING = register("herb_jar_jungle_sapling", new HerbJarBlock(Blocks.JUNGLE_SAPLING), false);
    public static final Block HERB_JAR_ACACIA_SAPLING = register("herb_jar_acacia_sapling", new HerbJarBlock(Blocks.ACACIA_SAPLING), false);
    public static final Block HERB_JAR_CHERRY_SAPLING = register("herb_jar_cherry_sapling", new HerbJarBlock(Blocks.CHERRY_SAPLING), false);
    public static final Block HERB_JAR_DARK_OAK_SAPLING = register("herb_jar_dark_oak_sapling", new HerbJarBlock(Blocks.DARK_OAK_SAPLING), false);
    public static final Block HERB_JAR_MANGROVE_PROPAGULE = register("herb_jar_mangrove_propagule", new HerbJarBlock(Blocks.MANGROVE_PROPAGULE), false);
    public static final Block HERB_JAR_FERN = register("herb_jar_fern", new HerbJarBlock(Blocks.FERN), false);
    public static final Block HERB_JAR_DANDELION = register("herb_jar_dandelion", new HerbJarBlock(Blocks.DANDELION), false);
    public static final Block HERB_JAR_POPPY = register("herb_jar_poppy", new HerbJarBlock(Blocks.POPPY), false);
    public static final Block HERB_JAR_BLUE_ORCHID = register("herb_jar_blue_orchid", new HerbJarBlock(Blocks.BLUE_ORCHID), false);
    public static final Block HERB_JAR_ALLIUM = register("herb_jar_allium", new HerbJarBlock(Blocks.ALLIUM), false);
    public static final Block HERB_JAR_AZURE_BLUET = register("herb_jar_azure_bluet", new HerbJarBlock(Blocks.AZURE_BLUET), false);
    public static final Block HERB_JAR_RED_TULIP = register("herb_jar_red_tulip", new HerbJarBlock(Blocks.RED_TULIP), false);
    public static final Block HERB_JAR_ORANGE_TULIP = register("herb_jar_orange_tulip", new HerbJarBlock(Blocks.ORANGE_TULIP), false);
    public static final Block HERB_JAR_WHITE_TULIP = register("herb_jar_white_tulip", new HerbJarBlock(Blocks.WHITE_TULIP), false);
    public static final Block HERB_JAR_PINK_TULIP = register("herb_jar_pink_tulip", new HerbJarBlock(Blocks.PINK_TULIP), false);
    public static final Block HERB_JAR_OXEYE_DAISY = register("herb_jar_oxeye_daisy", new HerbJarBlock(Blocks.OXEYE_DAISY), false);
    public static final Block HERB_JAR_CORNFLOWER = register("herb_jar_cornflower", new HerbJarBlock(Blocks.CORNFLOWER), false);
    public static final Block HERB_JAR_LILY_OF_THE_VALLEY = register("herb_jar_lily_of_the_valley", new HerbJarBlock(Blocks.LILY_OF_THE_VALLEY), false);
    public static final Block HERB_JAR_WITHER_ROSE = register("herb_jar_wither_rose", new HerbJarBlock(Blocks.WITHER_ROSE), false);
    public static final Block HERB_JAR_RED_MUSHROOM = register("herb_jar_red_mushroom", new HerbJarBlock(Blocks.RED_MUSHROOM), false);
    public static final Block HERB_JAR_BROWN_MUSHROOM = register("herb_jar_brown_mushroom", new HerbJarBlock(Blocks.BROWN_MUSHROOM), false);
    public static final Block HERB_JAR_DEAD_BUSH = register("herb_jar_dead_bush", new HerbJarBlock(Blocks.DEAD_BUSH), false);

    private static Block register(String name, Block block) {
        return register(name, block, true);
    }

    private static Block register(String name, Block block, boolean createItem) {
        if (createItem) registerItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Main.MOD_NAMESPACE, name), block);
    }

    private static void registerItem(String name, Block block) {
        var item = Registry.register(Registries.ITEM, new Identifier(Main.MOD_NAMESPACE, name), new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> content.addAfter(Items.WAXED_OXIDIZED_CHISELED_COPPER, item));
        ItemGroupEvents.modifyEntriesEvent(COBALT_GROUP_KEY).register(content -> content.add(item));
        ItemGroupEvents.modifyEntriesEvent(COBALT_BLOCK_GROUP_KEY).register(content -> content.add(item));
    }

    public static ToIntFunction<BlockState> createLightLevelFromBooleanProperty(int litLevel, BooleanProperty property) {
        return state -> state.get(property) ? litLevel : 0; // TODO: Move visible block state to custom thingy
    }
    public static void register() {

        // Add to functional blocks
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
            content.add(ANCIENT_POT_1);
            content.add(ANCIENT_POT_2);
            content.add(ANCIENT_POT_3);
            content.add(ANCIENT_POT_4_BOTTOM);
            content.add(ANCIENT_POT_4_MIDDLE);
            content.add(ANCIENT_POT_4_TOP);
            content.add(ANCIENT_PEDESTAL);
            content.add(ANCIENT_PILLAR);
            content.add(PEDESTAL_BLOCK);
            content.add(LIGHT_HOLDER);
            content.add(DIRECTIONAL_LIGHT_HOLDER);
            content.add(EXPOSED_COPPER_CRATE);
            content.add(EXPOSED_COPPER_VENT);
            content.add(COPPER_SPEAKER);
            content.add(WEATHERED_COPPER_SPEAKER);
            content.add(EXPOSED_COPPER_SPEAKER);
            content.add(OXIDIZED_COPPER_SPEAKER);
            // content.add(RUNE_BLOCK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
            content.add(ELECTRIC_LIGHT);
            content.add(DISPLAY_BLOCK);
        });

        // Add to natural blocks
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            content.addAfter(Items.SCULK_SENSOR, SCULK_SPREADER);
            content.addAfter(Items.SCULK_SENSOR, SCULK_SUMMONER);
            content.addAfter(Items.SCULK_SENSOR, SCULK_STEM);
            content.addAfter(Items.SCULK_SENSOR, SCULK_GROWTH);
            content.addAfter(Items.SCULK_SENSOR, SHORT_SCULK_GRASS);
            content.addAfter(Items.SCULK_SENSOR, SCULK_GRASS);
            content.addAfter(Items.BLUE_ICE, ICICLE_BLOCK);
        });
    }

}
