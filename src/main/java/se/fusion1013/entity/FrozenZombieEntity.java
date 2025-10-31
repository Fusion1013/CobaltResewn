package se.fusion1013.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class FrozenZombieEntity extends ZombieEntity {

    public FrozenZombieEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();

        if (this.getWorld().isClient && random.nextInt(3) == 0) {
            this.getWorld().addParticle(ParticleTypes.SNOWFLAKE, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), 0, 0, 0);
        }
    }

    @Override
    protected boolean isAffectedByDaylight() {
        return false;
    }
}
