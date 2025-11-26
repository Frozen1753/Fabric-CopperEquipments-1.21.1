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
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
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

public class UnwaxingRecipe extends SpecialCraftingRecipe implements ModRecipe {
    public UnwaxingRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    private static final Map<Block, Block> UNWAXING_MAP = Map.ofEntries(
        // Vanilla
        Map.entry(Blocks.WAXED_COPPER_BLOCK, Blocks.COPPER_BLOCK),
        Map.entry(Blocks.WAXED_EXPOSED_COPPER, Blocks.EXPOSED_COPPER),
        Map.entry(Blocks.WAXED_WEATHERED_COPPER, Blocks.WEATHERED_COPPER),
        Map.entry(Blocks.WAXED_OXIDIZED_COPPER, Blocks.OXIDIZED_COPPER),

        Map.entry(Blocks.WAXED_CHISELED_COPPER, Blocks.CHISELED_COPPER),
        Map.entry(Blocks.WAXED_EXPOSED_CHISELED_COPPER, Blocks.EXPOSED_CHISELED_COPPER),
        Map.entry(Blocks.WAXED_WEATHERED_CHISELED_COPPER, Blocks.WEATHERED_CHISELED_COPPER),
        Map.entry(Blocks.WAXED_OXIDIZED_CHISELED_COPPER, Blocks.OXIDIZED_CHISELED_COPPER),

        Map.entry(Blocks.WAXED_COPPER_GRATE, Blocks.COPPER_GRATE),
        Map.entry(Blocks.WAXED_EXPOSED_COPPER_GRATE, Blocks.EXPOSED_COPPER_GRATE),
        Map.entry(Blocks.WAXED_WEATHERED_COPPER_GRATE, Blocks.WEATHERED_COPPER_GRATE),
        Map.entry(Blocks.WAXED_OXIDIZED_COPPER_GRATE, Blocks.OXIDIZED_COPPER_GRATE),

        Map.entry(Blocks.WAXED_CUT_COPPER, Blocks.CUT_COPPER),
        Map.entry(Blocks.WAXED_EXPOSED_CUT_COPPER, Blocks.EXPOSED_CUT_COPPER),
        Map.entry(Blocks.WAXED_WEATHERED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER),
        Map.entry(Blocks.WAXED_OXIDIZED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER),

        Map.entry(Blocks.WAXED_CUT_COPPER_STAIRS, Blocks.CUT_COPPER_STAIRS),
        Map.entry(Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER_STAIRS),
        Map.entry(Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER_STAIRS),
        Map.entry(Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER_STAIRS),

        Map.entry(Blocks.WAXED_CUT_COPPER_SLAB, Blocks.CUT_COPPER_SLAB),
        Map.entry(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB, Blocks.EXPOSED_CUT_COPPER_SLAB),
        Map.entry(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB, Blocks.WEATHERED_CUT_COPPER_SLAB),
        Map.entry(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB, Blocks.OXIDIZED_CUT_COPPER_SLAB),

        Map.entry(Blocks.WAXED_COPPER_BULB, Blocks.COPPER_BULB),
        Map.entry(Blocks.WAXED_EXPOSED_COPPER_BULB, Blocks.EXPOSED_COPPER_BULB),
        Map.entry(Blocks.WAXED_WEATHERED_COPPER_BULB, Blocks.WEATHERED_COPPER_BULB),
        Map.entry(Blocks.WAXED_OXIDIZED_COPPER_BULB, Blocks.OXIDIZED_COPPER_BULB),

        Map.entry(Blocks.WAXED_COPPER_DOOR, Blocks.COPPER_DOOR),
        Map.entry(Blocks.WAXED_EXPOSED_COPPER_DOOR, Blocks.EXPOSED_COPPER_DOOR),
        Map.entry(Blocks.WAXED_WEATHERED_COPPER_DOOR, Blocks.WEATHERED_COPPER_DOOR),
        Map.entry(Blocks.WAXED_OXIDIZED_COPPER_DOOR, Blocks.OXIDIZED_COPPER_DOOR),

        Map.entry(Blocks.WAXED_COPPER_TRAPDOOR, Blocks.COPPER_TRAPDOOR),
        Map.entry(Blocks.WAXED_EXPOSED_COPPER_TRAPDOOR, Blocks.EXPOSED_COPPER_TRAPDOOR),
        Map.entry(Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR, Blocks.WEATHERED_COPPER_TRAPDOOR),
        Map.entry(Blocks.WAXED_OXIDIZED_COPPER_TRAPDOOR, Blocks.OXIDIZED_COPPER_TRAPDOOR),

        // Mod
        Map.entry(ModBlocks.WAXED_COPPER_BARS, ModBlocks.COPPER_BARS),
        Map.entry(ModBlocks.WAXED_EXPOSED_COPPER_BARS, ModBlocks.EXPOSED_COPPER_BARS),
        Map.entry(ModBlocks.WAXED_WEATHERED_COPPER_BARS, ModBlocks.WEATHERED_COPPER_BARS),
        Map.entry(ModBlocks.WAXED_OXIDIZED_COPPER_BARS, ModBlocks.OXIDIZED_COPPER_BARS),

        Map.entry(ModBlocks.WAXED_COPPER_CHAIN, ModBlocks.COPPER_CHAIN),
        Map.entry(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN, ModBlocks.EXPOSED_COPPER_CHAIN),
        Map.entry(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN, ModBlocks.WEATHERED_COPPER_CHAIN),
        Map.entry(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN, ModBlocks.OXIDIZED_COPPER_CHAIN),

        Map.entry(ModBlocks.WAXED_COPPER_LANTERN, ModBlocks.COPPER_LANTERN),
        Map.entry(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN, ModBlocks.EXPOSED_COPPER_LANTERN),
        Map.entry(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN, ModBlocks.WEATHERED_COPPER_LANTERN),
        Map.entry(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN, ModBlocks.OXIDIZED_COPPER_LANTERN)
    );

