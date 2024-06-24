package se.fusion1013.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;

public class CobaltEntityTypeTags {
    public static final TagKey<EntityType<?>> CORRUPTED_CORE_FRIENDS = of("corrupted_core_friends");

    private static TagKey<EntityType<?>> of(String id) {
        return TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Main.MOD_NAMESPACE, id));
    }
}
