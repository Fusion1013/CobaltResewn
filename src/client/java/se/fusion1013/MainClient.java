package se.fusion1013;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import org.lwjgl.glfw.GLFW;
import se.fusion1013.block.CobaltBlocks;
import se.fusion1013.entity.CobaltEntities;
import se.fusion1013.gui.ItemDisplayScreen;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.model.CobaltPredicateProviderRegister;
import se.fusion1013.networking.CobaltClientNetworking;
import se.fusion1013.render.block.CobaltBlockEntityRenderers;
import se.fusion1013.render.block.DirectionalLightHolderBlockEntityRenderer;
import se.fusion1013.render.entity.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;
import se.fusion1013.render.entity.model.CorruptedCoreEntityModel;
import se.fusion1013.render.entity.model.CorruptedSpiderEntityModel;
import se.fusion1013.render.entity.model.RatEntityModel;
import se.fusion1013.screen.CobaltScreenHandlers;

public class MainClient implements ClientModInitializer {

	private static KeyBinding itemSetTriggerKeyBinding;
	private static KeyBinding armorToggleKeyBinding;

	public static final EntityModelLayer MODEL_CORRUPTED_CORE_LAYER = new EntityModelLayer(Identifier.of(Main.MOD_NAMESPACE, "corrupted_core"), "main");
	public static final EntityModelLayer MODEL_CORRUPTED_SPIDER_LAYER = new EntityModelLayer(Identifier.of(Main.MOD_NAMESPACE, "corrupted_spider"), "main");
	public static final EntityModelLayer MODEL_RAT_LAYER = new EntityModelLayer(Identifier.of(Main.MOD_NAMESPACE, "rat"), "main");

	public static final EntityModelLayer TEST_BLOCK_ENTITY_LAYER = new EntityModelLayer(Identifier.of(Main.MOD_NAMESPACE, "empty_lens"), "main");

	@Override
	public void onInitializeClient() {
		initializeKeybinds();
		registerItems();

		// Entity rendering
		EntityRendererRegistry.register(CobaltEntities.LIGHTNING_ARROW, LightningArrowEntityRenderer::new);
		EntityRendererRegistry.register(CobaltEntities.EXPLOSIVE_ARROW, ExplosiveArrowEntityRenderer::new);

		EntityRendererRegistry.register(CobaltEntities.CORRUPTED_CORE, CorruptedCoreEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MODEL_CORRUPTED_CORE_LAYER, CorruptedCoreEntityModel::getTexturedModelData);

		EntityRendererRegistry.register(CobaltEntities.CORRUPTED_ZOMBIE, CorruptedZombieEntityRenderer::new);
		EntityRendererRegistry.register(CobaltEntities.CORRUPTED_SKELETON, CorruptedSkeletonEntityRenderer::new);
		EntityRendererRegistry.register(CobaltEntities.CORRUPTED_SPIDER, CorruptedSpiderEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MODEL_CORRUPTED_SPIDER_LAYER, CorruptedSpiderEntityModel::getTexturedModelData);

		EntityRendererRegistry.register(CobaltEntities.RAT, RatEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MODEL_RAT_LAYER, RatEntityModel::getTexturedModelData);

		EntityRendererRegistry.register(CobaltEntities.AUTOMATON, AutomatonEntityRenderer::new);

		EntityRendererRegistry.register(CobaltEntities.SMOKE_BOMB, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(CobaltEntities.SMOKE_CLOUD, EmptyEntityRenderer::new);
		EntityRendererRegistry.register(CobaltEntities.DYNAMITE, FlyingItemEntityRenderer::new);

		EntityModelLayerRegistry.registerModelLayer(TEST_BLOCK_ENTITY_LAYER, DirectionalLightHolderBlockEntityRenderer::getTestTexturedModelData);

		// Block rendering
		CobaltBlockEntityRenderers.registerAll();

		// Block rendering
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
				CobaltBlocks.SCULK_GRASS,
				CobaltBlocks.SHORT_SCULK_GRASS,
				CobaltBlocks.ITEM_DISPLAY,
				CobaltBlocks.ICICLE_BLOCK,
				CobaltBlocks.SCULK_BUBBLE
		);

		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),
				CobaltBlocks.RUNE_BLOCK,
				CobaltBlocks.SCULK_GROWTH,
				CobaltBlocks.DIRECTIONAL_LIGHT_HOLDER,
				CobaltBlocks.DISPLAY_BLOCK
		);

		// Screens
		HandledScreens.register(CobaltScreenHandlers.ITEM_DISPLAY_SCREEN_HANDLER, ItemDisplayScreen::new);



		// Networking
		CobaltClientNetworking.register();
	}

	private void registerItems() {
		CobaltPredicateProviderRegister.register();
	}

	private void initializeKeybinds() {
		itemSetTriggerKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.cobalt.item_set_trigger",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_X,
				"category.cobalt.main"
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (itemSetTriggerKeyBinding.wasPressed()) {
				if (client.player == null) continue;
				// TODO: ClientPlayNetworking.send(new ItemSetTriggerAbilityPayloadC2S(KeyBind.ItemSetTrigger));
			}
		});

		armorToggleKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.cobalt.armor_toggle",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_G,
				"category.cobalt.main"
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (armorToggleKeyBinding.wasPressed()) {
				if (client.player == null) continue;

				PacketByteBuf buf = PacketByteBufs.create();
				// TODO: ClientPlayNetworking.send(CobaltNetworkingConstants.ARMOR_SET_TOGGLE_ABILITY_C2S, buf);
			}
		});
	}
}