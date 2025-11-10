package net.frozen1753.copperequipments.material;

import net.frozen1753.copperequipments.CopperEquipments;

import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final RegistryEntry<ArmorMaterial> COPPER_ARMOR_MATERIAL = registerArmorMaterial();

    private static RegistryEntry<ArmorMaterial> registerArmorMaterial() {
        EnumMap<ArmorItem.Type, Integer> defense = Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
            map.put(ArmorItem.Type.BOOTS, 1);
            map.put(ArmorItem.Type.LEGGINGS, 3);
            map.put(ArmorItem.Type.CHESTPLATE, 4);
            map.put(ArmorItem.Type.HELMET, 2);
            map.put(ArmorItem.Type.BODY, 5);
        });

        Supplier<Ingredient> repairIngredient = () -> Ingredient.ofItems(net.minecraft.item.Items.COPPER_INGOT);
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(Identifier.of(CopperEquipments.MOD_ID, "copper")));

        return Registry.registerReference(
                Registries.ARMOR_MATERIAL,
                Identifier.of(CopperEquipments.MOD_ID, "copper"),
                new ArmorMaterial(defense, 12, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, repairIngredient, layers, 0.0F, 0.0F)
        );
    }

    public static void registerModArmorMaterials() {
        CopperEquipments.LOGGER.info("Registering Armor Materials for " + CopperEquipments.MOD_ID);
    }
}