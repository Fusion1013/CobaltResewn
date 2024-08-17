package se.fusion1013.render.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.VindicatorEntityRenderer;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;

public class AutomatonEntityRenderer extends VindicatorEntityRenderer {

    private static final Identifier TEXTURE = new Identifier(Main.MOD_NAMESPACE, "textures/entity/automaton.png");

    public AutomatonEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(VindicatorEntity vindicatorEntity) {
        return TEXTURE;
    }
}
