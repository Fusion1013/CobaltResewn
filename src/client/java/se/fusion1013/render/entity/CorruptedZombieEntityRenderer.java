package se.fusion1013.render.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;
import se.fusion1013.entity.CorruptedZombieEntity;

public class CorruptedZombieEntityRenderer extends ZombieEntityRenderer {

    private static final Identifier TEXTURE = new Identifier(Main.MOD_NAMESPACE, "textures/entity/corrupted_zombie.png");

    public CorruptedZombieEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ZombieEntity zombieEntity) {
        return TEXTURE;
    }
}
