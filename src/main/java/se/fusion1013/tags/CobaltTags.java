package se.fusion1013.tags;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

/**
 * {@link TagKey}s registered by Cobalt.
 */
public class CobaltTags {

    /**
     * Contains {@link Item}s that reveal {@link se.fusion1013.block.RuneBlock}s.
     */
    public static final TagKey<Item> REVEALS_RUNES = TagKey.of(RegistryKeys.ITEM, Identifier.of("cobalt", "reveals_runes"));

}
