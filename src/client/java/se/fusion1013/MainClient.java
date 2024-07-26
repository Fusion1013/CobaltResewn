package se.fusion1013;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import org.lwjgl.glfw.GLFW;
import se.fusion1013.block.CobaltBlocks;
import se.fusion1013.entity.CustomEntityRegistry;
import se.fusion1013.gui.ItemDisplayScreen;
import se.fusion1013.items.trinkets.BackpackItem;
import se.fusion1013.model.CobaltPredicateProviderRegister;
import se.fusion1013.networking.CobaltClientNetworking;
import se.fusion1013.networking.CobaltNetworkingConstants;
import se.fusion1013.render.block.CobaltBlockEntityRenderers;
import se.fusion1013.render.block.DirectionalLightHolderBlockEntityRenderer;
import se.fusion1013.render.entity.CorruptedCoreEntityModel;
import se.fusion1013.render.entity.CorruptedCoreEntityRenderer;
import se.fusion1013.render.entity.ExplosiveArrowEntityRenderer;
import se.fusion1013.render.entity.LightningArrowEntityRenderer;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import se.fusion1013.screen.CobaltScreenHandlers;

import static se.fusion1013.networking.CobaltNetworkingConstants.*;

public class MainClient implements ClientModInitializer {

	private static KeyBinding itemSetTriggerKeyBinding;
	private static KeyBinding armorToggleKeyBinding;

	public static final Item BACKPACK = new BackpackItem(new FabricItemSettings());

	public static final EntityModelLayer MODEL_CORRUPTED_CORE_LAYER = new EntityModelLayer(new Identifier("cobalt", "corrupted_core"), "main");

	public static final EntityModelLayer TEST_BLOCK_ENTITY_LAYER = new EntityModelLayer(new Identifier("cobalt", "empty_lens"), "main");

	@Override
	public void onInitializeClient() {
		initializeKeybinds();
		registerItems();

		// Entity rendering
		EntityRendererRegistry.register(CustomEntityRegistry.LIGHTNING_ARROW, LightningArrowEntityRenderer::new);
		EntityRendererRegistry.register(CustomEntityRegistry.EXPLOSIVE_ARROW, ExplosiveArrowEntityRenderer::new);

		EntityRendererRegistry.register(CustomEntityRegistry.CORRUPTED_CORE, CorruptedCoreEntityRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MODEL_CORRUPTED_CORE_LAYER, CorruptedCoreEntityModel::getTexturedModelData);

		EntityModelLayerRegistry.registerModelLayer(TEST_BLOCK_ENTITY_LAYER, DirectionalLightHolderBlockEntityRenderer::getTestTexturedModelData);

		// Block rendering
		CobaltBlockEntityRenderers.registerAll();

		// Block rendering
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
				CobaltBlocks.SCULK_GRASS,
				CobaltBlocks.SHORT_SCULK_GRASS,
				CobaltBlocks.ITEM_DISPLAY,
				CobaltBlocks.ICICLE_BLOCK
		);

		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),
				CobaltBlocks.RUNE_BLOCK,
				CobaltBlocks.SCULK_GROWTH,
				CobaltBlocks.DIRECTIONAL_LIGHT_HOLDER
		);

		// Screens
		HandledScreens.register(CobaltScreenHandlers.ITEM_DISPLAY_SCREEN_HANDLER, ItemDisplayScreen::new);



		// Networking
		CobaltClientNetworking.register();
	}

	private void registerItems() {
		Registry.register(Registries.ITEM, new Identifier("cobalt", "backpack"), BACKPACK);
		TrinketRendererRegistry.registerRenderer(BACKPACK, (TrinketRenderer) BACKPACK);
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

				PacketByteBuf buf = PacketByteBufs.create();
				ClientPlayNetworking.send(ITEM_SET_TRIGGER_ABILITY_C2S, buf);
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
				ClientPlayNetworking.send(CobaltNetworkingConstants.ARMOR_SET_TOGGLE_ABILITY_C2S, buf);
			}
		});
	}
}