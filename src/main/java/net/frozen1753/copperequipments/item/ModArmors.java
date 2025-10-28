package net.frozen1753.copperequipments.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.material.ModArmorMaterials;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModArmors {
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

    public static void registerModArmors() {
        CopperEquipments.LOGGER.info("Registering Copper Armors");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(COPPER_HELMET);
            entries.add(COPPER_CHESTPLATE);
            entries.add(COPPER_LEGGINGS);
            entries.add(COPPER_BOOTS);
        });
    }
}