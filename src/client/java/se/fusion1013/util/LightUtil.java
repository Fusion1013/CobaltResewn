package se.fusion1013.util;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.deferred.light.AreaLight;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.MathHelper;
import org.joml.Quaternionf;
import se.fusion1013.items.CobaltItems;

public class LightUtil {

    public static void setAreaLightToHead(LivingEntity entity, AreaLight light) {
        light.setPosition(entity.getX(), entity.getEyeY(), entity.getZ());
        float pitch = MathHelper.RADIANS_PER_DEGREE * entity.getPitch();
        float headYaw = MathHelper.RADIANS_PER_DEGREE * entity.getHeadYaw();
        float roll = MathHelper.RADIANS_PER_DEGREE * entity.getRoll();
        light.setOrientation(new Quaternionf().rotationXYZ(-pitch, headYaw, roll));
    }

    public static AreaLight renderFlashlight(LivingEntity entity, PlayerInventory inventory, AreaLight flashlightLight) {
        var mainHandStack = inventory.getMainHandStack();

        boolean isHoldingFlashlight = true;

        if (mainHandStack.isEmpty()) isHoldingFlashlight = false;
        else if (mainHandStack.getItem() != CobaltItems.FLASHLIGHT) isHoldingFlashlight = false;

        // Player is holding flashlight

        var lightRenderer = VeilRenderSystem.renderer().getDeferredRenderer().getLightRenderer();

        if (isHoldingFlashlight) {
            if (flashlightLight == null) {
                flashlightLight = new AreaLight();
                flashlightLight.setBrightness(1.0f);
                flashlightLight.setAngle((float)Math.toRadians(40));
                flashlightLight.setDistance(300);
                flashlightLight.setColor(40/255f, 174/255f, 189/255f);
                lightRenderer.addLight(flashlightLight);
            }

            LightUtil.setAreaLightToHead(entity, flashlightLight);

        } else if (flashlightLight != null) {
            lightRenderer.removeLight(flashlightLight);
            flashlightLight = null;
        }

        return flashlightLight;
    }

}
