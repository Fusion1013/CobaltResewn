package se.fusion1013.items.components;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;

public class CobaltComponents {

    public static final ComponentType<Boolean> ACTIVATE = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Main.MOD_NAMESPACE, "activate"),
            ComponentType.<Boolean>builder().codec(Codec.BOOL).build()
    );

    public static final ComponentType<Boolean> MUTE = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Main.MOD_NAMESPACE, "mute"),
            ComponentType.<Boolean>builder().codec(Codec.BOOL).build()
    );

    public static final ComponentType<Integer> CANAL = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Main.MOD_NAMESPACE, "canal"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );

    public static void initialize() {
        Main.LOGGER.info("Registering {} components", Main.MOD_NAMESPACE);
    }

}
