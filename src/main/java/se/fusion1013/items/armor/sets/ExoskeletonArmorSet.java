package se.fusion1013.items.armor.sets;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import se.fusion1013.effect.CobaltEffects;
import se.fusion1013.items.IItemSetMethods;

public class ExoskeletonArmorSet implements IItemSetMethods {

    @Override
    public void triggerSetAbility(Entity entity) {
        IItemSetMethods.super.triggerSetAbility(entity);
        if (entity instanceof PlayerEntity playerEntity) {
            if (playerEntity.getInventory().containsAny(itemStack -> itemStack.getItem() == Items.COAL) && !playerEntity.hasStatusEffect(CobaltEffects.IMMOVABLE_EFFECT)) {
                // Remove coal
                playerEntity.getInventory().remove(itemStack -> itemStack.getItem() == Items.COAL, 2, playerEntity.getInventory());
                playerEntity.playSound(SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS, 1, 1);

                // Add status effects
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10*20, 1));
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 10*20, 1));
                playerEntity.addStatusEffect(new StatusEffectInstance(CobaltEffects.IMMOVABLE_EFFECT, 10*20, 0));
            }
        }
    }

    @Override
    public String[] setAbilityTooltipString() {
        return new String[] {
                "item.cobalt.exoskeleton.trigger_ability.tooltip"
        };
    }

}
