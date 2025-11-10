package net.frozen1753.copperequipments;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.frozen1753.copperequipments.block.ModBlocks;
import net.frozen1753.copperequipments.item.ModItems;
import net.frozen1753.copperequipments.item.custom.CopperArmorItem;
import net.frozen1753.copperequipments.material.custom.OxidizableArmorRenderer;
import net.frozen1753.copperequipments.particle.ModParticles;
import net.frozen1753.copperequipments.particle.custom.CopperFlameParticle;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class CopperEquipmentsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelPredicateProviderRegistry.register(ModItems.COPPER_SWORD, Identifier.of("oxidation"), (stack, world, entity, seed) -> {
            if (!stack.isDamageable()) return 0.0F;
            return (float) stack.getDamage() / stack.getMaxDamage();
        });

        ModelPredicateProviderRegistry.register(ModItems.COPPER_PICKAXE, Identifier.of("oxidation"), (stack, world, entity, seed) -> {
            if (!stack.isDamageable()) return 0.0F;
            return (float) stack.getDamage() / stack.getMaxDamage();
        });

        ModelPredicateProviderRegistry.register(ModItems.COPPER_AXE, Identifier.of("oxidation"), (stack, world, entity, seed) -> {
            if (!stack.isDamageable()) return 0.0F;
            return (float) stack.getDamage() / stack.getMaxDamage();
        });

        ModelPredicateProviderRegistry.register(ModItems.COPPER_SHOVEL, Identifier.of("oxidation"), (stack, world, entity, seed) -> {
            if (!stack.isDamageable()) return 0.0F;
            return (float) stack.getDamage() / stack.getMaxDamage();
        });

        ModelPredicateProviderRegistry.register(ModItems.COPPER_HOE, Identifier.of("oxidation"), (stack, world, entity, seed) -> {
            if (!stack.isDamageable()) return 0.0F;
            return (float) stack.getDamage() / stack.getMaxDamage();
        });

        ModelPredicateProviderRegistry.register(ModItems.COPPER_HELMET, Identifier.of("oxidation"), (stack, world, entity, seed) -> {
            if (!stack.isDamageable()) return 0.0F;
            return (float) stack.getDamage() / stack.getMaxDamage();
        });

        ModelPredicateProviderRegistry.register(ModItems.COPPER_CHESTPLATE, Identifier.of("oxidation"), (stack, world, entity, seed) -> {
            if (!stack.isDamageable()) return 0.0F;
            return (float) stack.getDamage() / stack.getMaxDamage();
        });

        ModelPredicateProviderRegistry.register(ModItems.COPPER_LEGGINGS, Identifier.of("oxidation"), (stack, world, entity, seed) -> {
            if (!stack.isDamageable()) return 0.0F;
            return (float) stack.getDamage() / stack.getMaxDamage();
        });

        ModelPredicateProviderRegistry.register(ModItems.COPPER_BOOTS, Identifier.of("oxidation"), (stack, world, entity, seed) -> {
            if (!stack.isDamageable()) return 0.0F;
            return (float) stack.getDamage() / stack.getMaxDamage();
        });

        ArmorRenderer.register(new OxidizableArmorRenderer(), ModItems.COPPER_HELMET, ModItems.COPPER_CHESTPLATE, ModItems.COPPER_LEGGINGS, ModItems.COPPER_BOOTS);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_TORCH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_WALL_TORCH, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OXIDIZED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_CHAIN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OXIDIZED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_LANTERN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_COPPER_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WEATHERED_COPPER_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OXIDIZED_COPPER_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_COPPER_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_EXPOSED_COPPER_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_WEATHERED_COPPER_BARS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WAXED_OXIDIZED_COPPER_BARS, RenderLayer.getCutout());

        ParticleFactoryRegistry.getInstance().register(ModParticles.COPPER_FLAME, CopperFlameParticle.Factory::new);
    }
}
