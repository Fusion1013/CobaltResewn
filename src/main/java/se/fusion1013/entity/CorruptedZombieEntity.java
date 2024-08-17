package se.fusion1013.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

public class CorruptedZombieEntity extends ZombieEntity {

    public CorruptedZombieEntity(EntityType<? extends CorruptedZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected boolean isAffectedByDaylight() {
        return false;
    }
}
