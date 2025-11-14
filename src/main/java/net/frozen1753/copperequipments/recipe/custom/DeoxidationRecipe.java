package net.frozen1753.copperequipments.recipe.custom;

import net.frozen1753.copperequipments.block.ModBlocks;
import net.frozen1753.copperequipments.item.custom.CopperItem;
import net.frozen1753.copperequipments.recipe.ModRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

import java.util.Map;

public class DeoxidationRecipe extends SpecialCraftingRecipe {
    public DeoxidationRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    private static final Map<ItemConvertible, ItemConvertible> DEOXIDATION_MAP = Map.ofEntries(
            // Vanilla
            Map.entry(Blocks.OXIDIZED_COPPER, Blocks.WEATHERED_COPPER),
            Map.entry(Blocks.WEATHERED_COPPER, Blocks.EXPOSED_COPPER),
            Map.entry(Blocks.EXPOSED_COPPER, Blocks.COPPER_BLOCK),

            Map.entry(Blocks.OXIDIZED_CHISELED_COPPER, Blocks.WEATHERED_CHISELED_COPPER),
            Map.entry(Blocks.WEATHERED_CHISELED_COPPER, Blocks.EXPOSED_CHISELED_COPPER),
            Map.entry(Blocks.EXPOSED_CHISELED_COPPER, Blocks.CHISELED_COPPER),

            Map.entry(Blocks.OXIDIZED_COPPER_GRATE, Blocks.WEATHERED_COPPER_GRATE),
            Map.entry(Blocks.WEATHERED_COPPER_GRATE, Blocks.EXPOSED_COPPER_GRATE),
            Map.entry(Blocks.EXPOSED_COPPER_GRATE, Blocks.COPPER_GRATE),

            Map.entry(Blocks.OXIDIZED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER),
            Map.entry(Blocks.WEATHERED_CUT_COPPER, Blocks.EXPOSED_CUT_COPPER),
            Map.entry(Blocks.EXPOSED_CUT_COPPER, Blocks.CUT_COPPER),

            Map.entry(Blocks.OXIDIZED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER_STAIRS),
            Map.entry(Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER_STAIRS),
            Map.entry(Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.CUT_COPPER_STAIRS),

            Map.entry(Blocks.OXIDIZED_CUT_COPPER_SLAB, Blocks.WEATHERED_CUT_COPPER_SLAB),
            Map.entry(Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.EXPOSED_CUT_COPPER_SLAB),
            Map.entry(Blocks.EXPOSED_CUT_COPPER_SLAB, Blocks.CUT_COPPER_SLAB),

            Map.entry(Blocks.OXIDIZED_COPPER_BULB, Blocks.WEATHERED_COPPER_BULB),
            Map.entry(Blocks.WEATHERED_COPPER_BULB, Blocks.EXPOSED_COPPER_BULB),
            Map.entry(Blocks.EXPOSED_COPPER_BULB, Blocks.COPPER_BULB),

            Map.entry(Blocks.OXIDIZED_COPPER_DOOR, Blocks.WEATHERED_COPPER_DOOR),
            Map.entry(Blocks.WEATHERED_COPPER_DOOR, Blocks.EXPOSED_COPPER_DOOR),
            Map.entry(Blocks.EXPOSED_COPPER_DOOR, Blocks.COPPER_DOOR),

            Map.entry(Blocks.OXIDIZED_COPPER_TRAPDOOR, Blocks.WEATHERED_COPPER_TRAPDOOR),
            Map.entry(Blocks.WEATHERED_COPPER_TRAPDOOR, Blocks.EXPOSED_COPPER_TRAPDOOR),
            Map.entry(Blocks.EXPOSED_COPPER_TRAPDOOR, Blocks.COPPER_TRAPDOOR),

            // Mod
            Map.entry(ModBlocks.OXIDIZED_COPPER_BARS, ModBlocks.WEATHERED_COPPER_BARS),
            Map.entry(ModBlocks.WEATHERED_COPPER_BARS, ModBlocks.EXPOSED_COPPER_BARS),
            Map.entry(ModBlocks.EXPOSED_COPPER_BARS, ModBlocks.COPPER_BARS),

            Map.entry(ModBlocks.OXIDIZED_COPPER_CHAIN, ModBlocks.WEATHERED_COPPER_CHAIN),
            Map.entry(ModBlocks.WEATHERED_COPPER_CHAIN, ModBlocks.EXPOSED_COPPER_CHAIN),
            Map.entry(ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.COPPER_CHAIN),

            Map.entry(ModBlocks.OXIDIZED_COPPER_LANTERN, ModBlocks.WEATHERED_COPPER_LANTERN),
            Map.entry(ModBlocks.WEATHERED_COPPER_LANTERN, ModBlocks.EXPOSED_COPPER_LANTERN),
            Map.entry(ModBlocks.EXPOSED_COPPER_LANTERN, ModBlocks.COPPER_LANTERN)
    );

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        if (input.getStackCount() != 1) return false;

        for (ItemStack stack : input.getStacks()) {
            if (stack.isEmpty()) continue;
            if (stack.getItem() instanceof BlockItem blockItem) {
                if (DEOXIDATION_MAP.containsKey(blockItem.getBlock())) {
                    return true;
                }
            }
            if (stack.getItem() instanceof CopperItem) {
                if (!CopperItem.isWaxed(stack)) {
                    int stage = CopperItem.getOxidationStage(stack);
                    if (stage > 0) return true; // can still revert backwards
                }
            }

        }
        return false;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        for (ItemStack stack : input.getStacks()) {
            if (stack.isEmpty()) continue;
            if (stack.getItem() instanceof BlockItem blockItem) {
                Block result = (Block) DEOXIDATION_MAP.get(blockItem.getBlock());
                if (result != null) {
                    return new ItemStack(result.asItem());
                }
            }

            if (stack.getItem() instanceof CopperItem) {
                ItemStack result = stack.copy();
                int stage = CopperItem.getOxidationStage(result);

                if (stage > 0) {
                    CopperItem.setOxidationStage(result, stage - 1);
                }

                return result;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CRAFTING_SPECIAL_DEOXIDATION;
    }
}

