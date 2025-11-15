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

    public static class DeoxidationSettings {
        @ConfigEntry.Gui.Tooltip(count = 1)
        public boolean axeRequiredForDeoxidize = false;

        @ConfigEntry.Gui.Tooltip(count = 1)
        public boolean axeDurabilityForDeoxidize = true;
    }

    public static class UnwaxingSettings {
        @ConfigEntry.Gui.Tooltip(count = 1)
        public boolean axeRequiredForUnwaxing = false;

        @ConfigEntry.Gui.Tooltip(count = 1)
        public boolean axeDurabilityForUnwaxing = true;
    }

    public static class OxidationSettings {
        @ConfigEntry.Gui.Tooltip(count = 1)
        @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
        public ItemOxidationMethod itemOxidationMethod = ItemOxidationMethod.DURABILITY_AND_TIME;

        public enum ItemOxidationMethod {
            NONE, DURABILITY_ONLY, TIME_ONLY, DURABILITY_AND_TIME
        }
    }

    public static class TexturesSettings {
        @ConfigEntry.Gui.Tooltip(count = 1)
        public boolean waxedUniqueTextures = false;
    }
}