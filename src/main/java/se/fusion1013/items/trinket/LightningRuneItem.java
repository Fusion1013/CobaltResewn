package se.fusion1013.items.trinket;

import net.minecraft.item.Item;
import net.minecraft.util.Formatting;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.CobaltItemConfiguration;
import se.fusion1013.items.CobaltRarity;

public class LightningRuneItem extends CobaltTrinketItem {

    public LightningRuneItem() {
        super(new CobaltItem.Settings().rarity(CobaltRarity.Perfect), (modifiers, stack, slot, entity, uuid) -> modifiers);
    }
}
