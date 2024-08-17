package se.fusion1013.render.entity.model;

import com.google.common.collect.Sets;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;

import java.util.Set;

public class CobaltEntityModelLayers {
    private static final Set<EntityModelLayer> LAYERS = Sets.newHashSet();

    public static final EntityModelLayer TEST = register("test", "main");

    private static EntityModelLayer register(String id, String layer) {
        EntityModelLayer entityModelLayer = new EntityModelLayer(new Identifier(Main.MOD_NAMESPACE, id), layer);
        if (!LAYERS.add(entityModelLayer)) {
            throw new IllegalStateException("Duplicate layer " + entityModelLayer);
        }
        return entityModelLayer;
    }
}
