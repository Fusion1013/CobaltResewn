package se.fusion1013.mixin.client.render.entity;

import foundry.veil.api.client.render.deferred.light.AreaLight;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import se.fusion1013.MainClient;
import se.fusion1013.effect.CobaltEffects;
import se.fusion1013.util.LightUtil;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @Unique
    private AreaLight flashlightLight;

    @Inject(method = "render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("RETURN"))
    public void renderFlashLight(AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        flashlightLight = LightUtil.renderFlashlight(abstractClientPlayerEntity, abstractClientPlayerEntity.getInventory(), flashlightLight);
    }

    @Inject(method = "render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("HEAD"), cancellable = true)
    public void overrideRenderPlayer(AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        ClientPlayerEntity clientPlayer = MinecraftClient.getInstance().player;
        if (clientPlayer == null) return;
        if (clientPlayer.isCreative()) return;

        boolean isSelfDreaming = clientPlayer.hasStatusEffect(CobaltEffects.DREAMING);
        boolean isOtherDreaming = abstractClientPlayerEntity.hasStatusEffect(CobaltEffects.DREAMING);

        if (isSelfDreaming != isOtherDreaming) ci.cancel();
    }
}
