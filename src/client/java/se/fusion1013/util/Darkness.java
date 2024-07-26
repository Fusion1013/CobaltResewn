package se.fusion1013.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class Darkness {

    public static boolean enabled = false;
    private static final float[][] LUMINANCE = new float[16][16];

    public static void updateLuminance(float tickDelta, MinecraftClient client, GameRenderer worldRenderer, float prevFlicker, float amount) {
        final ClientWorld world = client.world;
        final ClientPlayerEntity player = client.player;

        if (world == null) return;
        if (player == null) return;

        if (!isDark(world) || player.hasStatusEffect(StatusEffects.NIGHT_VISION) || (player.hasStatusEffect(StatusEffects.CONDUIT_POWER) && player.getUnderwaterVisibility() > 0) || world.getLightningTicksLeft() > 0) {
            enabled = false;
            return;
        } else {
            enabled = true;
        }

        final float dimSkyFactor = 1; // TODO
        final float ambient = world.getSkyBrightness(1.0f);
        final DimensionType dimensionType = world.getDimension();
        final boolean blockAmbient = !Darkness.isDark(world);

        for (int skyIndex = 0; skyIndex < 16; ++skyIndex) {

            float skyFactor = 1f - skyIndex / 15f;
            skyFactor = 1 - (skyFactor * skyFactor * skyFactor * skyFactor * amount);
            skyFactor *= dimSkyFactor;

            float min = skyFactor * 0.05f;
            final float rawAmbient = ambient * skyFactor;
            final float minAmbient = rawAmbient * (1 - min) + min;
            final float skyBase = LightmapTextureManager.getBrightness(dimensionType, skyIndex) * minAmbient;

            min = 0.35f * skyFactor;
            float skyRed = skyBase * (rawAmbient * (1 - min) + min);
            float skyGreen = skyBase * (rawAmbient * (1 - min) + min);
            float skyBlue = skyBase;

            if (worldRenderer.getSkyDarkness(tickDelta) > 0.0F) {
                final float skyDarkness = worldRenderer.getSkyDarkness(tickDelta);
                skyRed = skyRed * (1.0F - skyDarkness) + skyRed * 0.7F * skyDarkness;
                skyGreen = skyGreen * (1.0F - skyDarkness) + skyGreen * 0.6F * skyDarkness;
                skyBlue = skyBlue * (1.0F - skyDarkness) + skyBlue * 0.6F * skyDarkness;
            }

            for (int blockIndex = 0; blockIndex < 16; ++blockIndex) {

                float blockFactor = 1f;

                if (!blockAmbient) {
                    blockFactor = 1f - blockIndex / 15f;
                    blockFactor = 1 - (blockFactor * blockFactor * blockFactor * blockFactor * amount);
                }

                final float blockBase = blockFactor * LightmapTextureManager.getBrightness(dimensionType, blockIndex) * (prevFlicker * 0.1F + 1.5F);
                min = 0.4f * blockFactor;
                final float blockGreen = blockBase * ((blockBase * (1 - min) + min) * (1 - min) + min);
                final float blockBlue = blockBase * (blockBase * blockBase * (1 - min) + min);

                float red = skyRed + blockBase;
                float green = skyGreen + blockGreen;
                float blue = skyBlue + blockBlue;

                final float f = Math.max(skyFactor, blockFactor);
                min = 0.03f * f;
                red = red * (0.99F - min) + min;
                green = green * (0.99F - min) + min;
                blue = blue * (0.99F - min) + min;

                if (red > 1.0F) {
                    red = 1.0F;
                }

                if (green > 1.0F) {
                    green = 1.0F;
                }

                if (blue > 1.0F) {
                    blue = 1.0F;
                }

                final float gamma = client.options.getGamma().getValue().floatValue() * f;
                float invRed = 1.0F - red;
                float invGreen = 1.0F - green;
                float invBlue = 1.0F - blue;
                invRed = 1.0F - invRed * invRed * invRed * invRed;
                invGreen = 1.0F - invGreen * invGreen * invGreen * invGreen;
                invBlue = 1.0F - invBlue * invBlue * invBlue * invBlue;
                red = red * (1.0F - gamma) + invRed * gamma;
                green = green * (1.0F - gamma) + invGreen * gamma;
                blue = blue * (1.0F - gamma) + invBlue * gamma;

                min = 0.03f * f;
                red = red * (0.99F - min) + min;
                green = green * (0.99F - min) + min;
                blue = blue * (0.99F - min) + min;

                if (red > 1.0F) {
                    red = 1.0F;
                }

                if (green > 1.0F) {
                    green = 1.0F;
                }

                if (blue > 1.0F) {
                    blue = 1.0F;
                }

                if (red < 0.0F) {
                    red = 0.0F;
                }

                if (green < 0.0F) {
                    green = 0.0F;
                }

                if (blue < 0.0F) {
                    blue = 0.0F;
                }

                LUMINANCE[blockIndex][skyIndex] = Darkness.luminance(red, green, blue);
            }
        }
    }

    private static boolean isDark(ClientWorld world) {
        final RegistryKey<World> dimensionType = world.getRegistryKey();

        if (dimensionType == ClientWorld.OVERWORLD) {
            return true;
        }
        return false;
    }

    public static int darken(int c, int blockIndex, int skyIndex) {

        // Fetch the target luminance from the precomputed array
        final float luminanceTarget = LUMINANCE[blockIndex][skyIndex];

        // Extract red, green and blue components and normalize to [0..1]
        final float r = (c & 0xFF) / 255f;
        final float g = (c >> 8 & 0xFF) / 255f;
        final float b = (c >> 16 & 0xFF) / 255f;

        // Compute the current luminance of the color
        final float luminance = luminance(r, g, b);

        // Calculate the adjustment factor
        final float adjustmentFactor = luminance > 0 ?
                Math.min(1, luminanceTarget / luminance) :
                0;

        // Adjust color brightness if needed
        return adjustmentFactor == 1f ?
                c :
                0xFF000000 | Math.round(adjustmentFactor * r * 255) | (Math.round(adjustmentFactor * g * 255) << 8) | (Math.round(adjustmentFactor * b * 255) << 16);
    }

    public static float luminance(float r, float g, float b) {
        return r * 0.2126f + g * 0.7152f + b * 0.0722f;
    }

}
