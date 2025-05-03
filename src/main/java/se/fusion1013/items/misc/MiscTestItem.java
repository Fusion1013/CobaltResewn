package se.fusion1013.items.misc;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import se.fusion1013.Main;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.CobaltItemConfiguration;
import se.fusion1013.registries.CobaltRegistryKeys;

public class MiscTestItem extends CobaltItem {

    public MiscTestItem() {
        super(CobaltItemConfiguration.create(Formatting.GOLD), new Item.Settings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) return super.use(world, user, hand);

        final DynamicRegistryManager registryManager = world.getRegistryManager();
        Main.LOGGER.info("There are " + registryManager.get(CobaltRegistryKeys.SLIDE_REEL).size() + " dynamic slide reels loaded");
        registryManager.get(CobaltRegistryKeys.SLIDE_REEL).forEach(k -> {
            Main.LOGGER.info("Slide Reel: " + k.title);
            Main.LOGGER.info("Pictures:");
            for (String picturePath : k.pictures) {
                Main.LOGGER.info(" - " + picturePath);
            }
        });

        return super.use(world, user, hand);
    }
}
