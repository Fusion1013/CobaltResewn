package se.fusion1013.sounds;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;

public class CobaltSoundEvents {

    public static final SoundEvent LIGHT_HOLDER_SOUL_INSERT = register("block.light_holder.soul_insert");
    public static final SoundEvent LIGHT_HOLDER_SOUL_REMOVE = register("block.light_holder.soul_remove");

    public static final SoundEvent LIGHT_HOLDER_LENS_INSERT = register("block.light_holder.lens_insert");
    public static final SoundEvent LIGHT_HOLDER_LENS_REMOVE = register("block.light_holder.lens_remove");

    public static final SoundEvent ILLUSORY_WALL = register("illusory_wall");

    public static final SoundEvent ANCIENT_GETAWAY = register("music_disc.ancient_getaway");
    public static final SoundEvent ERA_OF_PEACE = register("music_disc.era_of_peace");
    public static final SoundEvent HYDROANGEA = register("music_disc.hydroangea");
    public static final SoundEvent OUTPOST = register("music_disc.outpost");
    public static final SoundEvent SHOOTING_FOR_THE_STARS = register("music_disc.shooting_for_the_stars");
    public static final SoundEvent AKALI = register("music_disc.akali");

    // Mobs
    public static final SoundEvent RAT_HURT = register("entity.rat.hurt");
    public static final SoundEvent RAT_AMBIENT = register("entity.rat.ambient");
    public static final SoundEvent RAT_DEATH = register("entity.rat.death");

    public static SoundEvent register(String id) {
        Identifier identifier = Identifier.of(Main.MOD_NAMESPACE, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void register() {}
}
