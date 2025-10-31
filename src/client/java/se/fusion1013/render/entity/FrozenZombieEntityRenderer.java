package se.fusion1013.render.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;

public class FrozenZombieEntityRenderer extends ZombieEntityRenderer {

    private static final Identifier TEXTURE = Identifier.of(Main.MOD_NAMESPACE, "textures/entity/frozen_zombie.png");

    public FrozenZombieEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ZombieEntity zombieEntity) {
        return TEXTURE;
    }
}
