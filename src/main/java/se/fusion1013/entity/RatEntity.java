package se.fusion1013.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import se.fusion1013.sounds.CobaltSoundEvents;

public class RatEntity extends SilverfishEntity {

    public RatEntity(EntityType<? extends SilverfishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return CobaltSoundEvents.RAT_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return CobaltSoundEvents.RAT_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return CobaltSoundEvents.RAT_HURT;
    }
}
