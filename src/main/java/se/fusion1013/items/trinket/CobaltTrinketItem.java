package se.fusion1013.items.trinket;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.ItemSet;

import java.util.List;
import java.util.UUID;

public class CobaltTrinketItem extends TrinketItem {

    private final TrinketModifierProvider modifierProvider;
    private final CobaltItem.Settings settings;

    public CobaltTrinketItem(CobaltItem.Settings settings, TrinketModifierProvider modifierProvider) {
        super(settings.maxCount(1)); // Override stack size for all trinkets
        this.settings = settings;
        this.modifierProvider = modifierProvider;
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.tick(stack, slot, entity);
        ItemSet.trinketTick(stack, slot, entity);
    }

    @Override
    public Text getName(ItemStack stack) {
        Text text = super.getName(stack);
        return settings.applyNameFormatting(text);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        return modifierProvider.getModifiers(modifiers, stack, slot, entity, uuid);


        // SlotAttributes.addSlotModifier(modifiers, "feet/aglet", uuid, 1, EntityAttributeModifier.Operation.ADDITION);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        settings.appendTooltip(stack, context, tooltip, type);
        super.appendTooltip(stack, context, tooltip, type);
    }

    public interface TrinketModifierProvider {
        Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(Multimap<EntityAttribute, EntityAttributeModifier> modifiers, ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid);
    }
}
