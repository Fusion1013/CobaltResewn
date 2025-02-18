package se.fusion1013.mixin.client.render;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import se.fusion1013.Main;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity> extends EntityRendererMixin<T> {

    @Inject(method = "getRenderLayer", at = @At("HEAD"), cancellable = true)
    protected void getRenderLayer(T entity, boolean showBody, boolean translucent, boolean showOutline, CallbackInfoReturnable<RenderLayer> cir) {
        Identifier identifier = getTexture(entity);
        if (entity instanceof PlayerEntity) {
            Main.LOGGER.info("ShowBody: " + showBody + ", Translucent: " + translucent + ", ShowOutline: " + showOutline);
            // cir.setReturnValue(RenderLayer.getItemEntityTranslucentCull(identifier));
        }
    }
// Ordinal 9
    @ModifyVariable(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "STORE"), ordinal = 0)
    public boolean render(boolean original) {
        return original;
    }

    @Inject(method = "isVisible", at = @At("RETURN"), cancellable = true)
    protected void isVisible(T entity, CallbackInfoReturnable<Boolean> cir) {
        // cir.setReturnValue(false);
    }
}
