package net.frozen1753.copperequipments.util;

import net.frozen1753.copperequipments.CopperEquipments;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_COPPER_TOOL = createTag("needs_copper_tool");
        public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = createTag("incorrect_for_copper_tool");

        public static final TagKey<Block> OXIDIZABLE_BLOCKS = createTag("oxidizable_blocks"); // all unexposed-weathered
        public static final TagKey<Block> SCRAPABLE_BLOCKS = createTag("scrapable_blocks"); // all oxidized-exposed
        public static final TagKey<Block> WAXABLE_BLOCKS = createTag("waxable_blocks"); // all non-waxed
        public static final TagKey<Block> WAXED_BLOCKS = createTag("waxed_blocks"); // all waxed
        public static final TagKey<Block> COPPER_BLOCKS = createTag("copper_blocks"); // all

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(CopperEquipments.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> COPPER_ITEMS = createTag("copper_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(CopperEquipments.MOD_ID, name));
        }
    }

    public static void registerModTags() {
        CopperEquipments.LOGGER.info("Registering Tags for " + CopperEquipments.MOD_ID);
    }
}
