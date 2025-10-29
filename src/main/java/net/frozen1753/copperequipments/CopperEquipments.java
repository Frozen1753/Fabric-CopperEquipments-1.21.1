package net.frozen1753.copperequipments;

import net.fabricmc.api.ModInitializer;

import net.frozen1753.copperequipments.item.ModItemGroups;
import net.frozen1753.copperequipments.item.ModItems;
import net.frozen1753.copperequipments.material.ModArmorMaterials;
import net.frozen1753.copperequipments.material.ModToolMaterials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopperEquipments implements ModInitializer {
	public static final String MOD_ID = "copperequipments";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModArmorMaterials.registerModArmorMaterials();
        ModToolMaterials.registerModToolMaterials();
        ModItems.registerModItems();
        ModItemGroups.registerItemGroup();
	}
}