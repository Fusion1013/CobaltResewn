package se.fusion1013.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.CobaltItems;

import java.util.function.Consumer;

public class CobaltDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(AdvancementProvider::new);
    }

    static class AdvancementProvider extends FabricAdvancementProvider {

        protected AdvancementProvider(FabricDataOutput dataGenerator) {
            super(dataGenerator);
        }

        @Override
        public void generateAdvancement(Consumer<AdvancementEntry> consumer) {
            AdvancementEntry rootAdvancement = Advancement.Builder.create()
                    .display(
                            CobaltItems.ADVENTURER_JOURNAL,
                            Text.literal("Adventurer's Journal"),
                            Text.literal("Acquire an Adventurer's Journal"),
                            new Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                            AdvancementFrame.TASK,
                            true,
                            false,
                            false
                    )
                    .criterion("got_adventure_journal", InventoryChangedCriterion.Conditions.items(CobaltItems.ADVENTURER_JOURNAL))
                    .build(consumer, Main.MOD_NAMESPACE + "/adventure_journal/root");
            AdvancementEntry grafwallQuagmire = Advancement.Builder.create().parent(rootAdvancement)
                    .display(
                            Items.MANGROVE_LOG,
                            Text.literal("Grafwall Quagmire"),
                            Text.literal("Visit the Grafwall Quagmire"),
                            null,
                            AdvancementFrame.TASK,
                            true,
                            false,
                            true
                    )
                    .criterion("visit_grafwall_quagmire", Criteria.IMPOSSIBLE.create(new ImpossibleCriterion.Conditions()))
                    .build(consumer, Main.MOD_NAMESPACE + "/adventure_journal/grafwall_quagmire");
            AdvancementEntry waterFacility = Advancement.Builder.create().parent(grafwallQuagmire)
                    .display(
                            Items.COPPER_BLOCK,
                            Text.literal("Water Facility"),
                            Text.literal("Visit the Water Facility"),
                            null,
                            AdvancementFrame.TASK,
                            true,
                            false,
                            true
                    )
                    .criterion("visit_water_facility", Criteria.IMPOSSIBLE.create(new ImpossibleCriterion.Conditions()))
                    .build(consumer, Main.MOD_NAMESPACE + "/adventure_journal/water_facility");
            AdvancementEntry codeVaults = Advancement.Builder.create().parent(rootAdvancement)
                    .display(
                            Items.COPPER_BLOCK,
                            Text.literal("Code Vaults"),
                            Text.literal("Learn about Code Vaults"),
                            null,
                            AdvancementFrame.TASK,
                            true,
                            false,
                            true
                    )
                    .criterion("visit_code_vaults", Criteria.IMPOSSIBLE.create(new ImpossibleCriterion.Conditions()))
                    .build(consumer, Main.MOD_NAMESPACE + "/adventure_journal/code_vaults");
            AdvancementEntry codeVault1 = Advancement.Builder.create().parent(codeVaults)
                    .display(
                            Items.COPPER_BLOCK,
                            Text.literal("Code Vault 1"),
                            Text.literal("Visit Code Vault 1"),
                            null,
                            AdvancementFrame.TASK,
                            true,
                            false,
                            true
                    )
                    .criterion("visit_code_vault_1", Criteria.IMPOSSIBLE.create(new ImpossibleCriterion.Conditions()))
                    .build(consumer, Main.MOD_NAMESPACE + "/adventure_journal/code_vault_1");
            AdvancementEntry codeVault2 = Advancement.Builder.create().parent(codeVaults)
                    .display(
                            Items.COPPER_BLOCK,
                            Text.literal("Code Vault 2"),
                            Text.literal("Visit Code Vault 2"),
                            null,
                            AdvancementFrame.TASK,
                            true,
                            false,
                            true
                    )
                    .criterion("visit_code_vault_2", Criteria.IMPOSSIBLE.create(new ImpossibleCriterion.Conditions()))
                    .build(consumer, Main.MOD_NAMESPACE + "/adventure_journal/code_vault_2");
            AdvancementEntry ravenfell = Advancement.Builder.create().parent(rootAdvancement)
                    .display(
                            Items.COPPER_BLOCK,
                            Text.literal("Ravenfell"),
                            Text.literal("Visit Ravenfell"),
                            null,
                            AdvancementFrame.TASK,
                            true,
                            false,
                            true
                    )
                    .criterion("visit_ravenfell", Criteria.IMPOSSIBLE.create(new ImpossibleCriterion.Conditions()))
                    .build(consumer, Main.MOD_NAMESPACE + "/adventure_journal/ravenfell");
        }

    }
}
