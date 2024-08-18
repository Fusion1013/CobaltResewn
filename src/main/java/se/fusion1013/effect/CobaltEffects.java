package se.fusion1013.effect;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;

/**
 * Handles registering custom {@link StatusEffect}s.
 */
public class CobaltEffects {

    public static final RegistryEntry<StatusEffect> CROWD_CONTROL = register("crowd_control", new CrowdControlEffect());
    public static final RegistryEntry<StatusEffect> CORRUPTION_SPREAD = register("corruption_spread", new CorruptionSpreadEffect());
    public static final RegistryEntry<StatusEffect> IMMOVABLE_EFFECT = register("immovable", new ImmovableEffect());
    public static final RegistryEntry<StatusEffect> CURSED_KNOWLEDGE = register("cursed_knowledge", new CursedKnowledgeEffect());
    public static final RegistryEntry<StatusEffect> ENVIRONMENT_EFFECT = register("environment", new EnvironmentEffect());
    public static final RegistryEntry<StatusEffect> FREEZING_EFFECT = register("freezing", new FreezingEffect());
    public static final RegistryEntry<StatusEffect> COLD_RESISTANCE_EFFECT = register("cold_resistance", new ColdResistanceEffect());
    public static final RegistryEntry<StatusEffect> HEAVY = register("heavy", new HeavyEffect());
    public static final RegistryEntry<StatusEffect> INFECTED = register("infected", new InfectedEffect());
    public static final RegistryEntry<StatusEffect> DARK_SHADOWS = register("dark_shadows", new DarkShadowsEffect());
    public static final RegistryEntry<StatusEffect> LIGHT_FOG = register("light_fog", new FogEffect());
    public static final RegistryEntry<StatusEffect> MEDIUM_FOG = register("medium_fog", new FogEffect());
    public static final RegistryEntry<StatusEffect> HEAVY_FOG = register("heavy_fog", new FogEffect());

    public static RegistryEntry<StatusEffect> register(String id, StatusEffect effect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Main.MOD_NAMESPACE, id), effect);
    }

    public static void registerAll() {}

}
