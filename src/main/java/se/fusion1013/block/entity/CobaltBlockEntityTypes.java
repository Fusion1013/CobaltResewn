package se.fusion1013.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;
import se.fusion1013.block.CobaltBlocks;
import se.fusion1013.screen.ItemDisplayScreenHandler;

/**
 * Handles registering custom {@link BlockEntityType}s.
 */
public class CobaltBlockEntityTypes {

    public static final BlockEntityType<CopperCrateBlockEntity>                   COPPER_CRATE_ENTITY;
    public static final BlockEntityType<AncientPotBlockEntity>                    ANCIENT_POT_BLOCK_ENTITY;
    public static final BlockEntityType<SculkSpreaderBlockEntity>                 SCULK_SPREADER;
    public static final BlockEntityType<SpeakerBlockEntity>                       SPEAKER;
    public static final BlockEntityType<ParticleBlockEntity>                      PARTICLE_BLOCK;
    public static final BlockEntityType<RuneBlockEntity>                          RUNE_BLOCK;
    public static final BlockEntityType<ForgeSideCrystalBlockEntity>              FORGE_SIDE_CRYSTAL_BLOCK;
    public static final BlockEntityType<PedestalBlockEntity>                      PEDESTAL_BLOCK_ENTITY;
    public static final BlockEntityType<ItemDisplayBlockEntity>                   ITEM_DISPLAY_BLOCK_ENTITY;
    public static final BlockEntityType<ForgeBlockEntity>                         FORGE_BLOCK_ENTITY;

    private static <T extends BlockEntity> BlockEntityType<T> register(String id, FabricBlockEntityTypeBuilder<T> builder) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Main.MOD_NAMESPACE, id), builder.build());
    }

    public static void registerAll() {}

    static {
        COPPER_CRATE_ENTITY = register("exposed_copper_crate_block_entity", FabricBlockEntityTypeBuilder.create(CopperCrateBlockEntity::new, CobaltBlocks.EXPOSED_COPPER_CRATE));
        ANCIENT_POT_BLOCK_ENTITY = register("ancient_pot_block_entity", FabricBlockEntityTypeBuilder.create(AncientPotBlockEntity::new, CobaltBlocks.ANCIENT_POT_1, CobaltBlocks.ANCIENT_POT_2, CobaltBlocks.ANCIENT_POT_3, CobaltBlocks.ANCIENT_POT_4_TOP));
        SCULK_SPREADER = register("sculk_spreader_block_entity", FabricBlockEntityTypeBuilder.create(SculkSpreaderBlockEntity::new, CobaltBlocks.SCULK_SPREADER));
        SPEAKER = register("speaker_block_entity", FabricBlockEntityTypeBuilder.create(SpeakerBlockEntity::new, CobaltBlocks.OXIDIZED_COPPER_SPEAKER, CobaltBlocks.EXPOSED_COPPER_SPEAKER, CobaltBlocks.WEATHERED_COPPER_SPEAKER, CobaltBlocks.COPPER_SPEAKER));
        PARTICLE_BLOCK = register("particle_block_entity", FabricBlockEntityTypeBuilder.create(ParticleBlockEntity::new, CobaltBlocks.PARTICLE_COMMAND_BLOCK));
        RUNE_BLOCK = register("rune_block_entity", FabricBlockEntityTypeBuilder.create(RuneBlockEntity::new, CobaltBlocks.RUNE_BLOCK));
        FORGE_SIDE_CRYSTAL_BLOCK = register("forge_side_crystal_block_entity", FabricBlockEntityTypeBuilder.create(ForgeSideCrystalBlockEntity::new, CobaltBlocks.FORGE_SIDE_CRYSTAL_BLOCK));
        PEDESTAL_BLOCK_ENTITY = register("pedestal_block_entity", FabricBlockEntityTypeBuilder.create(PedestalBlockEntity::new, CobaltBlocks.PEDESTAL_BLOCK));
        ITEM_DISPLAY_BLOCK_ENTITY = register("item_display_entity", FabricBlockEntityTypeBuilder.create(ItemDisplayBlockEntity::new, CobaltBlocks.ITEM_DISPLAY));
        FORGE_BLOCK_ENTITY = register("forge_block_entity", FabricBlockEntityTypeBuilder.create(ForgeBlockEntity::new, CobaltBlocks.FORGE_BLOCK));
    }

}
