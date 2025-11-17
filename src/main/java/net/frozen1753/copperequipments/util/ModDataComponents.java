package net.frozen1753.copperequipments.util;

import com.mojang.serialization.Codec;
import net.frozen1753.copperequipments.CopperEquipments;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModDataComponents {
    public static final ComponentType<Boolean> WAXED = registerDataComponent("waxed",
            ComponentType.<Boolean>builder()
                    .codec(Codec.BOOL)
                    .build());

    public static final ComponentType<Integer> OXIDATION_STAGE = registerDataComponent("oxidation_stage",
            ComponentType.<Integer>builder()
                    .codec(Codec.INT)
                    .build());

    public static final ComponentType<Long> CREATION_TIME = registerDataComponent("creation_time",
            ComponentType.<Long>builder()
                    .codec(Codec.LONG)
                    .build());

    private static <T> ComponentType<T> registerDataComponent(String name, ComponentType<T> component) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(CopperEquipments.MOD_ID, name), component);
    }

    public static void registerModDataComponents() {
        CopperEquipments.LOGGER.info("Registering DataComponent for " + CopperEquipments.MOD_ID);
    }
}

