package net.frozen1753.copperequipments.mixin;

import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.item.custom.CopperItem;
import net.frozen1753.copperequipments.recipe.custom.DeoxidationRecipe;
import net.frozen1753.copperequipments.recipe.custom.UnwaxingRecipe;
import net.frozen1753.copperequipments.util.accessor.ScrapeFlagHolder;
import net.frozen1753.copperequipments.util.accessor.UnwaxFlagHolder;
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
 * <p>This mixin adds custom sound effects and copper item aging when using an axe
 * in crafting recipes for copper deoxidation and unwaxing. It ensures:
 * <ul>
 *   <li>Scrape sound plays once per tick when a deoxidation recipe succeeds.</li>
 *   <li>Wax‑off sound plays once per tick when an unwaxing recipe succeeds.</li>
 *   <li>Break sound plays if the axe disappears between snapshot (HEAD) and final state (TAIL),
 *       provided durability checks are enabled in config.</li>
 *   <li>Config flags are respected to skip unnecessary checks:
 *       <ul>
 *         <li>If {@code axeRequiredForDeoxidize} or {@code axeRequiredForUnwaxing} is false,
 *             we skip axe snapshot logic for that recipe type.</li>
 *         <li>If {@code axeDurabilityForDeoxidize} or {@code axeDurabilityForUnwaxing} is false,
 *             we know durability never changes for that recipe type, so we don’t need to rescan
 *             the grid at TAIL.</li>
 *       </ul>
 *   </li>
 *   <li>Copper items crafted are updated with {@link CopperItem#setAgeTicksForStage} and
 *       {@link CopperItem#setCreationTime} to synchronize their oxidation state and creation time.</li>
 * </ul>
 *
 * <p>Implementation notes:
 * <ul>
 *   <li>At HEAD, {@code axeBefore} is always reset to {@link ItemStack#EMPTY}.
 *      This acts as a sentinel: if the recipe is not a deoxidation recipe or unwaxing recipe,
 *      or config disables axe logic, {@code axeBefore} stays empty and TAIL will skip sound playback.</li>
 *  <li>We snapshot the first axe found at HEAD before the grid mutates, so we can compare before vs. after.</li>
 *  <li>Scrape sound is guarded by {@link ScrapeFlagHolder} to prevent spam when shift‑clicking.
 *      Wax‑off sound is similarly guarded by {@link UnwaxFlagHolder}. Each player tracks their own flags independently, see {@link PlayerEntityMixin}.</li>
 *  <li>Break sound is independent of the scrape/unwax guards, so it always plays if the axe breaks.</li>
 *  <li>Loops short‑circuit on the first axe found to avoid scanning the entire grid.</li>
 * </ul>
 */
@Mixin(CraftingResultSlot.class)
public abstract class CraftingResultSlotMixin {
    @Shadow private RecipeInputInventory input;
    @Shadow private PlayerEntity player;

    // Snapshot of the axe stack before crafting. Empty if no axe or config disables axe requirement.
    private ItemStack axeBefore;
    // We remember the recipe found
    private CraftingRecipe recipe;

    @Inject(method = "onTakeItem", at = @At("HEAD"))
    private void snapshotBefore(PlayerEntity player, ItemStack crafted, CallbackInfo ci) {
        World world = player.getWorld();
        if (world.isClient) return;

        recipe = null;
        axeBefore = ItemStack.EMPTY;

        // Check if the recipe is a deoxidation recipe
        Optional<CraftingRecipe> recipeOpt = world.getRecipeManager()
                .getFirstMatch(RecipeType.CRAFTING, input.createPositionedRecipeInput().input(), world)
                .map(RecipeEntry::value);

        if (recipeOpt.isEmpty()) return;

        recipe = recipeOpt.get();

        // If recipe is not either DeoxidationRecipe or UnwaxingRecipe
        if (!(recipe instanceof DeoxidationRecipe) && !(recipe instanceof UnwaxingRecipe)) {
            return;
        }

        // We check configs to see if an axe is necessary
        boolean axeRequired = (recipe instanceof DeoxidationRecipe && CopperEquipments.CONFIG.deoxidation.axeRequiredForDeoxidize)
                || (recipe instanceof UnwaxingRecipe && CopperEquipments.CONFIG.unwaxing.axeRequiredForUnwaxing);

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

        if (axeBefore.isEmpty()) return; // axeBefore is EMPTY if snapshotBefore decided this wasn't our recipe or no axe logic applies

        ScrapeFlagHolder scrapeAccessor = (ScrapeFlagHolder) player;
        UnwaxFlagHolder unwaxAccessor = (UnwaxFlagHolder) player;

        if (crafted.getItem() instanceof CopperItem) {
            CopperItem.setAgeTicksForStage(crafted, world);
        }

        // Deoxidizing
        if (recipe instanceof DeoxidationRecipe) {
            // play scraping sound without spam
            if (!scrapeAccessor.hasPlayedScrapeThisTick()) {
                scrapeAccessor.setPlayedScrapeThisTick(true);
                world.playSound(null, player.getBlockPos(),
                        SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
        }
        // Unwaxing
        else if (recipe instanceof UnwaxingRecipe) {
            // play unwaxing sound without spam
            if (!unwaxAccessor.hasPlayedUnwaxThisTick()) {
                unwaxAccessor.setPlayedUnwaxThisTick(true);
                world.playSound(null, player.getBlockPos(),
                        SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
        }

        boolean checkDurability =
                (recipe instanceof DeoxidationRecipe && CopperEquipments.CONFIG.deoxidation.axeDurabilityForDeoxidize)
                || (recipe instanceof UnwaxingRecipe && CopperEquipments.CONFIG.unwaxing.axeDurabilityForUnwaxing);

        if (!checkDurability) return;

        ItemStack axeAfter = findAxe();
        if (axeAfter.isEmpty()) {
            world.playSound(null, player.getBlockPos(),
                    SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
    }

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