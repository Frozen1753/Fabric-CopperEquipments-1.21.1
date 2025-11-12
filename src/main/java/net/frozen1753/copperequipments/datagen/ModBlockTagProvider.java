package net.frozen1753.copperequipments.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.frozen1753.copperequipments.block.ModBlocks;
import net.frozen1753.copperequipments.util.ModTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)

            .add(ModBlocks.COPPER_CHAIN)
            .add(ModBlocks.EXPOSED_COPPER_CHAIN)
            .add(ModBlocks.WEATHERED_COPPER_CHAIN)
            .add(ModBlocks.OXIDIZED_COPPER_CHAIN)
            .add(ModBlocks.WAXED_COPPER_CHAIN)
            .add(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN)
            .add(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN)
            .add(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN)

            .add(ModBlocks.COPPER_LANTERN)
            .add(ModBlocks.EXPOSED_COPPER_LANTERN)
            .add(ModBlocks.WEATHERED_COPPER_LANTERN)
            .add(ModBlocks.OXIDIZED_COPPER_LANTERN)
            .add(ModBlocks.WAXED_COPPER_LANTERN)
            .add(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN)
            .add(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN)
            .add(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN)

            .add(ModBlocks.COPPER_BARS)
            .add(ModBlocks.EXPOSED_COPPER_BARS)
            .add(ModBlocks.WEATHERED_COPPER_BARS)
            .add(ModBlocks.OXIDIZED_COPPER_BARS)
            .add(ModBlocks.WAXED_COPPER_BARS)
            .add(ModBlocks.WAXED_EXPOSED_COPPER_BARS)
            .add(ModBlocks.WAXED_WEATHERED_COPPER_BARS)
            .add(ModBlocks.WAXED_OXIDIZED_COPPER_BARS);

        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_COPPER_TOOL)
            .forceAddTag(BlockTags.INCORRECT_FOR_STONE_TOOL);
    }
}
