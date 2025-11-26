package net.frozen1753.copperequipments.mixin;

import net.frozen1753.copperequipments.config.CopperEquipmentsConfigs;
import net.frozen1753.copperequipments.item.custom.CopperItem;
import net.frozen1753.copperequipments.recipe.custom.*;
import net.frozen1753.copperequipments.util.ModFunctions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

/**
 * Mixin for {@link CraftingResultSlot}.
 *
 * <p>This mixin extends crafting behavior to handle copper‑related recipes and axe interactions.
 * It adds:
 * <ul>
 *   <li>Custom and vanilla sound effects for deoxidation, unwaxing, waxing recipes and even tool breaking</li>
 *   <li>Automatic update of oxidation state for crafted copper items.</li>
 * </ul>
 *
 * <p>Implementation highlights:
 * <ul>
 *   <li>Snapshots the first axe at HEAD to compare before/after crafting.</li>
 *   <li>Respects config flags to skip unnecessary logic.</li>
 *   <li>Uses recipe type to play corresponding sounds.</li>
 * </ul>
 *
 * <p>Detailed behavior for each step is documented in the corresponding methods.</p>
 */
@Mixin(CraftingResultSlot.class)
public abstract class CraftingResultSlotMixin {
    @Shadow private RecipeInputInventory input;
    @Shadow private PlayerEntity player;

    // snapshot of the axe stack before crafting.
    // empty if no axe or config disables axe requirement.
    private ItemStack axeBefore;
    // we remember the recipe found
    private CraftingRecipe recipe;

    @Inject(method = "onTakeItem", at = @At("HEAD"))
    private void snapshotBefore(PlayerEntity player, ItemStack crafted, CallbackInfo ci) {
        World world = player.getWorld();
        if (world.isClient) return;

        recipe = null;
        axeBefore = ItemStack.EMPTY;

        // check if the recipe is a deoxidation recipe
        Optional<CraftingRecipe> recipeOpt = world.getRecipeManager()
                .getFirstMatch(RecipeType.CRAFTING, input.createPositionedRecipeInput().input(), world)
                .map(RecipeEntry::value);

        if (recipeOpt.isEmpty()) return;

        recipe = recipeOpt.get();

        // if recipe is not one of the mod recipe
        if (!(recipe instanceof ModRecipe)) {
            return;
        }

        // we check configs to see if an axe is necessary
        boolean axeRequired = (recipe instanceof DeoxidationRecipe && CopperEquipmentsConfigs.axeRequiredForDeoxidize)
                || (recipe instanceof UnwaxingRecipe && CopperEquipmentsConfigs.axeRequiredForUnwaxing);

        if (!axeRequired) return;

        ItemStack axe = findAxe();
        if (!axe.isEmpty()) {
            axeBefore = axe.copy();
        }
    }

    @Inject(method = "onTakeItem", at = @At("TAIL"))
    private void playAxeSounds(PlayerEntity player, ItemStack crafted, CallbackInfo ci) {
        World world = player.getWorld();
        if (world.isClient) return;

        // update oxidation stage for copper tools
        if (crafted.getItem() instanceof CopperItem) {
            CopperItem.setAgeTicksForStage(crafted, world);
        }

        // play corresponding sound
        if (recipe instanceof ModRecipe modRecipe) {
            modRecipe.playSound(world, player);
            modRecipe.grantAdvancement(player);
        } else {
            ModFunctions.playWaxSoundCrafting(crafted, world, player);
            ModFunctions.grantWaxAdvancementCrafting(crafted, player);
        }

        // if the axe disappeared (broke) and one of the two durability configs is enabled
        if (!axeBefore.isEmpty() && canAxeBreak(recipe)) {
            ItemStack axeAfter = findAxe();
            if (axeAfter.isEmpty()) {
                world.playSound(null, player.getBlockPos(),
                        SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
        }
    }

    /**
     * Determines if the axe can break using the durability config options
     * <ul>
     *   <li>{@link CopperEquipmentsConfigs#axeDurabilityForDeoxidize} for deoxidation recipes.</li>
     *   <li>{@link CopperEquipmentsConfigs#axeDurabilityForUnwaxing} for unwaxing recipes.</li>
     * </ul>
     */
    private boolean canAxeBreak(CraftingRecipe recipe) {
        if (recipe instanceof DeoxidationRecipe) {
            return CopperEquipmentsConfigs.axeDurabilityForDeoxidize;
        }
        if (recipe instanceof UnwaxingRecipe) {
            return CopperEquipmentsConfigs.axeDurabilityForUnwaxing;
        }
        return false;
    }

    /**
     * Finds the first axe in the crafting grid.
     * <p>Loops short‑circuit on the first axe found to avoid scanning the entire grid.</p>
     */
    private ItemStack findAxe() {
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getStack(i);
            if (!stack.isEmpty() && stack.getItem() instanceof AxeItem) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }
}