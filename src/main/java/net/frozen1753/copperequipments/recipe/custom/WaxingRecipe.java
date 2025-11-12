package net.frozen1753.copperequipments.recipe.custom;

import net.frozen1753.copperequipments.item.custom.CopperItem;
import net.frozen1753.copperequipments.recipe.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

public class WaxingRecipe extends SpecialCraftingRecipe {
    public WaxingRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        System.out.println("[WAXING] [MATCHING] Checking matches...");
        System.out.println("[WAXING] [MATCHING] Got :" + input.getStacks());
        if (input.getStackCount() != 2) {
            System.out.println("[WAXING] [MATCHING] Need exactly 2 items; got " + input.getStackCount());
            System.out.println("[WAXING] [MATCHING] matches -> false");
            return false;
        }
        System.out.println("[WAXING] [MATCHING] Found exactly 2 items; continue...");

        boolean foundUnwaxedCopper = false;
        boolean foundHoney = false;

        for (ItemStack stack : input.getStacks()) {
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof CopperItem && !CopperItem.isWaxed(stack)) {
                if (CopperItem.isWaxed(stack)) {
                    System.out.println("[WAXING] [MATCHING] CopperItem is already waxed");
                    System.out.println("[WAXING] [MATCHING] matches -> false");
                    return false;
                }
                foundUnwaxedCopper = true;
                System.out.println("[WAXING] [MATCHING] Found unwaxed CopperItem");
            } else if (stack.isOf(Items.HONEYCOMB)) {
                foundHoney = true;
                System.out.println("[WAXING] [MATCHING] Found Honeycomb");
            } else {
                System.out.println("[WAXING] [MATCHING] Invalid ingredient: " + stack.getItem());
                return false;
            }
        }

        boolean ok = foundUnwaxedCopper && foundHoney;
        System.out.println("[WAXING] [MATCHING] matches -> " + ok);
        return ok;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        System.out.println("[WAXING] [CRAFTING] Attempting to craft waxed copper item...");

        for (int i = 0; i < input.getSize(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            System.out.println("[WAXING] [CRAFTING] Slot " + i + ": " + stack);

            if (stack.getItem() instanceof CopperItem) {
                System.out.println("[WAXING] [CRAFTING] Found CopperItem. Waxed: " + CopperItem.isWaxed(stack));

                if (!CopperItem.isWaxed(stack)) {
                    ItemStack result = stack.copy();
                    CopperItem.setWaxed(result, true);
                    System.out.println("[WAXING] [CRAFTING] Returning waxed item: " + result);
                    return result;
                }
            }
        }

        System.out.println("[WAXING] [CRAFTING] No valid unwaxed copper item found. Returning EMPTY.");
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CRAFTING_SPECIAL_WAXING;
    }
}

