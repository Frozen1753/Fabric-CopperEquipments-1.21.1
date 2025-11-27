package net.frozen1753.copperequipments.recipe.custom;

import net.frozen1753.copperequipments.block.ModBlocks;
import net.frozen1753.copperequipments.config.CopperEquipmentsConfigs;
import net.frozen1753.copperequipments.item.custom.CopperItem;
import net.frozen1753.copperequipments.recipe.ModRecipes;
import net.frozen1753.copperequipments.util.ModFunctions;
import net.frozen1753.copperequipments.util.accessor.ActionFlagHolder;
import net.frozen1753.copperequipments.util.accessor.ActionType;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.frozen1753.copperequipments.util.ModFunctions.getEnchantmentLevel;

/**
 * Special crafting recipe for copper deoxidation.
 *
 * <p>This recipe allows players to "scrape" oxidized copper blocks and items back
 * to a less oxidized state using an axe. It supports both vanilla copper variants
 * and custom modded copper blocks/items defined in {@link ModBlocks}.
 *
 * <p>Key behaviors:
 * <ul>
 *   <li>Uses {@link #DEOXIDATION_MAP} to map oxidized blocks to their less oxidized form.</li>
 *   <li>Supports {@link CopperItem} oxidation stages for non-block copper items.</li>
 *   <li>Requires an axe if {@code axeRequiredForDeoxidize} is enabled in config.</li>
 *   <li>Consumes axe durability if {@code axeDurabilityForDeoxidize} is enabled in config,
 *       respecting Unbreaking enchantment logic.</li>
 *   <li>Ensures copper axes are treated strictly as tools, not as oxidized candidates,
 *       to avoid recipe conflicts (e.g. vanilla repair).</li>
 * </ul>
 *
 * <p>Implementation notes:
 * <ul>
 *   <li>{@link #findCandidates(CraftingRecipeInput)} scans the grid once to identify
 *       the oxidized candidate and axe candidate.</li>
 *   <li>{@link #matches(CraftingRecipeInput, World)} enforces stack count and candidate presence.</li>
 *   <li>{@link #craft(CraftingRecipeInput, RegistryWrapper.WrapperLookup)} produces the deoxidized result.</li>
 *   <li>{@link #getRemainder(CraftingRecipeInput)} handles axe durability and breakage.</li>
 *   <li>{@link net.frozen1753.copperequipments.util.ModFunctions#getEnchantmentLevel(ItemStack, RegistryKey)} reads enchantment levels from the new
 *       data component system.</li>
 * </ul>
 */
