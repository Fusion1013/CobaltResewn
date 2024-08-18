package se.fusion1013.items.sword;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.CobaltItemConfiguration;
import se.fusion1013.items.CobaltRarity;
import se.fusion1013.util.TextUtil;

import java.util.List;

public class AdvancedBionicFistItem extends CobaltSwordItem {

    private static final float ABILITY_RADIUS = 3;
    private static final int ABILITY_COOLDOWN = 60; // 3 seconds
    private static final float AOE_DAMAGE = 5;

    public AdvancedBionicFistItem() {
        super(9, 2.3f, new CobaltItem.Settings().rarity(CobaltRarity.Perfect));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.addAll(TextUtil.splitText(Text.translatable("item.cobalt.advanced_bionic_fist.ability").formatted(Formatting.GOLD)));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        tryTriggerAbility(world, user);
        return super.use(world, user, hand);
    }

    private void tryTriggerAbility(World world, PlayerEntity user) {
        if (user.getInventory().containsAny(itemStack -> itemStack.getItem() == Items.COAL)) {
            user.getInventory().remove(itemStack -> itemStack.getItem() == Items.COAL, 1, user.getInventory());

            lunge(world, user);
            triggerAoeAbility(world, user);

            user.playSound(SoundEvents.BLOCK_LAVA_EXTINGUISH, 1, 1);
            user.getItemCooldownManager().set(this, ABILITY_COOLDOWN);
        }
    }

    private void triggerAoeAbility(World world, PlayerEntity user) {
        Random random = Random.create();
        Box box = user.getBoundingBox().expand(ABILITY_RADIUS);
        List<Entity> otherEntities = world.getOtherEntities(user, box);

        // Damage entities in the radius
        for (Entity otherEntity : otherEntities) {
            if (otherEntity instanceof LivingEntity livingEntity) {
                if (livingEntity.distanceTo(user) > ABILITY_RADIUS) continue;

                livingEntity.damage(livingEntity.getDamageSources().playerAttack(user), AOE_DAMAGE);
            }
        }

        // Display particles
        int particleCount = 32;
        for (int i = 0; i < particleCount; i++) {
            float g = i * (float)Math.PI / (particleCount / 2f);
            world.addParticle(ParticleTypes.SMOKE,
                    user.getX() + MathHelper.cos(g) * ABILITY_RADIUS,
                    user.getY() + 0.1f,
                    user.getZ() + MathHelper.sin(g) * ABILITY_RADIUS,
                    0, 0, 0
            );
            world.addParticle(ParticleTypes.LARGE_SMOKE,
                    user.getX() + MathHelper.cos(g) * ABILITY_RADIUS / 1.5f,
                    user.getY() + 0.1f,
                    user.getZ() + MathHelper.sin(g) * ABILITY_RADIUS / 1.5f,
                    0, 0, 0
            );
            for (int j = 0; j < 8; j++) {
                world.addParticle(ParticleTypes.CRIT,
                        user.getX() + MathHelper.cos(g) * ABILITY_RADIUS + random.nextFloat() * 0.25f,
                        user.getY() + random.nextFloat() * 0.1f,
                        user.getZ() + MathHelper.sin(g) * ABILITY_RADIUS + random.nextFloat() * 0.25f,
                        0, 0, 0
                );
            }
        }
    }

    private void lunge(World world, PlayerEntity user) {
        Vec3d lookDirection = user.getRotationVec(1.0f);
        user.addVelocity(lookDirection.multiply(-1.25));
    }
}
