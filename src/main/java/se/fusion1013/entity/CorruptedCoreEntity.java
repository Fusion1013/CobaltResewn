package se.fusion1013.entity;

import net.minecraft.block.Blocks;
import net.minecraft.block.entity.SculkSpreadManager;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;
import se.fusion1013.util.block.BlockSpreadManager;
import se.fusion1013.util.math.MathUtil;

import java.util.List;
import java.util.function.Predicate;

public class CorruptedCoreEntity extends HostileEntity {

    private final ServerBossBar bossBar;
    private final SculkSpreadManager spreadManager;
    private final BlockSpreadManager blockSpreadManager;
    private final net.minecraft.util.math.random.Random random;

    // Tracked data
    private static final TrackedData<Boolean> IS_DYING;

    private static final Predicate<LivingEntity> CAN_BOOST_PREDICATE;
    private static final TargetPredicate CAN_ATTACK_PREDICATE;

    private CorruptedCoreState state = CorruptedCoreState.ALIVE;

    // Block spreader that gets rid of sculk when the core dies
    private static final BlockSpreadManager.IBlockSpreadReplacer replacer = blockState -> {
        if (blockState.getBlock() == Blocks.SCULK) return Blocks.STONE.getDefaultState();
        if (blockState.getBlock() == Blocks.SCULK_VEIN) return Blocks.AIR.getDefaultState();
        if (blockState.getBlock() == Blocks.SCULK_CATALYST) return Blocks.AIR.getDefaultState();
        if (blockState.getBlock() == Blocks.SCULK_SENSOR) return Blocks.AIR.getDefaultState();
        if (blockState.getBlock() == Blocks.SCULK_SHRIEKER) return Blocks.AIR.getDefaultState();
        return null;
    };

