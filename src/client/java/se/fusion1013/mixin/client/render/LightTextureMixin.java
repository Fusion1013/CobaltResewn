package se.fusion1013.mixin.client.render;

import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.texture.NativeImage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import se.fusion1013.util.Darkness;
import se.fusion1013.util.LightmapAccess;

@Mixin(LightmapTextureManager.class)
public class LightTextureMixin implements LightmapAccess {

    @Shadow @Final private NativeImage image;

    @Shadow private float flickerIntensity;

    @Shadow private boolean dirty;

    @Inject(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/NativeImageBackedTexture;upload()V"))
    void onUpload(float delta, CallbackInfo ci) {
        if (Darkness.enabled && image != null) {
            for (int b = 0; b < 16; b++) {
                for (int s = 0; s < 16; s++) {
                    final int color = Darkness.darken(image.getColor(b, s), b, s);
                    image.setColor(b, s, color);
                }
            }
        }
    }

    @Override
    public boolean darkness_isDirty() {
        return dirty;
    }

    @Override
    public float darkness_prevFlicker() {
        return flickerIntensity;
    }
}
