package se.fusion1013.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SmokeCloudEntity extends Entity implements Ownable {

    private static final TrackedData<Float> RADIUS = DataTracker.registerData(SmokeCloudEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final float MAX_RADIUS = 32.0f;
    private int duration = 600;
    private int waitTime = 20;
    private float radiusGrowth;
    private LivingEntity owner;

    public SmokeCloudEntity(EntityType<? extends Entity> type, World world) {
        super(type, world);
        this.noClip = true;
    }

    public SmokeCloudEntity(World world, double x, double y, double z) {
        this(CobaltEntities.SMOKE_CLOUD, world);
        this.setPosition(x, y, z);
    }

    @Override
    public void calculateDimensions() {
        double d = this.getX();
        double e = this.getY();
        double f = this.getZ();
        super.calculateDimensions();
        this.setPosition(d, e, f);
    }

    @Override
    public void tick() {
        float radius = getRadius();

        if (getWorld().isClient) {
            super.tick();
            displayParticles();
            return;
        }

        // Remove cloud if age exceeds max
        if (this.age >= this.waitTime + this.duration) {
            this.discard();
            return;
        }
        if (this.radiusGrowth != 0.0f) {
            if ((radius += this.radiusGrowth) < 0.5f) {
                this.discard();
                return;
            }
            this.setRadius(radius);
        }

        // Apply potion effects to players within radius
        List<LivingEntity> livingEntities = this.getWorld().getNonSpectatingEntities(LivingEntity.class, this.getBoundingBox());
        if (livingEntities.isEmpty()) return;

        for (LivingEntity livingEntity : livingEntities) {
            if (livingEntity == owner) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 20, 0, false, true), this);
            } else {
                livingEntity.addStatusEffect(new  StatusEffectInstance(StatusEffects.BLINDNESS, 40, 0, false, true), this);
            }
        }
    }

    @Override
    public boolean shouldRender(double distance) {
        return distance < 120;
    }

    private void displayParticles() {
        // Add particles in sphere with radius
        Random random = Random.create();

        float radius = this.getRadius();

        for (int i = 0; i < radius * radius * 4; i++) {
            double xOffset = random.nextDouble() - 0.5;
            double yOffset = random.nextDouble() - 0.5;
            double zOffset = random.nextDouble() - 0.5;

            double magnitude = Math.sqrt(xOffset * xOffset + yOffset * yOffset + zOffset * zOffset);
            xOffset /= magnitude;
            yOffset /= magnitude;
            zOffset /= magnitude;

            double d = random.nextDouble() * getRadius() * 1.6f;

            this.getWorld().addImportantParticle(ParticleTypes.CLOUD, getX() + xOffset * d, getY() + yOffset * d, getZ() + zOffset * d, 0, 0, 0);
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(RADIUS, 3.0f);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.duration = nbt.getInt("Duration");
        this.waitTime = nbt.getInt("WaitTime");
        this.setRadius(nbt.getFloat("Radius"));
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putInt("Duration", this.duration);
        nbt.putInt("WaitTime", this.waitTime);
        nbt.putFloat("Radius", this.getRadius());
    }

    @Nullable
    @Override
    public Entity getOwner() {
        // TODO: Get owner if uuid exists but owner does not
        return this.owner;
    }

    public void setOwner(LivingEntity owner) {
        this.owner = owner;
    }

    public float getRadius() {
        return this.getDataTracker().get(RADIUS).floatValue();
    }

    public void setRadius(float radius) {
        if (this.getWorld().isClient) return;
        this.getDataTracker().set(RADIUS, MathHelper.clamp(radius, 0.0f, MAX_RADIUS));
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public void setRadiusGrowth(float radiusGrowth) {
        this.radiusGrowth = radiusGrowth;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        if (RADIUS.equals(data)) {
            this.calculateDimensions();
        }
        super.onTrackedDataSet(data);
    }

    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        return EntityDimensions.changing(this.getRadius() * 2.0f, 0.5f);
    }
}
