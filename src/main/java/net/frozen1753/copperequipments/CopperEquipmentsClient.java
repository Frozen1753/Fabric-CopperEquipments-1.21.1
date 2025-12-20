package net.frozen1753.copperequipments;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.frozen1753.copperequipments.block.ModBlocks;
import net.frozen1753.copperequipments.config.CopperEquipmentsConfigs;
import net.frozen1753.copperequipments.item.ModItems;
import net.frozen1753.copperequipments.item.custom.CopperItem;
import net.frozen1753.copperequipments.material.custom.CopperHorseEntityRenderer;
import net.frozen1753.copperequipments.material.custom.OxidizableArmorRenderer;
import net.frozen1753.copperequipments.particle.ModParticles;
import net.frozen1753.copperequipments.particle.custom.CopperFlameParticle;
import net.frozen1753.copperequipments.particle.custom.ForcedOxidationParticle;
import net.frozen1753.copperequipments.util.ModDataComponents;
import net.frozen1753.copperequipments.util.events.ItemDurabilityChangeCallback;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class CopperEquipmentsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerCopperOxidationPredicate(
                ModItems.COPPER_SWORD,
                ModItems.COPPER_PICKAXE,
                ModItems.COPPER_AXE,
                ModItems.COPPER_SHOVEL,
                ModItems.COPPER_HOE,
                ModItems.COPPER_HELMET,
                ModItems.COPPER_CHESTPLATE,
                ModItems.COPPER_LEGGINGS,
                ModItems.COPPER_BOOTS,
                ModItems.COPPER_HORSE_ARMOR
        );

        ArmorRenderer.register(new OxidizableArmorRenderer(), ModItems.COPPER_HELMET, ModItems.COPPER_CHESTPLATE, ModItems.COPPER_LEGGINGS, ModItems.COPPER_BOOTS);
        EntityRendererRegistry.register(EntityType.HORSE, CopperHorseEntityRenderer::new);

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
        ParticleFactoryRegistry.getInstance().register(ModParticles.FORCED_OXIDATION, ForcedOxidationParticle.Factory::new);

        ItemDurabilityChangeCallback.EVENT.register((stack, amount, world, player) -> {});

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            CopperEquipmentsConfigs.updateEnv();
            CopperEquipments.LOGGER.info("[DEBUG] [ENV] Updated on world join: " + CopperEquipmentsConfigs.isServerOwner);
        });

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            CopperEquipmentsConfigs.updateEnv();
            CopperEquipments.LOGGER.info("[DEBUG] [ENV] Updated on disconnect: " + CopperEquipmentsConfigs.isServerOwner);
        });
    }

    private static void registerCopperOxidationPredicate(Item... items) {
        for (Item item : items) {
            ModelPredicateProviderRegistry.register(
                    item,
                    Identifier.of("oxidation"),
                    (stack, world, entity, seed) -> {
                        if (!stack.contains(ModDataComponents.OXIDATION_STAGE)) return -1.0F;

                        return switch (CopperItem.getOxidationStage(stack)) {
                            case 0 -> 0.0F;
                            case 1 -> 0.25F;
                            case 2 -> 0.50F;
                            case 3 -> 0.75F;
                            default -> -1.0F;
                        };
                    }
            );
        }
    }
}
