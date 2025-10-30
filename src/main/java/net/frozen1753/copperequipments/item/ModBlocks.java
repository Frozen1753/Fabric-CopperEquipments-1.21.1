package net.frozen1753.copperequipments.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.particle.ModParticles;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block COPPER_CHAIN = registerBlock("copper_chain",
            new ChainBlock(AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.CHAIN)
                    .nonOpaque()
            )
    );

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

    public static final Block COPPER_LANTERN = registerBlock("copper_lantern",
            new LanternBlock(
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
            new PaneBlock(AbstractBlock.Settings.create()
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.METAL)
                    .nonOpaque())
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
        CopperEquipments.LOGGER.info("Registering Mod Blocks for " + CopperEquipments.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(COPPER_CHAIN);
            entries.add(COPPER_BARS);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(COPPER_CHAIN);
            entries.add(COPPER_TORCH);
            entries.add(COPPER_LANTERN);
        });
    }
}
