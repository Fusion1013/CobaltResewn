package se.fusion1013.items.components;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;

import java.util.function.UnaryOperator;

public class CobaltComponents {

    public static final ComponentType<Boolean> IS_ON = register("is_on", booleanBuilder -> booleanBuilder.codec(Codec.BOOL));
    public static final ComponentType<Integer> COLOR = register("color", booleanBuilder -> booleanBuilder.codec(Codec.INT));
    public static final ComponentType<Float> LIGHT_DISTANCE = register("light_distance", floatBuilder -> floatBuilder.codec(Codec.FLOAT));
    public static final ComponentType<Float> LIGHT_INTENSITY = register("light_intensity", floatBuilder -> floatBuilder.codec(Codec.FLOAT));
    public static final ComponentType<Float> LIGHT_ANGLE = register("light_angle", floatBuilder -> floatBuilder.codec(Codec.FLOAT));

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

    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Main.MOD_NAMESPACE, name), builderOperator.apply(ComponentType.builder()).build());
    }

    public static void initialize() {
        Main.LOGGER.info("Registering {} components", Main.MOD_NAMESPACE);
    }

}
