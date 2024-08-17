package se.fusion1013.render.entity.feature;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;

public class CorruptedSpiderEyesFeatureRenderer<T extends Entity, M extends SpiderEntityModel<T>> extends EyesFeatureRenderer<T, M> {

    private static final RenderLayer SKIN = RenderLayer.getEyes(new Identifier(Main.MOD_NAMESPACE, "textures/entity/corrupted_spider_eyes.png"));

    public CorruptedSpiderEyesFeatureRenderer(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return SKIN;
    }
}
