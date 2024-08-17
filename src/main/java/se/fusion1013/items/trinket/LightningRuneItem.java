package se.fusion1013.items.trinket;

import net.minecraft.item.Item;
import net.minecraft.util.Formatting;
import se.fusion1013.items.CobaltItemConfiguration;

public class LightningRuneItem extends CobaltTrinketItem {

    public LightningRuneItem() {
        super(new Item.Settings(), new CobaltItemConfiguration().nameFormatting(Formatting.YELLOW), (modifiers, stack, slot, entity, uuid) -> modifiers);
    }
}
