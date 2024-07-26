package se.fusion1013.items.trinket;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import se.fusion1013.effect.CobaltEffects;
import se.fusion1013.items.CobaltItemConfiguration;
import se.fusion1013.items.CobaltItems;
import se.fusion1013.util.item.ItemSetUtil;

import java.util.List;

public class IceRuneItem extends CobaltTrinketItem {

    private static final float ICE_RADIUS = 8;

    public IceRuneItem() {
        super(new Item.Settings(),
                new CobaltItemConfiguration()
                        .nameFormatting(Formatting.AQUA)
                        .setBonusTooltip("ice_rune_health")
                        .setBonusTooltip("ice_rune_heavy")
                        .setBonusTooltip("ice_rune_fast"),
                (modifiers, stack, slot, entity, uuid) -> modifiers);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity, TrinketComponent trinketComponent) {
        super.tick(stack, slot, entity, trinketComponent);

        // Tick depending on which base rune is equipped
        if (trinketComponent.isEquipped(CobaltItems.TrinketItems.HEALTH_RUNE)) healthRuneTick(stack, slot, entity);
        if (trinketComponent.isEquipped(CobaltItems.TrinketItems.HEAVY_RUNE)) heavyRuneTick(stack, slot, entity);
        if (trinketComponent.isEquipped(CobaltItems.TrinketItems.FAST_RUNE)) fastRuneTick(stack, slot, entity);
    }

    private void healthRuneTick(ItemStack stack, SlotReference slot, LivingEntity entity) {
    }

    private void heavyRuneTick(ItemStack stack, SlotReference slot, LivingEntity entity) {
    }

    private void fastRuneTick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        World world = entity.getWorld();
        float time = world.getTime();
        Random r = Random.create();

        // Add freezing to nearby non-player entities
        Box box = entity.getBoundingBox().expand(ICE_RADIUS, ICE_RADIUS, ICE_RADIUS);
        List<Entity> otherEntities = world.getOtherEntities(entity, box, other -> !other.isPlayer());
        for (Entity other : otherEntities) {
            if (other instanceof LivingEntity living) {
                living.addStatusEffect(new StatusEffectInstance(CobaltEffects.FREEZING_EFFECT, 20, 0));
                Box otherBox = other.getBoundingBox();
                if (r.nextDouble() < .25) {
                    world.addParticle(ParticleTypes.SNOWFLAKE,
                            r.nextDouble() * (otherBox.maxX - otherBox.minX) + other.getX(),
                            r.nextDouble() * (otherBox.maxY - otherBox.minY) + other.getY(),
                            r.nextDouble() * (otherBox.maxZ - otherBox.minZ) + other.getZ(),
                            0, 0, 0
                    );
                }
            }
        }

        // Display particles in circle around player
        for (int i = 0; i < 8; i++) {
            float g = (i * (2 * (float)Math.PI / 16f) + time) % ((float)Math.PI * 2);
            world.addParticle(ParticleTypes.SNOWFLAKE,
                    entity.getX() + MathHelper.cos(g) * ICE_RADIUS,
                    entity.getY(),
                    entity.getZ() + MathHelper.sin(g) * ICE_RADIUS,
                    0, 0, 0
            );
        }
    }
}
