package net.frozen1753.copperequipments;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import net.frozen1753.copperequipments.block.ModBlocks;
import net.frozen1753.copperequipments.config.CopperEquipmentsConfigs;
import net.frozen1753.copperequipments.item.ModItemGroups;
import net.frozen1753.copperequipments.item.ModItems;
import net.frozen1753.copperequipments.material.ModArmorMaterials;
import net.frozen1753.copperequipments.material.ModToolMaterials;
import net.frozen1753.copperequipments.particle.ModParticles;
import net.frozen1753.copperequipments.recipe.ModRecipes;
import net.frozen1753.copperequipments.sound.ModSounds;
import net.frozen1753.copperequipments.util.ModDataComponents;
import net.frozen1753.copperequipments.util.ModTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopperEquipments implements ModInitializer {
	public static final String MOD_ID = "copperequipments";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
	public void onInitialize() {
        MidnightConfig.init(MOD_ID, CopperEquipmentsConfigs.class);
        System.out.println("[DEBUG] [ENV] Initial environment: " + CopperEquipmentsConfigs.isServerOwner);
        ModArmorMaterials.registerModArmorMaterials();
        ModToolMaterials.registerModToolMaterials();
        ModRecipes.registerModRecipes();
        ModParticles.registerParticles();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModItemGroups.registerItemGroup();
        ModDataComponents.registerModDataComponents();
        ModSounds.registerModSounds();
        ModTags.registerModTags();
    }
}