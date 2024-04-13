package com.example;

import com.example.entity.CustomEntityRegistry;
import com.example.items.trinkets.BackpackItem;
import com.example.model.CobaltPredicateProviderRegister;
import com.example.render.entity.ExplosiveArrowEntityRenderer;
import com.example.render.entity.LightningArrowEntityRenderer;
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

public class ExampleModClient implements ClientModInitializer {

	private static KeyBinding toggleGuiKeybind;

	public static final Item BACKPACK = new BackpackItem(new FabricItemSettings());

	@Override
	public void onInitializeClient() {
		initializeKeybinds();
		registerItems();

		EntityRendererRegistry.register(CustomEntityRegistry.LIGHTNING_ARROW, LightningArrowEntityRenderer::new);
		EntityRendererRegistry.register(CustomEntityRegistry.EXPLOSIVE_ARROW, ExplosiveArrowEntityRenderer::new);
	}

	private void registerItems() {
		Registry.register(Registries.ITEM, new Identifier("cobalt", "backpack"), BACKPACK);
		TrinketRendererRegistry.registerRenderer(BACKPACK, (TrinketRenderer) BACKPACK);
		CobaltPredicateProviderRegister.register();
	}

	private void initializeKeybinds() {
	}
}