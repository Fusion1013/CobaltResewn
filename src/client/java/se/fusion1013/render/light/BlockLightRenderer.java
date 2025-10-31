package se.fusion1013.render.light;

import foundry.veil.api.client.render.light.AreaLight;
import foundry.veil.api.client.render.light.Light;
import foundry.veil.api.client.render.light.PointLight;
import foundry.veil.platform.VeilEventPlatform;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BlockLightRenderer {

    public static final HashMap<BlockPos, List<AreaLight>> BLOCK_AREA_LIGHTS = new HashMap<>();
    public static final HashMap<BlockPos, List<PointLight>> BLOCK_POINT_LIGHTS = new HashMap<>();

    public static void init() {
        VeilEventPlatform.INSTANCE.onVeilRenderTypeStageRender(((stage, worldRenderer, immediate, matrixStack, matrix4fc, matrix4fc1, i, renderTickCounter, camera, frustum) -> {

        }));
    }

}
