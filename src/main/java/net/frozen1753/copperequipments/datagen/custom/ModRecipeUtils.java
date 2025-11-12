package net.frozen1753.copperequipments.datagen.custom;

import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ModRecipeUtils {
    public static void offerWaxingRecipes(RecipeExporter exporter, Map<ItemConvertible, ItemConvertible> blocks) {
        blocks.forEach((unwaxed, waxed) -> {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, waxed)
                    .input(unwaxed)
                    .input(Items.HONEYCOMB)
                    .group(getItemPath(waxed))
                    .criterion(hasItem(unwaxed), conditionsFromItem(unwaxed))
                    .offerTo(exporter, convertBetween(waxed, Items.HONEYCOMB));
        });
    }

    public static String hasItem(ItemConvertible item) {
        return "has_" + getItemPath(item);
    }

    public static String getItemPath(ItemConvertible item) {
        return Registries.ITEM.getId(item.asItem()).getPath();
    }

    public static String convertBetween(ItemConvertible to, ItemConvertible from) {
        return getItemPath(to) + "_from_" + getItemPath(from);
    }

    public static AdvancementCriterion<InventoryChangedCriterion.Conditions> conditionsFromItem(ItemConvertible item) {
        return conditionsFromPredicates(ItemPredicate.Builder.create().items(item));
    }

    public static AdvancementCriterion<InventoryChangedCriterion.Conditions> conditionsFromPredicates(ItemPredicate.Builder... predicates) {
        return conditionsFromItemPredicates((ItemPredicate[]) Arrays.stream(predicates).map(ItemPredicate.Builder::build).toArray(ItemPredicate[]::new));
    }

    public static AdvancementCriterion<InventoryChangedCriterion.Conditions> conditionsFromItemPredicates(ItemPredicate... predicates) {
        return Criteria.INVENTORY_CHANGED
                .create(new InventoryChangedCriterion.Conditions(Optional.empty(), InventoryChangedCriterion.Conditions.Slots.ANY, List.of(predicates)));
    }
}
