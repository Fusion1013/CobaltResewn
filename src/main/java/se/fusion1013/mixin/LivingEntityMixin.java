package se.fusion1013.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import se.fusion1013.effect.CobaltEffects;
import se.fusion1013.util.entity.DamageUtil;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin {

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow public abstract int getArmor();

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

    @Redirect(method = "applyArmorToDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/DamageUtil;getDamageLeft(FFF)F"))
    private float injected(float damage, float armor, float armorToughness) {
        return DamageUtil.getDamageLeft(damage, armor, armorToughness);
    }
}
