package net.frozen1753.copperequipments.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.material.ModArmorMaterials;
import net.frozen1753.copperequipments.material.ModToolMaterials;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.List;

public class ModItems {
    public static final Item COPPER_NUGGET = registerItem("copper_nugget", new Item(new Item.Settings()));

    public static final Item COPPER_TORCH_ITEM = registerItem("copper_torch",
            new VerticallyAttachableBlockItem(ModBlocks.COPPER_TORCH, ModBlocks.COPPER_WALL_TORCH, new Item.Settings(), Direction.DOWN));

    public static final Item COPPER_SWORD = registerItem("copper_sword",
            new SwordItem(ModToolMaterials.COPPER,
                    new Item.Settings()
                            .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.COPPER, 3, -2.4F))
            )
    );

    public static final Item COPPER_PICKAXE = registerItem("copper_pickaxe",
            new PickaxeItem(ModToolMaterials.COPPER,
                    new Item.Settings()
                            .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.COPPER, 1, -2.8F))
            )
    );

    public static final Item COPPER_SHOVEL = registerItem("copper_shovel",
            new ShovelItem(ModToolMaterials.COPPER,
                    new Item.Settings()
                            .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.COPPER, 1.5F, -3.0F))
            )
    );

    public static final Item COPPER_AXE = registerItem("copper_axe",
            new AxeItem(ModToolMaterials.COPPER,
                    new Item.Settings()
                            .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.COPPER, 6.5F, -3.1F))
            )
    );

    public static final Item COPPER_HOE = registerItem("copper_hoe",
            new HoeItem(ModToolMaterials.COPPER,
                    new Item.Settings()
                            .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.COPPER, -1.5F, -1.5F))
            )
    );

    private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11}; // boots, leggings, chestplate, helmet
    private static final int DURABILITY_MULTIPLIER = 11; // copper

    public static final Item COPPER_HELMET = registerItem("copper_helmet",
            new ArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Settings().maxDamage(BASE_DURABILITY[3] * DURABILITY_MULTIPLIER))
    );

    public static final Item COPPER_CHESTPLATE = registerItem("copper_chestplate",
            new ArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().maxDamage(BASE_DURABILITY[2] * DURABILITY_MULTIPLIER))
    );

    public static final Item COPPER_LEGGINGS = registerItem("copper_leggings",
            new ArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Settings().maxDamage(BASE_DURABILITY[1] * DURABILITY_MULTIPLIER))
    );

    public static final Item COPPER_BOOTS = registerItem("copper_boots",
            new ArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Settings().maxDamage(BASE_DURABILITY[0] * DURABILITY_MULTIPLIER))
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CopperEquipments.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CopperEquipments.LOGGER.info("Registering Mod Items for " + CopperEquipments.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(COPPER_NUGGET);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(COPPER_SWORD);
            entries.add(COPPER_AXE);

            entries.add(COPPER_HELMET);
            entries.add(COPPER_CHESTPLATE);
            entries.add(COPPER_LEGGINGS);
            entries.add(COPPER_BOOTS);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(COPPER_SHOVEL);
            entries.add(COPPER_PICKAXE);
            entries.add(COPPER_AXE);
            entries.add(COPPER_HOE);
        });
    }
}