    public CorruptedCoreEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.bossBar = (ServerBossBar) (new ServerBossBar(this.getDisplayName(), BossBar.Color.PURPLE, BossBar.Style.PROGRESS)).setDarkenSky(false);
        this.spreadManager = SculkSpreadManager.create();
        this.random = Random.create();
        this.blockSpreadManager = BlockSpreadManager.create();
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(IS_DYING, false);
    }

    @Override
    protected void initGoals() {
        // Target selector that looks for mobs that the core can buff
        this.targetSelector.add(0, new ActiveTargetGoal<>(this, HostileEntity.class, 0, false, false, CAN_BOOST_PREDICATE));
    }

    @Override
    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        this.bossBar.setName(this.getDisplayName());
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }

    @Override
    public boolean isPersistent() {
        return true;
    }

    @Override
    public void takeKnockback(double strength, double x, double z) {}

    @Override
    public void move(MovementType movementType, Vec3d movement) {}

    @Override
    public boolean damage(DamageSource source, float amount) {

        if (source.getSource() instanceof PersistentProjectileEntity) return false;

        if (getWorld().isClient) return super.damage(source, amount);

        // State switch checker
        switch (state) {
            case ALIVE -> damagedAlive(source, amount);
            case DYING -> damagedDying(source, amount);
        }

        return super.damage(source, amount);
    }

    // Triggered when the core is damaged while alive
    private void damagedAlive(DamageSource source, float amount) {
        var charge = Math.round(Math.min(amount, 4));

        // Spread sculk on the nearby blocks, depending on the amount of damage that was dealt
        this.spreadManager.spread(BlockPos.ofFloored(this.getX()-2, this.getY()-1, this.getZ()), charge);
        this.spreadManager.spread(BlockPos.ofFloored(this.getX()+2, this.getY()-1, this.getZ()), charge);
        this.spreadManager.spread(BlockPos.ofFloored(this.getX(), this.getY()-1, this.getZ()-2), charge);
        this.spreadManager.spread(BlockPos.ofFloored(this.getX(), this.getY()-1, this.getZ()+2), charge);

        // Give buffs to nearby entities
        List<LivingEntity> list = this.getWorld().getTargets(LivingEntity.class, CAN_ATTACK_PREDICATE, this, getBoundingBox().expand(20, 8, 20));
        for (LivingEntity entity : list) {
            // Boost nearby entities
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20*10, 0));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20*8, 1));
        }
        this.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, 1, 1);
    }

    private void damagedDying(DamageSource source, float amount) {}

    private float getHealthPercentage() {
        return (getHealth() - (getMaxHealth() * 0.5f)) / (getMaxHealth() * 0.5f);
    }

    @Override
    public void tickMovement() {
        if (getWorld().isClient) {
            Random random = Random.create();
            if (this.getIsDying()) {
                // Spawn dying effects
                if (random.nextInt(5) == 0 && !isSilent()) {
                    getWorld().playSound(this.getX()+.5, getY()+.5, getZ()+.5, SoundEvents.ENTITY_WARDEN_DEATH, SoundCategory.HOSTILE, 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);
                }
                getWorld().addParticle(ParticleTypes.EXPLOSION, getParticleX(1), getRandomBodyY(), getParticleZ(1), 0, 0, 0);
            }

            // Spawn particles at target entities
            List<LivingEntity> list = this.getWorld().getTargets(LivingEntity.class, CAN_ATTACK_PREDICATE, this, getBoundingBox().expand(20, 8, 20));
            for (LivingEntity entity : list) {
                // Spawn particle at entity
                getWorld().addParticle(ParticleTypes.SCULK_SOUL, entity.getX() + (random.nextFloat() - .5) * 2, entity.getY() + (random.nextFloat() - .5) * 2, entity.getZ() + (random.nextFloat() - .5) * 2, 0, 0, 0);

                // Spawn particles towards entity
                var line = MathUtil.getPointsOnLine(new Vector3d(this.getParticleX(0), this.getY()+.5, this.getParticleZ(0)), new Vector3d(entity.getX(), entity.getY() + .5, entity.getZ()), 2);
                for (Vector3d vec : line) {
                    getWorld().addParticle(ParticleTypes.SCULK_CHARGE_POP, vec.x + (random.nextFloat() - .5) * .25, vec.y + (random.nextFloat() - .5) * .25, vec.z + (random.nextFloat() - .5) * .25, 0, 0, 0);
                }
            }
        }

        // State switch check
        if (!getWorld().isClient) {
            if (getHealthPercentage() < 0 && state == CorruptedCoreState.ALIVE) {
                switchState(CorruptedCoreState.DYING);
            }

            switch (state) {
                case ALIVE -> aliveTick();
                case DYING -> dyingTick();
            }

            // Give effects to nearby enemies
            List<LivingEntity> list = this.getWorld().getTargets(LivingEntity.class, CAN_ATTACK_PREDICATE, this, getBoundingBox().expand(20, 8, 20));
            for (LivingEntity entity : list) {
                // Boost nearby entities
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20*10, 0));
            }
        }

        super.tickMovement();
    }

    private void aliveTick() {
        var healthPercent = getHealthPercentage();
        this.bossBar.setPercent(healthPercent);
        updatePlayers();
        this.spreadManager.tick(getWorld(), BlockPos.ofFloored(this.getX(), this.getY(), this.getZ()), random, true);
    }

    private void dyingTick() {
        this.blockSpreadManager.tick(getWorld());

        if (blockSpreadManager.getCursors().size() > 0) return;

        // There are no cursors left, remove the entity
        remove(RemovalReason.DISCARDED);
    }

    private void switchState(CorruptedCoreState newState) {
        if (newState == CorruptedCoreState.DYING) {
            this.bossBar.setVisible(false);

            // Start spreading grass
            for (int i = 0; i < 3; i++) {
                this.blockSpreadManager.spreadFrom(BlockPos.ofFloored(this.getX()-2, this.getY()-1, this.getZ()), 30, replacer);
                this.blockSpreadManager.spreadFrom(BlockPos.ofFloored(this.getX()+2, this.getY()-1, this.getZ()), 30, replacer);
                this.blockSpreadManager.spreadFrom(BlockPos.ofFloored(this.getX(), this.getY()-1, this.getZ()-2), 30, replacer);
                this.blockSpreadManager.spreadFrom(BlockPos.ofFloored(this.getX(), this.getY()-1, this.getZ()+2), 30, replacer);
            }

            setInvulnerable(true);
            // setInvisible(true);
            setIsDying(true);
        }

        state = newState;
    }

    private void updatePlayers() {
        // Update player boss bars
        for (PlayerEntity playerEntity : this.getWorld().getPlayers()) {
            ServerPlayerEntity player = (ServerPlayerEntity) playerEntity;
            if (player.distanceTo(this) < 16) bossBar.addPlayer(player);
            else bossBar.removePlayer(player);
        }
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        this.bossBar.setPercent(0);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        bossBar.removePlayer(player);
    }

    public boolean getIsDying() {
        return this.dataTracker.get(IS_DYING);
    }

    public void setIsDying(boolean isDying) {
        this.dataTracker.set(IS_DYING, isDying);
    }

    public static DefaultAttributeContainer.Builder createCorruptedCoreAttributes() {
        return DefaultAttributeContainer.builder()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 200)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED)
                .add(EntityAttributes.GENERIC_ARMOR)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)
                .add(EntityAttributes.GENERIC_MAX_ABSORPTION)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK);
    }

    static {
        IS_DYING = DataTracker.registerData(CorruptedCoreEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
        CAN_BOOST_PREDICATE = hostileEntity -> !hostileEntity.isPlayer() && hostileEntity.getType().isIn(CobaltEntityTypeTags.CORRUPTED_CORE_FRIENDS);
        CAN_ATTACK_PREDICATE = TargetPredicate.createAttackable().setBaseMaxDistance(20).setPredicate(CAN_BOOST_PREDICATE);
    }

    private enum CorruptedCoreState {
        ALIVE,
        DYING
    }
}
