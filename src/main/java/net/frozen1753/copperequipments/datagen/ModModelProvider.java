package net.frozen1753.copperequipments.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.block.ModBlocks;
import net.frozen1753.copperequipments.datagen.custom.ModModelUtils;
import net.frozen1753.copperequipments.item.ModItems;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerTorch(ModBlocks.COPPER_TORCH, ModBlocks.COPPER_WALL_TORCH);

        blockStateModelGenerator.registerAxisRotated(ModBlocks.COPPER_CHAIN, ModelIds.getBlockModelId(ModBlocks.COPPER_CHAIN));
        blockStateModelGenerator.registerAxisRotated(ModBlocks.EXPOSED_COPPER_CHAIN, ModelIds.getBlockModelId(ModBlocks.EXPOSED_COPPER_CHAIN));
        blockStateModelGenerator.registerAxisRotated(ModBlocks.WEATHERED_COPPER_CHAIN, ModelIds.getBlockModelId(ModBlocks.WEATHERED_COPPER_CHAIN));
        blockStateModelGenerator.registerAxisRotated(ModBlocks.OXIDIZED_COPPER_CHAIN, ModelIds.getBlockModelId(ModBlocks.OXIDIZED_COPPER_CHAIN));
        blockStateModelGenerator.registerAxisRotated(ModBlocks.WAXED_COPPER_CHAIN, ModelIds.getBlockModelId(ModBlocks.WAXED_COPPER_CHAIN));
        blockStateModelGenerator.registerAxisRotated(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN, ModelIds.getBlockModelId(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN));
        blockStateModelGenerator.registerAxisRotated(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN, ModelIds.getBlockModelId(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN));
        blockStateModelGenerator.registerAxisRotated(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN, ModelIds.getBlockModelId(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN));

        blockStateModelGenerator.registerLantern(ModBlocks.COPPER_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.EXPOSED_COPPER_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.WEATHERED_COPPER_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.OXIDIZED_COPPER_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.WAXED_COPPER_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN);
        blockStateModelGenerator.registerLantern(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN);

        ModModelUtils.registerMetalBars(blockStateModelGenerator, ModBlocks.COPPER_BARS);
        ModModelUtils.registerMetalBars(blockStateModelGenerator, ModBlocks.EXPOSED_COPPER_BARS);
        ModModelUtils.registerMetalBars(blockStateModelGenerator, ModBlocks.WEATHERED_COPPER_BARS);
        ModModelUtils.registerMetalBars(blockStateModelGenerator, ModBlocks.OXIDIZED_COPPER_BARS);
        ModModelUtils.registerMetalBars(blockStateModelGenerator, ModBlocks.WAXED_COPPER_BARS);
        ModModelUtils.registerMetalBars(blockStateModelGenerator, ModBlocks.WAXED_EXPOSED_COPPER_BARS);
        ModModelUtils.registerMetalBars(blockStateModelGenerator, ModBlocks.WAXED_WEATHERED_COPPER_BARS);
        ModModelUtils.registerMetalBars(blockStateModelGenerator, ModBlocks.WAXED_OXIDIZED_COPPER_BARS);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.COPPER_NUGGET, Models.GENERATED);

        itemModelGenerator.register(ModBlocks.COPPER_CHAIN.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.EXPOSED_COPPER_CHAIN.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.WEATHERED_COPPER_CHAIN.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.OXIDIZED_COPPER_CHAIN.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.WAXED_COPPER_CHAIN.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN.asItem(), Models.GENERATED);

        ModModelUtils.registerOxidizingEquipmentModel(itemModelGenerator, ModItems.COPPER_SWORD, Models.HANDHELD);
        ModModelUtils.registerOxidizingEquipmentModel(itemModelGenerator, ModItems.COPPER_PICKAXE, Models.HANDHELD);
        ModModelUtils.registerOxidizingEquipmentModel(itemModelGenerator, ModItems.COPPER_AXE, Models.HANDHELD);
        ModModelUtils.registerOxidizingEquipmentModel(itemModelGenerator, ModItems.COPPER_SHOVEL, Models.HANDHELD);
        ModModelUtils.registerOxidizingEquipmentModel(itemModelGenerator, ModItems.COPPER_HOE, Models.HANDHELD);

        ModModelUtils.registerOxidizingEquipmentModel(itemModelGenerator, ModItems.COPPER_HELMET, Models.GENERATED);
        ModModelUtils.registerOxidizingEquipmentModel(itemModelGenerator, ModItems.COPPER_CHESTPLATE, Models.GENERATED);
        ModModelUtils.registerOxidizingEquipmentModel(itemModelGenerator, ModItems.COPPER_LEGGINGS, Models.GENERATED);
        ModModelUtils.registerOxidizingEquipmentModel(itemModelGenerator, ModItems.COPPER_BOOTS, Models.GENERATED);
    }
}
