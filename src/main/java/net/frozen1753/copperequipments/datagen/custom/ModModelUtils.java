package net.frozen1753.copperequipments.datagen.custom;

import net.frozen1753.copperequipments.CopperEquipments;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

public class ModModelUtils {
    public static void registerMetalBars(BlockStateModelGenerator generator, Block block) {
        Identifier postEnds = ModelIds.getBlockSubModelId(block, "_post_ends");
        Identifier post = ModelIds.getBlockSubModelId(block, "_post");
        Identifier cap = ModelIds.getBlockSubModelId(block, "_cap");
        Identifier capAlt = ModelIds.getBlockSubModelId(block, "_cap_alt");
        Identifier side = ModelIds.getBlockSubModelId(block, "_side");
        Identifier sideAlt = ModelIds.getBlockSubModelId(block, "_side_alt");

        MultipartBlockStateSupplier supplier = MultipartBlockStateSupplier.create(block)
                .with(BlockStateVariant.create().put(VariantSettings.MODEL, postEnds))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, post))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, cap))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, cap).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, capAlt))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, capAlt).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, side))
                .with(When.create().set(Properties.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, side).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideAlt))
                .with(When.create().set(Properties.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideAlt).put(VariantSettings.Y, VariantSettings.Rotation.R90));

        generator.blockStateCollector.accept(supplier);
        generator.registerItemModel(block);
    }
}
