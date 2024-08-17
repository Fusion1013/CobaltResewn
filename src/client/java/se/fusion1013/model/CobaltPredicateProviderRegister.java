package se.fusion1013.model;

import se.fusion1013.items.CobaltItems;
import se.fusion1013.items.crossbow.CobaltCrossbowItem;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class CobaltPredicateProviderRegister {

    public static void register() {
        registerCrossbow(CobaltItems.HUNTER_CROSSBOW);
        registerCrossbow(CobaltItems.HARPOON_GUN);
    }

    private static void registerCrossbow(Item item) {
        ModelPredicateProviderRegistry.register(item, Identifier.of("pulling"), (stack, world, entity, seed) -> {
            if (entity == null) return 0;
            return entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
        });
        ModelPredicateProviderRegistry.register(item, Identifier.of("pull"), (stack, world, entity, seed) -> {
            if (entity == null) return 0;
            return entity.getActiveItem() != stack ? 0.0F : (stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / 20.0F;
        });
        ModelPredicateProviderRegistry.register(item, Identifier.of("charged"), (stack, world, entity, seed) -> {
            if (entity == null) return 0;
            return CobaltCrossbowItem.isCharged(stack) ? 1F : 0F;
        });
    }

}
