package se.fusion1013.items.misc;

import net.minecraft.item.Item;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.CobaltRarity;
import se.fusion1013.items.components.CobaltComponents;

public class LightSoulItem extends CobaltItem {

    public LightSoulItem(CobaltItem.Settings settings) {
        super((Settings) settings
                .maxCount(1)
                .rarity(CobaltRarity.Quest)
                .component(CobaltComponents.LIGHT_INTENSITY, 1f)
                .component(CobaltComponents.LIGHT_ANGLE, 1f)
                .component(CobaltComponents.LIGHT_DISTANCE, 8f)
                .component(CobaltComponents.IS_ON, true)
                .component(CobaltComponents.COLOR, 0x327ba8));
    }

}