public class DeoxidationRecipe extends SpecialCraftingRecipe implements ModRecipe {
    public DeoxidationRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    /**
     * Maps oxidized copper blocks to their less oxidized form.
     * Includes both vanilla and modded copper variants.
     */
    private static final Map<ItemConvertible, ItemConvertible> DEOXIDATION_MAP = Map.ofEntries(
            // Vanilla
            Map.entry(Blocks.OXIDIZED_COPPER, Blocks.WEATHERED_COPPER),
            Map.entry(Blocks.WEATHERED_COPPER, Blocks.EXPOSED_COPPER),
            Map.entry(Blocks.EXPOSED_COPPER, Blocks.COPPER_BLOCK),

            Map.entry(Blocks.OXIDIZED_CHISELED_COPPER, Blocks.WEATHERED_CHISELED_COPPER),
            Map.entry(Blocks.WEATHERED_CHISELED_COPPER, Blocks.EXPOSED_CHISELED_COPPER),
            Map.entry(Blocks.EXPOSED_CHISELED_COPPER, Blocks.CHISELED_COPPER),

            Map.entry(Blocks.OXIDIZED_COPPER_GRATE, Blocks.WEATHERED_COPPER_GRATE),
            Map.entry(Blocks.WEATHERED_COPPER_GRATE, Blocks.EXPOSED_COPPER_GRATE),
            Map.entry(Blocks.EXPOSED_COPPER_GRATE, Blocks.COPPER_GRATE),

            Map.entry(Blocks.OXIDIZED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER),
            Map.entry(Blocks.WEATHERED_CUT_COPPER, Blocks.EXPOSED_CUT_COPPER),
            Map.entry(Blocks.EXPOSED_CUT_COPPER, Blocks.CUT_COPPER),

            Map.entry(Blocks.OXIDIZED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER_STAIRS),
            Map.entry(Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER_STAIRS),
            Map.entry(Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.CUT_COPPER_STAIRS),

            Map.entry(Blocks.OXIDIZED_CUT_COPPER_SLAB, Blocks.WEATHERED_CUT_COPPER_SLAB),
            Map.entry(Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.EXPOSED_CUT_COPPER_SLAB),
            Map.entry(Blocks.EXPOSED_CUT_COPPER_SLAB, Blocks.CUT_COPPER_SLAB),

            Map.entry(Blocks.OXIDIZED_COPPER_BULB, Blocks.WEATHERED_COPPER_BULB),
            Map.entry(Blocks.WEATHERED_COPPER_BULB, Blocks.EXPOSED_COPPER_BULB),
            Map.entry(Blocks.EXPOSED_COPPER_BULB, Blocks.COPPER_BULB),

            Map.entry(Blocks.OXIDIZED_COPPER_DOOR, Blocks.WEATHERED_COPPER_DOOR),
            Map.entry(Blocks.WEATHERED_COPPER_DOOR, Blocks.EXPOSED_COPPER_DOOR),
            Map.entry(Blocks.EXPOSED_COPPER_DOOR, Blocks.COPPER_DOOR),

            Map.entry(Blocks.OXIDIZED_COPPER_TRAPDOOR, Blocks.WEATHERED_COPPER_TRAPDOOR),
            Map.entry(Blocks.WEATHERED_COPPER_TRAPDOOR, Blocks.EXPOSED_COPPER_TRAPDOOR),
            Map.entry(Blocks.EXPOSED_COPPER_TRAPDOOR, Blocks.COPPER_TRAPDOOR),

            // Mod
            Map.entry(ModBlocks.OXIDIZED_COPPER_BARS, ModBlocks.WEATHERED_COPPER_BARS),
            Map.entry(ModBlocks.WEATHERED_COPPER_BARS, ModBlocks.EXPOSED_COPPER_BARS),
            Map.entry(ModBlocks.EXPOSED_COPPER_BARS, ModBlocks.COPPER_BARS),

            Map.entry(ModBlocks.OXIDIZED_COPPER_CHAIN, ModBlocks.WEATHERED_COPPER_CHAIN),
            Map.entry(ModBlocks.WEATHERED_COPPER_CHAIN, ModBlocks.EXPOSED_COPPER_CHAIN),
            Map.entry(ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.COPPER_CHAIN),

            Map.entry(ModBlocks.OXIDIZED_COPPER_LANTERN, ModBlocks.WEATHERED_COPPER_LANTERN),
            Map.entry(ModBlocks.WEATHERED_COPPER_LANTERN, ModBlocks.EXPOSED_COPPER_LANTERN),
            Map.entry(ModBlocks.EXPOSED_COPPER_LANTERN, ModBlocks.COPPER_LANTERN)
    );

    private record Candidates(ItemStack oxidized, ItemStack axe) {}

