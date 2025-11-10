package net.frozen1753.copperequipments.material.custom;

import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.util.ModDataComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
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
    }
}