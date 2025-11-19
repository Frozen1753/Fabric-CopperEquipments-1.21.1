package net.frozen1753.copperequipments.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.frozen1753.copperequipments.item.ModItems;
import net.frozen1753.copperequipments.util.ModTags;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.*;
import net.minecraft.item.Items;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementsProvider extends FabricAdvancementProvider {
    public ModAdvancementsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry shineAgain = Advancement.Builder.create()
                .display(
                        Items.IRON_AXE, // icon
                        Text.translatable("advancements.husbandry.scrape_oxidation.title"),
                        Text.translatable("advancements.husbandry.scrape_oxidation.description"),
                        Identifier.of("minecraft","textures/gui/advancements/backgrounds/husbandry.png"),
                        AdvancementFrame.TASK,
                        true, true, false
                )
                .criterion("scrape_oxidation", ItemCriterion.Conditions.createItemUsedOnBlock(
                        LocationPredicate.Builder.create()
                                .block(BlockPredicate.Builder.create().tag(ModTags.Blocks.SCRAPABLE_BLOCKS)),
                        ItemPredicate.Builder.create().tag(ItemTags.AXES)
                ))
                .parent(Identifier.of("minecraft", "husbandry/root")) // deprecation warning, no worries
                .build(consumer, "husbandry/scrape_oxidation");

        AdvancementEntry rustRush = Advancement.Builder.create()
                .display(
                        ModItems.OXIDIZING_POWDER, // icon
                        Text.translatable("advancements.husbandry.force_oxidation.title"),
                        Text.translatable("advancements.husbandry.force_oxidation.description"),
                        Identifier.of("minecraft","textures/gui/advancements/backgrounds/husbandry.png"),
                        AdvancementFrame.TASK,
                        true, true, false
                )
                .criterion("force_oxidation", ItemCriterion.Conditions.createItemUsedOnBlock(
                        LocationPredicate.Builder.create()
                                .block(BlockPredicate.Builder.create().tag(ModTags.Blocks.OXIDIZABLE_BLOCKS)),
                        ItemPredicate.Builder.create().items(ModItems.OXIDIZING_POWDER)
                ))
                .parent(shineAgain)
                .build(consumer, "husbandry/force_oxidation");

        AdvancementEntry safelyHarvestHoney = Advancement.Builder.create()
                .display(
                        Items.HONEY_BOTTLE,
                        Text.translatable("advancements.husbandry.safely_harvest_honey.title"),
                        Text.translatable("advancements.husbandry.safely_harvest_honey.description"),
                        Identifier.of("minecraft","textures/gui/advancements/backgrounds/husbandry.png"),
                        AdvancementFrame.TASK,
                        true, true, false
                )
                .criterion("safely_harvest_honey", ItemCriterion.Conditions.createItemUsedOnBlock(
                        LocationPredicate.Builder.create()
                                .block(BlockPredicate.Builder.create().tag(BlockTags.BEEHIVES))
                                .smokey(true),
                        ItemPredicate.Builder.create().items(Items.GLASS_BOTTLE)
                ))
                .parent(shineAgain)
                .build(consumer, "husbandry/safely_harvest_honey");

        AdvancementEntry waxOn = Advancement.Builder.create()
                .display(
                        Items.HONEYCOMB,
                        Text.translatable("advancements.husbandry.wax_on.title"),
                        Text.translatable("advancements.husbandry.wax_on.description"),
                        Identifier.of("minecraft","textures/gui/advancements/backgrounds/husbandry.png"),
                        AdvancementFrame.TASK,
                        true, true, false
                )
                .criterion("wax_on", ItemCriterion.Conditions.createItemUsedOnBlock(
                        LocationPredicate.Builder.create()
                                .block(BlockPredicate.Builder.create().tag(ModTags.Blocks.WAXABLE_BLOCKS)),
                        ItemPredicate.Builder.create().items(Items.HONEYCOMB)
                ))
                .parent(safelyHarvestHoney)
                .build(consumer, "husbandry/wax_on");

        AdvancementEntry waxOff = Advancement.Builder.create()
                .display(
                        Items.STONE_AXE,
                        Text.translatable("advancements.husbandry.wax_off.title"),
                        Text.translatable("advancements.husbandry.wax_off.description"),
                        Identifier.of("minecraft","textures/gui/advancements/backgrounds/husbandry.png"),
                        AdvancementFrame.TASK,
                        true, true, false
                )
                .criterion("wax_off", ItemCriterion.Conditions.createItemUsedOnBlock(
                        LocationPredicate.Builder.create()
                                .block(BlockPredicate.Builder.create().tag(ModTags.Blocks.WAXED_BLOCKS)),
                        ItemPredicate.Builder.create().tag(ItemTags.AXES)
                ))
                .parent(waxOn)
                .build(consumer, "husbandry/wax_off");
    }
}
