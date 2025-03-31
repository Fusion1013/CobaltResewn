package se.fusion1013.render.block;

import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.impl.client.rendering.BlockEntityRendererRegistryImpl;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import se.fusion1013.MainClient;
import se.fusion1013.block.CobaltBlocks;
import se.fusion1013.block.entity.CobaltBlockEntityTypes;

public class CobaltBlockEntityRenderers {

    public static void registerAll() {
        BlockEntityRendererFactories.register(CobaltBlockEntityTypes.PEDESTAL_BLOCK_ENTITY, PedestalBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(CobaltBlockEntityTypes.ITEM_DISPLAY_BLOCK_ENTITY, ItemDisplayBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(CobaltBlockEntityTypes.LIGHT_HOLDER_BLOCK_ENTITY, LightHolderBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(CobaltBlockEntityTypes.DIRECTIONAL_LIGHT_HOLDER_BLOCK_ENTITY, context -> new DirectionalLightHolderBlockEntityRenderer(context.getLayerModelPart(MainClient.TEST_BLOCK_ENTITY_LAYER)));
        // BlockEntityRendererFactories.register(CobaltBlockEntityTypes.RUNE_BLOCK, RuneBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(CobaltBlockEntityTypes.DISPLAY_BLOCK_ENTITY, DisplayBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(CobaltBlockEntityTypes.SPOTLIGHT_BLOCK_ENTITY, SpotlightBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(CobaltBlockEntityTypes.PROJECTOR_BLOCK_ENTITY, ProjectorBlockEntityRenderer::new);
    }

}
