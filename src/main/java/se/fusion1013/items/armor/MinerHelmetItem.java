package se.fusion1013.items.armor;

import net.minecraft.component.ComponentMap;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import se.fusion1013.items.CobaltEquipmentItem;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.components.CobaltComponents;

public class MinerHelmetItem extends CobaltEquipmentItem {
    public MinerHelmetItem(RegistryEntry<ArmorMaterial> material, CobaltItem.Settings settings) {
        super(material, (CobaltItem.Settings) settings
                .component(CobaltComponents.LIGHT_DISTANCE, 20f)
                .component(CobaltComponents.IS_ON, true)
                .component(CobaltComponents.COLOR, 0xFFffc294)
                .component(CobaltComponents.LIGHT_INTENSITY, 1.2f)
                .component(CobaltComponents.LIGHT_ANGLE, 0.6f), EquipmentSlot.HEAD);
    }
}
