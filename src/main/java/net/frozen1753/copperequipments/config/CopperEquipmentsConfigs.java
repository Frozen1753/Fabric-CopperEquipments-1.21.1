package net.frozen1753.copperequipments.config;

import eu.midnightdust.lib.config.MidnightConfig;
import net.frozen1753.copperequipments.CopperEquipments;
import net.minecraft.client.MinecraftClient;

public class CopperEquipmentsConfigs extends MidnightConfig {
    public static final String DEOXIDATION = "deoxidation";
    public static final String UNWAXING = "unwaxing";
    public static final String OXIDATION = "oxidation";
    public static final String TEXTURES = "textures";
    public static final String ITEMS = "items";

    public static final String INTERNAL = "internal";

    @Condition(
            requiredModId = "___locked___",
            visibleButLocked = true
    )
    @Entry(category = INTERNAL)
    public static boolean isServerOwner = false;

    public static void updateEnv() {
        MinecraftClient client = MinecraftClient.getInstance();
        boolean host = client.isIntegratedServerRunning()
                && client.getServer() != null
                && client.getServer().isSingleplayer();

        if (isServerOwner != host) {
            isServerOwner = host;
            MidnightConfig.write(CopperEquipments.MOD_ID);
            MidnightConfig.configInstances.get(CopperEquipments.MOD_ID).loadValuesFromJson();
        }
    }

    // -------------------
    // Deoxidation
    // -------------------

    @Condition(requiredOption = "copperequipments:isServerOwner", requiredValue = "false")
    @Comment(category = DEOXIDATION, centered = true)
    public static Comment notHostWarningCommentDeoxidation;

    @Entry(category = DEOXIDATION)
    public static boolean axeRequiredForDeoxidize = true;

    @Condition(requiredOption = "copperequipments:axeRequiredForDeoxidize", requiredValue = "true")
    @Entry(category = DEOXIDATION)
    public static boolean axeDurabilityForDeoxidize = true;

    // -------------------
    // Unwaxing
    // -------------------

    @Condition(requiredOption = "copperequipments:isServerOwner", requiredValue = "false")
    @Comment(category = UNWAXING, centered = true)
    public static Comment notHostWarningCommentUnwaxing;

    @Entry(category = UNWAXING)
    public static boolean axeRequiredForUnwaxing = true;

    @Condition(requiredOption = "copperequipments:axeRequiredForUnwaxing", requiredValue = "true")
    @Entry(category = UNWAXING)
    public static boolean axeDurabilityForUnwaxing = true;

    // -------------------
    // Oxidation
    // -------------------

    @Condition(requiredOption = "copperequipments:isServerOwner", requiredValue = "false")
    @Comment(category = OXIDATION, centered = true)
    public static Comment notHostWarningCommentOxidation;

    public enum ItemOxidationMethod {
        NONE, DURABILITY_ONLY, TIME_ONLY, DURABILITY_AND_TIME
    }

    @Entry(category = OXIDATION)
    public static ItemOxidationMethod itemOxidationMethod = ItemOxidationMethod.DURABILITY_AND_TIME;

    @Condition(
        requiredOption = "copperequipments:itemOxidationMethod",
        requiredValue = {"TIME_ONLY", "DURABILITY_AND_TIME"}
    )
    @Entry(category = OXIDATION, min = 0d)
    public static double maxLifespanTick = 36000; // 30 minutes RL

    @Condition(
        requiredOption = "copperequipments:itemOxidationMethod",
        requiredValue = "DURABILITY_AND_TIME"
    )
    @Entry(category = OXIDATION, isSlider = true, min = 0d, max = 1d)
    public static double alphaWeight = 0.03; // max 3% per attempt

    @Condition(
        requiredOption = "copperequipments:itemOxidationMethod",
        requiredValue = "DURABILITY_AND_TIME"
    )
    @Entry(category = OXIDATION, isSlider = true, min = 0d, max = 1d)
    public static double betaWeight = 0.02; // max 2% per attempt

    @Condition(
        requiredOption = "copperequipments:itemOxidationMethod",
        requiredValue = {"TIME_ONLY", "DURABILITY_AND_TIME", "DURABILITY_ONLY"}
    )
    @Entry(category = OXIDATION, min = 1d)
    public static double oxidationIntervalAttempt = 200; // 10s RL

    // -------------------
    // Textures
    // -------------------
    @Condition(
        requiredModId = "___coming_soon___",
        visibleButLocked = true
    )
    @Entry(category = TEXTURES)
    public static boolean waxedUniqueTextures = false;

    @Condition(
        requiredModId = "___coming_soon___",
        visibleButLocked = true
    )
    @Entry(category = TEXTURES)
    public static boolean waxedIndicatorItem = false;

    // -------------------
    // Items
    // -------------------

    @Condition(requiredOption = "copperequipments:isServerOwner", requiredValue = "false")
    @Comment(category = ITEMS, centered = true)
    public static Comment notHostWarningCommentItems;

    @Condition(
        requiredModId = "___coming_soon___",
        visibleButLocked = true
    )
    @Entry(category = ITEMS)
    public static boolean copperHorseArmorCraftable = false;
}
