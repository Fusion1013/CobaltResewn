package se.fusion1013.render.block;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.joml.Matrix4f;
import se.fusion1013.block.entity.RuneBlockEntity;

public class RuneBlockEntityRenderer implements BlockEntityRenderer<RuneBlockEntity> {

    public RuneBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    private static final float Unit = 1/16f;

    private final String[] LetterA = {
            "----------------",
            "----------------",
            "----------------",
            "----------------",
            "------xxxx------",
            "-----x----x-----",
            "-----x----x-----",
            "-----x----x-----",
            "-----xxxxxx-----",
            "-----x----x-----",
            "-----x----x-----",
            "----------------",
            "----------------",
            "----------------",
            "----------------",
            "----------------",
    };

    @Override
    public void render(RuneBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        matrices.translate(0, 1, 0);

        Matrix4f matrix = matrices.peek().getPositionMatrix();

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getSolid());

        float r = 1.0f, g = 0.0f, b = 0.0f, a = 1.0f;

        for (int x = 0; x < LetterA.length; x++) {
            String str = LetterA[x];
            matrices.translate(0, -Unit, 0);
            for (int y = 0; y < str.length(); y++) {
                matrices.translate(Unit, 0, 0);

                double offset = Math.sin(MathHelper.lerp(tickDelta + (x * y * (1/16f*16f)), RuneBlockEntity.getLastTick(), RuneBlockEntity.getTick()) / 32f) / 8f;
                matrices.translate(0, 0, offset);

                if (str.charAt(y) == 'x') drawCube(matrix, vertexConsumer, r, g, b, a, light, overlay, 1/16f);
                matrices.translate(0, 0, -offset);
            }
            matrices.translate(-1, 0, 0);
        }

        matrices.pop();
    }

    public static void drawCube(Matrix4f matrix, VertexConsumer vertexConsumer, float r, float g, float b, float a, int light, int overlay, float scale) {
        // Define cube corners
        float minX = 0f * scale, maxX = 1.0f * scale;
        float minY = 0f * scale, maxY = 1.0f * scale;
        float minZ = 0f * scale, maxZ = 1.0f * scale;

        // Front Face
        addVertex(matrix, vertexConsumer, minX, minY, maxZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, maxX, minY, maxZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, maxX, maxY, maxZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, minX, maxY, maxZ, r, g, b, a, light, overlay);

        // Back Face
        addVertex(matrix, vertexConsumer, maxX, minY, minZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, minX, minY, minZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, minX, maxY, minZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, maxX, maxY, minZ, r, g, b, a, light, overlay);

        // Left Face
        addVertex(matrix, vertexConsumer, minX, minY, minZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, minX, minY, maxZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, minX, maxY, maxZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, minX, maxY, minZ, r, g, b, a, light, overlay);

        // Right Face
        addVertex(matrix, vertexConsumer, maxX, minY, maxZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, maxX, minY, minZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, maxX, maxY, minZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, maxX, maxY, maxZ, r, g, b, a, light, overlay);

        // Top Face
        addVertex(matrix, vertexConsumer, minX, maxY, minZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, minX, maxY, maxZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, maxX, maxY, maxZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, maxX, maxY, minZ, r, g, b, a, light, overlay);

        // Bottom Face
        addVertex(matrix, vertexConsumer, minX, minY, maxZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, minX, minY, minZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, maxX, minY, minZ, r, g, b, a, light, overlay);
        addVertex(matrix, vertexConsumer, maxX, minY, maxZ, r, g, b, a, light, overlay);
    }

    public static void addVertex(Matrix4f matrix, VertexConsumer vertexConsumer, float x, float y, float z, float r, float g, float b, float a, int light, int overlay) {
        vertexConsumer.vertex(matrix, x, y, z).color(r, g, b, a).texture(0, 0).overlay(overlay).light(light).normal(0, 1, 0).next();
    }
}
