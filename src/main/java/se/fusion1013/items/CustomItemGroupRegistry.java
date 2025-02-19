package se.fusion1013.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import se.fusion1013.block.CobaltBlocks;

import static se.fusion1013.items.CobaltItems.ADVENTURER_JOURNAL;
import static se.fusion1013.items.CobaltItems.WF_INSTRUCTION_MANUAL;

public class CustomItemGroupRegistry {


    public static final RegistryKey<ItemGroup> COBALT_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("cobalt", "item_group_all"));
    public static final ItemGroup COBALT_GROUP = register(COBALT_GROUP_KEY, FabricItemGroup.builder()
            .icon(() -> new ItemStack(CobaltItems.ICON_ITEM))
            .displayName(Text.translatable("item_group.cobalt.items"))
            .entries((displayContext, entries) -> {
                entries.add(WF_INSTRUCTION_MANUAL);
                entries.add(ADVENTURER_JOURNAL);
            })
            .build());

    public static final RegistryKey<ItemGroup> COBALT_WEAPON_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("cobalt", "weapon_group"));
    public static final ItemGroup COBALT_WEAPON_GROUP = register(COBALT_WEAPON_GROUP_KEY, FabricItemGroup.builder()
            .icon(() -> new ItemStack(CobaltItems.ADVENTURE_SWORD))
            .displayName(Text.translatable("item_group.cobalt.weapons"))
            .build());

    public static final RegistryKey<ItemGroup> COBALT_TRINKET_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("cobalt", "trinket_group"));
    public static final ItemGroup COBALT_TRINKET_GROUP = register(COBALT_TRINKET_GROUP_KEY, FabricItemGroup.builder()
            .icon(() -> new ItemStack(CobaltItems.MECHANIC_GLOVES))
            .displayName(Text.translatable("item_group.cobalt.trinkets"))
            .build());

    public static final RegistryKey<ItemGroup> COBALT_ARMOR_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("cobalt", "armor_group"));
    public static final ItemGroup COBALT_ARMOR_GROUP = register(COBALT_ARMOR_GROUP_KEY, FabricItemGroup.builder()
            .icon(() -> new ItemStack(CobaltItems.TINKER_ARMOR_SET.registeredHelmet))
            .displayName(Text.translatable("item_group.cobalt.armor"))
            .build());

    public static final RegistryKey<ItemGroup> COBALT_BLOCK_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier("cobalt", "block_group"));
    public static final ItemGroup COBALT_BLOCK_GROUP = register(COBALT_BLOCK_GROUP_KEY, FabricItemGroup.builder()
            .icon(() -> new ItemStack(CobaltBlocks.SCULK_STEM))
            .displayName(Text.translatable("item_group.cobalt.block"))
            .build());

    private static ItemGroup register(RegistryKey<ItemGroup> key, ItemGroup group) {
        Registry.register(Registries.ITEM_GROUP, key, group);
        return group;
    }

    public static void register() {
    }

}
