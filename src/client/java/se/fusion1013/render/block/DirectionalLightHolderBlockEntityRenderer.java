package se.fusion1013.render.block;

import net.minecraft.block.BlockState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import se.fusion1013.Main;
import se.fusion1013.block.DirectionalLightContainerBlock;
import se.fusion1013.block.entity.DirectionalLightHolderBlockEntity;

import java.awt.*;

public class DirectionalLightHolderBlockEntityRenderer implements BlockEntityRenderer<DirectionalLightHolderBlockEntity> {

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
        if (entity.isRemoved()) return;

        LightHolderBlockEntityRenderer.tryRenderLightSoul(entity, tickDelta, matrices, vertexConsumers, light, overlay);

        BlockState state = entity.getWorld().getBlockState(entity.getPos());
        DirectionalLightContainerBlock.LensType lensType = state.get(DirectionalLightContainerBlock.LENS_TYPE);

        if (lensType != DirectionalLightContainerBlock.LensType.NONE) renderLens(state, matrices, vertexConsumers, light, overlay, lensType == DirectionalLightContainerBlock.LensType.CLEAR ? Color.WHITE : lensType.color);
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
