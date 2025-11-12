package net.frozen1753.copperequipments.recipe;

import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.recipe.custom.UnwaxingRecipe;
import net.frozen1753.copperequipments.recipe.custom.WaxingRecipe;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<WaxingRecipe> CRAFTING_SPECIAL_WAXING =
            Registry.register(
                    Registries.RECIPE_SERIALIZER,
                    Identifier.of(CopperEquipments.MOD_ID, "crafting_special_waxing"),
                    new SpecialRecipeSerializer<>(WaxingRecipe::new)
            );

    public static final RecipeSerializer<UnwaxingRecipe> CRAFTING_SPECIAL_UNWAXING =
            Registry.register(
                    Registries.RECIPE_SERIALIZER,
                    Identifier.of(CopperEquipments.MOD_ID, "crafting_special_unwaxing"),
                    new SpecialRecipeSerializer<>(UnwaxingRecipe::new)
            );

    public static void registerModRecipes() {
        CopperEquipments.LOGGER.info("Registering Recipe for " + CopperEquipments.MOD_ID);
    }
}
