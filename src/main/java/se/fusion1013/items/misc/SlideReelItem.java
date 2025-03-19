package se.fusion1013.items.misc;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.CobaltItemConfiguration;
import se.fusion1013.registries.CobaltRegistries;
import se.fusion1013.registries.CobaltRegistryKeys;
import se.fusion1013.slidereel.SlideReel;
import se.fusion1013.slidereel.SlideReelRegistry;

import java.util.List;
import java.util.Optional;

public class SlideReelItem extends CobaltItem {

    private final RegistryKey<SlideReel> slideReel;

    public SlideReelItem(RegistryKey<SlideReel> slideReel) {
        super(CobaltItemConfiguration.create(Formatting.WHITE), new FabricItemSettings().maxCount(1));
        this.slideReel = slideReel;
    }

    public RegistryKey<SlideReel> getSlideReel() {
        return slideReel;
    }
}
