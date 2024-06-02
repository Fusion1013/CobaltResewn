package se.fusion1013.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;

import java.util.function.ToIntFunction;

import static se.fusion1013.items.CustomItemGroupRegistry.COBALT_BLOCK_GROUP_KEY;
import static se.fusion1013.items.CustomItemGroupRegistry.COBALT_GROUP_KEY;

public class CobaltBlocks {

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

    public static final Block EXPOSED_COPPER_VENT = register("exposed_copper_vent", new CopperVentBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));
    public static final Block EXPOSED_COPPER_CRATE = register("exposed_copper_crate", new CopperCrateBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));

    // TODO: Replace with custom class
    public static final Block ANCIENT_POT_1 = register("ancient_pot_1", new AncientPot1Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block ANCIENT_POT_2 = register("ancient_pot_2", new AncientPot1Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block ANCIENT_POT_3 = register("ancient_pot_3", new AncientPot1Block(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block ANCIENT_POT_4_BOTTOM = register("ancient_pot_4_bottom", new AncientPot4BottomBlock(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block ANCIENT_POT_4_MIDDLE = register("tall_ancient_pot_middle", new AncientPot4BottomBlock(AbstractBlock.Settings.create().strength(3, 6)));
    public static final Block ANCIENT_POT_4_TOP = register("ancient_pot_4_top", new AncientPot4TopBlock(AbstractBlock.Settings.create().strength(3, 6)));


    public static final Block SCULK_STEM = register("sculk_stem", new PillarBlock(FabricBlockSettings.copyOf(Blocks.WARPED_STEM)));
    public static final Block SHORT_SCULK_GRASS = register("short_sculk_grass", new SculkPlantBlock(FabricBlockSettings.create().noCollision()));
    public static final Block SCULK_GRASS = register("sculk_grass", new SculkPlantBlock(FabricBlockSettings.create().noCollision()));
    public static final Block SCULK_SUMMONER = register("sculk_summoner", new SculkSummonerBlock(FabricBlockSettings.copyOf(Blocks.SCULK)));
    public static final Block SCULK_SPREADER = register("sculk_spreader", new SculkSpreaderBlock(FabricBlockSettings.copyOf(Blocks.SCULK)));

    public static final Block OXIDIZED_COPPER_SPEAKER = register("oxidized_copper_speaker", new SpeakerBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));
    public static final Block WEATHERED_COPPER_SPEAKER = register("weathered_copper_speaker", new SpeakerBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));
    public static final Block EXPOSED_COPPER_SPEAKER = register("exposed_copper_speaker", new SpeakerBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));
    public static final Block COPPER_SPEAKER = register("copper_speaker", new SpeakerBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK)));

    public static final Block PARTICLE_COMMAND_BLOCK = register("particle_command_block", new ParticleBlock(FabricBlockSettings.copyOf(Blocks.COMMAND_BLOCK)));

    public static final Block RUNE_BLOCK = register("rune_block", new RuneBlock(FabricBlockSettings.copyOf(Blocks.BAMBOO_PLANKS).nonOpaque().luminance(createLightLevelFromVisibleBlockState(4))));

    public static final Block FORGE_SIDE_CRYSTAL_BLOCK = register("forge_side_crystal_block", new ForgeSideCrystalBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));

    private static Block register(String name, Block block) {
        registerItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Main.MOD_NAMESPACE, name), block);
    }

    private static void registerItem(String name, Block block) {
        var item = Registry.register(Registries.ITEM, new Identifier(Main.MOD_NAMESPACE, name), new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.addAfter(Items.WAXED_OXIDIZED_CHISELED_COPPER, item);
        });
        ItemGroupEvents.modifyEntriesEvent(COBALT_GROUP_KEY).register(content -> {
            content.add(item);
        });
        ItemGroupEvents.modifyEntriesEvent(COBALT_BLOCK_GROUP_KEY).register(content -> {
            content.add(item);
        });
    }

    public static ToIntFunction<BlockState> createLightLevelFromVisibleBlockState(int litLevel) {
        return state -> state.get(RuneBlock.VISIBLE) ? litLevel : 0;
    }

    public static void register() {}

}
