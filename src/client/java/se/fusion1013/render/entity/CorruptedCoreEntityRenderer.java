package se.fusion1013.render.entity;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import se.fusion1013.MainClient;
import se.fusion1013.entity.CorruptedCoreEntity;
import se.fusion1013.render.entity.model.CorruptedCoreEntityModel;

public class CorruptedCoreEntityRenderer extends MobEntityRenderer<CorruptedCoreEntity, CorruptedCoreEntityModel> {

    private static final float HALF_SQRT_3 = (float)(Math.sqrt(3.0D) / 2.0D);;

    public CorruptedCoreEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CorruptedCoreEntityModel(context.getPart(MainClient.MODEL_CORRUPTED_CORE_LAYER)), 0.5f);
    }

    @Override
    public void render(CorruptedCoreEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(CorruptedCoreEntity entity) {
        return new Identifier("cobalt", "textures/entity/corrupted_core.png");
    }
}
