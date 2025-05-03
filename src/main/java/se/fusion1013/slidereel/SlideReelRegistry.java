package se.fusion1013.slidereel;

import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;
import se.fusion1013.registries.CobaltRegistryKeys;

public class SlideReelRegistry {

    public static final RegistryKey<SlideReel> TEST = RegistryKey.of(CobaltRegistryKeys.SLIDE_REEL, Identifier.of(Main.MOD_NAMESPACE, "test"));
    public static final RegistryKey<SlideReel> OUTER_WILDS = RegistryKey.of(CobaltRegistryKeys.SLIDE_REEL, Identifier.of(Main.MOD_NAMESPACE, "outer_wilds"));

}
