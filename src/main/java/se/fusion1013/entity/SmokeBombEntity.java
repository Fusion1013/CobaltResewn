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

public class SmokeBombEntity extends ThrownItemEntity {

    public SmokeBombEntity(EntityType<? extends SmokeBombEntity> entityType, World world) {
        super(entityType, world);
    }

    public SmokeBombEntity(World world, LivingEntity owner) {
        super(CobaltEntities.SMOKE_BOMB, owner, world);
    }

    public SmokeBombEntity(World world, double x, double y, double z) {
        super(CobaltEntities.SMOKE_BOMB, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return CobaltItems.SMOKE_BOMB;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (this.getWorld().isClient) return;

        // TODO: Spawn smoke effect
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (this.getWorld().isClient) return;
        applySmokeEffect();
        this.discard();
    }

    private void applySmokeEffect() {
        SmokeCloudEntity smokeCloud = new SmokeCloudEntity(this.getWorld(), this.getX(), this.getY(), this.getZ());
        Entity owner = this.getOwner();
        if (owner instanceof LivingEntity livingOwner) {
            smokeCloud.setOwner(livingOwner);
        }

        smokeCloud.setRadius(6.0f);
        smokeCloud.setWaitTime(10);
        smokeCloud.setRadiusGrowth(-smokeCloud.getRadius() / smokeCloud.getDuration());
        this.getWorld().spawnEntity(smokeCloud);
    }
}
