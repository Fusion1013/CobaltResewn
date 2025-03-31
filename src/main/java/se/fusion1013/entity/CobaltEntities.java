package se.fusion1013.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static se.fusion1013.Main.MOD_NAMESPACE;

/**
 * Handles registering custom {@link Entity}s.
 */
public class CobaltEntities {

    // Arrows
    public static EntityType<LightningArrowEntity> LIGHTNING_ARROW;
    public static EntityType<ExplosiveArrowEntity> EXPLOSIVE_ARROW;

    // Sculk entities
    public static EntityType<CorruptedCoreEntity> CORRUPTED_CORE;
    public static EntityType<CorruptedZombieEntity> CORRUPTED_ZOMBIE;
    public static EntityType<CorruptedSkeletonEntity> CORRUPTED_SKELETON;
    public static EntityType<CorruptedSpiderEntity> CORRUPTED_SPIDER;
    public static EntityType<AutomatonEntity> AUTOMATON;

    public static EntityType<SmokeBombEntity> SMOKE_BOMB;
    public static EntityType<SmokeCloudEntity> SMOKE_CLOUD;
    public static EntityType<DynamiteEntity> DYNAMITE;

    public static EntityType<RatEntity> RAT;
    public static EntityType<RatEntity> CORRUPTED_RAT;

    public static void register() {
        LIGHTNING_ARROW = register("lightning_arrow", createThrownEntityType(LightningArrowEntity::new));
        EXPLOSIVE_ARROW = register("explosive_arrow", createThrownEntityType(ExplosiveArrowEntity::new));

        CORRUPTED_CORE = register("corrupted_core", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, CorruptedCoreEntity::new).dimensions(EntityDimensions.fixed(2f, 2f)).build());
        FabricDefaultAttributeRegistry.register(CORRUPTED_CORE, CorruptedCoreEntity.createCorruptedCoreAttributes());

        CORRUPTED_ZOMBIE = register("corrupted_zombie", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, CorruptedZombieEntity::new)
                .dimensions(EntityDimensions.fixed(0.6f, 1.95f))
                .build());
        FabricDefaultAttributeRegistry.register(CORRUPTED_ZOMBIE, CorruptedZombieEntity.createZombieAttributes());

        CORRUPTED_SKELETON = register("corrupted_skeleton", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, CorruptedSkeletonEntity::new)
                .dimensions(EntityDimensions.fixed(0.6f, 1.95f))
                .build());
        FabricDefaultAttributeRegistry.register(CORRUPTED_SKELETON, CorruptedSkeletonEntity.createAbstractSkeletonAttributes());

        CORRUPTED_SPIDER = register("corrupted_spider", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, CorruptedSpiderEntity::new)
                .dimensions(EntityDimensions.fixed(1.4f, 0.9f))
                .build());
        FabricDefaultAttributeRegistry.register(CORRUPTED_SPIDER, CorruptedSpiderEntity.createSpiderAttributes());

        AUTOMATON = register("automaton", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AutomatonEntity::new)
                .dimensions(EntityDimensions.fixed(0.6f, 1.95f))
                .build());
        FabricDefaultAttributeRegistry.register(AUTOMATON, AutomatonEntity.createVindicatorAttributes());

        SMOKE_BOMB = register("smoke_bomb", createThrownEntityType(SmokeBombEntity::new));
        SMOKE_CLOUD = register("smoke_cloud", FabricEntityTypeBuilder.<SmokeCloudEntity>create(SpawnGroup.MISC, SmokeCloudEntity::new).dimensions(EntityDimensions.fixed(6f, 0.5f)).trackRangeBlocks(120).trackedUpdateRate(Integer.MAX_VALUE).fireImmune().build());
        DYNAMITE = register("dynamite", createThrownEntityType(DynamiteEntity::new));


        RAT = register("rat", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, RatEntity::new).dimensions(EntityDimensions.fixed(0.45f, 0.35f)).build());
        FabricDefaultAttributeRegistry.register(RAT, RatEntity.createSilverfishAttributes());

        CORRUPTED_RAT = register("corrupted_rat", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, RatEntity::new).dimensions(EntityDimensions.fixed(0.45f, 0.35f)).build());
        FabricDefaultAttributeRegistry.register(CORRUPTED_RAT, RatEntity.createSilverfishAttributes());
    }

    private static <T extends Entity> EntityType<T> register(String s, EntityType<T> entityType) {
        return Registry.register(Registries.ENTITY_TYPE, MOD_NAMESPACE + ":" + s, entityType);
    }

    private static <T extends Entity> EntityType<T> createThrownEntityType(EntityType.EntityFactory<T> factory) {
        return FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory).dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeBlocks(4).trackedUpdateRate(20).build();
    }

}
