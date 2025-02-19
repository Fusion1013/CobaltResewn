package se.fusion1013.entity;

import se.fusion1013.items.CobaltItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class ExplosiveArrowEntity extends PersistentProjectileEntity {

    private static final TrackedData<Integer> FUSE = DataTracker.registerData(ExplosiveArrowEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final int DEFAULT_FUSE = 40;
    private int explosionPower = 2;

    protected ExplosiveArrowEntity(EntityType<? extends PersistentProjectileEntity> type, World world, ItemStack stack) {
        super(type, world, stack);
        this.setFuse(DEFAULT_FUSE);
    }

    protected ExplosiveArrowEntity(EntityType<? extends PersistentProjectileEntity> type, double x, double y, double z, World world, ItemStack stack) {
        super(type, x, y, z, world, stack);
        this.setFuse(DEFAULT_FUSE);
    }

    protected ExplosiveArrowEntity(EntityType<? extends PersistentProjectileEntity> type, LivingEntity owner, World world, ItemStack stack) {
        super(type, owner, world, stack);
        this.setFuse(DEFAULT_FUSE);
    }
    public ExplosiveArrowEntity(World world, LivingEntity owner, ItemStack stack) {
        super(CobaltEntities.EXPLOSIVE_ARROW, owner, world, stack);
        this.setFuse(DEFAULT_FUSE);
    }
    public ExplosiveArrowEntity(EntityType<? extends PersistentProjectileEntity> entityEntityType, World world) {
        super(entityEntityType, world, new ItemStack(CobaltItems.EXPLOSIVE_ARROW));
        this.setFuse(DEFAULT_FUSE);
    }
    public ExplosiveArrowEntity(double x, double y, double z, World world, ItemStack stack) {
        super(CobaltEntities.EXPLOSIVE_ARROW, x, y, z, world, stack);
        this.setFuse(DEFAULT_FUSE);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(FUSE, DEFAULT_FUSE);
    }

    @Override
    public void tick() {
        super.tick();

        // If the arrow is in the ground;
        // Update the fuse timer
        // If fuse timer runs out, explode
        if (this.inGround)
        {
            int i = this.getFuse() - 1;
            this.setFuse(i);
            if (i <= 0) {
                this.explode();
            } else {
                this.updateWaterState();
            }
        }

        // If on the client, display particles
        // If on the server, send status to clients
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
            this.getWorld().addParticle(ParticleTypes.SMOKE, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), d, e, f);
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        HitResult.Type type = hitResult.getType();
        if (type == HitResult.Type.ENTITY) {
            this.onEntityHit((EntityHitResult)hitResult);
        } else if (type == HitResult.Type.BLOCK) {
            this.onBlockHit((BlockHitResult)hitResult);
            getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
        if (type != HitResult.Type.MISS) {
            this.emitGameEvent(GameEvent.PROJECTILE_LAND, this.getOwner());
        }
    }

    @Override
    protected void onHit(LivingEntity target) {
        explodeOnEntity(target.getX(), target.getBodyY(1.0), target.getZ());
    }

    private void explodeOnEntity(double x, double y, double z) {
        this.getWorld().createExplosion(this, x, y, z, this.explosionPower, World.ExplosionSourceType.MOB);
        this.discard();
    }

    private void explode()
    {
        this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), this.explosionPower, World.ExplosionSourceType.MOB);
        this.discard();
    }


    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putShort("Fuse", (short)this.getFuse());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.setFuse(nbt.getShort("Fuse"));
    }

    public void setFuse(int fuse) {
        this.dataTracker.set(FUSE, fuse);
    }

    public int getFuse() {
        return this.dataTracker.get(FUSE);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(CobaltItems.EXPLOSIVE_ARROW);
    }
}