    /**
     * Checks if the given crafting input matches the unwaxing recipe.
     * <ul>
     *   <li>Requires either one waxed item (if {@link CopperEquipmentsConfigs#axeRequiredForUnwaxing} = false)</li>
     *   <li>Or one waxed item + one axe (if {@link CopperEquipmentsConfigs#axeRequiredForUnwaxing} = true)</li>
     *   <li>Two axes are only valid if one is a waxed copper axe and the other is a non-copper axe</li>
     * </ul>
     */
    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        boolean axeRequired = CopperEquipmentsConfigs.axeRequiredForUnwaxing;

        // validate count based on config
        if (input.getStackCount() != (axeRequired ? 2 : 1)) {
            return false;
        }

        ItemStack waxed = findWaxedItem(input);
        ItemStack axe = findAxe(input);

        // must have a waxed item, and if axe is required, a valid axe too
        return waxed != null && (!axeRequired || axe != null);
    }

    /**
     * Produces the result of the unwaxing recipe.
     * <ul>
     *    <li>If the waxed item is tool → return a copy with wax removed</li>
     *    <li>If the waxed item is a block → return its unwaxed variant</li>
     * </ul>
     */
    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack waxed = findWaxedItem(input);
        if (waxed == null) return ItemStack.EMPTY;

        if (waxed.getItem() instanceof CopperItem) {
            ItemStack result = waxed.copy();
            CopperItem.setWaxed(result, false);
            return result;
        }

        if (waxed.getItem() instanceof BlockItem blockItem) {
            Block unwaxed = UNWAXING_MAP.get(blockItem.getBlock());
            if (unwaxed != null) {
                return new ItemStack(unwaxed.asItem());
            }
        }

        return ItemStack.EMPTY;
    }

    /**
     * Returns the remainder items after crafting.
     * <ul>
     *   <li>If axe is required, the axe may take durability damage</li>
     *   <li>The waxed item itself is consumed (not returned)</li>
     * </ul>
     */
    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingRecipeInput input) {
        DefaultedList<ItemStack> remainders = DefaultedList.ofSize(input.getSize(), ItemStack.EMPTY);

        if (!CopperEquipmentsConfigs.axeRequiredForUnwaxing) {
            return remainders; // no axe required -> no remainders
        }

        ItemStack waxed = findWaxedItem(input);

        for (int i = 0; i < input.getSize(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            if (stack.isEmpty() || !(stack.getItem() instanceof AxeItem)) continue;

            // 'consume' the waxed axe
            if (stack == waxed) continue;

            remainders.set(i, damageOrBreakAxe(stack));
        }

        return remainders;
    }

    /**
     * Finds the waxed item in the input.
     * <ul>
     *  <li>Can be a waxed copper item or a waxed copper block</li>
     *  <li>Special case: if there are two axes, only return the copper axe if it is waxed
     *   and the other axe is not copper</li>
     * </ul>
     */
    private ItemStack findWaxedItem(CraftingRecipeInput input) {
        List<ItemStack> axes = new ArrayList<>();
        ItemStack copperWaxed = null;
        ItemStack otherWaxed = null;

        for (ItemStack stack : input.getStacks()) {
            if (stack.isEmpty()) continue;
            Item item = stack.getItem();

            if (item instanceof AxeItem) {
                axes.add(stack);
                // keep track of a waxed copper axe
                if (item instanceof CopperItem && CopperItem.isWaxed(stack)) {
                    copperWaxed = stack;
                }
                continue;
            }

            if (item instanceof CopperItem && CopperItem.isWaxed(stack)) {
                otherWaxed = stack;
            } else if (item instanceof BlockItem blockItem && UNWAXING_MAP.containsKey(blockItem.getBlock())) {
                otherWaxed = stack;
            }
        }

        // in case of two axes
        if (axes.size() == 2) {
            // only valid if one is a waxed copper axe and the other not copper
            if (copperWaxed != null && axes.stream().anyMatch(s -> !(s.getItem() instanceof CopperItem))) {
                return copperWaxed;
            }
            return null;
        }

        // in case 1 waxed axe and axeRequiredForUnwaxing = false
        if (axes.size() == 1 && copperWaxed != null && !CopperEquipmentsConfigs.axeRequiredForUnwaxing) {
            return copperWaxed;
        }

        return otherWaxed;
    }

    /**
     * Finds the axe used in the recipe.
     * <ul>
     *   <li>If two axes are present, return the non-copper one</li>
     *   <li>Otherwise return the first axe found</li>
     * </ul>
     */
    private ItemStack findAxe(CraftingRecipeInput input) {
        List<ItemStack> axes = new ArrayList<>();
        for (ItemStack stack : input.getStacks()) {
            if (!stack.isEmpty() && stack.getItem() instanceof AxeItem) {
                axes.add(stack);
            }
        }

        if (axes.size() == 2) {
            for (ItemStack axe : axes) {
                if (!(axe.getItem() instanceof CopperItem)) {
                    return axe;
                }
            }
            return null; // both copper -> invalid
        }

        return axes.isEmpty() ? null : axes.getFirst();
    }

    /**
     * Applies durability damage to an axe, or breaks it if max damage is reached.
     * <ul>
     *   <li>If durability is disabled, return the axe unchanged</li>
     *   <li>Uses Unbreaking enchantment to reduce chance of damage</li>
     * </ul>
     */
    private ItemStack damageOrBreakAxe(ItemStack axe) {
        ItemStack copy = axe.copy();
        if (!CopperEquipmentsConfigs.axeDurabilityForUnwaxing) return copy;

        Random random = Random.create();
        int unbreakingLevel = getEnchantmentLevel(copy, Enchantments.UNBREAKING);

        if (random.nextInt(unbreakingLevel + 1) == 0) {
            int newDamage = copy.getDamage() + 1;
            if (newDamage < copy.getMaxDamage()) {
                copy.setDamage(newDamage);
                return copy; // damaged
            }
            return ItemStack.EMPTY; // broken
        }
        return copy; // no damaged
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CRAFTING_SPECIAL_UNWAXING;
    }

    @Override
    public void playSound(World world, PlayerEntity player) {
        ActionFlagHolder accessor = (ActionFlagHolder) player;
        if (!accessor.hasPlayedFlag(ActionType.UNWAX)) {
            accessor.setPlayedFlag(ActionType.UNWAX, true);
            world.playSound(null, player.getBlockPos(),
                    SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }

    @Override
    public void grantAdvancement(PlayerEntity player) {
        ModFunctions.grantAdvancementCrafting(
                Identifier.of("minecraft","husbandry/wax_off"),
                player);
    }
}