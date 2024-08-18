package se.fusion1013.render.entity;

import se.fusion1013.entity.ExplosiveArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class ExplosiveArrowEntityRenderer extends ProjectileEntityRenderer<ExplosiveArrowEntity> {

    public static final Identifier TEXTURE = Identifier.of("cobalt:textures/entity/explosive_arrow.png");

    public ExplosiveArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ExplosiveArrowEntity entity) {
        return TEXTURE;
    }
}
