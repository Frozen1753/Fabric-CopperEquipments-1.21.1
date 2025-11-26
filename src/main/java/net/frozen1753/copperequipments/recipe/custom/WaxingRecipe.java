package net.frozen1753.copperequipments.recipe.custom;

import net.frozen1753.copperequipments.item.custom.CopperItem;
import net.frozen1753.copperequipments.recipe.ModRecipes;
import net.frozen1753.copperequipments.util.ModFunctions;
import net.frozen1753.copperequipments.util.accessor.ActionFlagHolder;
import net.frozen1753.copperequipments.util.accessor.ActionType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class WaxingRecipe extends SpecialCraftingRecipe implements ModRecipe{
    public WaxingRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        if (input.getStackCount() != 2) {
            return false;
        }

        boolean foundUnwaxedCopper = false;
        boolean foundHoney = false;

        for (ItemStack stack : input.getStacks()) {
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof CopperItem && !CopperItem.isWaxed(stack)) {
                if (CopperItem.isWaxed(stack)) {
                    return false;
                }
                foundUnwaxedCopper = true;
            } else if (stack.isOf(Items.HONEYCOMB)) {
                foundHoney = true;
            } else {
                return false;
            }
        }

        return foundUnwaxedCopper && foundHoney;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {

        for (int i = 0; i < input.getSize(); i++) {
            ItemStack stack = input.getStackInSlot(i);

            if (stack.getItem() instanceof CopperItem) {

                if (!CopperItem.isWaxed(stack)) {
                    ItemStack result = stack.copy();
                    CopperItem.setWaxed(result, true);
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
        return ModRecipes.CRAFTING_SPECIAL_WAXING;
    }

    @Override
    public void playSound(World world, PlayerEntity player) {
        ActionFlagHolder accessor = (ActionFlagHolder) player;
        if (!accessor.hasPlayedFlag(ActionType.WAX)) {
            accessor.setPlayedFlag(ActionType.WAX, true);
            world.playSound(null, player.getBlockPos(),
                    SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }

    @Override
    public void grantAdvancement(PlayerEntity player) {
        ModFunctions.grantAdvancementCrafting(
                Identifier.of("minecraft","husbandry/wax_on"),
                player);
    }
}

