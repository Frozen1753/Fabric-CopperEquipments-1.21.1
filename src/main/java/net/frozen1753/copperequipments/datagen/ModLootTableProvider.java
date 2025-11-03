package net.frozen1753.copperequipments.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.frozen1753.copperequipments.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.COPPER_TORCH);

        addDrop(ModBlocks.COPPER_CHAIN);
        addDrop(ModBlocks.EXPOSED_COPPER_CHAIN);
        addDrop(ModBlocks.WEATHERED_COPPER_CHAIN);
        addDrop(ModBlocks.OXIDIZED_COPPER_CHAIN);
        addDrop(ModBlocks.WAXED_COPPER_CHAIN);
        addDrop(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN);
        addDrop(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN);
        addDrop(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN);

        addDrop(ModBlocks.COPPER_LANTERN);
        addDrop(ModBlocks.EXPOSED_COPPER_LANTERN);
        addDrop(ModBlocks.WEATHERED_COPPER_LANTERN);
        addDrop(ModBlocks.OXIDIZED_COPPER_LANTERN);
        addDrop(ModBlocks.WAXED_COPPER_LANTERN);
        addDrop(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN);
        addDrop(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN);
        addDrop(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN);

        addDrop(ModBlocks.COPPER_BARS);
        addDrop(ModBlocks.EXPOSED_COPPER_BARS);
        addDrop(ModBlocks.WEATHERED_COPPER_BARS);
        addDrop(ModBlocks.OXIDIZED_COPPER_BARS);
        addDrop(ModBlocks.WAXED_COPPER_BARS);
        addDrop(ModBlocks.WAXED_EXPOSED_COPPER_BARS);
        addDrop(ModBlocks.WAXED_WEATHERED_COPPER_BARS);
        addDrop(ModBlocks.WAXED_OXIDIZED_COPPER_BARS);
    }
}
