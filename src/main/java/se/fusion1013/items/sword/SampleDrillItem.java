package se.fusion1013.items.sword;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import se.fusion1013.Main;
import se.fusion1013.items.CobaltItem;

import java.util.Random;

public class SampleDrillItem extends CobaltSwordItem {

    private static final float HEAL_AMOUNT_PERCENTAGE = 0.1f;

    public SampleDrillItem(int attackDamage, float attackSpeed, CobaltItem.Settings settings) {
        super(attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        var rand = new Random();
        var chance = rand.nextInt(0, 100);
        Main.LOGGER.info("Chance: " + chance);
        if (chance > 2) return super.postHit(stack, target, attacker);

        attacker.heal(attacker.getMaxHealth() * HEAL_AMOUNT_PERCENTAGE);

        // Play effects
        if (attacker instanceof PlayerEntity player) {
            player.playSoundToPlayer(SoundEvents.ENTITY_WITCH_DRINK, SoundCategory.PLAYERS, 1, 1);
        }
        attacker.getWorld().addParticle(ParticleTypes.HEART, attacker.getX(), attacker.getY(), attacker.getZ(), 1, 1, 1);

        return super.postHit(stack, target, attacker);
    }
}
