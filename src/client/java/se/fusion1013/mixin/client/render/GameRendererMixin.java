package se.fusion1013.mixin.client.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import se.fusion1013.effect.CobaltEffects;
import se.fusion1013.util.Darkness;
import se.fusion1013.util.LightmapAccess;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Shadow @Final private LightmapTextureManager lightmapTextureManager;
    @Shadow @Final MinecraftClient client;

    @Unique
    private static final int OUT_DURATION = 100;

    @Inject(method = "renderWorld", at = @At("HEAD"))
    public void renderWorld(float tickDelta, long limitTime, MatrixStack matrices, CallbackInfo ci) {
        final LightmapAccess lightmap = (LightmapAccess) lightmapTextureManager;

        float darknessAmount = getTargetDarkness(tickDelta);
        if (darknessAmount <= 0.0F) return;

        if (lightmap.darkness_isDirty()) {
            client.getProfiler().push("lightTex");
            Darkness.updateLuminance(tickDelta, client, (GameRenderer) (Object) this, lightmap.darkness_prevFlicker(), darknessAmount);
            client.getProfiler().pop();
        }
    }

    @Unique
    private float getTargetDarkness(float tickDelta) {
        ClientPlayerEntity player = client.player;
        if (player == null) return 0;
        if (!player.hasStatusEffect(CobaltEffects.DARK_SHADOWS)) return 0;

        StatusEffectInstance effect = player.getStatusEffect(CobaltEffects.DARK_SHADOWS);
        if (effect == null) return 0;

        if (effect.isDurationBelow(OUT_DURATION)) return effect.getDuration() / (float)OUT_DURATION;
        else {
            float delta = effect.getFactorCalculationData().get().lerp(player, tickDelta);
            return MathHelper.lerp(delta, 0f, 1f);
        }
    }

}
