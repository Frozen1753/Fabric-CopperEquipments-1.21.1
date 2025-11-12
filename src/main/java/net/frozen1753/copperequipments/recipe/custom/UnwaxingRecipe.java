package net.frozen1753.copperequipments.recipe.custom;

import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.item.custom.CopperItem;
import net.frozen1753.copperequipments.recipe.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
        System.out.println("[UNWAXING] [MATCHING] Checking matches...");
        System.out.println("[UNWAXING] [MATCHING] Got :" + input.getStacks());
        if (input.getStackCount() != 1) {
            System.out.println("[UNWAXING] [MATCHING] Need exactly 1 item; got " + input.getStackCount());
            System.out.println("[UNWAXING] [MATCHING] matches -> false");
            return false;
        }
        System.out.println("[UNWAXING] [MATCHING] Found exactly 1 item; continue...");

        boolean foundWaxedCopper = false;

        for (ItemStack stack : input.getStacks()) {
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof CopperItem) {
                if (!CopperItem.isWaxed(stack)) {
                    System.out.println("[UNWAXING] [MATCHING] CopperItem is not waxed");
                    System.out.println("[UNWAXING] [MATCHING] matches -> false");
                    return false;
                }
                System.out.println("[UNWAXING] [MATCHING] Found waxed CopperItem");
                foundWaxedCopper = true;
            } else {
                System.out.println("[UNWAXING] [MATCHING] Invalid ingredient: " + stack.getItem());
                return false;
            }
        }

        System.out.println("[UNWAXING] [MATCHING] matches -> " + foundWaxedCopper);
        return foundWaxedCopper;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        CopperEquipments.LOGGER.debug("Attempting to craft unwaxed copper item...");

        for (int i = 0; i < input.getSize(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            CopperEquipments.LOGGER.debug("Slot {}: {}", i, stack);

            if (stack.getItem() instanceof CopperItem) {
                CopperEquipments.LOGGER.debug("Found CopperItem. Waxed: {}", CopperItem.isWaxed(stack));

                if (CopperItem.isWaxed(stack)) {
                    ItemStack result = stack.copy();
                    CopperItem.setWaxed(result, false);
                    CopperItem.updateWaxStageFromDamage(result);
                    CopperEquipments.LOGGER.debug("Returning unwaxed item: {}", result);
                    return result;
                }
            }
        }

        CopperEquipments.LOGGER.debug("No valid waxed copper item found. Returning EMPTY.");
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