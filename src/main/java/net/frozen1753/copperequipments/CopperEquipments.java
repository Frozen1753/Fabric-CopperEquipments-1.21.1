package net.frozen1753.copperequipments;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

import net.frozen1753.copperequipments.block.ModBlocks;
import net.frozen1753.copperequipments.config.ModConfigs;
import net.frozen1753.copperequipments.item.ModItemGroups;
import net.frozen1753.copperequipments.item.ModItems;
import net.frozen1753.copperequipments.material.ModArmorMaterials;
import net.frozen1753.copperequipments.material.ModToolMaterials;
import net.frozen1753.copperequipments.particle.ModParticles;
import net.frozen1753.copperequipments.recipe.ModRecipes;
import net.frozen1753.copperequipments.util.ModDataComponents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopperEquipments implements ModInitializer {
	public static final String MOD_ID = "copperequipments";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ModConfigs CONFIG;

	@Override
	public void onInitialize() {
        ModArmorMaterials.registerModArmorMaterials();
        ModToolMaterials.registerModToolMaterials();
        ModRecipes.registerModRecipes();
        ModParticles.registerParticles();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModItemGroups.registerItemGroup();
        ModDataComponents.registerModDataComponents();

        AutoConfig.register(ModConfigs.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ModConfigs.class).getConfig();
    }
}