package net.frozen1753.copperequipments.material.custom;

import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.util.ModDataComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.trim.ArmorTrim;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class OxidizableArmorRenderer implements ArmorRenderer {

    @Override
    public void render(MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers,
                       ItemStack stack,
                       LivingEntity entity,
                       EquipmentSlot slot,
                       int light,
                       BipedEntityModel<LivingEntity> contextModel) {

        String stage = switch (stack.getOrDefault(ModDataComponents.OXIDATION_STAGE, 0)) {
            case 0 -> "";
            case 1 -> "_exposed";
            case 2 -> "_weathered";
            default -> "_oxidized";
        };

        boolean secondLayer = slot == EquipmentSlot.LEGS;
        String layer = secondLayer ? "layer_2" : "layer_1";

        Identifier texture = Identifier.of(CopperEquipments.MOD_ID, "textures/models/armor/copper" + stage + "_" + layer + ".png");

        BipedEntityModel<LivingEntity> armorModel;
        if (slot == EquipmentSlot.LEGS) {
            armorModel = new BipedEntityModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(EntityModelLayers.PLAYER_INNER_ARMOR));
        } else {
            armorModel = new BipedEntityModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(EntityModelLayers.PLAYER_OUTER_ARMOR));
        }

        contextModel.copyBipedStateTo(armorModel);

        armorModel.setVisible(false);
        switch (slot) {
            case HEAD -> {
                armorModel.head.visible = true;
                armorModel.hat.visible = true;
            }
            case CHEST -> {
                armorModel.body.visible = true;
                armorModel.rightArm.visible = true;
                armorModel.leftArm.visible = true;
            }
            case LEGS -> {
                armorModel.body.visible = true;
                armorModel.rightLeg.visible = true;
                armorModel.leftLeg.visible = true;
            }
            case FEET -> {
                armorModel.rightLeg.visible = true;
                armorModel.leftLeg.visible = true;
            }
        }

        ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, armorModel, texture);
        /*
        ArmorTrim trim = stack.get(DataComponentTypes.TRIM);

        if (trim != null) {
            // 1. Pattern texture (NOT material-specific)
            String pattern = trim.getPattern().value().assetId().getPath();
            String patternPath = "trims/models/armor/" + pattern;
            if (secondLayer) patternPath += "_leggings";

            Identifier patternTex = Identifier.of("minecraft", patternPath + ".png");

            // 2. Palette texture (material)
            String material = trim.getMaterial().value().assetName();
            Identifier paletteTex = Identifier.of("minecraft", "trims/color_palettes/" + material + ".png");

            // 3. Use the vanilla 2-texture trim layer
            RenderLayer trimLayer = RenderLayer.getArmorCutoutNoCull(patternTex, paletteTex);

            VertexConsumer vc = ItemRenderer.getArmorGlintConsumer(
                    vertexConsumers,
                    trimLayer,
                    stack.hasGlint()
            );

            armorModel.render(matrices, vc, light, OverlayTexture.DEFAULT_UV, 0xFFFFFFFF);
        }
        */
    }
}