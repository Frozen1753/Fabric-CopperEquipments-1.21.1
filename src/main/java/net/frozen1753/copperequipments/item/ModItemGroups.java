package net.frozen1753.copperequipments.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup COPPER_EQUIPMENTS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CopperEquipments.MOD_ID,"copper_equipments"),
            FabricItemGroup.builder().icon(() -> new ItemStack(Items.COPPER_INGOT))
                    .displayName(Text.translatable("itemgroup.copperequipments.copper_equipments"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.COPPER_NUGGET);

                        entries.add(ModItems.COPPER_TORCH_ITEM);

                        entries.add(ModBlocks.COPPER_CHAIN);
                        entries.add(ModBlocks.EXPOSED_COPPER_CHAIN);
                        entries.add(ModBlocks.WEATHERED_COPPER_CHAIN);
                        entries.add(ModBlocks.OXIDIZED_COPPER_CHAIN);
                        entries.add(ModBlocks.WAXED_COPPER_CHAIN);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN);

                        entries.add(ModBlocks.COPPER_LANTERN);
                        entries.add(ModBlocks.EXPOSED_COPPER_LANTERN);
                        entries.add(ModBlocks.WEATHERED_COPPER_LANTERN);
                        entries.add(ModBlocks.OXIDIZED_COPPER_LANTERN);
                        entries.add(ModBlocks.WAXED_COPPER_LANTERN);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN);

                        entries.add(ModBlocks.COPPER_BARS);
                        entries.add(ModBlocks.EXPOSED_COPPER_BARS);
                        entries.add(ModBlocks.WEATHERED_COPPER_BARS);
                        entries.add(ModBlocks.OXIDIZED_COPPER_BARS);
                        entries.add(ModBlocks.WAXED_COPPER_BARS);
                        entries.add(ModBlocks.WAXED_EXPOSED_COPPER_BARS);
                        entries.add(ModBlocks.WAXED_WEATHERED_COPPER_BARS);
                        entries.add(ModBlocks.WAXED_OXIDIZED_COPPER_BARS);

                        entries.add(ModItems.COPPER_SWORD);
                        entries.add(ModItems.COPPER_PICKAXE);
                        entries.add(ModItems.COPPER_AXE);
                        entries.add(ModItems.COPPER_SHOVEL);
                        entries.add(ModItems.COPPER_HOE);

                        entries.add(ModItems.COPPER_HELMET);
                        entries.add(ModItems.COPPER_CHESTPLATE);
                        entries.add(ModItems.COPPER_LEGGINGS);
                        entries.add(ModItems.COPPER_BOOTS);

                        entries.add(ModItems.COPPER_HORSE_ARMOR);
                    })

                    .build());

    public static void registerItemGroup() {
        CopperEquipments.LOGGER.info("Registering Item Groups for " + CopperEquipments.MOD_ID);
    }
}
