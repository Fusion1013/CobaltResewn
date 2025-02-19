package se.fusion1013.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import se.fusion1013.entity.SmokeBombEntity;

public class ThrownItem extends CobaltItem {

    private final ThrownEntityFactory thrownEntityFactory;
    private final int cooldown;

    public ThrownItem(CobaltItemConfiguration configuration, Settings settings, ThrownEntityFactory thrownEntityFactory) {
        this(configuration, settings, thrownEntityFactory, 20);
    }

    public ThrownItem(CobaltItemConfiguration configuration, Settings settings, ThrownEntityFactory thrownEntityFactory, int cooldown) {
        super(configuration, settings);
        this.thrownEntityFactory = thrownEntityFactory;
        this.cooldown = cooldown;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack heldItem = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_LINGERING_POTION_THROW, SoundCategory.NEUTRAL, 0.5f, 1f);
        user.getItemCooldownManager().set(this, cooldown);
        if (!world.isClient) {
            ThrownItemEntity thrownEntity = thrownEntityFactory.create(world, user);
            thrownEntity.setItem(heldItem);
            thrownEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0f, 0.5f, 1.0f);
            world.spawnEntity(thrownEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            heldItem.decrement(1);
        }

        return TypedActionResult.success(heldItem, world.isClient());
    }

    public interface ThrownEntityFactory<T extends ThrownItemEntity> {
        T create(World world, PlayerEntity user);
    }
}
