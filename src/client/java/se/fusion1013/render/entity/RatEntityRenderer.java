package se.fusion1013.render.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.SilverfishEntityRenderer;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;
import se.fusion1013.MainClient;
import se.fusion1013.entity.RatEntity;
import se.fusion1013.render.entity.model.RatEntityModel;

public class RatEntityRenderer extends MobEntityRenderer<RatEntity, RatEntityModel> {

    private static final Identifier TEXTURE = new Identifier(Main.MOD_NAMESPACE, "textures/entity/rat.png");

    public RatEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new RatEntityModel(context.getPart(MainClient.MODEL_RAT_LAYER)), 0.3f);
    }

    @Override
    protected float getLyingAngle(RatEntity entity) {
        return 180.0f;
    }

    @Override
    public Identifier getTexture(RatEntity entity) {
        return TEXTURE;
    }
}
