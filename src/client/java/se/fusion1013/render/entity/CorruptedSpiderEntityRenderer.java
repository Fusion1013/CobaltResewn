package se.fusion1013.render.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;
import se.fusion1013.MainClient;
import se.fusion1013.entity.CorruptedSpiderEntity;
import se.fusion1013.render.entity.feature.CorruptedSpiderEyesFeatureRenderer;
import se.fusion1013.render.entity.model.CorruptedSpiderEntityModel;

public class CorruptedSpiderEntityRenderer extends MobEntityRenderer<CorruptedSpiderEntity, CorruptedSpiderEntityModel> {

    private static final Identifier TEXTURE = new Identifier(Main.MOD_NAMESPACE, "textures/entity/corrupted_spider.png");

    public CorruptedSpiderEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CorruptedSpiderEntityModel(context.getPart(MainClient.MODEL_CORRUPTED_SPIDER_LAYER)), 0.8f);
        this.addFeature(new CorruptedSpiderEyesFeatureRenderer(this));
    }

    @Override
    protected float getLyingAngle(CorruptedSpiderEntity entity) {
        return 180.0f;
    }

    @Override
    public Identifier getTexture(CorruptedSpiderEntity spiderEntity) {
        return TEXTURE;
    }
}
