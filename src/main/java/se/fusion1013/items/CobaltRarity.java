package se.fusion1013.items;

import net.minecraft.util.Formatting;

public enum CobaltRarity {
    Average(Formatting.RED),
    Good(Formatting.GOLD),
    Great(Formatting.YELLOW),
    Outstanding(Formatting.GREEN),
    Perfect(Formatting.BLUE),
    Quest(Formatting.BOLD, Formatting.AQUA);

    public final Formatting[] formatting;

    CobaltRarity(Formatting... formatting) {
        this.formatting = formatting;
    }
}
