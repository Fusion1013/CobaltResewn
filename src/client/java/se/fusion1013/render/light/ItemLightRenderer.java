package se.fusion1013.render.light;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.AreaLight;
import foundry.veil.platform.VeilEventPlatform;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;
import se.fusion1013.effect.CobaltEffects;
import se.fusion1013.items.components.CobaltComponents;
import se.fusion1013.tags.CobaltTags;
import se.fusion1013.util.ColorUtils;
import se.fusion1013.util.OrientationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ItemLightRenderer {

    public static final HashMap<UUID, AreaLight> ITEM_LIGHT = new HashMap<>();
    public static final HashMap<UUID, Float> DREAMING_MODIFIERS = new HashMap<>();
    public static final int DEFAULT_LIGHT_COLOR = 0xFFf0d3d3;

    public static void init() {
        VeilEventPlatform.INSTANCE.onVeilRenderTypeStageRender(((stage, worldRenderer, immediate, matrixStack, matrix4fc, matrix4fc1, i, renderTickCounter, camera, frustum) -> {
            List<UUID> removeLights = new ArrayList<>(ITEM_LIGHT.keySet());
            for (PlayerEntity entity : MinecraftClient.getInstance().world.getPlayers()) {
                updateBrightnessModifiers(entity, renderTickCounter);
                tickPlayerItemLights(entity, renderTickCounter);
                removeLights.remove(entity.getUuid());
            }
            for (UUID light : removeLights) {
                DREAMING_MODIFIERS.remove(light);
                removeLight(light);
            }
        }));
    }

    private static void updateBrightnessModifiers(PlayerEntity player, RenderTickCounter deltaTracker) {
        float target = player.hasStatusEffect(CobaltEffects.DREAMING) ? 0 : 1;
        float current = DREAMING_MODIFIERS.getOrDefault(player.getUuid(), 1f);
        float newValue = MathHelper.lerp(deltaTracker.getTickDelta(false) * 0.01f, current, target);
        DREAMING_MODIFIERS.put(player.getUuid(), newValue);
    }

    private static boolean hasLightItem(PlayerEntity player) {
        return !getLightItems(player).isEmpty();
    }

    private static List<ItemStack> getLightItems(PlayerEntity player) {
        List<ItemStack> lightItems = new ArrayList<>();

        tryAddLight(lightItems, player.getMainHandStack());
        tryAddLight(lightItems, player.getOffHandStack());
        for (ItemStack armorItem : player.getArmorItems()) {
            tryAddLight(lightItems, armorItem);
        }

        return lightItems;
    }

    private static void tryAddLight(List<ItemStack> lights, ItemStack stack) {
        if (!stack.isIn(CobaltTags.PRODUCES_LIGHT)) return;
        if (stack.get(CobaltComponents.IS_ON) == null) return;
        if (!stack.get(CobaltComponents.IS_ON)) return;
        lights.add(stack);
    }

    private static float getTotalLightDistance(PlayerEntity player) {
        List<ItemStack> lightItems = getLightItems(player);
        float highestLightDistance = 0f;
        for (ItemStack item : lightItems) {
            if (item.get(CobaltComponents.LIGHT_DISTANCE) != null) {
                float distance = item.get(CobaltComponents.LIGHT_DISTANCE);
                highestLightDistance = Math.max(highestLightDistance, distance);
            }
        }
        return highestLightDistance;
    }

    private static int getTotalLightColor(PlayerEntity player) {
        List<ItemStack> lightItems = getLightItems(player);
        int color = DEFAULT_LIGHT_COLOR;
        for (ItemStack lightItem : lightItems) {
            if (lightItem.get(CobaltComponents.COLOR) == null) continue;

            color = ColorUtils.blendColors(color, lightItem.get(CobaltComponents.COLOR));
        }
        return color;
    }

    private static float getLargestBrightness(PlayerEntity player) {
        List<ItemStack> lightItems = getLightItems(player);
        float brightness = 0;
        for (ItemStack lightItem : lightItems) {
            if (lightItem.get(CobaltComponents.LIGHT_INTENSITY) == null) continue;
            float t = lightItem.get(CobaltComponents.LIGHT_INTENSITY);
            if (t < brightness) continue;
            brightness = t;
        }
        return brightness;
    }

    private static float getLargestAngle(PlayerEntity player) {
        List<ItemStack> lightItems = getLightItems(player);
        float angle = 0;
        for (ItemStack lightItem : lightItems) {
            if (lightItem.get(CobaltComponents.LIGHT_ANGLE) == null) continue;
            float t = lightItem.get(CobaltComponents.LIGHT_ANGLE);
            if (t < angle) continue;
            angle = t;
        }
        return angle;
    }

    private static void removeLight(UUID id) {
        if (ITEM_LIGHT.containsKey(id)) {
            AreaLight light = ITEM_LIGHT.get(id);
            VeilRenderSystem.renderer().getLightRenderer().removeLight(light);
            ITEM_LIGHT.remove(id);
        }
    }

    private static AreaLight getOrCreateLight(UUID id, Quaternionf orientation) {
        // Get light if it already exists, if not create it
        if (ITEM_LIGHT.containsKey(id)) {
            return ITEM_LIGHT.get(id);
        } else {
            // Main.LOGGER.info("Created new light");
            AreaLight light = new AreaLight();
            light.setOrientation(orientation);
            ITEM_LIGHT.put(id, light);
            VeilRenderSystem.renderer().getLightRenderer().addLight(light);
            return light;
        }
    }

    private static void tryRenderLight(PlayerEntity player, RenderTickCounter deltaTracker) {
        Quaternionf targetOrientation = OrientationUtils.getOrientation(player.getRotationVector());

        AreaLight light = getOrCreateLight(player.getUuid(), targetOrientation);

        Quaternionf currentOrientation = light.getOrientation();
        Quaternionf newOrientation = currentOrientation.slerp(targetOrientation, deltaTracker.getTickDelta(false) * 0.01f);

        Vec3d renderPosition = player.getLerpedPos(deltaTracker.getTickDelta(false));

        float dreamingModifier = DREAMING_MODIFIERS.get(player.getUuid());
        float brightnessModifier;
        if (dreamingModifier > .8f) brightnessModifier = 1;
        else if (dreamingModifier > .6f) brightnessModifier = 0f;
        else if (dreamingModifier > .3f) brightnessModifier = 1f;
        else brightnessModifier = 0f;

        // Set light position and targetOrientation
        light.setPosition(renderPosition.x, renderPosition.y+1.5, renderPosition.z);
        light.setOrientation(newOrientation);

        light.setBrightness(getLargestBrightness(player) * brightnessModifier);
        light.setAngle(getLargestAngle(player));
        light.setSize(.2f, .2f);

        light.setColor(getTotalLightColor(player));
        light.setDistance(getTotalLightDistance(player));
    }

    private static void tickPlayerItemLights(PlayerEntity player, RenderTickCounter deltaTracker) {
        // If player is no longer holding the flashlight, remove it
        if (!hasLightItem(player)) removeLight(player.getUuid());
        else tryRenderLight(player, deltaTracker);
    }


}
