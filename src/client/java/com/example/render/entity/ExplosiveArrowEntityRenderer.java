package com.example.render.entity;

import com.example.entity.ExplosiveArrowEntity;
import com.example.entity.LightningArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class ExplosiveArrowEntityRenderer extends ProjectileEntityRenderer<ExplosiveArrowEntity> {

    public static final Identifier TEXTURE = new Identifier("cobalt:textures/entity/explosive_arrow.png");

    public ExplosiveArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ExplosiveArrowEntity entity) {
        return TEXTURE;
    }
}
