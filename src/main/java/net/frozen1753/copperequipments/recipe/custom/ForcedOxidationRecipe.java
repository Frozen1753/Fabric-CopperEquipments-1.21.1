package net.frozen1753.copperequipments.recipe.custom;

import net.frozen1753.copperequipments.block.ModBlocks;
import net.frozen1753.copperequipments.item.ModItems;
import net.frozen1753.copperequipments.item.custom.CopperItem;
import net.frozen1753.copperequipments.recipe.ModRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

import java.util.Map;

public class ForcedOxidationRecipe extends SpecialCraftingRecipe {
    public ForcedOxidationRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    private static final Map<ItemConvertible, ItemConvertible> OXIDATION_MAP = Map.ofEntries(
            // Vanilla
            Map.entry(Blocks.COPPER_BLOCK, Blocks.EXPOSED_COPPER),
            Map.entry(Blocks.EXPOSED_COPPER, Blocks.WEATHERED_COPPER),
            Map.entry(Blocks.WEATHERED_COPPER, Blocks.OXIDIZED_COPPER),

            Map.entry(Blocks.CHISELED_COPPER, Blocks.EXPOSED_CHISELED_COPPER),
            Map.entry(Blocks.EXPOSED_CHISELED_COPPER, Blocks.WEATHERED_CHISELED_COPPER),
            Map.entry(Blocks.WEATHERED_CHISELED_COPPER, Blocks.OXIDIZED_CHISELED_COPPER),

            Map.entry(Blocks.COPPER_GRATE, Blocks.EXPOSED_COPPER_GRATE),
            Map.entry(Blocks.EXPOSED_COPPER_GRATE, Blocks.WEATHERED_COPPER_GRATE),
            Map.entry(Blocks.WEATHERED_COPPER_GRATE, Blocks.OXIDIZED_COPPER_GRATE),

            Map.entry(Blocks.CUT_COPPER, Blocks.EXPOSED_CUT_COPPER),
            Map.entry(Blocks.EXPOSED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER),
            Map.entry(Blocks.WEATHERED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER),

            Map.entry(Blocks.CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER_STAIRS),
            Map.entry(Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER_STAIRS),
            Map.entry(Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER_STAIRS),

            Map.entry(Blocks.CUT_COPPER_SLAB, Blocks.EXPOSED_CUT_COPPER_SLAB),
            Map.entry(Blocks.EXPOSED_CUT_COPPER_SLAB, Blocks.WEATHERED_CUT_COPPER_SLAB),
            Map.entry(Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.OXIDIZED_CUT_COPPER_SLAB),

            Map.entry(Blocks.COPPER_BULB, Blocks.EXPOSED_COPPER_BULB),
            Map.entry(Blocks.EXPOSED_COPPER_BULB, Blocks.WEATHERED_COPPER_BULB),
            Map.entry(Blocks.WEATHERED_COPPER_BULB, Blocks.OXIDIZED_COPPER_BULB),

            Map.entry(Blocks.COPPER_DOOR, Blocks.EXPOSED_COPPER_DOOR),
            Map.entry(Blocks.EXPOSED_COPPER_DOOR, Blocks.WEATHERED_COPPER_DOOR),
            Map.entry(Blocks.WEATHERED_COPPER_DOOR, Blocks.OXIDIZED_COPPER_DOOR),

            Map.entry(Blocks.COPPER_TRAPDOOR, Blocks.EXPOSED_COPPER_TRAPDOOR),
            Map.entry(Blocks.EXPOSED_COPPER_TRAPDOOR, Blocks.WEATHERED_COPPER_TRAPDOOR),
            Map.entry(Blocks.WEATHERED_COPPER_TRAPDOOR, Blocks.OXIDIZED_COPPER_TRAPDOOR),

            // Mod
            Map.entry(ModBlocks.COPPER_BARS, ModBlocks.EXPOSED_COPPER_BARS),
            Map.entry(ModBlocks.EXPOSED_COPPER_BARS, ModBlocks.WEATHERED_COPPER_BARS),
            Map.entry(ModBlocks.WEATHERED_COPPER_BARS, ModBlocks.OXIDIZED_COPPER_BARS),

            Map.entry(ModBlocks.COPPER_CHAIN, ModBlocks.EXPOSED_COPPER_CHAIN),
            Map.entry(ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.WEATHERED_COPPER_CHAIN),
            Map.entry(ModBlocks.WEATHERED_COPPER_CHAIN, ModBlocks.OXIDIZED_COPPER_CHAIN),

            Map.entry(ModBlocks.COPPER_LANTERN, ModBlocks.EXPOSED_COPPER_LANTERN),
            Map.entry(ModBlocks.EXPOSED_COPPER_LANTERN, ModBlocks.WEATHERED_COPPER_LANTERN),
            Map.entry(ModBlocks.WEATHERED_COPPER_LANTERN, ModBlocks.OXIDIZED_COPPER_LANTERN)
    );

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        if (input.getStackCount() != 2) {
            return false;
        }
        boolean foundPowder = false;
        boolean foundOxidizable = false;

        for (ItemStack stack : input.getStacks()) {
            if (stack.isEmpty()) continue;

            Item item = stack.getItem();

            if (item == ModItems.OXIDIZING_POWDER) {
                foundPowder = true;
                continue;
            }

            if (item instanceof BlockItem blockItem) {
                Block block = blockItem.getBlock();
                if (OXIDATION_MAP.containsKey(block)) {
                    foundOxidizable = true;
                }
            }

            if (item instanceof CopperItem) {
                if (!CopperItem.isWaxed(stack)) {
                    int stage = CopperItem.getOxidationStage(stack);
                    foundOxidizable = stage >= 0 && stage < 3; // can still oxidize forward
                }
            }
        }

        return foundPowder && foundOxidizable;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack oxidizableStack = ItemStack.EMPTY;

        for (ItemStack stack : input.getStacks()) {
            if (stack.isEmpty()) continue;

            Item item = stack.getItem();

            if (item instanceof BlockItem blockItem) {
                Block block = blockItem.getBlock();
                Block oxidized = (Block) OXIDATION_MAP.get(block);
                if (oxidized != null) {
                    oxidizableStack = new ItemStack(oxidized.asItem());
                    break;
                }
            }

            if (item instanceof CopperItem) {
                ItemStack result = stack.copy();
                int stage = CopperItem.getOxidationStage(result);
                if (stage < 3) {
                    CopperItem.setOxidationStage(result, stage + 1);
                }
            }
        }

        return oxidizableStack;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CRAFTING_SPECIAL_FORCED_OXIDATION;
    }
}
