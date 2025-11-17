package net.frozen1753.copperequipments.recipe.custom;

import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.block.ModBlocks;
import net.frozen1753.copperequipments.item.custom.CopperItem;
import net.frozen1753.copperequipments.recipe.ModRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.Map;

import static net.frozen1753.copperequipments.recipe.custom.DeoxidationRecipe.getEnchantmentLevel;

public class UnwaxingRecipe extends SpecialCraftingRecipe {
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

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        boolean axeRequired = CopperEquipments.CONFIG.unwaxing.axeRequiredForUnwaxing;

        // Si l'outil est requis, il faut exactement 2 items (un waxed + une hache)
        // Sinon, un seul item waxed suffit
        if (input.getStackCount() != (axeRequired ? 2 : 1)) {
            return false;
        }

        ItemStack waxed = null;
        ItemStack axe = null;

        for (ItemStack stack : input.getStacks()) {
            if (stack.isEmpty()) continue;
            Item item = stack.getItem();

            if (item instanceof AxeItem) {
                if (axe == null) axe = stack;
                continue;
            }

            if (item instanceof CopperItem && CopperItem.isWaxed(stack)) {
                waxed = stack;
                continue;
            }

            if (item instanceof BlockItem blockItem) {
                Block block = blockItem.getBlock();
                if (UNWAXING_MAP.containsKey(block)) {
                    waxed = stack;
                }
            }
        }

        if (waxed == null) return false;
        if (axeRequired && axe == null) return false;

        return true;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        ItemStack waxed = null;

        for (ItemStack stack : input.getStacks()) {
            if (stack.isEmpty()) continue;
            Item item = stack.getItem();

            if (item instanceof CopperItem && CopperItem.isWaxed(stack)) {
                ItemStack result = stack.copy();
                CopperItem.setWaxed(result, false);
                return result;
            }

            if (item instanceof BlockItem blockItem) {
                Block block = blockItem.getBlock();
                Block unwaxed = UNWAXING_MAP.get(block);
                if (unwaxed != null) {
                    return new ItemStack(unwaxed.asItem());
                }
            }
        }

        return ItemStack.EMPTY;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingRecipeInput input) {
        DefaultedList<ItemStack> remainders = DefaultedList.ofSize(input.getSize(), ItemStack.EMPTY);

        boolean axeRequired = CopperEquipments.CONFIG.unwaxing.axeRequiredForUnwaxing;
        boolean axeDurability = CopperEquipments.CONFIG.unwaxing.axeDurabilityForUnwaxing;

        if (!axeRequired) {
            return remainders; // pas d'outil requis → pas de remainder
        }

        Random random = Random.create();

        for (int i = 0; i < input.getSize(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            if (stack.isEmpty() || !(stack.getItem() instanceof AxeItem)) continue;

            ItemStack axeCopy = stack.copy();

            if (axeDurability) {
                int unbreakingLevel = getEnchantmentLevel(axeCopy, Enchantments.UNBREAKING);
                if (random.nextInt(unbreakingLevel + 1) == 0) {
                    int newDamage = axeCopy.getDamage() + 1;
                    if (newDamage < axeCopy.getMaxDamage()) {
                        axeCopy.setDamage(newDamage);
                        remainders.set(i, axeCopy);
                    } else {
                        remainders.set(i, ItemStack.EMPTY); // l'outil se casse
                    }
                } else {
                    remainders.set(i, axeCopy); // pas de dégât
                }
            } else {
                remainders.set(i, axeCopy); // durabilité désactivée
            }
        }

        return remainders;
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