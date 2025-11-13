package net.frozen1753.copperequipments.recipe.custom;

import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.item.custom.CopperItem;
import net.frozen1753.copperequipments.recipe.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

public class UnwaxingRecipe extends SpecialCraftingRecipe {
    public UnwaxingRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        if (input.getStackCount() != 1) {
            return false;
        }

        boolean foundWaxedCopper = false;

        for (ItemStack stack : input.getStacks()) {
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof CopperItem) {
                if (!CopperItem.isWaxed(stack)) {
                    return false;
                }
                foundWaxedCopper = true;
            } else {
                return false;
            }
        }

        return foundWaxedCopper;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {

        for (int i = 0; i < input.getSize(); i++) {
            ItemStack stack = input.getStackInSlot(i);

            if (stack.getItem() instanceof CopperItem) {

                if (CopperItem.isWaxed(stack)) {
                    ItemStack result = stack.copy();
                    CopperItem.setWaxed(result, false);
                    CopperItem.updateWaxStageFromDamage(result);
                    return result;
                }
            }
        }

        return ItemStack.EMPTY;
    }


    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CRAFTING_SPECIAL_UNWAXING;
    }
}