package se.fusion1013.render.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import se.fusion1013.entity.CorruptedCoreEntity;

public class CorruptedCoreEntityModel extends EntityModel<CorruptedCoreEntity> {

    private static final float HALF_SQRT_3 = (float)(Math.sqrt(3.0D) / 2.0D);

    private final ModelPart bb_main;
    public CorruptedCoreEntityModel(ModelPart root) {
        this.bb_main = root.getChild("bb_main");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-16.0F, -32.0F, -16.0F, 32.0F, 32.0F, 32.0F, new Dilation(0.0F))
                .uv(0, 64).cuboid(-12.0F, -28.0F, -20.0F, 24.0F, 24.0F, 24.0F, new Dilation(0.0F))
                .uv(0, 64).cuboid(-12.0F, -28.0F, -4.0F, 24.0F, 24.0F, 24.0F, new Dilation(0.0F))
                .uv(0, 64).cuboid(-20.0F, -28.0F, -12.0F, 24.0F, 24.0F, 24.0F, new Dilation(0.0F))
                .uv(0, 64).cuboid(-4.0F, -28.0F, -12.0F, 24.0F, 24.0F, 24.0F, new Dilation(0.0F))
                .uv(0, 64).cuboid(-12.0F, -36.0F, -12.0F, 24.0F, 24.0F, 24.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 256, 128);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public void setAngles(CorruptedCoreEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}
