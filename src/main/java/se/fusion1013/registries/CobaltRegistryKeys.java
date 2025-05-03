package se.fusion1013.registries;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;
import se.fusion1013.slidereel.SlideReel;

public class CobaltRegistryKeys {

    public static final RegistryKey<Registry<SlideReel>> SLIDE_REEL = of("slide_reel");

    private static <T>RegistryKey<Registry<T>> of(String id) {
        return RegistryKey.ofRegistry(new Identifier(id));
    }

}
