package net.frozen1753.copperequipments.recipe.custom;

import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.block.ModBlocks;
import net.frozen1753.copperequipments.item.custom.CopperItem;
import net.frozen1753.copperequipments.recipe.ModRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.*;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.Map;

public class DeoxidationRecipe extends SpecialCraftingRecipe {
    public DeoxidationRecipe(CraftingRecipeCategory category) {
        super(category);
    }

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

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        boolean axeRequired = CopperEquipments.CONFIG.deoxidation.axeRequiredForDeoxidize;

        // Expect 1 item if no axe required, 2 items if axe required
        if (input.getStackCount() != (axeRequired ? 2 : 1)) return false;

        ItemStack oxidizedCandidate = null;
        ItemStack axeCandidate = null;

        for (ItemStack stack : input.getStacks()) {
            if (stack.isEmpty()) continue;
            Item item = stack.getItem();

            if (item instanceof BlockItem blockItem && DEOXIDATION_MAP.containsKey(blockItem.getBlock())) {
                if (oxidizedCandidate == null) oxidizedCandidate = stack;
                continue;
            }
            if (item instanceof CopperItem && !CopperItem.isWaxed(stack)) {
                int stage = CopperItem.getOxidationStage(stack);
                if (stage > 0 && oxidizedCandidate == null) {
                    oxidizedCandidate = stack;
                }
                continue;
            }

            // Check axe tool
            if (item instanceof AxeItem) {
                // Only count as axe if it's not already chosen as oxidized
                if (axeCandidate == null) axeCandidate = stack;
            }
        }

        if (oxidizedCandidate == null) return false;

        // If axe is required, must have a separate axe stack
        if (axeRequired && axeCandidate == null) return false;

        return true;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        boolean axeRequired = CopperEquipments.CONFIG.deoxidation.axeRequiredForDeoxidize;
        boolean axeDurability = CopperEquipments.CONFIG.deoxidation.axeDurabilityForDeoxidize;

        ItemStack oxidizedCandidate = null;
        ItemStack axeCandidate = null;

        // Separate oxidized item and axe tool
        for (ItemStack stack : input.getStacks()) {
            if (stack.isEmpty()) continue;
            Item item = stack.getItem();

            // Oxidized block
            if (item instanceof BlockItem blockItem && DEOXIDATION_MAP.containsKey(blockItem.getBlock())) {
                if (oxidizedCandidate == null) oxidizedCandidate = stack;
                continue;
            }

            // Oxidized copper item
            if (item instanceof CopperItem && !CopperItem.isWaxed(stack)) {
                int stage = CopperItem.getOxidationStage(stack);
                if (stage > 0 && oxidizedCandidate == null) {
                    oxidizedCandidate = stack;
                }
                continue;
            }

            // Axe tool (only if not already chosen as oxidized)
            if (item instanceof AxeItem) {
                if (axeCandidate == null) {
                    if (stack.getMaxDamage() >= stack.getDamage()) axeCandidate = stack;
                }
            }
        }

        if (oxidizedCandidate == null) return ItemStack.EMPTY;

        ItemStack result;

        // Handle block deoxidation
        if (oxidizedCandidate.getItem() instanceof BlockItem blockItem) {
            Block target = (Block)DEOXIDATION_MAP.get(blockItem.getBlock());
            if (target == null) return ItemStack.EMPTY;
            result = new ItemStack(target.asItem());
        }
        // Handle copper item deoxidation
        else if (oxidizedCandidate.getItem() instanceof CopperItem) {
            result = oxidizedCandidate.copy();
            int stage = CopperItem.getOxidationStage(result);
            if (stage > 0) {
                CopperItem.setOxidationStage(result, stage - 1);
            }
        } else {
            return ItemStack.EMPTY;
        }

        return result;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingRecipeInput input) {
        DefaultedList<ItemStack> remainders = DefaultedList.ofSize(input.getSize(), ItemStack.EMPTY);

        boolean axeRequired = CopperEquipments.CONFIG.deoxidation.axeRequiredForDeoxidize;
        boolean axeDurability = CopperEquipments.CONFIG.deoxidation.axeDurabilityForDeoxidize;

        Random random = Random.create();

        for (int i = 0; i < input.getSize(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            if (stack.isEmpty()) continue;

            if (stack.getItem() instanceof AxeItem && axeRequired) {
                ItemStack axeCopy = stack.copy();

                if (axeDurability) {

                    int unbreakingLevel = getEnchantmentLevel(axeCopy, Enchantments.UNBREAKING);

                    // Vanilla logic: chance to consume durability is 1 / (level+1)
                    if (random.nextInt(unbreakingLevel + 1) == 0) {
                        int newDamage = axeCopy.getDamage() + 1;
                        if (newDamage < axeCopy.getMaxDamage()) {
                            axeCopy.setDamage(newDamage);
                            remainders.set(i, axeCopy);
                        } else {
                            // Axe broke
                            remainders.set(i, ItemStack.EMPTY);
                        }
                    } else {
                        // No durability consumed this time
                        remainders.set(i, axeCopy);
                    }
                } else {
                    // Axe not damaged at all
                    remainders.set(i, axeCopy);
                }
            }
        }
        return remainders;
    }

    public static int getEnchantmentLevel(ItemStack stack, RegistryKey<Enchantment> key) {
        // Get the enchantments component from the stack
        ItemEnchantmentsComponent comp =
                stack.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);

        // Iterate through the RegistryEntries present
        for (RegistryEntry<Enchantment> entry : comp.getEnchantments()) {
            if (entry.matchesKey(key)) {
                // Ask the component for the level of this entry
                return comp.getLevel(entry);
            }
        }
        return 0;
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

