package se.fusion1013.items.misc;

import net.minecraft.registry.RegistryKey;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.slidereel.SlideReel;

public class SlideReelItem extends CobaltItem {

    private final RegistryKey<SlideReel> slideReel;

    public SlideReelItem(RegistryKey<SlideReel> slideReel) {
        super(new CobaltItem.Settings().maxCount(1));
        this.slideReel = slideReel;
    }

    public RegistryKey<SlideReel> getSlideReel() {
        return slideReel;
    }
}
