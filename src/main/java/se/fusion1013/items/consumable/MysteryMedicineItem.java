package se.fusion1013.items.consumable;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import se.fusion1013.items.CobaltItem;

import java.util.Random;

public class MysteryMedicineItem extends CobaltDrinkItem {

    private final static RegistryEntry<StatusEffect>[] POSITIVE_POOL = new RegistryEntry[] {
            StatusEffects.REGENERATION,
            StatusEffects.STRENGTH,
            StatusEffects.ABSORPTION,
            StatusEffects.HASTE
    };
    private final static RegistryEntry<StatusEffect>[] NEGATIVE_POOL = new RegistryEntry[] {
            StatusEffects.BLINDNESS,
            StatusEffects.DARKNESS,
            StatusEffects.HUNGER,
            StatusEffects.MINING_FATIGUE,
            StatusEffects.SLOWNESS,
    };

    public MysteryMedicineItem(CobaltItem.Settings settings) {

        super((CobaltItem.Settings) settings.food(new FoodComponent.Builder()
                .nutrition(4)
                .saturationModifier(.5f)
                .alwaysEdible()
                .build())
        );
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient()) return super.finishUsing(stack, world, user);


        var rand = new Random();
        addEffectRandomDuration(user, POSITIVE_POOL[rand.nextInt(0, POSITIVE_POOL.length)], 180*20, 1);
        addEffectRandomDuration(user, NEGATIVE_POOL[rand.nextInt(0, NEGATIVE_POOL.length)], 120*20, 0);
        addEffectRandomDuration(user, NEGATIVE_POOL[rand.nextInt(0, NEGATIVE_POOL.length)], 120*20, 0);

        return super.finishUsing(stack, world, user);
    }
}
