package se.fusion1013.render.block;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.deferred.light.AreaLight;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apache.commons.math3.complex.Quaternion;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import se.fusion1013.block.DirectionalLightContainerBlock;
import se.fusion1013.block.ProjectorBlock;
import se.fusion1013.block.entity.ProjectorBlockEntity;

import java.util.HashMap;
import java.util.Map;

import static se.fusion1013.render.block.DirectionalLightHolderBlockEntityRenderer.*;

public class ProjectorBlockEntityRenderer implements BlockEntityRenderer<ProjectorBlockEntity> {

    private static final Map<ProjectorBlockEntity, AreaLight> lights = new HashMap<>();
    private static final Vector3f DEFAULT_LIGHT_COLOR = new Vector3f(50/255f, 123/255f, 168/255f);

    // Area light settings
    private static final float AREA_LIGHT_BRIGHTNESS_SPEED = 0.1f;
    private static final float AREA_LIGHT_MAX_BRIGHTNESS = 2;
    private static final float AREA_LIGHT_ANGLE_SPEED = 0.1f;
    private static final float AREA_LIGHT_OPEN_ANGLE = 45;
    private static final float AREA_LIGHT_FOCUSED_ANGLE = 20;
    private static final float AREA_LIGHT_DISTANCE_SPEED = 0.1f;
    private static final float AREA_LIGHT_OPEN_DISTANCE = 16;
    private static final float AREA_LIGHT_FOCUSED_DISTANCE = 32;

    public ProjectorBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(ProjectorBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        World world = entity.getWorld();
        BlockState state = world.getBlockState(entity.getPos());

        if (!entity.isRemoved()) renderLight(entity, state, tickDelta, state.get(ProjectorBlock.HAS_SLIDE_REEL));
        else removeLight(entity);
    }

    private void renderLight(ProjectorBlockEntity entity, BlockState state, float tickDelta, boolean isLit) {
        AreaLight light = lights.get(entity);
        Direction facing = state.get(DirectionalLightContainerBlock.FACING);
        Vec3d offsets = getOffsets(facing);
        float rotation = DirectionalLightHolderBlockEntityRenderer.getRotation(facing);

        // Create light if it does not exist
        if (light == null) light = createLight(entity, (float) offsets.x, (float) offsets.z, rotation);

        // Calculate values
        float brightness = MathHelper.lerp(tickDelta * AREA_LIGHT_BRIGHTNESS_SPEED, light.getBrightness(), isLit ? AREA_LIGHT_MAX_BRIGHTNESS : 0);
        Vector3fc color = getLerpedColor(light.getColor(), DirectionalLightContainerBlock.LensType.BLUE, tickDelta);
        float angle = MathHelper.lerp(tickDelta * AREA_LIGHT_ANGLE_SPEED, light.getAngle(), toRad(AREA_LIGHT_FOCUSED_ANGLE));
        float distance = MathHelper.lerp(tickDelta * AREA_LIGHT_DISTANCE_SPEED, light.getDistance(), isLit ? AREA_LIGHT_FOCUSED_DISTANCE : 0);

        // Update values
        light.setBrightness(brightness);
        light.setColor(color);
        light.setAngle(angle);
        light.setDistance(distance);
    }

    private static AreaLight createLight(ProjectorBlockEntity entity, float xOffset, float zOffset, float rotation) {
        AreaLight light;
        Vec3d blockCenterPos = entity.getPos().toCenterPos();
        light = new AreaLight()
                .setPosition(blockCenterPos.x + xOffset, entity.getPos().getY() + 6/16f, blockCenterPos.z + zOffset)
                .setBrightness(0f)
                .setDistance(0)
                .setAngle(0)
                .setColor(DEFAULT_LIGHT_COLOR)
                .setOrientation(new Quaternionf().rotationXYZ(0, rotation * ((float)Math.PI * 2) / 4, 0))
                .setSize(.25, .25);
        VeilRenderSystem.renderer().getDeferredRenderer().getLightRenderer().addLight(light);
        lights.put(entity, light);
        return light;
    }

    private void removeLight(ProjectorBlockEntity entity) {
        AreaLight light = lights.get(entity);
        if (light != null) VeilRenderSystem.renderer().getDeferredRenderer().getLightRenderer().removeLight(light);
    }
}
