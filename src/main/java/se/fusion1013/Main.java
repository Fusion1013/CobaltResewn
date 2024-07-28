package se.fusion1013;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.fusion1013.block.CobaltBlocks;
import se.fusion1013.block.entity.CobaltBlockEntityTypes;
import se.fusion1013.commands.CobaltCommands;
import se.fusion1013.effect.CobaltEffects;
import se.fusion1013.entity.CustomEntityRegistry;
import se.fusion1013.items.CobaltItems;
import se.fusion1013.items.CustomItemGroupRegistry;
import se.fusion1013.networking.CobaltServerNetworking;
import se.fusion1013.screen.CobaltScreenHandlers;
import se.fusion1013.sounds.CobaltSoundEvents;

import java.util.Random;

public class Main implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("cobalt");
	public static final String MOD_NAMESPACE = "cobalt";
	public static MinecraftServer server;

	@Override
	public void onInitialize() {

		LOGGER.info("Initializing Cobalt...");

		ServerLifecycleEvents.SERVER_STARTED.register(this::onServerStart);

		CobaltCommands.register();
		CobaltSoundEvents.register();

		LOGGER.info("Loading Bananas...");
		CobaltItems.register();

		CustomEntityRegistry.register();

		LOGGER.info("Loading the big boi...");
		CobaltBlocks.register();

		LOGGER.info("Loading some other random things...");
		CustomItemGroupRegistry.register();
		CobaltBlockEntityTypes.registerAll();
		CobaltEffects.registerAll();
		CobaltServerNetworking.register();
		CobaltScreenHandlers.registerAll();

	}

	private void onServerStart(MinecraftServer server) {
		Main.server = server;
	}
}