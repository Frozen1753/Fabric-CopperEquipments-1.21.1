package net.frozen1753.copperequipments.material.custom;

import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.item.ModItems;
import net.frozen1753.copperequipments.util.ModDataComponents;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.AnimalArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

public class OxidizableHorseArmorFeatureRenderer extends FeatureRenderer<HorseEntity, HorseEntityModel<HorseEntity>> {
    private final HorseEntityModel<HorseEntity> model;

    public OxidizableHorseArmorFeatureRenderer(
            FeatureRendererContext<HorseEntity, HorseEntityModel<HorseEntity>> context,
            EntityModelLoader loader
    ) {
        super(context);
        this.model = new HorseEntityModel<>(loader.getModelPart(EntityModelLayers.HORSE_ARMOR));
    }

    @Override
    public void render(MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers,
                       int light,
                       HorseEntity horse,
                       float limbAngle,
                       float limbDistance,
                       float tickDelta,
                       float animationProgress,
                       float headYaw,
                       float headPitch) {

        ItemStack stack = horse.getBodyArmor();
        if (!(stack.getItem() instanceof AnimalArmorItem armorItem) || armorItem.getType() != AnimalArmorItem.Type.EQUESTRIAN) {
            return;
        }

        // Default: use the armor item’s own texture
        Identifier texture = armorItem.getEntityTexture();

        // Special case: copper horse armor with oxidation stages
        if (stack.getItem() == ModItems.COPPER_HORSE_ARMOR) {
            String stage = switch (stack.getOrDefault(ModDataComponents.OXIDATION_STAGE, 0)) {
                case 0 -> "";
                case 1 -> "_exposed";
                case 2 -> "_weathered";
                default -> "_oxidized";
            };
            texture = Identifier.of(CopperEquipments.MOD_ID, "textures/entity/horse/armor/horse_armor_copper" + stage + ".png");
        }

        // Sync horse state to armor model
        this.getContextModel().copyStateTo(this.model);
        this.model.animateModel(horse, limbAngle, limbDistance, tickDelta);
        this.model.setAngles(horse, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

        // Handle dyeable leather horse armor
        int color = -1;
        if (stack.isIn(ItemTags.DYEABLE)) {
            color = ColorHelper.Argb.fullAlpha(DyedColorComponent.getColor(stack, -6265536));
        }

        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(texture));
        this.model.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, color);
    }
}
