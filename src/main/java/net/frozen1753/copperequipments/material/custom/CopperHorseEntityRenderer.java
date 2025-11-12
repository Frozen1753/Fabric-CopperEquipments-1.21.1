package net.frozen1753.copperequipments.material.custom;

import net.minecraft.client.render.entity.AbstractHorseEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.entity.passive.HorseColor;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.util.Identifier;

public class CopperHorseEntityRenderer extends AbstractHorseEntityRenderer<HorseEntity, HorseEntityModel<HorseEntity>> {

    public CopperHorseEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new HorseEntityModel<>(ctx.getModelLoader().getModelPart(EntityModelLayers.HORSE)), 1.0F);

        this.addFeature(new OxidizableHorseArmorFeatureRenderer(this, ctx.getModelLoader()));
    }

    @Override
    public Identifier getTexture(HorseEntity entity) {
        HorseColor color = entity.getVariant();

        return Identifier.of("minecraft", "textures/entity/horse/horse_" + color.asString() + ".png");
    }
}