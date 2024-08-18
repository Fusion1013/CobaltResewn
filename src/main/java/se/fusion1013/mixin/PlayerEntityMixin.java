package se.fusion1013.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import se.fusion1013.Main;
import se.fusion1013.entity.attribute.CobaltAttributes;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntityMixin {

    @Inject(method = "onKilledOther", at = @At("HEAD"))
    public void onKilledOther(ServerWorld world, LivingEntity other, CallbackInfoReturnable<Boolean> cir) {
        var instance = getAttributeInstance(CobaltAttributes.GENERIC_LIFESTEAL);
        if (instance == null) return;

        double amount = 0;
        for (EntityAttributeModifier modifier : instance.getModifiers()) {
            if (modifier.operation() == EntityAttributeModifier.Operation.ADD_VALUE) amount += modifier.value();
            else amount *= (amount + 1);
        }

        if (amount <= 0) return;

        this.heal((float) (this.getMaxHealth() * amount));
    }
}
