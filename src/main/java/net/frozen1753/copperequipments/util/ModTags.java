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

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(CopperEquipments.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> COPPER_TOOLS = createTag("copper_tools");
        public static final TagKey<Item> COPPER_ARMOR = createTag("copper_armor");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(CopperEquipments.MOD_ID, name));
        }
    }

    public static void registerModTags() {
        CopperEquipments.LOGGER.info("Registering Tags for " + CopperEquipments.MOD_ID);
    }
}
