package net.frozen1753.copperequipments.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.block.custom.OxidizableChainBlock;
import net.frozen1753.copperequipments.block.custom.OxidizableLanternBlock;
import net.frozen1753.copperequipments.block.custom.OxidizablePaneBlock;
import net.frozen1753.copperequipments.particle.ModParticles;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block COPPER_TORCH = registerBlock("copper_torch",
            new TorchBlock(
                    ModParticles.COPPER_FLAME,
                    AbstractBlock.Settings.create()
                            .noCollision()
                            .breakInstantly()
                            .luminance(state -> 14)
                            .sounds(BlockSoundGroup.WOOD)
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .nonOpaque()
            )
    );
    public static final Block COPPER_WALL_TORCH = registerBlock("copper_wall_torch",
            new WallTorchBlock(
                    ModParticles.COPPER_FLAME,
                    AbstractBlock.Settings.create()
                            .noCollision()
                            .breakInstantly()
                            .luminance(state -> 14)
                            .sounds(BlockSoundGroup.WOOD)
                            .dropsLike(COPPER_TORCH)
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .nonOpaque()
            ),
            false
    );

    public static final Block COPPER_CHAIN = registerBlock("copper_chain",
            new OxidizableChainBlock(Oxidizable.OxidationLevel.UNAFFECTED,
                    AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.CHAIN)
                    .nonOpaque()
            )
    );

    public static final Block EXPOSED_COPPER_CHAIN = registerBlock("exposed_copper_chain",
            new OxidizableChainBlock(Oxidizable.OxidationLevel.EXPOSED,
                    AbstractBlock.Settings.create()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.CHAIN)
                    .nonOpaque()
            )
    );

    public static final Block WEATHERED_COPPER_CHAIN = registerBlock("weathered_copper_chain",
            new OxidizableChainBlock(Oxidizable.OxidationLevel.WEATHERED,
                    AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.CHAIN)
                    .nonOpaque()
            )
    );

    public static final Block OXIDIZED_COPPER_CHAIN = registerBlock("oxidized_copper_chain",
            new OxidizableChainBlock(Oxidizable.OxidationLevel.OXIDIZED,
                    AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.CHAIN)
                    .nonOpaque()
            )
    );

    public static final Block WAXED_COPPER_CHAIN = registerBlock("waxed_copper_chain",
            new OxidizableChainBlock(Oxidizable.OxidationLevel.UNAFFECTED,
                    AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.CHAIN)
                    .nonOpaque()
            )
    );

    public static final Block WAXED_EXPOSED_COPPER_CHAIN = registerBlock("waxed_exposed_copper_chain",
            new OxidizableChainBlock(Oxidizable.OxidationLevel.EXPOSED,
                    AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.CHAIN)
                    .nonOpaque()
            )
    );

    public static final Block WAXED_WEATHERED_COPPER_CHAIN = registerBlock("waxed_weathered_copper_chain",
            new OxidizableChainBlock(Oxidizable.OxidationLevel.WEATHERED,
                    AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.CHAIN)
                    .nonOpaque()
            )
    );

    public static final Block WAXED_OXIDIZED_COPPER_CHAIN = registerBlock("waxed_oxidized_copper_chain",
            new OxidizableChainBlock(Oxidizable.OxidationLevel.OXIDIZED,
                    AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.CHAIN)
                    .nonOpaque()
            )
    );

    public static final Block COPPER_LANTERN = registerBlock("copper_lantern",
            new OxidizableLanternBlock(Oxidizable.OxidationLevel.UNAFFECTED,
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.IRON_GRAY)
                            .solid()
                            .requiresTool()
                            .strength(3.5F,3.5F)
                            .sounds(BlockSoundGroup.LANTERN)
                            .luminance(state -> 15)
                            .nonOpaque()
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .nonOpaque()
            )
    );

    public static final Block EXPOSED_COPPER_LANTERN = registerBlock("exposed_copper_lantern",
            new OxidizableLanternBlock(Oxidizable.OxidationLevel.EXPOSED,
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.IRON_GRAY)
                            .solid()
                            .requiresTool()
                            .strength(3.5F,3.5F)
                            .sounds(BlockSoundGroup.LANTERN)
                            .luminance(state -> 15)
                            .nonOpaque()
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .nonOpaque()
            )
    );

    public static final Block WEATHERED_COPPER_LANTERN = registerBlock("weathered_copper_lantern",
            new OxidizableLanternBlock(Oxidizable.OxidationLevel.WEATHERED,
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.IRON_GRAY)
                            .solid()
                            .requiresTool()
                            .strength(3.5F,3.5F)
                            .sounds(BlockSoundGroup.LANTERN)
                            .luminance(state -> 15)
                            .nonOpaque()
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .nonOpaque()
            )
    );

    public static final Block OXIDIZED_COPPER_LANTERN = registerBlock("oxidized_copper_lantern",
            new OxidizableLanternBlock(Oxidizable.OxidationLevel.OXIDIZED,
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.IRON_GRAY)
                            .solid()
                            .requiresTool()
                            .strength(3.5F,3.5F)
                            .sounds(BlockSoundGroup.LANTERN)
                            .luminance(state -> 15)
                            .nonOpaque()
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .nonOpaque()
            )
    );

    public static final Block WAXED_COPPER_LANTERN = registerBlock("waxed_copper_lantern",
            new OxidizableLanternBlock(Oxidizable.OxidationLevel.UNAFFECTED,
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.IRON_GRAY)
                            .solid()
                            .requiresTool()
                            .strength(3.5F,3.5F)
                            .sounds(BlockSoundGroup.LANTERN)
                            .luminance(state -> 15)
                            .nonOpaque()
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .nonOpaque()
            )
    );

    public static final Block WAXED_EXPOSED_COPPER_LANTERN = registerBlock("waxed_exposed_copper_lantern",
            new OxidizableLanternBlock(Oxidizable.OxidationLevel.EXPOSED,
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.IRON_GRAY)
                            .solid()
                            .requiresTool()
                            .strength(3.5F,3.5F)
                            .sounds(BlockSoundGroup.LANTERN)
                            .luminance(state -> 15)
                            .nonOpaque()
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .nonOpaque()
            )
    );

    public static final Block WAXED_WEATHERED_COPPER_LANTERN = registerBlock("waxed_weathered_copper_lantern",
            new OxidizableLanternBlock(Oxidizable.OxidationLevel.WEATHERED,
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.IRON_GRAY)
                            .solid()
                            .requiresTool()
                            .strength(3.5F,3.5F)
                            .sounds(BlockSoundGroup.LANTERN)
                            .luminance(state -> 15)
                            .nonOpaque()
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .nonOpaque()
            )
    );

    public static final Block WAXED_OXIDIZED_COPPER_LANTERN = registerBlock("waxed_oxidized_copper_lantern",
            new OxidizableLanternBlock(Oxidizable.OxidationLevel.OXIDIZED,
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.IRON_GRAY)
                            .solid()
                            .requiresTool()
                            .strength(3.5F,3.5F)
                            .sounds(BlockSoundGroup.LANTERN)
                            .luminance(state -> 15)
                            .nonOpaque()
                            .pistonBehavior(PistonBehavior.DESTROY)
                            .nonOpaque()
            )
    );

    public static final Block COPPER_BARS = registerBlock("copper_bars",
            new OxidizablePaneBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.METAL)
                    .nonOpaque()
            )
    );

    public static final Block EXPOSED_COPPER_BARS = registerBlock("exposed_copper_bars",
            new OxidizablePaneBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.METAL)
                    .nonOpaque()
            )
    );

    public static final Block WEATHERED_COPPER_BARS = registerBlock("weathered_copper_bars",
            new OxidizablePaneBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.METAL)
                    .nonOpaque()
            )
    );

    public static final Block OXIDIZED_COPPER_BARS = registerBlock("oxidized_copper_bars",
            new OxidizablePaneBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.METAL)
                    .nonOpaque()
            )
    );

    public static final Block WAXED_COPPER_BARS = registerBlock("waxed_copper_bars",
            new OxidizablePaneBlock(Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.METAL)
                    .nonOpaque()
            )
    );

    public static final Block WAXED_EXPOSED_COPPER_BARS = registerBlock("waxed_exposed_copper_bars",
            new OxidizablePaneBlock(Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.METAL)
                    .nonOpaque()
            )
    );

    public static final Block WAXED_WEATHERED_COPPER_BARS = registerBlock("waxed_weathered_copper_bars",
            new OxidizablePaneBlock(Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.METAL)
                    .nonOpaque()
            )
    );

    public static final Block WAXED_OXIDIZED_COPPER_BARS = registerBlock("waxed_oxidized_copper_bars",
            new OxidizablePaneBlock(Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.METAL)
                    .nonOpaque()
            )
    );

    private static Block registerBlock(String name, Block block, boolean registerItem) {
        if (registerItem) {
            registerBlockItem(name, block);
        }
        return Registry.register(Registries.BLOCK, Identifier.of(CopperEquipments.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        return registerBlock(name, block, true);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(CopperEquipments.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        CopperEquipments.LOGGER.info("Registering Blocks for " + CopperEquipments.MOD_ID);

        OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER_CHAIN, EXPOSED_COPPER_CHAIN);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER_CHAIN, WEATHERED_COPPER_CHAIN);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_COPPER_CHAIN, OXIDIZED_COPPER_CHAIN);
        OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER_CHAIN, WAXED_COPPER_CHAIN);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER_CHAIN, WAXED_EXPOSED_COPPER_CHAIN);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER_CHAIN, WAXED_WEATHERED_COPPER_CHAIN);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER_CHAIN, WAXED_OXIDIZED_COPPER_CHAIN);

        OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER_LANTERN, EXPOSED_COPPER_LANTERN);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER_LANTERN, WEATHERED_COPPER_LANTERN);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_COPPER_LANTERN, OXIDIZED_COPPER_LANTERN);
        OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER_LANTERN, WAXED_COPPER_LANTERN);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER_LANTERN, WAXED_EXPOSED_COPPER_LANTERN);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER_LANTERN, WAXED_WEATHERED_COPPER_LANTERN);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER_LANTERN, WAXED_OXIDIZED_COPPER_LANTERN);

        OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER_BARS, EXPOSED_COPPER_BARS);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER_BARS, WEATHERED_COPPER_BARS);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(WEATHERED_COPPER_BARS, OXIDIZED_COPPER_BARS);
        OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER_BARS, WAXED_COPPER_BARS);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER_BARS, WAXED_EXPOSED_COPPER_BARS);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER_BARS, WAXED_WEATHERED_COPPER_BARS);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER_BARS, WAXED_OXIDIZED_COPPER_BARS);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(COPPER_CHAIN);
            entries.add(EXPOSED_COPPER_CHAIN);
            entries.add(WEATHERED_COPPER_CHAIN);
            entries.add(OXIDIZED_COPPER_CHAIN);
            entries.add(WAXED_COPPER_CHAIN);
            entries.add(WAXED_EXPOSED_COPPER_CHAIN);
            entries.add(WAXED_WEATHERED_COPPER_CHAIN);
            entries.add(WAXED_OXIDIZED_COPPER_CHAIN);

            entries.add(COPPER_BARS);
            entries.add(EXPOSED_COPPER_BARS);
            entries.add(WEATHERED_COPPER_BARS);
            entries.add(OXIDIZED_COPPER_BARS);
            entries.add(WAXED_COPPER_BARS);
            entries.add(WAXED_EXPOSED_COPPER_BARS);
            entries.add(WAXED_WEATHERED_COPPER_BARS);
            entries.add(WAXED_OXIDIZED_COPPER_BARS);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(COPPER_CHAIN);
            entries.add(EXPOSED_COPPER_CHAIN);
            entries.add(WEATHERED_COPPER_CHAIN);
            entries.add(OXIDIZED_COPPER_CHAIN);
            entries.add(WAXED_COPPER_CHAIN);
            entries.add(WAXED_EXPOSED_COPPER_CHAIN);
            entries.add(WAXED_WEATHERED_COPPER_CHAIN);
            entries.add(WAXED_OXIDIZED_COPPER_CHAIN);

            entries.add(COPPER_TORCH);

            entries.add(COPPER_LANTERN);
            entries.add(EXPOSED_COPPER_LANTERN);
            entries.add(WEATHERED_COPPER_LANTERN);
            entries.add(OXIDIZED_COPPER_LANTERN);
            entries.add(WAXED_COPPER_LANTERN);
            entries.add(WAXED_EXPOSED_COPPER_LANTERN);
            entries.add(WAXED_WEATHERED_COPPER_LANTERN);
            entries.add(WAXED_OXIDIZED_COPPER_LANTERN);
        });
    }
}
