package se.fusion1013.render.block;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.deferred.light.PointLight;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import se.fusion1013.Main;
import se.fusion1013.block.LightContainerBlock;
import se.fusion1013.block.entity.LightHolderBlockEntity;
import se.fusion1013.items.CobaltItems;

import java.util.HashMap;
import java.util.Map;

public class LightHolderBlockEntityRenderer implements BlockEntityRenderer<LightHolderBlockEntity> {

    private static final ItemStack stack = new ItemStack(CobaltItems.MiscItems.LIGHT_SOUL, 1);

    private static final Map<LightHolderBlockEntity, PointLight> lights = new HashMap<>();

    public LightHolderBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(LightHolderBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        tryRenderLightSoul(entity, tickDelta, matrices, vertexConsumers, light, overlay);
        tryRenderPointLight(entity, tickDelta);
    }

    public static void tryRenderPointLight(LightHolderBlockEntity entity, float tickDelta) {
        World world = entity.getWorld();
        BlockState state = world.getBlockState(entity.getPos());

        if (!entity.isRemoved()) {
            PointLight pointLight = lights.get(entity);
            if (pointLight == null) {
                Vec3d blockCenterPos = entity.getPos().toCenterPos();
                pointLight = new PointLight()
                        .setPosition(blockCenterPos.x, blockCenterPos.y + 1, blockCenterPos.z)
                        .setBrightness(1.0f)
                        .setRadius(0f)
                        .setColor(50/255f, 123/255f, 168/255f);
                VeilRenderSystem.renderer().getDeferredRenderer().getLightRenderer().addLight(pointLight);
                lights.put(entity, pointLight);
            }

            boolean isLit = state.get(LightContainerBlock.LIT);

            // float brightness = MathHelper.lerp(tickDelta, pointLight.getBrightness(), isLit ? 1f : 0f);
            float radius = MathHelper.lerp(tickDelta * 0.1f, pointLight.getRadius(), isLit ? 16f : 0f);

            // pointLight.setBrightness(brightness);
            pointLight.setRadius(radius);

        } else {
            PointLight pointLight = lights.get(entity);
            if (pointLight != null) {
                VeilRenderSystem.renderer().getDeferredRenderer().getLightRenderer().removeLight(pointLight);
            }
        }
    }

    public static void tryRenderLightSoul(LightHolderBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        World world = entity.getWorld();
        BlockState state = world.getBlockState(entity.getPos());

        if (!entity.isRemoved()) {
            renderLightSoul(state, tickDelta, matrices, entity, vertexConsumers);
        }

        matrices.pop();
    }

    private static void renderLightSoul(BlockState state, float tickDelta, MatrixStack matrices, LightHolderBlockEntity entity, VertexConsumerProvider vertexConsumers) {
        // If block is lit, render light soul inside of it
        if (state.get(LightContainerBlock.LIT)) {
            double offset = Math.sin(MathHelper.lerp(tickDelta, (float)LightHolderBlockEntity.getLastTick(), (float)LightHolderBlockEntity.getTick()) / 32f) / 32.0;

            matrices.translate(0.5, (1/16f)*6 + offset, 0.5);

            int lightAbove = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().up());
            MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.GROUND, 16, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 0);
        }
    }
}
