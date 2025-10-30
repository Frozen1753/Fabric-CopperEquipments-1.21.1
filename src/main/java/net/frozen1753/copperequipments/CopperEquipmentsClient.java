package net.frozen1753.copperequipments;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.frozen1753.copperequipments.item.ModBlocks;
import net.frozen1753.copperequipments.particle.ModParticles;
import net.frozen1753.copperequipments.particle.custom.CopperFlameParticle;
import net.minecraft.client.render.RenderLayer;

public class CopperEquipmentsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_WALL_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_BARS, RenderLayer.getCutout());

        ParticleFactoryRegistry.getInstance().register(ModParticles.COPPER_FLAME, CopperFlameParticle.Factory::new);
    }
}
