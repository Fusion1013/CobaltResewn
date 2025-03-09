package se.fusion1013.render.block;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.deferred.light.AreaLight;
import foundry.veil.api.client.render.deferred.light.PointLight;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.joml.Quaternionf;
import se.fusion1013.block.entity.SpotlightBlockEntity;

import java.util.HashMap;
import java.util.Map;

import static net.minecraft.block.HorizontalFacingBlock.FACING;

public class SpotlightBlockEntityRenderer implements BlockEntityRenderer<SpotlightBlockEntity> {

    private static final Map<SpotlightBlockEntity, AreaLight> lights = new HashMap<>();

    public SpotlightBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(SpotlightBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        World world = entity.getWorld();
        BlockState state  = world.getBlockState(entity.getPos());

        if (!entity.isRemoved()) {
            Direction direction = state.get(FACING);
            Vec3i dirVec = direction.getVector();

            AreaLight pointLight = lights.get(entity);
            if (pointLight == null) {
                Vec3d blockCenterPos = entity.getPos().toCenterPos();
                pointLight = new AreaLight()
                        .setPosition(blockCenterPos.x - dirVec.getX() * 0.5f, blockCenterPos.y - dirVec.getY() * 0.5f, blockCenterPos.z - dirVec.getZ() * 0.5f)
                        .setBrightness(1.0f)
                        .setOrientation(new Quaternionf().lookAlong(-dirVec.getX(), -dirVec.getY(), -dirVec.getZ(), 0, 1, 0))
                        .setDistance(16)
                        .setSize(3f/16, 3f/16)
                        .setColor(50/255f, 123/255f, 168/255f);
                VeilRenderSystem.renderer().getDeferredRenderer().getLightRenderer().addLight(pointLight);
                lights.put(entity, pointLight);
            }
        } else {
            AreaLight pointLight = lights.get(entity);
            if (pointLight != null) {
                VeilRenderSystem.renderer().getDeferredRenderer().getLightRenderer().removeLight(pointLight);
            }
        }
    }
}