    private Candidates findCandidates(CraftingRecipeInput input) {
        List<ItemStack> axes = new ArrayList<>();
        ItemStack blockOxidized = null;
        ItemStack copperOxidizedAxe = null;

        for (ItemStack stack : input.getStacks()) {
            if (stack.isEmpty()) continue;
            Item item = stack.getItem();

            if (item instanceof AxeItem) {
                axes.add(stack);
                if (item instanceof CopperItem && !CopperItem.isWaxed(stack)) {
                    int stage = CopperItem.getOxidationStage(stack);
                    if (stage > 0) copperOxidizedAxe = stack;
                }
                continue;
            }

            if (item instanceof BlockItem blockItem && DEOXIDATION_MAP.containsKey(blockItem.getBlock())) {
                blockOxidized = stack;
                continue;
            }

            if (item instanceof CopperItem && !CopperItem.isWaxed(stack)) {
                int stage = CopperItem.getOxidationStage(stack);
                if (stage > 0 && blockOxidized == null) {
                    blockOxidized = stack;
                }
            }
        }

        // priority to blocks
        if (blockOxidized != null) {
            ItemStack axe = axes.isEmpty() ? null : axes.getFirst();
            return new Candidates(blockOxidized, axe);
        }

        // two axe
        if (axes.size() == 2 && copperOxidizedAxe != null) {
            ItemStack tool = axes.stream().filter(s -> !(s.getItem() instanceof CopperItem)).findFirst().orElse(null);
            if (tool != null) {
                return new Candidates(copperOxidizedAxe, tool);
            }
        }

        return new Candidates(null, null);
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        boolean axeRequired = CopperEquipmentsConfigs.axeRequiredForDeoxidize;

        if (input.getStackCount() != (axeRequired ? 2 : 1)) return false;

        Candidates c = findCandidates(input);
        if (c.oxidized == null) return false;

        return !axeRequired || c.axe != null;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        Candidates c = findCandidates(input);
        if (c.oxidized == null) return ItemStack.EMPTY;

        Item item = c.oxidized.getItem();

        if (item instanceof BlockItem blockItem) {
            Block target = (Block) DEOXIDATION_MAP.get(blockItem.getBlock());
            return target == null ? ItemStack.EMPTY : new ItemStack(target.asItem());
        }

        if (item instanceof CopperItem) {
            ItemStack result = c.oxidized.copy();
            int stage = CopperItem.getOxidationStage(result);
            if (stage > 0) CopperItem.setOxidationStage(result, stage - 1);
            return result;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingRecipeInput input) {
        DefaultedList<ItemStack> remainders = DefaultedList.ofSize(input.getSize(), ItemStack.EMPTY);
        if (!CopperEquipmentsConfigs.axeRequiredForDeoxidize) return remainders;

        Candidates c = findCandidates(input);
        ItemStack oxidized = c.oxidized;

        for (int i = 0; i < input.getSize(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            if (stack.isEmpty()) continue;

            if (stack == oxidized) continue; // consumed

            if (stack.getItem() instanceof AxeItem) {
                remainders.set(i, damageOrBreakAxe(stack));
            }
        }
        return remainders;
    }

    private ItemStack damageOrBreakAxe(ItemStack axe) {
        ItemStack copy = axe.copy();
        if (!CopperEquipmentsConfigs.axeDurabilityForDeoxidize) return copy;

        Random random = Random.create();
        int unbreakingLevel = getEnchantmentLevel(copy, Enchantments.UNBREAKING);

        if (random.nextInt(unbreakingLevel + 1) == 0) {
            int newDamage = copy.getDamage() + 1;
            if (newDamage < copy.getMaxDamage()) {
                copy.setDamage(newDamage);
                return copy;
            }
            return ItemStack.EMPTY; // broken
        }
        return copy; // no damage
    }

    @Override
    public void playSound(World world, PlayerEntity player) {
        ActionFlagHolder accessor = (ActionFlagHolder) player;
        if (!accessor.hasPlayedFlag(ActionType.SCRAPE)) {
            accessor.setPlayedFlag(ActionType.SCRAPE, true);
            world.playSound(null, player.getBlockPos(),
                    SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }

    @Override
    public void grantAdvancement(PlayerEntity player) {
        ModFunctions.grantAdvancementCrafting(
                Identifier.of("minecraft","husbandry/scrape_oxidation"),
                player);
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CRAFTING_SPECIAL_DEOXIDATION;
    }
}

