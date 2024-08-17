package se.fusion1013.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import se.fusion1013.items.CobaltItems;

public class DynamiteEntity extends ThrownItemEntity {

    private static final int EXPLOSION_POWER = 3;

    public DynamiteEntity(EntityType<? extends DynamiteEntity> entityType, World world) {
        super(entityType, world);
    }

    public DynamiteEntity(World world, LivingEntity owner) {
        super(CobaltEntities.DYNAMITE, owner, world);
    }

    public DynamiteEntity(World world, double x, double y, double z) {
        super(CobaltEntities.SMOKE_BOMB, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return CobaltItems.SMOKE_BOMB;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (this.getWorld().isClient) return;
        explode();
        this.discard();
    }

    private void explode() {
        this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), EXPLOSION_POWER, World.ExplosionSourceType.MOB);
        this.discard();
    }

}
