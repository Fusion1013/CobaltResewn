package se.fusion1013.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class ThrownItem extends CobaltItem implements ProjectileItem {

    private final ThrownEntityFactoryPlayer thrownEntityFactoryPlayer;
    private final ThrownEntityFactoryPosition thrownEntityFactoryPosition;
    private final int cooldown;

    public ThrownItem(CobaltItem.Settings settings, ThrownEntityFactoryPlayer thrownEntityFactoryPlayer, ThrownEntityFactoryPosition thrownEntityFactoryPosition) {
        this(settings, thrownEntityFactoryPlayer, thrownEntityFactoryPosition, 20);
    }

    public ThrownItem(CobaltItem.Settings settings, ThrownEntityFactoryPlayer thrownEntityFactoryPlayer, ThrownEntityFactoryPosition thrownEntityFactoryPosition, int cooldown) {
        super(settings);
        this.thrownEntityFactoryPlayer = thrownEntityFactoryPlayer;
        this.thrownEntityFactoryPosition = thrownEntityFactoryPosition;
        this.cooldown = cooldown;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack heldItem = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_LINGERING_POTION_THROW, SoundCategory.NEUTRAL, 0.5f, 1f);
        user.getItemCooldownManager().set(this, cooldown);
        if (!world.isClient) {
            ThrownItemEntity thrownEntity = thrownEntityFactoryPlayer.create(world, user);
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

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        return thrownEntityFactoryPosition.create(world, pos);
    }

    public interface ThrownEntityFactoryPlayer<T extends ThrownItemEntity> {
        T create(World world, PlayerEntity user);
    }

    public interface ThrownEntityFactoryPosition<T extends ThrownItemEntity> {
        T create(World world, Position position);
    }
}
