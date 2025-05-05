package se.fusion1013.items.consumable;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.potion.Potions;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import se.fusion1013.items.CobaltItem;

import java.util.Random;

public class CobaltDrinkItem extends CobaltItem {

    public CobaltDrinkItem(CobaltItem.Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack itemStack = super.getDefaultStack();
        itemStack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(Potions.WATER));
        return itemStack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 32;
    }

    @Override
    public SoundEvent getEatSound() {
        return getDrinkSound();
    }

    protected static void addEffectRandomDuration(LivingEntity user, RegistryEntry<StatusEffect> effect, int randomDuration, int randomAmplifier) {
        var rand = new Random();
        user.addStatusEffect(new StatusEffectInstance(effect, rand.nextInt(200, randomDuration+1), rand.nextInt(0, randomAmplifier+1)));
    }
}
