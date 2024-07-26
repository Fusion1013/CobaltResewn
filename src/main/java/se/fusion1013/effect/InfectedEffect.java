package se.fusion1013.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import se.fusion1013.Main;
import se.fusion1013.entity.damage.CobaltDamageTypes;

import java.util.List;

public class InfectedEffect extends StatusEffect {

    private static final int DAMAGE_COOLDOWN = 20; // 1 second
    private static final float DAMAGE_AMOUNT = 1.5f;
    private static final float SPREAD_RADIUS = 6;
    private static final float SPREAD_CHANCE = 0.05f;
    private static final int SPREAD_COUNT_MAX = 4;

    protected InfectedEffect() {
        super(StatusEffectCategory.HARMFUL, 0x380c7d);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);

        var world = entity.getWorld();
        entity.damage(CobaltDamageTypes.of(world, CobaltDamageTypes.INFECTION), DAMAGE_AMOUNT);
        trySpread(entity, world);
    }

    private void trySpread(LivingEntity entity, World world) {
        Random random = Random.create();

        // Find nearby entities
        Box box = entity.getBoundingBox().expand(SPREAD_RADIUS, SPREAD_RADIUS, SPREAD_RADIUS);
        List<Entity> otherEntities = world.getOtherEntities(entity, box);

        int spreadToCount = random.nextBetween(1, SPREAD_COUNT_MAX);
        int currentSpread = 0;

        // Apply effect to found entities, if they do not already have the effect
        for (Entity otherEntity : otherEntities) {
            if (otherEntity instanceof LivingEntity livingEntity) {
                if (!livingEntity.hasStatusEffect(CobaltEffects.INFECTED)) {

                    if (random.nextDouble() > SPREAD_CHANCE) continue;

                    // Apply effect
                    var thisEffect = entity.getStatusEffect(CobaltEffects.INFECTED);
                    if (thisEffect == null) continue;
                    livingEntity.addStatusEffect(new StatusEffectInstance(CobaltEffects.INFECTED, thisEffect.getDuration(), thisEffect.getAmplifier()));

                    // Visuals
                    world.addParticle(ParticleTypes.ENTITY_EFFECT, livingEntity.getParticleX(0.5), livingEntity.getRandomBodyY(), livingEntity.getParticleZ(0.5), 61/255f, 17/255f, 120/255f);

                    currentSpread++;
                    if (currentSpread >= spreadToCount) break;
                }
            }
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % DAMAGE_COOLDOWN == 0;
    }
}
