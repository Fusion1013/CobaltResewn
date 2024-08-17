package se.fusion1013.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.world.World;

public class CorruptedSkeletonEntity extends SkeletonEntity {

    public CorruptedSkeletonEntity(EntityType<? extends SkeletonEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected boolean isAffectedByDaylight() {
        return false;
    }
}
