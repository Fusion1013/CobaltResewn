package se.fusion1013.render.block;

import foundry.veil.api.client.color.ColorTheme;
import foundry.veil.api.client.tooltip.Tooltippable;
import foundry.veil.api.client.tooltip.VeilUIItemTooltipDataHolder;
import foundry.veil.api.client.tooltip.anim.TooltipTimeline;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import se.fusion1013.block.entity.ItemDisplayBlockEntity;

import java.util.ArrayList;
import java.util.List;

public class ItemDisplayBlockEntityRenderer implements BlockEntityRenderer<ItemDisplayBlockEntity> {

    public ItemDisplayBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    @Override
    public void render(ItemDisplayBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        int lightAbove = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().up());
        var t = MathHelper.lerp(tickDelta, (float)ItemDisplayBlockEntity.getLastTick(), (float)ItemDisplayBlockEntity.getTick());

        renderTransformedItem(entity, t, matrices, vertexConsumers, lightAbove);
        renderInteriorItem(entity, t, matrices, vertexConsumers, lightAbove);
    }

    private void renderInteriorItem(ItemDisplayBlockEntity entity, float t, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int lightAbove) {
        matrices.push();

        var stack = entity.getStack(0);

        if (!entity.getStack(0).isEmpty()) {
            // -- Render the interior item
            matrices.translate(.5, .5, .5); // Translate it to the center of the block
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(t * .5f));

            MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 0);
        }

        matrices.pop();
    }

    private void renderTransformedItem(ItemDisplayBlockEntity entity, float t, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int lightAbove) {
        matrices.push();

        var stack = entity.getStack(0);

        if (!entity.getStack(0).isEmpty()) {

            // -- Render the transformed item

            // Translation
            // Add .5 to each to center it inside the block by default
            var xOffset = entity.getOffset().x + .5f + Math.sin(t * entity.getOffsetFrequency().x) * entity.getOffsetAmplitude().x;
            var yOffset = entity.getOffset().y + .5f + Math.sin(t * entity.getOffsetFrequency().y) * entity.getOffsetAmplitude().y;
            var zOffset = entity.getOffset().z + .5f + Math.sin(t * entity.getOffsetFrequency().z) * entity.getOffsetAmplitude().z;
            matrices.translate(xOffset, yOffset, zOffset);

            // Rotation
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(entity.getRotation().x + t * entity.getRotationSpeed().x));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.getRotation().y + t * entity.getRotationSpeed().y));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(entity.getRotation().z + t * entity.getRotationSpeed().z));

            // Scale
            float xScale = (float) (entity.getScale().x + Math.sin(t * entity.getScaleFrequency().x) * entity.getScaleAmplitude().x);
            float yScale = (float) (entity.getScale().y + Math.sin(t * entity.getScaleFrequency().y) * entity.getScaleFrequency().y);
            float zScale = (float) (entity.getScale().z + Math.sin(t * entity.getScaleFrequency().z) * entity.getScaleFrequency().z);
            matrices.scale(xScale, yScale, zScale);

            // Render the item
            MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 0);
        }

        matrices.pop();
    }
}
