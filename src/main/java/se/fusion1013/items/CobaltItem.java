package se.fusion1013.items;

import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import se.fusion1013.util.TextUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CobaltItem extends Item {

    private final Settings settings;

    public CobaltItem(Settings settings) {
        super(settings);
        this.settings = settings;
    }

    @Override
    public Text getName(ItemStack stack) {
        return settings.applyNameFormatting(super.getName(stack));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        settings.appendTooltip(stack, context, tooltip, type);
        super.appendTooltip(stack, context, tooltip, type);
    }

    public static class Settings extends Item.Settings {

        private final Formatting nameFormatting;
        private final List<Text> tooltip = new ArrayList<>();
        private CobaltRarity rarity = CobaltRarity.Average;

        public Settings() {
            this(Formatting.WHITE);
        }

        public Settings(Formatting nameFormatting) {
            super();
            this.nameFormatting = nameFormatting;

            // Apply default components
            component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(false));
        }

        // Apply

        public Text applyNameFormatting(Text text) {
            if (nameFormatting == Formatting.WHITE) return text.copy().formatted(rarity.formatting);
            else return text.copy().formatted(nameFormatting);
        }

        public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
            // var tooltipText = Text.translatable(stack.getTranslationKey() + ".tooltip").formatted(Formatting.DARK_GRAY);
            // var splitTooltip = TextUtil.splitText(tooltipText);
            // tooltip.addAll(splitTooltip);
            tooltip.addAll(this.tooltip);
        }

        // Builder

        public Settings rarity(CobaltRarity rarity) {
            this.rarity = rarity;
            return this;
        }

        public Settings tooltip(Text... tooltip) {
            this.tooltip.addAll(Arrays.asList(tooltip));
            return this;
        }

        private Settings tooltip(String... translatableStrings) {
            for (String s : translatableStrings) this.tooltip.add(Text.translatable(s).formatted(Formatting.DARK_GRAY));
            return this;
        }

        // Override

        public Settings maxCount(int amount) {
            super.maxCount(amount);
            return this;
        }
    }
}
