package se.fusion1013.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.util.TextUtil;
import se.fusion1013.util.item.AttributeModifierProvider;

import java.util.*;

/***
 * Provides global item configuration for all cobalt items
 */
public class CobaltItemConfiguration {

    private Formatting nameFormatting = Formatting.WHITE;
    private final List<Text> tooltip = new ArrayList<>();
    private final Map<EquipmentSlot, List<AttributeModifierProvider>> attributeModifiers = new HashMap<>();

    public CobaltItemConfiguration() {}

    public CobaltItemConfiguration(CobaltItemConfiguration configuration) {
        nameFormatting = configuration.nameFormatting;
        tooltip.addAll(configuration.tooltip);
        attributeModifiers.putAll(configuration.attributeModifiers);
    }

    public CobaltItemConfiguration clone() {
        return new CobaltItemConfiguration(this);
    }

    public static CobaltItemConfiguration create(Formatting nameFormatting) {
        return new CobaltItemConfiguration().nameFormatting(nameFormatting);
    }

    // Apply to item methods // NOTE: MUST BE CALLED FROM THE ITEM BASE CLASS

    public Text applyNameFormatting(Text text) {
        return text.copy().formatted(nameFormatting);
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        // General tooltip
        var tooltipText = Text.translatable(stack.getTranslationKey() + ".tooltip").formatted(Formatting.DARK_GRAY);
        var splitTooltip = TextUtil.splitText(tooltipText);
        tooltip.addAll(splitTooltip);

        // Extra tooltips
        tooltip.addAll(this.tooltip);
    }

    public int attributeCount() {
        return attributeModifiers.size();
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(Multimap<EntityAttribute, EntityAttributeModifier> map, ItemStack stack, EquipmentSlot slot) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        for (AttributeModifierProvider attribute : attributeModifiers.getOrDefault(slot, new ArrayList<>())) {
            builder.put(attribute.attribute(), attribute.modifier());
        }

        builder.putAll(map);
        return builder.build();
    }

    /***
     * Applies default nbt modifiers to the item.
     *
     * @param nbt the nbt of the item to modify.
     */
    public void postProcessNbt(NbtCompound nbt) {
        nbt.putBoolean("Unbreakable", true);
    }

    // Builder pattern

    /**
     * Set the name formatting for the item.
     *
     * @param formatting the formatting.
     * @return builder.
     */
    public CobaltItemConfiguration nameFormatting(Formatting formatting) {
        nameFormatting = formatting;
        return this;
    }

    /**
     * Apply a tooltip to the item with custom formatting.
     *
     * @param tooltip the text to apply to the item.
     * @return builder.
     */
    public CobaltItemConfiguration tooltip(Text... tooltip) {
        this.tooltip.addAll(Arrays.asList(tooltip));
        return this;
    }

    /**
     * Apply a tooltip to the item with default formatting.
     *
     * @param translatableStrings the translation string to apply to the tooltip.
     * @return builder.
     */
    public CobaltItemConfiguration tooltip(String... translatableStrings) {
        for (String s : translatableStrings) this.tooltip.add(Text.translatable(s).formatted(Formatting.DARK_GRAY));
        return this;
    }

    /**
     * Apply a tooltip in the set bonus style.
     *
     * @param setBonusId the id of the set bonus
     * @return builder.
     */
    public CobaltItemConfiguration setBonusTooltip(String setBonusId) {
        this.tooltip.add(Text.empty());
        this.tooltip.add(Text.translatable("item_set.cobalt." + setBonusId + ".tooltip.header").formatted(Formatting.GOLD));
        this.tooltip.add(Text.translatable("item_set.cobalt." + setBonusId + ".tooltip").formatted(Formatting.DARK_GRAY));
        return this;
    }

    /**
     * Apply an attribute modifier to the item.
     *
     * @param attribute the attribute to apply.
     * @param modifier the attribute modifier.
     * @param slots the slots to apply the attribute to. If left empty the attribute will not be applied.
     * @return builder.
     */
    public CobaltItemConfiguration attributeModifier(EntityAttribute attribute, EntityAttributeModifier modifier, EquipmentSlot... slots) {
        for (EquipmentSlot slot : slots) {
            List<AttributeModifierProvider> list = attributeModifiers.computeIfAbsent(slot, k -> new ArrayList<>());
            list.add(new AttributeModifierProvider(attribute, modifier));
        }
        return this;
    }
}
