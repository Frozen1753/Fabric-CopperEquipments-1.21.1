package net.frozen1753.copperequipments.mixin;

import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.util.accessor.ScrapeFlagHolder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for {@link CraftingResultSlot}.
 *
 * <p>This mixin adds custom sound effects when using an axe in crafting recipes
 * (for copper deoxidation). It ensures:
 * <ul>
 *   <li>Scrape sound always plays when crafting succeeds.</li>
 *   <li>Break sound plays if the axe disappears between snapshot and final state.</li>
 *   <li>Config flags are respected to skip unnecessary checks:
 *       <ul>
 *         <li>If {@code axeRequiredForDeoxidize} is false, we skip all axe logic.</li>
 *         <li>If {@code axeDurabilityForDeoxidize} is false, we know durability never changes,
 *             so we don’t need to compare damage values.</li>
 *       </ul>
 *   </li>
 * </ul>
 */
@Mixin(CraftingResultSlot.class)
public abstract class CraftingResultSlotMixin {
    @Shadow private RecipeInputInventory input;
    @Shadow private PlayerEntity player;

    // Snapshot of the axe stack before crafting happens.
    // We need this because by the time onTakeItem runs at TAIL,
    // the input inventory has already been mutated with remainders.
    // Without a snapshot, "before" would actually be the post-craft state.
    private ItemStack axeBefore;

    // At HEAD, the input grid still contains the true pre-craft stacks.
    // We loop through the grid and copy the first axe we find.
    // This snapshot lets us compare durability before vs after crafting.
    @Inject(method = "onTakeItem", at = @At("HEAD"))
    private void snapshotBefore(PlayerEntity player, ItemStack crafted, CallbackInfo ci) {
        // Skip snapshot entirely if axe is not required by config
        if (!CopperEquipments.CONFIG.deoxidation.axeRequiredForDeoxidize) {
            axeBefore = ItemStack.EMPTY;
            return;
        }
        axeBefore = ItemStack.EMPTY;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getStack(i);
            if (!stack.isEmpty() && stack.getItem() instanceof AxeItem) {
                axeBefore = stack.copy();
                break; // only handle one axe
            }
        }
    }

    @Inject(method = "onTakeItem", at = @At("TAIL"))
    private void playAxeSounds(PlayerEntity player, ItemStack crafted, CallbackInfo ci) {
        World world = player.getWorld();
        if (world.isClient) return; // sounds only on server side
        if (axeBefore.isEmpty()) return;

        // ScrapeFlagHolder is a helper attached to the player
        // to prevent playing multiple scrape sounds in the same tick
        // (important when shift-clicking crafts).
        ScrapeFlagHolder accessor = (ScrapeFlagHolder) player;
        if (accessor.hasPlayedScrapeThisTick()) return;
        accessor.setPlayedScrapeThisTick(true);

        // Always play the scrape sound when crafting succeeds,
        // regardless of whether durability was consumed or saved by Unbreaking.
        world.playSound(null, player.getBlockPos(),
                SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.PLAYERS, 1.0F, 1.0F);

        // If durability is disabled by config, we know the axe never breaks
        if (!CopperEquipments.CONFIG.deoxidation.axeDurabilityForDeoxidize) {
            return;
        }

        // At TAIL, the input grid has already been updated with remainders.
        // We check again to see if an axe is still present after crafting.
        ItemStack axeAfter = ItemStack.EMPTY;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getStack(i);
            if (!stack.isEmpty() && stack.getItem() instanceof AxeItem) {
                axeAfter = stack;
                break;
            }
        }

        // If the axe was present before but is gone after,
        // that means it broke during this craft
        if (axeAfter.isEmpty() && !axeBefore.isEmpty()) {
            world.playSound(null, player.getBlockPos(),
                    SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
    }
}