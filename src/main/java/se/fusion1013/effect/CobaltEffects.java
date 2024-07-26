package se.fusion1013.effect;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;

/**
 * Handles registering custom {@link StatusEffect}s.
 */
public class CobaltEffects {

    public static final StatusEffect CROWD_CONTROL = register("crowd_control", new CrowdControlEffect());
    public static final StatusEffect CORRUPTION_SPREAD = register("corruption_spread", new CorruptionSpreadEffect());
    public static final StatusEffect IMMOVABLE_EFFECT = register("immovable", new ImmovableEffect());
    public static final StatusEffect CURSED_KNOWLEDGE = register("cursed_knowledge", new CursedKnowledgeEffect());
    public static final StatusEffect ENVIRONMENT_EFFECT = register("environment", new EnvironmentEffect().setFactorCalculationDataSupplier(() -> new StatusEffectInstance.FactorCalculationData(22)));
    public static final StatusEffect FREEZING_EFFECT = register("freezing", new FreezingEffect());
    public static final StatusEffect COLD_RESISTANCE_EFFECT = register("cold_resistance", new ColdResistanceEffect());
    public static final StatusEffect HEAVY = register("heavy", new HeavyEffect());
    public static final StatusEffect INFECTED = register("infected", new InfectedEffect());

    public static StatusEffect register(String id, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Main.MOD_NAMESPACE, id), effect);
    }

    public static void registerAll() {}

}
