package net.frozen1753.copperequipments.mixin;

import net.frozen1753.copperequipments.item.custom.CopperItem;
import net.frozen1753.copperequipments.recipe.custom.DeoxidationRecipe;
import net.frozen1753.copperequipments.recipe.custom.WaxingRecipe;
import net.frozen1753.copperequipments.util.ModFunctions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

/**
 * Mixin for {@link CraftingScreenHandler}.
 *
 * <p>This mixin handles the special case of shift‑click crafting for copper deoxidation recipes.
 * Normally, {@link net.minecraft.screen.slot.CraftingResultSlot#onTakeItem} receives the crafted stack and applies custom logic.
 * However, during shift‑click, {@link CraftingScreenHandler#quickMove} consumes and inserts the stack
 * before calling onTakeItem, leaving the 'crafted' parameter empty. This mixin ensures our copper
 * aging logic still runs correctly in that path.</p>
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Intercept shift‑click at the HEAD of {@code quickMove} before the output stack is mutated.</li>
 *   <li>Check that the clicked slot is the result slot (index 0).</li>
 *   <li>Reconstruct the current recipe from the crafting grid to verify it is a {@link DeoxidationRecipe}.</li>
 *   <li>If the output is a {@link CopperItem}, apply {@link CopperItem#setAgeTicksForStage} to set its age.</li>
 * </ul>
 *
 * <p>Implementation notes:
 * <ul>
 *   <li>We use {@link CraftingResultInventory#getStack(int)} to access the intact output stack
 *       before quickMove copies and mutates it.</li>
 *   <li>Recipe reconstruction ensures we only apply logic to deoxidation recipes, not all crafting.</li>
 *   <li>Guarded by {@code world.isClient} to run only on the server side.</li>
 *   <li>Debug prints are included to trace execution and recipe checks during development.</li>
 *   <li>This mixin complements {@link CraftingResultSlotMixin}, which covers normal clicks;
 *       together they ensure consistent behavior for both click and shift‑click crafting.</li>
 * </ul>
 */
@Mixin(CraftingScreenHandler.class)
public abstract class CraftingScreenHandlerMixin {
    @Shadow private RecipeInputInventory input; // the 3x3 crafting grid
    @Shadow private CraftingResultInventory result; // the output slot
    @Shadow private PlayerEntity player;

    @Inject(method = "quickMove", at = @At("HEAD"))
    private void applyCopperAgingOnShiftClick(PlayerEntity p, int slot, CallbackInfoReturnable<ItemStack> cir) {
        World world = this.player.getWorld();
        if (world.isClient) return; // run only on server side

        // Only process the result slot (index 0)
        if (slot != 0) return;

        // Get the current stack in the result slot
        ItemStack out = this.result.getStack(0);
        if (out.isEmpty()) return; // nothing to do if empty

        // Build the crafting input for the RecipeManager
        CraftingRecipeInput craftingInput = this.input.createRecipeInput();
        Optional<RecipeEntry<CraftingRecipe>> opt = world.getRecipeManager()
                .getFirstMatch(RecipeType.CRAFTING, craftingInput, world);

        if (opt.isEmpty()) return;

        CraftingRecipe recipe = opt.get().value();

        // copper aging for deoxidation recipes
        if (recipe instanceof DeoxidationRecipe && out.getItem() instanceof CopperItem) {
            CopperItem.setAgeTicksForStage(out, world);
        }

        ModFunctions.playWaxSoundCrafting(out, world, player);
        ModFunctions.grantWaxAdvancementCrafting(out, player);
    }
}

