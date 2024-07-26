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
		var random = new Random();
		LOGGER.info(motd[random.nextInt(0, motd.length)]);

		ServerLifecycleEvents.SERVER_STARTED.register(this::onServerStart);

		CobaltCommands.register();
		CobaltSoundEvents.register();
		CobaltItems.register();
		CustomEntityRegistry.register();
		CobaltBlocks.register();
		CustomItemGroupRegistry.register();
		CobaltBlockEntityTypes.registerAll();
		CobaltEffects.registerAll();
		CobaltServerNetworking.register();
		CobaltScreenHandlers.registerAll();

	}

	private void onServerStart(MinecraftServer server) {
		Main.server = server;
	}

	private static final String[] motd = new String[] {
			"1.21 is a lie",
			"why are you looking here?"
	};
}