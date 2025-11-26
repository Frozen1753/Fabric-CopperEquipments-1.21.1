package net.frozen1753.copperequipments.util;

import net.frozen1753.copperequipments.util.accessor.ActionFlagHolder;
import net.frozen1753.copperequipments.util.accessor.ActionType;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModFunctions {
    /**
     * Retrieves the level of a specific enchantment from an {@link ItemStack}.
     * <p>
     * This method inspects the {@link ItemEnchantmentsComponent} attached to the stack
     * (via {@link DataComponentTypes#ENCHANTMENTS}). It iterates through all registered
     * enchantments and, if one matches the provided {@link RegistryKey}, returns its level.
     * </p>
     *
     * <p>
     * If the stack does not contain the requested enchantment, the method returns {@code 0}.
     * </p>
     *
     * @param stack the item stack to inspect
     * @param key   the registry key of the enchantment to look for
     * @return the level of the enchantment if present, otherwise {@code 0}
     */
    public static int getEnchantmentLevel(ItemStack stack, RegistryKey<Enchantment> key) {
        ItemEnchantmentsComponent comp =
                stack.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);

        for (RegistryEntry<Enchantment> entry : comp.getEnchantments()) {
            if (entry.matchesKey(key)) {
                return comp.getLevel(entry);
            }
        }
        return 0;
    }

    /**
     * Plays the vanilla "wax on" sound when crafting produces a waxed copper block.
     * <p>
     * This method checks if the given {@link ItemStack} is a {@link BlockItem} whose block
     * belongs to {@link ModTags.Blocks#WAXED_BLOCKS}. If so, it plays the
     * {@link SoundEvents#ITEM_HONEYCOMB_WAX_ON} sound at the player's position.
     * </p>
     *
     * <p>
     * To prevent sound spam when shift‑clicking large stacks, the method uses
     * {@link ActionFlagHolder} with {@link ActionType#WAX} to ensure the sound
     * is only played once per tick.
     * </p>
     *
     * @param stack  the crafted item stack to inspect
     * @param world  the world in which the sound should be played
     * @param player the player who performed the crafting
     */
    public static void playWaxSoundCrafting(ItemStack stack, World world, PlayerEntity player) {
        if (!(stack.getItem() instanceof BlockItem blockItem)) return;

        Block block = blockItem.getBlock();
        if (!block.getRegistryEntry().isIn(ModTags.Blocks.WAXED_BLOCKS)) return;

        ActionFlagHolder accessor = (ActionFlagHolder) player;
        if (accessor.hasPlayedFlag(ActionType.WAX)) return;

        accessor.setPlayedFlag(ActionType.WAX, false);
        world.playSound(null, player.getBlockPos(),
                SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    /**
     * Grants the "Wax On" advancement when crafting produces any waxed copper block.
     * <p>
     * This method checks if the given {@link ItemStack} corresponds to a {@link ModTags.Blocks#WAXED_BLOCKS}.
     * If so, it delegates to {@link #grantAdvancementCrafting(Identifier, PlayerEntity)} with the identifier
     * {@code minecraft:husbandry/wax_on}.
     * </p>
     *
     * @param stack  the crafted item stack to inspect
     * @param player the player who performed the crafting
     */
    public static void grantWaxAdvancementCrafting(ItemStack stack, PlayerEntity player) {
        if (!(stack.getItem() instanceof BlockItem blockItem)) return;

        Block block = blockItem.getBlock();
        if (!block.getRegistryEntry().isIn(ModTags.Blocks.WAXED_BLOCKS)) return;

        grantAdvancementCrafting(Identifier.of("minecraft", "husbandry/wax_on"), player);
    }

    /**
     * Grants the specified advancement to the given player.
     * <p>
     * This method runs only on the server side. It retrieves the {@link AdvancementEntry} and then checks
     * the player's {@link AdvancementProgress}. For each criterion not yet obtained,
     * it calls {@link net.minecraft.server.network.ServerPlayerEntity#getAdvancementTracker()}
     * to grant the criterion, effectively completing the advancement.
     * </p>
     *
     * <p>
     * If the advancement cannot be found or the player is not a server-side entity,
     * the method exits silently.
     * </p>
     *
     * @param advancementId the identifier of the advancement (e.g. {@code minecraft:husbandry/scrape_oxidation})
     * @param player        the player to grant the advancement to; must be a {@link ServerPlayerEntity}
     */
    public static void grantAdvancementCrafting(Identifier advancementId, PlayerEntity player) {
        if (player.getWorld().isClient) return;
        if (!(player instanceof ServerPlayerEntity serverPlayer)) return;

        AdvancementEntry advancement = player.getServer()
                .getAdvancementLoader()
                .get(advancementId);
        if (advancement == null) return;

        AdvancementProgress progress = serverPlayer.getAdvancementTracker().getProgress(advancement);

        for (String criterion : progress.getUnobtainedCriteria()) {
            serverPlayer.getAdvancementTracker().grantCriterion(advancement, criterion);
        }
    }
}
