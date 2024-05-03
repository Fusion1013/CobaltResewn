package se.fusion1013.items.consumable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.UseAction;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.CobaltItemConfiguration;

import java.util.Random;

public class CobaltDrinkItem extends CobaltItem {

    public CobaltDrinkItem(CobaltItemConfiguration configuration, Settings settings) {
        super(configuration, settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return getDrinkSound();
    }

    protected static void addEffectRandomDuration(LivingEntity user, StatusEffect effect, int randomDuration, int randomAmplifier) {
        var rand = new Random();
        user.addStatusEffect(new StatusEffectInstance(effect, rand.nextInt(200, randomDuration+1), rand.nextInt(0, randomAmplifier+1)));
    }
}
