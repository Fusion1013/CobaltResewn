package se.fusion1013.entity.attribute;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;

public class CobaltAttributes {

    public static final RegistryEntry<EntityAttribute> GENERIC_LIFESTEAL = register("generic.lifesteal", new ClampedEntityAttribute("attribute.name.lifesteal.generic", 0, 0, 1024));

    private static RegistryEntry<EntityAttribute> register(String id, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, Identifier.of(Main.MOD_NAMESPACE, id), attribute.setTracked(true));
    }

    public static void register() {}

}
