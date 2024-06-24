package se.fusion1013.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import se.fusion1013.effect.CobaltEffects;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin {

    @Shadow public abstract boolean hasStatusEffect(RegistryEntry<StatusEffect> effect);

    @Shadow public abstract boolean isFallFlying();

    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    private void travel(Vec3d movementInput, CallbackInfo ci) {
        // Immovable Effect
        if (hasStatusEffect(CobaltEffects.IMMOVABLE_EFFECT) && isOnGround()) ci.cancel();
    }

    @Inject(method = "travel", at = @At("TAIL"))
    private void travelTail(Vec3d movementInput, CallbackInfo ci) {
        // Heavy Effect
        double d = 0.08;
        if (hasStatusEffect(CobaltEffects.HEAVY) && isSubmergedInWater()) {
            this.setVelocity(this.getVelocity().add(0, -d / 4.0, 0));
        }
    }
}
