package se.fusion1013.mixin.client.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import se.fusion1013.util.Darkness;
import se.fusion1013.util.LightmapAccess;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Shadow @Final private LightmapTextureManager lightmapTextureManager;
    @Shadow @Final MinecraftClient client;

    @Inject(method = "renderWorld", at = @At("HEAD"))
    public void renderWorld(float tickDelta, long limitTime, MatrixStack matrices, CallbackInfo ci) {
        final LightmapAccess lightmap = (LightmapAccess) lightmapTextureManager;

        if (lightmap.darkness_isDirty()) {
            client.getProfiler().push("lightTex");
            Darkness.updateLuminance(tickDelta, client, (GameRenderer) (Object) this, lightmap.darkness_prevFlicker(), 1.0f);
            client.getProfiler().pop();
        }
    }

}
