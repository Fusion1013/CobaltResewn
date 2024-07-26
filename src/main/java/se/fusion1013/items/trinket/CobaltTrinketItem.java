package se.fusion1013.items.trinket;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.items.CobaltItemConfiguration;
import se.fusion1013.items.ItemSet;

import java.util.List;
import java.util.UUID;

public class CobaltTrinketItem extends TrinketItem {

    private final CobaltItemConfiguration configuration;
    private final TrinketModifierProvider modifierProvider;

    public CobaltTrinketItem(Settings settings, CobaltItemConfiguration configuration, TrinketModifierProvider modifierProvider) {
        super(settings.maxCount(1)); // Override stack size for all trinkets
        this.configuration = configuration;
        this.modifierProvider = modifierProvider;
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.tick(stack, slot, entity);
        ItemSet.trinketTick(stack, slot, entity);

        // Call tick with trinket component, if one exists
        var trinketComponentOptional = TrinketsApi.getTrinketComponent(entity);
        if (trinketComponentOptional.isEmpty()) return;
        tick(stack, slot, entity, trinketComponentOptional.get());
    }

    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity, TrinketComponent trinketComponent) {}

    @Override
    public Text getName(ItemStack stack) {
        Text text = super.getName(stack);
        return configuration.applyNameFormatting(text);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
        return configuration.getAttributeModifiers(super.getAttributeModifiers(stack, slot), stack, slot);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        return modifierProvider.getModifiers(modifiers, stack, slot, entity, uuid);


        // SlotAttributes.addSlotModifier(modifiers, "feet/aglet", uuid, 1, EntityAttributeModifier.Operation.ADDITION);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        configuration.appendTooltip(stack, world, tooltip, context);
        super.appendTooltip(stack, world, tooltip, context);
    }

    public interface TrinketModifierProvider {
        Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(Multimap<EntityAttribute, EntityAttributeModifier> modifiers, ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid);
    }
}
