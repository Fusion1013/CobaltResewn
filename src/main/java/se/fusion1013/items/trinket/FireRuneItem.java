package se.fusion1013.items.trinket;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.items.CobaltItemConfiguration;
import se.fusion1013.items.CobaltItems;
import se.fusion1013.util.item.ItemSetUtil;

import java.util.List;

public class FireRuneItem extends CobaltTrinketItem {

    private int healCooldown;

    public FireRuneItem() {
        super(new Item.Settings(),
                new CobaltItemConfiguration()
                        .nameFormatting(Formatting.RED)
                        .setBonusTooltip("fire_rune_health")
                        .setBonusTooltip("fire_rune_heavy")
                        .setBonusTooltip("fire_rune_fast"),
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
        // Does nothing, applied from health rune
    }

    private void heavyRuneTick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        ItemSetUtil.addSetBonusStatusEffect(entity, new StatusEffectInstance(StatusEffects.RESISTANCE, 20, 0));
    }

    private void fastRuneTick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        // TODO
    }
}
