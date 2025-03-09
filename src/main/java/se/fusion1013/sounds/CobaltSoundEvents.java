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

    public static final SoundEvent ILLUSORY_WALL = register("sfx.illusory_wall");

    // Mobs
    public static final SoundEvent RAT_HIT = register("entity.rat.hit");
    public static final SoundEvent RAT_SAY = register("entity.rat.say");
    public static final SoundEvent RAT_KILL = register("entity.rat.kill");

    public static final SoundEvent ELECTRIC_SPARK = register("sfx.electric.spark");

    public static SoundEvent register(String id) {
        Identifier identifier = new Identifier(Main.MOD_NAMESPACE + ":" + id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void register() {}
}
