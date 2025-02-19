package se.fusion1013.entity;

import se.fusion1013.items.CobaltItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class LightningArrowEntity extends PersistentProjectileEntity {

    protected LightningArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world, ItemStack stack) {
        super(entityType, world, stack);
    }

    public LightningArrowEntity(World world, LivingEntity owner, ItemStack stack) {
        super(CobaltEntities.LIGHTNING_ARROW, owner, world, stack);
    }

    public LightningArrowEntity(EntityType<? extends PersistentProjectileEntity> entityEntityType, World world) {
        super(entityEntityType, world, new ItemStack(CobaltItems.LIGHTNING_ARROW));
    }

    public LightningArrowEntity(double x, double y, double z, World world, ItemStack stack) {
        super(CobaltEntities.LIGHTNING_ARROW, x, y, z, world, stack);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
    }

    @Override
    public void tick() {
        super.tick();

        // If the arrow is in the ground, display lightning particles
        if (this.getWorld().isClient) {
            if (this.inGround) {
                if (this.inGroundTime % 5 == 0) {
                    this.spawnParticles(1);
                }
            } else {
                this.spawnParticles(2);
            }
        } else if (this.inGround && this.inGroundTime != 0 && this.inGroundTime >= 600) {
            this.getWorld().sendEntityStatus(this, (byte)0);
        }
    }

    private void spawnParticles(int amount) {
        double d = (double)(16 & 0xFF) / 255.0;
        double e = (double)(8 & 0xFF) / 255.0;
        double f = (double)(0) / 255.0;
        for (int j = 0; j < amount; ++j) {
            this.getWorld().addParticle(ParticleTypes.ELECTRIC_SPARK, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), d, e, f);
        }
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        if (this.getWorld().isSkyVisible(target.getBlockPos()))
        {
            LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(this.getWorld());
            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(target.getBlockPos()));
            getWorld().spawnEntity(lightningEntity);
            this.discard();
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        HitResult.Type type = hitResult.getType();
        if (type == HitResult.Type.ENTITY) {
            this.onEntityHit((EntityHitResult)hitResult);
        } else if (type == HitResult.Type.BLOCK) {
            this.onBlockHit((BlockHitResult)hitResult);
            BlockPos blockPos = ((BlockHitResult) hitResult).getBlockPos();
            if (getWorld().isThundering()  && this.getWorld().isSkyVisible(blockPos))
            {
                LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(this.getWorld());
                lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
                getWorld().spawnEntity(lightningEntity);
                this.discard();
            }
        }
        if (type != HitResult.Type.MISS) {
            this.emitGameEvent(GameEvent.PROJECTILE_LAND, this.getOwner());
        }
    }


    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(CobaltItems.LIGHTNING_ARROW);
    }
}
