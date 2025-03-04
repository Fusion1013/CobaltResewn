package se.fusion1013.render.block;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import se.fusion1013.Main;
import se.fusion1013.block.entity.DisplayBlockEntity;

public class DisplayBlockEntityRenderer implements BlockEntityRenderer<DisplayBlockEntity> {

    public DisplayBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    @Override
    public boolean rendersOutsideBoundingBox(DisplayBlockEntity blockEntity) {
        return true;
    }

    @Override
    public void render(DisplayBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        DisplayBlockEntity.DisplayDimensions dimensions = blockEntity.detectNearbyBlocks();

        if (!dimensions.mainBlock) return;

        // TODO: Load picture
        /*
        String url = blockEntity.getCurrentPicture();
        int cachedSeconds = blockEntity.getCacheSeconds();

        if (url == null || url.isEmpty()) return;
         */

        Identifier texture = new Identifier(Main.MOD_NAMESPACE, "textures/block/display_placeholder.png");
        Direction direction = blockEntity.getCachedState().get(Properties.HORIZONTAL_FACING);

        if (!dimensions.isPowered) return;

        float xOffset = 0.0F;
        float zOffset = 0.0F;

        float zTranslate = 0.0F;
        float xTranslate = 0.0F;

        float displayWidth = dimensions.width - 0.25F;
        float displayHeight = dimensions.height - 0.25F;

        Quaternionf yRotation = RotationAxis.POSITIVE_Y.rotationDegrees(0.0F);

        switch (direction) {
            case NORTH:
                zTranslate = 0.49F;
                zOffset = 1.0F;
                xOffset = 1.0F;
                yRotation = RotationAxis.POSITIVE_Y.rotationDegrees(180.0F);
                break;
            case SOUTH:
                zTranslate = -0.49F;
                break;
            case EAST:
                xTranslate = -0.49F;
                zOffset = 1.0F;
                yRotation = RotationAxis.POSITIVE_Y.rotationDegrees(90.0F);
                break;
            case WEST:
                yRotation = RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F);
                xTranslate = 0.49F;
                xOffset = 1.0F;
                break;
            default:
                break;
        }

        matrices.push();

        drawImageNew(matrices, vertexConsumers, texture, xOffset, zOffset, zTranslate, xTranslate, displayWidth, displayHeight, yRotation, light, overlay);

        matrices.pop();
    }

    private void drawImageNew(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Identifier texture, float xOffset, float zOffset, float zTranslate, float xTranslate, float displayWidth, float displayHeight, Quaternionf yRotation, int light, int overlay) {
        matrices.translate(xTranslate + xOffset, 0.00F, zTranslate + zOffset);
        matrices.multiply(yRotation);

        Matrix4f positionMatrix = matrices.peek().getPositionMatrix();
        Matrix3f normalMatrix = matrices.peek().getNormalMatrix();

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(texture));

        vertexConsumer.vertex(positionMatrix, displayWidth + 0.125F, 0.125F, 1.0F).color(255, 255, 255, 255).texture(1.0F, 1.0F).overlay(overlay).light(light).normal(normalMatrix, 0, 0, 1).next(); // A
        vertexConsumer.vertex(positionMatrix, displayWidth + 0.125F, displayHeight + 0.125F, 1.0F).color(255, 255, 255, 255).texture(1.0F, 0.0F).overlay(overlay).light(light).normal(normalMatrix, 0, 0, 1).next(); // B
        vertexConsumer.vertex(positionMatrix, 0.125F, displayHeight + 0.125F, 1.0F).color(255, 255, 255, 255).texture(0.0F, 0.0F).overlay(overlay).light(light).normal(normalMatrix, 0, 0, 1).next(); // C
        vertexConsumer.vertex(positionMatrix, 0.125F, 0.125F, 1.0F).color(255, 255, 255, 255).texture(0.0F, 1.0F).overlay(overlay).light(light).normal(normalMatrix, 0, 0, 1).next(); // D
    }
}
