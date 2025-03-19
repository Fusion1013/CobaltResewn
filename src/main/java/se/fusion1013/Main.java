package se.fusion1013;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.fusion1013.block.CobaltBlocks;
import se.fusion1013.block.entity.CobaltBlockEntityTypes;
import se.fusion1013.commands.CobaltCommands;
import se.fusion1013.effect.CobaltEffects;
import se.fusion1013.entity.CobaltEntities;
import se.fusion1013.items.CobaltItems;
import se.fusion1013.items.CustomItemGroupRegistry;
import se.fusion1013.networking.CobaltServerNetworking;
import se.fusion1013.registries.CobaltRegistries;
import se.fusion1013.registries.CobaltRegistryKeys;
import se.fusion1013.screen.CobaltScreenHandlers;
import se.fusion1013.slidereel.SlideReel;
import se.fusion1013.sounds.CobaltSoundEvents;

import java.io.InputStream;
import java.io.InputStreamReader;

public class Main implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("cobalt");
	public static final String MOD_NAMESPACE = "cobalt";
	public static MinecraftServer server;



	@Override
	public void onInitialize() {

		LOGGER.info("Initializing Cobalt...");

		ServerLifecycleEvents.SERVER_STARTED.register(this::onServerStart);

		CobaltRegistries.register();
		CobaltCommands.register();
		CobaltSoundEvents.register();
		CobaltEntities.register();
		CobaltItems.register();
		CobaltBlocks.register();
		CustomItemGroupRegistry.register();
		CobaltBlockEntityTypes.registerAll();
		CobaltEffects.registerAll();
		CobaltServerNetworking.register();
		CobaltScreenHandlers.registerAll();

		/*
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
			@Override
			public Identifier getFabricId() {
				return Identifier.of(MOD_NAMESPACE, "slide_reel");
			}

			@Override
			public void reload(ResourceManager manager) {
				for(Identifier id : manager.findResources("slide_reels", path -> path.getPath().endsWith(".json")).keySet()) {

					try(InputStream stream = manager.getResource(id).get().getInputStream()) {
						JsonReader reader = new JsonReader(new InputStreamReader(stream));
						Gson gson = new Gson();
						SlideReel slideReel = gson.fromJson(reader, SlideReel.class);
						// Main.LOGGER.info("Registered slide reel '" + id.getPath() + "'");
					} catch(Exception e) {
						Main.LOGGER.error("Error occurred while loading resource json " + id.toString(), e);
					}

				}
			}
		});
		 */
	}

	private void onServerStart(MinecraftServer server) {
		Main.server = server;
	}
}