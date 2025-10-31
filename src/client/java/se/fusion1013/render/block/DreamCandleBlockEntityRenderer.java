package se.fusion1013.render.block;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.PointLight;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;
import se.fusion1013.block.dream.DreamCandleBlock;
import se.fusion1013.block.dream.DreamCandleBlockEntity;

import java.util.HashMap;
import java.util.Map;

public class DreamCandleBlockEntityRenderer implements BlockEntityRenderer<DreamCandleBlockEntity> {

    private static final Map<BlockPos, PointLight> lights = new HashMap<>();
    private static final float POINT_LIGHT_BRIGHTNESS_SPEED = 0.05f;
    private static final float POINT_LIGHT_MAX_BRIGHTNESS = 0.8f;
    private static final Vector3f DEFAULT_LIGHT_COLOR = new Vector3f(50/255f, 123/255f, 168/255f);

    public DreamCandleBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(DreamCandleBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        World world = entity.getWorld();
        if (world == null) return;

        renderLights(entity, world.getBlockState(entity.getPos()), tickDelta);
    }

    private void renderLights(DreamCandleBlockEntity entity, BlockState state, float tickDelta) {
        if (!entity.isRemoved()) {
            boolean lit = state.get(DreamCandleBlock.LIT);
            updatePointLight(entity, lit, tickDelta, state);
        } else {
            PointLight light = lights.get(entity.getPos());
            if (light != null) {
                lights.remove(entity.getPos());
                VeilRenderSystem.renderer().getLightRenderer().removeLight(light);
            }
        }
    }

    private void updatePointLight(DreamCandleBlockEntity entity, boolean lit, float tickDelta, BlockState state) {
        PointLight pointLight = lights.get(entity.getPos());
        if (pointLight == null) pointLight = createPointLight(entity);

        float targetBrightness = lit ? POINT_LIGHT_MAX_BRIGHTNESS :
                0;
        float brightness = MathHelper.lerp(tickDelta * POINT_LIGHT_BRIGHTNESS_SPEED, pointLight.getBrightness(), targetBrightness);
        pointLight.setBrightness(brightness);
        int targetRadius = DreamCandleBlock.STATE_TO_LUMINANCE.applyAsInt(state);
        pointLight.setRadius(brightness * targetRadius);
    }

    private PointLight createPointLight(DreamCandleBlockEntity entity) {
        Vec3d blockCenter = getCenterPosition(entity);
        PointLight pointLight = new PointLight()
                .setPosition(blockCenter.x, blockCenter.y, blockCenter.z)
                .setRadius(8f)
                .setBrightness(0)
                .setColor(DEFAULT_LIGHT_COLOR);
        VeilRenderSystem.renderer().getLightRenderer().addLight(pointLight);
        lights.put(entity.getPos(), pointLight);
        return pointLight;
    }

    private Vec3d getCenterPosition(DreamCandleBlockEntity entity) {
        Vec3d blockCenter = entity.getPos().toCenterPos();
        return new Vec3d(blockCenter.x, entity.getPos().getY() + 6/16f, blockCenter.z);
    }
}
