package se.fusion1013.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

import java.util.UUID;

/**
 * Makes the entity immovable for the duration.
 */
public class ImmovableEffect extends StatusEffect {
    public ImmovableEffect() {
        super(StatusEffectCategory.HARMFUL, 0x818da1);

        addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, Identifier.of("effects.immovable.movement_speed"), 0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        return false;
    }
}
