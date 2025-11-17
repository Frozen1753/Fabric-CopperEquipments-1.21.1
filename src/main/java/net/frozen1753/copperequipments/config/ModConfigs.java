package net.frozen1753.copperequipments.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "CopperEquipmentsConfigs")
public class ModConfigs implements ConfigData {
    @ConfigEntry.Gui.CollapsibleObject
    public DeoxidationSettings deoxidation = new DeoxidationSettings();

    @ConfigEntry.Gui.CollapsibleObject
    public UnwaxingSettings unwaxing = new UnwaxingSettings();

    @ConfigEntry.Gui.CollapsibleObject
    public OxidationSettings oxidation = new OxidationSettings();

    @ConfigEntry.Gui.CollapsibleObject
    public TexturesSettings textures = new TexturesSettings();

    @ConfigEntry.Gui.CollapsibleObject
    public ItemsSettings items = new ItemsSettings();

    public static class DeoxidationSettings {
        @ConfigEntry.Gui.Tooltip
        public boolean axeRequiredForDeoxidize = true;

        @ConfigEntry.Gui.Tooltip
        public boolean axeDurabilityForDeoxidize = true;
    }

    public static class UnwaxingSettings {
        @ConfigEntry.Gui.Tooltip
        public boolean axeRequiredForUnwaxing = true;

        @ConfigEntry.Gui.Tooltip
        public boolean axeDurabilityForUnwaxing = true;
    }

    public static class OxidationSettings {
        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public ItemOxidationMethod itemOxidationMethod = ItemOxidationMethod.DURABILITY_AND_TIME;

        public enum ItemOxidationMethod {
            NONE, DURABILITY_ONLY, TIME_ONLY, DURABILITY_AND_TIME
        }

        @ConfigEntry.Gui.Tooltip
        public double maxLifespanTick = 36000; // 30 minutes RL

        @ConfigEntry.Gui.Tooltip
        public double alphaWeight = 0.5;

        @ConfigEntry.Gui.Tooltip
        public double betaWeight = 0.5;

        @ConfigEntry.Gui.Tooltip
        public double oxidationIntervalAttempt = 200; // 10 seconds RL = 10 * 20 = 200 ticks
    }

    public static class TexturesSettings {
        @ConfigEntry.Gui.Tooltip
        public boolean waxedUniqueTextures = true;

        @ConfigEntry.Gui.Tooltip
        public boolean waxedIndicatorItem = true;
    }

    public static class ItemsSettings {
        @ConfigEntry.Gui.Tooltip
        public boolean copperHorseArmorCraftable = true;
    }
}