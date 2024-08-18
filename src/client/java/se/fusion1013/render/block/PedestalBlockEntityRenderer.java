package se.fusion1013.render.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import se.fusion1013.Main;
import se.fusion1013.block.entity.PedestalBlockEntity;

@Environment(EnvType.CLIENT)
public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity> {

    private final ItemRenderer itemRenderer;

    public PedestalBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(PedestalBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        var stack = entity.getStack(0);

        if (!stack.isEmpty()) {

            double offset = Math.sin(MathHelper.lerp(tickDelta, (float)PedestalBlockEntity.getLastTick(), (float)PedestalBlockEntity.getTick()) / 32f) / 8.0;

            matrices.translate(.5, 1.25 + offset, .5);
            // matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((entity.getWorld().getTime() + tickDelta) * .5f));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float)MathHelper.lerp(tickDelta, (float)PedestalBlockEntity.getLastTick(), (float)PedestalBlockEntity.getTick()) / 2f));


            // Render the item
            int lightAbove = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().up());
            itemRenderer.renderItem(stack, ModelTransformationMode.GROUND, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 0);
        }

        matrices.pop();
    }
}
