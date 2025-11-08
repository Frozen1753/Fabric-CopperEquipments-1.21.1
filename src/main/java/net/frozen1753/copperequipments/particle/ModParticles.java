package net.frozen1753.copperequipments.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.frozen1753.copperequipments.CopperEquipments;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final SimpleParticleType COPPER_FLAME = registerParticle("copper_flame",
            FabricParticleTypes.simple()
    );

    public static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CopperEquipments.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        CopperEquipments.LOGGER.info("Registering Particles for " + CopperEquipments.MOD_ID);
    }
}
