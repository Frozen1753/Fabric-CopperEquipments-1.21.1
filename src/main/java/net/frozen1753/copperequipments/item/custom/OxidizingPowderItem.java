package net.frozen1753.copperequipments.item.custom;

import net.frozen1753.copperequipments.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

import java.util.Map;

public class OxidizingPowderItem extends Item {

    public OxidizingPowderItem(Settings settings) {
        super(settings);
    }

    private static final Map<ItemConvertible, ItemConvertible> OXIDATION_MAP = Map.ofEntries(
            // Vanilla
            Map.entry(Blocks.COPPER_BLOCK, Blocks.EXPOSED_COPPER),
            Map.entry(Blocks.EXPOSED_COPPER, Blocks.WEATHERED_COPPER),
            Map.entry(Blocks.WEATHERED_COPPER, Blocks.OXIDIZED_COPPER),

            Map.entry(Blocks.CHISELED_COPPER, Blocks.EXPOSED_CHISELED_COPPER),
            Map.entry(Blocks.EXPOSED_CHISELED_COPPER, Blocks.WEATHERED_CHISELED_COPPER),
            Map.entry(Blocks.WEATHERED_CHISELED_COPPER, Blocks.OXIDIZED_CHISELED_COPPER),

            Map.entry(Blocks.COPPER_GRATE, Blocks.EXPOSED_COPPER_GRATE),
            Map.entry(Blocks.EXPOSED_COPPER_GRATE, Blocks.WEATHERED_COPPER_GRATE),
            Map.entry(Blocks.WEATHERED_COPPER_GRATE, Blocks.OXIDIZED_COPPER_GRATE),

            Map.entry(Blocks.CUT_COPPER, Blocks.EXPOSED_CUT_COPPER),
            Map.entry(Blocks.EXPOSED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER),
            Map.entry(Blocks.WEATHERED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER),

            Map.entry(Blocks.CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER_STAIRS),
            Map.entry(Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER_STAIRS),
            Map.entry(Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER_STAIRS),

            Map.entry(Blocks.CUT_COPPER_SLAB, Blocks.EXPOSED_CUT_COPPER_SLAB),
            Map.entry(Blocks.EXPOSED_CUT_COPPER_SLAB, Blocks.WEATHERED_CUT_COPPER_SLAB),
            Map.entry(Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.OXIDIZED_CUT_COPPER_SLAB),

            Map.entry(Blocks.COPPER_BULB, Blocks.EXPOSED_COPPER_BULB),
            Map.entry(Blocks.EXPOSED_COPPER_BULB, Blocks.WEATHERED_COPPER_BULB),
            Map.entry(Blocks.WEATHERED_COPPER_BULB, Blocks.OXIDIZED_COPPER_BULB),

            Map.entry(Blocks.COPPER_DOOR, Blocks.EXPOSED_COPPER_DOOR),
            Map.entry(Blocks.EXPOSED_COPPER_DOOR, Blocks.WEATHERED_COPPER_DOOR),
            Map.entry(Blocks.WEATHERED_COPPER_DOOR, Blocks.OXIDIZED_COPPER_DOOR),

            Map.entry(Blocks.COPPER_TRAPDOOR, Blocks.EXPOSED_COPPER_TRAPDOOR),
            Map.entry(Blocks.EXPOSED_COPPER_TRAPDOOR, Blocks.WEATHERED_COPPER_TRAPDOOR),
            Map.entry(Blocks.WEATHERED_COPPER_TRAPDOOR, Blocks.OXIDIZED_COPPER_TRAPDOOR),

            // Mod
            Map.entry(ModBlocks.COPPER_BARS, ModBlocks.EXPOSED_COPPER_BARS),
            Map.entry(ModBlocks.EXPOSED_COPPER_BARS, ModBlocks.WEATHERED_COPPER_BARS),
            Map.entry(ModBlocks.WEATHERED_COPPER_BARS, ModBlocks.OXIDIZED_COPPER_BARS),

            Map.entry(ModBlocks.COPPER_CHAIN, ModBlocks.EXPOSED_COPPER_CHAIN),
            Map.entry(ModBlocks.EXPOSED_COPPER_CHAIN, ModBlocks.WEATHERED_COPPER_CHAIN),
            Map.entry(ModBlocks.WEATHERED_COPPER_CHAIN, ModBlocks.OXIDIZED_COPPER_CHAIN),

            Map.entry(ModBlocks.COPPER_LANTERN, ModBlocks.EXPOSED_COPPER_LANTERN),
            Map.entry(ModBlocks.EXPOSED_COPPER_LANTERN, ModBlocks.WEATHERED_COPPER_LANTERN),
            Map.entry(ModBlocks.WEATHERED_COPPER_LANTERN, ModBlocks.OXIDIZED_COPPER_LANTERN)
    );

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        ItemStack stack = context.getStack();

        Block next = (Block) OXIDATION_MAP.get(state.getBlock());
        if (next != null) {
            world.setBlockState(pos, next.getDefaultState());
            stack.decrement(1); // consume powder

            if (!world.isClient) {
                ServerWorld serverWorld = (ServerWorld) world;
                Random random = world.getRandom();

                // Coordinates of the block's center
                double cx = pos.getX() + 0.5;
                double cy = pos.getY() + 0.5;
                double cz = pos.getZ() + 0.5;

                // Offsets for the 6 faces (up, down, north, south, east, west)
                double[][] faces = {
                        {0, +0.5, 0}, // up
                        {0, -0.5, 0}, // down
                        {0, 0, +0.5}, // south
                        {0, 0, -0.5}, // north
                        {+0.5, 0, 0}, // east
                        {-0.5, 0, 0}  // west
                };

                for (double[] face : faces) {
                    double fx = cx + face[0];
                    double fy = cy + face[1];
                    double fz = cz + face[2];

                    for (int i = 0; i < 5; i++) {
                        double x = fx + (random.nextDouble() - 0.5);
                        double y = fy + (random.nextDouble() - 0.5);
                        double z = fz + (random.nextDouble() - 0.5);

                        serverWorld.spawnParticles(
                                ParticleTypes.WAX_OFF,
                                x, y, z,
                                1,
                                0,
                                0,
                                0,
                                0.3
                        );
                    }
                }

                world.playSound(
                        null,
                        pos,
                        SoundEvents.UI_STONECUTTER_TAKE_RESULT,
                        SoundCategory.BLOCKS,
                        1.0F,
                        1.5F
                );
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
