package se.fusion1013.registries;

import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;
import se.fusion1013.slidereel.SlideReel;

public class CobaltRegistries {

    // public static final Registry<SlideReel> SLIDE_REEL = FabricRegistryBuilder.createDefaulted(CobaltRegistryKeys.SLIDE_REEL, Identifier.of(Main.MOD_NAMESPACE, "slide_reel")).buildAndRegister();

    public static void register() {
        DynamicRegistries.register(CobaltRegistryKeys.SLIDE_REEL, SlideReel.CODEC);
    }
}
