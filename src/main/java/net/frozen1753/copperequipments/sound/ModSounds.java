package net.frozen1753.copperequipments.sound;

import net.frozen1753.copperequipments.CopperEquipments;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent OXIDIZING_POWDER_USE = registerSoundEvent("oxidizing_powder_use");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(CopperEquipments.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSounds() {
        CopperEquipments.LOGGER.info("Registering Sounds for " + CopperEquipments.MOD_ID);
    }
}
