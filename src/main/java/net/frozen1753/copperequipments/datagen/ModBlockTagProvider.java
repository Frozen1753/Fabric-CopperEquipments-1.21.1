package net.frozen1753.copperequipments.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.frozen1753.copperequipments.block.ModBlocks;
import net.frozen1753.copperequipments.util.ModTags;
import net.minecraft.block.Blocks;
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

        getOrCreateTagBuilder(ModTags.Blocks.OXIDIZABLE_BLOCKS)
                .add(Blocks.COPPER_BLOCK)
                .add(Blocks.EXPOSED_COPPER)
                .add(Blocks.WEATHERED_COPPER)

                .add(Blocks.CUT_COPPER)
                .add(Blocks.EXPOSED_CUT_COPPER)
                .add(Blocks.WEATHERED_CUT_COPPER)

                .add(Blocks.CUT_COPPER_SLAB)
                .add(Blocks.EXPOSED_CUT_COPPER_SLAB)
                .add(Blocks.WEATHERED_CUT_COPPER_SLAB)

                .add(Blocks.CUT_COPPER_STAIRS)
                .add(Blocks.EXPOSED_CUT_COPPER_STAIRS)
                .add(Blocks.WEATHERED_CUT_COPPER_STAIRS)

                .add(Blocks.CHISELED_COPPER)
                .add(Blocks.EXPOSED_CHISELED_COPPER)
                .add(Blocks.WEATHERED_CHISELED_COPPER)

                .add(Blocks.COPPER_BULB)
                .add(Blocks.EXPOSED_COPPER_BULB)
                .add(Blocks.WEATHERED_COPPER_BULB)

                .add(Blocks.COPPER_DOOR)
                .add(Blocks.EXPOSED_COPPER_DOOR)
                .add(Blocks.WEATHERED_COPPER_DOOR)

                .add(Blocks.COPPER_TRAPDOOR)
                .add(Blocks.EXPOSED_COPPER_TRAPDOOR)
                .add(Blocks.WEATHERED_COPPER_TRAPDOOR)

                .add(Blocks.COPPER_GRATE)
                .add(Blocks.EXPOSED_COPPER_GRATE)
                .add(Blocks.WEATHERED_COPPER_GRATE)

                .add(ModBlocks.COPPER_CHAIN)
                .add(ModBlocks.EXPOSED_COPPER_CHAIN)
                .add(ModBlocks.WEATHERED_COPPER_CHAIN)

                .add(ModBlocks.COPPER_LANTERN)
                .add(ModBlocks.EXPOSED_COPPER_LANTERN)
                .add(ModBlocks.WEATHERED_COPPER_LANTERN)

                .add(ModBlocks.COPPER_BARS)
                .add(ModBlocks.EXPOSED_COPPER_BARS)
                .add(ModBlocks.WEATHERED_COPPER_BARS);

        getOrCreateTagBuilder(ModTags.Blocks.WAXABLE_BLOCKS)
                .add(Blocks.COPPER_BLOCK)
                .add(Blocks.EXPOSED_COPPER)
                .add(Blocks.WEATHERED_COPPER)
                .add(Blocks.OXIDIZED_COPPER)

                .add(Blocks.CUT_COPPER)
                .add(Blocks.EXPOSED_CUT_COPPER)
                .add(Blocks.WEATHERED_CUT_COPPER)
                .add(Blocks.OXIDIZED_CUT_COPPER)

                .add(Blocks.CUT_COPPER_SLAB)
                .add(Blocks.EXPOSED_CUT_COPPER_SLAB)
                .add(Blocks.WEATHERED_CUT_COPPER_SLAB)
                .add(Blocks.OXIDIZED_CUT_COPPER_SLAB)

                .add(Blocks.CUT_COPPER_STAIRS)
                .add(Blocks.EXPOSED_CUT_COPPER_STAIRS)
                .add(Blocks.WEATHERED_CUT_COPPER_STAIRS)
                .add(Blocks.OXIDIZED_CUT_COPPER_STAIRS)

                .add(Blocks.CHISELED_COPPER)
                .add(Blocks.EXPOSED_CHISELED_COPPER)
                .add(Blocks.WEATHERED_CHISELED_COPPER)
                .add(Blocks.OXIDIZED_CHISELED_COPPER)

                .add(Blocks.COPPER_BULB)
                .add(Blocks.EXPOSED_COPPER_BULB)
                .add(Blocks.WEATHERED_COPPER_BULB)
                .add(Blocks.OXIDIZED_COPPER_BULB)

                .add(Blocks.COPPER_DOOR)
                .add(Blocks.EXPOSED_COPPER_DOOR)
                .add(Blocks.WEATHERED_COPPER_DOOR)
                .add(Blocks.OXIDIZED_COPPER_DOOR)

                .add(Blocks.COPPER_TRAPDOOR)
                .add(Blocks.EXPOSED_COPPER_TRAPDOOR)
                .add(Blocks.WEATHERED_COPPER_TRAPDOOR)
                .add(Blocks.OXIDIZED_COPPER_TRAPDOOR)

                .add(Blocks.COPPER_GRATE)
                .add(Blocks.EXPOSED_COPPER_GRATE)
                .add(Blocks.WEATHERED_COPPER_GRATE)
                .add(Blocks.OXIDIZED_COPPER_GRATE)

                .add(ModBlocks.COPPER_CHAIN)
                .add(ModBlocks.EXPOSED_COPPER_CHAIN)
                .add(ModBlocks.WEATHERED_COPPER_CHAIN)
                .add(ModBlocks.OXIDIZED_COPPER_CHAIN)

                .add(ModBlocks.COPPER_LANTERN)
                .add(ModBlocks.EXPOSED_COPPER_LANTERN)
                .add(ModBlocks.WEATHERED_COPPER_LANTERN)
                .add(ModBlocks.OXIDIZED_COPPER_LANTERN)

                .add(ModBlocks.COPPER_BARS)
                .add(ModBlocks.EXPOSED_COPPER_BARS)
                .add(ModBlocks.WEATHERED_COPPER_BARS)
                .add(ModBlocks.OXIDIZED_COPPER_BARS);

        getOrCreateTagBuilder(ModTags.Blocks.WAXED_BLOCKS)
                .add(Blocks.WAXED_COPPER_BLOCK)
                .add(Blocks.WAXED_EXPOSED_COPPER)
                .add(Blocks.WAXED_WEATHERED_COPPER)
                .add(Blocks.WAXED_OXIDIZED_COPPER)

                .add(Blocks.WAXED_CUT_COPPER)
                .add(Blocks.WAXED_EXPOSED_CUT_COPPER)
                .add(Blocks.WAXED_WEATHERED_CUT_COPPER)
                .add(Blocks.WAXED_OXIDIZED_CUT_COPPER)

                .add(Blocks.WAXED_CUT_COPPER_SLAB)
                .add(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB)
                .add(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB)
                .add(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB)

                .add(Blocks.WAXED_CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS)
                .add(Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS)

                .add(Blocks.WAXED_CHISELED_COPPER)
                .add(Blocks.WAXED_EXPOSED_CHISELED_COPPER)
                .add(Blocks.WAXED_WEATHERED_CHISELED_COPPER)
                .add(Blocks.WAXED_OXIDIZED_CHISELED_COPPER)

                .add(Blocks.WAXED_COPPER_BULB)
                .add(Blocks.WAXED_EXPOSED_COPPER_BULB)
                .add(Blocks.WAXED_WEATHERED_COPPER_BULB)
                .add(Blocks.WAXED_OXIDIZED_COPPER_BULB)

                .add(Blocks.WAXED_COPPER_DOOR)
                .add(Blocks.WAXED_EXPOSED_COPPER_DOOR)
                .add(Blocks.WAXED_WEATHERED_COPPER_DOOR)
                .add(Blocks.WAXED_OXIDIZED_COPPER_DOOR)

                .add(Blocks.WAXED_COPPER_TRAPDOOR)
                .add(Blocks.WAXED_EXPOSED_COPPER_TRAPDOOR)
                .add(Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR)
                .add(Blocks.WAXED_OXIDIZED_COPPER_TRAPDOOR)

                .add(Blocks.WAXED_COPPER_GRATE)
                .add(Blocks.WAXED_EXPOSED_COPPER_GRATE)
                .add(Blocks.WAXED_WEATHERED_COPPER_GRATE)
                .add(Blocks.WAXED_OXIDIZED_COPPER_GRATE)

                .add(ModBlocks.WAXED_COPPER_CHAIN)
                .add(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN)
                .add(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN)
                .add(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN)

                .add(ModBlocks.WAXED_COPPER_LANTERN)
                .add(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN)
                .add(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN)
                .add(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN)

                .add(ModBlocks.WAXED_COPPER_BARS)
                .add(ModBlocks.WAXED_EXPOSED_COPPER_BARS)
                .add(ModBlocks.WAXED_WEATHERED_COPPER_BARS)
                .add(ModBlocks.WAXED_OXIDIZED_COPPER_BARS);

        getOrCreateTagBuilder(ModTags.Blocks.SCRAPABLE_BLOCKS)
                // Exposed, Weathered, Oxidized
                .add(Blocks.EXPOSED_COPPER)
                .add(Blocks.WEATHERED_COPPER)
                .add(Blocks.OXIDIZED_COPPER)

                .add(Blocks.EXPOSED_CUT_COPPER)
                .add(Blocks.WEATHERED_CUT_COPPER)
                .add(Blocks.OXIDIZED_CUT_COPPER)

                .add(Blocks.EXPOSED_CUT_COPPER_SLAB)
                .add(Blocks.WEATHERED_CUT_COPPER_SLAB)
                .add(Blocks.OXIDIZED_CUT_COPPER_SLAB)

                .add(Blocks.EXPOSED_CUT_COPPER_STAIRS)
                .add(Blocks.WEATHERED_CUT_COPPER_STAIRS)
                .add(Blocks.OXIDIZED_CUT_COPPER_STAIRS)

                .add(Blocks.EXPOSED_CHISELED_COPPER)
                .add(Blocks.WEATHERED_CHISELED_COPPER)
                .add(Blocks.OXIDIZED_CHISELED_COPPER)

                .add(Blocks.EXPOSED_COPPER_BULB)
                .add(Blocks.WEATHERED_COPPER_BULB)
                .add(Blocks.OXIDIZED_COPPER_BULB)

                .add(Blocks.EXPOSED_COPPER_DOOR)
                .add(Blocks.WEATHERED_COPPER_DOOR)
                .add(Blocks.OXIDIZED_COPPER_DOOR)

                .add(Blocks.EXPOSED_COPPER_TRAPDOOR)
                .add(Blocks.WEATHERED_COPPER_TRAPDOOR)
                .add(Blocks.OXIDIZED_COPPER_TRAPDOOR)

                .add(Blocks.EXPOSED_COPPER_GRATE)
                .add(Blocks.WEATHERED_COPPER_GRATE)
                .add(Blocks.OXIDIZED_COPPER_GRATE)



                .add(ModBlocks.EXPOSED_COPPER_CHAIN)
                .add(ModBlocks.WEATHERED_COPPER_CHAIN)
                .add(ModBlocks.OXIDIZED_COPPER_CHAIN)

                .add(ModBlocks.EXPOSED_COPPER_LANTERN)
                .add(ModBlocks.WEATHERED_COPPER_LANTERN)
                .add(ModBlocks.OXIDIZED_COPPER_LANTERN)

                .add(ModBlocks.EXPOSED_COPPER_BARS)
                .add(ModBlocks.WEATHERED_COPPER_BARS)
                .add(ModBlocks.OXIDIZED_COPPER_BARS);

        getOrCreateTagBuilder(ModTags.Blocks.COPPER_BLOCKS)
                .addTag(ModTags.Blocks.WAXABLE_BLOCKS)
                .addTag(ModTags.Blocks.WAXED_BLOCKS);
    }
}
