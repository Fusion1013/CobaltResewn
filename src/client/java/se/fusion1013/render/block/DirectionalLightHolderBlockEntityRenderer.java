package se.fusion1013.render.block;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.VeilRenderer;
import foundry.veil.api.client.render.deferred.light.AreaLight;
import foundry.veil.api.client.render.deferred.light.PointLight;
import net.minecraft.block.BlockState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import se.fusion1013.Main;
import se.fusion1013.block.DirectionalLightContainerBlock;
import se.fusion1013.block.entity.DirectionalLightHolderBlockEntity;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DirectionalLightHolderBlockEntityRenderer implements BlockEntityRenderer<DirectionalLightHolderBlockEntity> {

    private static final Vector3f DEFAULT_LIGHT_COLOR = new Vector3f(50/255f, 123/255f, 168/255f);

    // Point light settings
    private static final float POINT_LIGHT_BRIGHTNESS_SPEED = 0.1f;
    private static final float POINT_LIGHT_MAX_BRIGHTNESS = 1;

    // Area light settings
    private static final float AREA_LIGHT_BRIGHTNESS_SPEED = 0.1f;
    private static final float AREA_LIGHT_MAX_BRIGHTNESS = 2;
    private static final float AREA_LIGHT_ANGLE_SPEED = 0.1f;
    private static final float AREA_LIGHT_OPEN_ANGLE = 45;
    private static final float AREA_LIGHT_FOCUSED_ANGLE = 10;
    private static final float AREA_LIGHT_DISTANCE_SPEED = 0.1f;
    private static final float AREA_LIGHT_OPEN_DISTANCE = 16;
    private static final float AREA_LIGHT_FOCUSED_DISTANCE = 32;


    private final ModelPart testPart;

    public DirectionalLightHolderBlockEntityRenderer(ModelPart root) {
        testPart = root.getChild("bb_main");
    }

    public static TexturedModelData getTestTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, 0.0F, 8.0F, 8.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    // :: TODO :: Look at this: https://github.com/JR1811/PulchraOccultorum/blob/master/src/main/java/net/shirojr/pulchra_occultorum/block/entity/client/renderer/FlagPoleBlockEntityRenderer.java
    // :: TODO :: It might have solution to how to do this shit

    @Override
    public void render(DirectionalLightHolderBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        World world = entity.getWorld();
        if (world == null) return;
        BlockState state = world.getBlockState(entity.getPos());

        renderLights(entity, state, tickDelta);

        if (entity.isRemoved()) return;

        LightHolderBlockEntityRenderer.tryRenderLightSoul(entity, tickDelta, matrices, vertexConsumers, light, overlay);
        DirectionalLightContainerBlock.LensType lensType = state.get(DirectionalLightContainerBlock.LENS_TYPE);

        if (lensType != DirectionalLightContainerBlock.LensType.NONE) renderLens(state, matrices, vertexConsumers, light, overlay, lensType == DirectionalLightContainerBlock.LensType.CLEAR ? Color.WHITE : lensType.color);
    }

    private void renderLights(DirectionalLightHolderBlockEntity entity, BlockState state, float tickDelta) {
        if (!entity.isRemoved()) {
            boolean isLit = state.get(DirectionalLightContainerBlock.LIT);
            updatePointLight(entity, state, tickDelta, isLit);
            updateAreaLight(entity, state, tickDelta, isLit);
        } else {
            AreaLight areaLight = entity.getAreaLight();
            if (areaLight != null) {
                VeilRenderSystem.renderer().getDeferredRenderer().getLightRenderer().removeLight(areaLight);
            }
            PointLight pointLight = entity.getPointLight();
            if (pointLight != null) {
                VeilRenderSystem.renderer().getDeferredRenderer().getLightRenderer().removeLight(pointLight);
            }
        }
    }

    private void updateAreaLight(DirectionalLightHolderBlockEntity entity, BlockState state, float tickDelta, boolean isLit) {
        Direction facing = state.get(DirectionalLightContainerBlock.FACING);
        DirectionalLightContainerBlock.LensType lens = state.get(DirectionalLightContainerBlock.LENS_TYPE);
        Vec3d offsets = getOffsets(facing);

        // Get the area light
        AreaLight areaLight = entity.getAreaLight();
        if (areaLight == null) areaLight = createAreaLight(entity, facing, (float) offsets.x, (float) offsets.z);

        // Calculate values
        float brightness = MathHelper.lerp(tickDelta * AREA_LIGHT_BRIGHTNESS_SPEED, areaLight.getBrightness(), isLit ? AREA_LIGHT_MAX_BRIGHTNESS : 0);
        Vector3fc color = getLerpedColor(areaLight.getColor(), lens, tickDelta);
        float angle = MathHelper.lerp(tickDelta * AREA_LIGHT_ANGLE_SPEED, areaLight.getAngle(), lens == DirectionalLightContainerBlock.LensType.NONE ? toRad(AREA_LIGHT_OPEN_ANGLE) : toRad(AREA_LIGHT_FOCUSED_ANGLE));
        float distance = MathHelper.lerp(tickDelta * AREA_LIGHT_DISTANCE_SPEED, areaLight.getDistance(), isLit ? (lens == DirectionalLightContainerBlock.LensType.NONE ? AREA_LIGHT_OPEN_DISTANCE : AREA_LIGHT_FOCUSED_DISTANCE) : 0);

        // Update values
        areaLight.setBrightness(brightness);
        areaLight.setColor(color);
        areaLight.setAngle(angle);
        areaLight.setDistance(distance);
    }

    private void updatePointLight(DirectionalLightHolderBlockEntity entity, BlockState state, float tickDelta, boolean isLit) {
        Direction facing = state.get(DirectionalLightContainerBlock.FACING);
        DirectionalLightContainerBlock.LensType lens = state.get(DirectionalLightContainerBlock.LENS_TYPE);
        Vec3d offsets = getOffsets(facing);

        // Get the point light
        PointLight pointLight = entity.getPointLight();
        if (pointLight == null) pointLight = createPointLight(entity, (float) offsets.x, (float) offsets.z);

        // Calculate values
        float brightness = MathHelper.lerp(tickDelta * POINT_LIGHT_BRIGHTNESS_SPEED, pointLight.getBrightness(), isLit ? POINT_LIGHT_MAX_BRIGHTNESS : 0);
        Vector3fc color = getLerpedColor(pointLight.getColor(), lens, tickDelta);

        // Update values
        pointLight.setBrightness(brightness);
        pointLight.setColor(color);
    }

    public static @NotNull Vector3fc getLerpedColor(Vector3fc currentColor, DirectionalLightContainerBlock.LensType lens, float tickDelta) {
        Color targetColor = lens == DirectionalLightContainerBlock.LensType.NONE ? new Color(DEFAULT_LIGHT_COLOR.x, DEFAULT_LIGHT_COLOR.y, DEFAULT_LIGHT_COLOR.z) : lens.color;
        return new Vector3f(
                MathHelper.lerp(tickDelta * 0.1f, currentColor.x(), targetColor.getRed() / 255f),
                MathHelper.lerp(tickDelta * 0.1f, currentColor.y(), targetColor.getGreen() / 255f),
                MathHelper.lerp(tickDelta * 0.1f, currentColor.z(), targetColor.getBlue() / 255f)
        );
    }

    private PointLight createPointLight(DirectionalLightHolderBlockEntity entity, float xOffset, float zOffset) {
        Vec3d blockCenter = getCenterPosition(entity);
        PointLight pointLight = new PointLight()
                .setPosition(blockCenter.x + (xOffset * 1.2), entity.getPos().getY() + 6/16f, blockCenter.z + (zOffset * 1.2))
                .setRadius(1f);
        VeilRenderSystem.renderer().getDeferredRenderer().getLightRenderer().addLight(pointLight);
        entity.setPointLight(pointLight);
        return pointLight;
    }

    private AreaLight createAreaLight(DirectionalLightHolderBlockEntity entity, Direction facing, float xOffset, float zOffset) {
        Vec3d blockCenter = getCenterPosition(entity);
        float rotation = getRotation(facing);
        AreaLight areaLight = new AreaLight()
                .setPosition(blockCenter.x + xOffset, entity.getPos().getY() + 6/16f, blockCenter.z + zOffset)
                .setBrightness(0f)
                .setDistance(0)
                .setAngle(0)
                .setColor(DEFAULT_LIGHT_COLOR.x, DEFAULT_LIGHT_COLOR.y, DEFAULT_LIGHT_COLOR.z)
                .setOrientation(new Quaternionf().rotationXYZ(0, rotation * ((float)Math.PI * 2) / 4, 0))
                .setSize(.25, .25);
        VeilRenderSystem.renderer().getDeferredRenderer().getLightRenderer().addLight(areaLight);
        entity.setAreaLight(areaLight);
        return areaLight;
    }

    public static int getRotation(Direction facing) {
        return switch (facing) {
            case DOWN, UP, SOUTH -> 0;
            case WEST -> 1;
            case NORTH -> 2;
            case EAST -> 3;
        };
    }

    public static Vec3d getOffsets(Direction facing) {
        return switch (facing) {
            case DOWN, UP -> new Vec3d(0, 0, 0);
            case NORTH -> new Vec3d(0, 0, -5.5f/16f);
            case SOUTH -> new Vec3d(0, 0, 5.5f/16f);
            case WEST -> new Vec3d(-5.5f/16f, 0, 0);
            case EAST -> new Vec3d(5.5f/16f, 0, 0);
        };
    }

    private Vec3d getCenterPosition(DirectionalLightHolderBlockEntity entity) {
        Vec3d blockCenter = entity.getPos().toCenterPos();
        return new Vec3d(blockCenter.x, entity.getPos().getY() + 6/16f, blockCenter.z);
    }

    public static float toRad(float deg) {
        return deg * ((float)Math.PI / 180);
    }

    private void renderLens(BlockState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, Color color) {
        Direction facing = state.get(DirectionalLightContainerBlock.FACING);

        Vec3d offset = new Vec3d(0, 0, 0);
        float rotation = 0;

        switch (facing) {
            case NORTH -> {
                offset = new Vec3d(0.0D, 0.0D, -5 / 16f);
                rotation = 0;
            }
            case SOUTH -> {
                offset = new Vec3d(0.0D, 0.0D, 5 / 16f);
                rotation = 180;
            }
            case WEST -> {
                offset = new Vec3d(-5 / 16f, 0.0D, 0.0D);
                rotation = 270;
            }
            case EAST -> {
                offset = new Vec3d(5 / 16f, 0.0D, 0.0D);
                rotation = 90;
            }
        }

        matrices.push();

        matrices.translate(0.5, -14/16f, 0.5);
        matrices.translate(offset.x, offset.y, offset.z);

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotation));

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(getRenderLayer());
        testPart.render(matrices, vertexConsumer, light, overlay, color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, 1);

        matrices.pop();
    }

    private RenderLayer getRenderLayer() {
        String texturePath = "textures/entity/lens.png";
        return RenderLayer.getEntityTranslucent(new Identifier(Main.MOD_NAMESPACE, texturePath));
    }
}
