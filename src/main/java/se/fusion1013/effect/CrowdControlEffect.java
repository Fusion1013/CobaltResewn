package se.fusion1013.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.UUID;

public class CrowdControlEffect extends StatusEffect {

    public CrowdControlEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x98D982);
        addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, Identifier.of("effects.crowd_control.attack_damage"), 3.0D, EntityAttributeModifier.Operation.ADD_VALUE);
    }
}
