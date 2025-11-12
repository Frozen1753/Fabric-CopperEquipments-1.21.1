package net.frozen1753.copperequipments.datagen.custom;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

public class ModModelUtils {
    public static void registerMetalBars(BlockStateModelGenerator generator, Block block) {
        Identifier postEnds = ModelIds.getBlockSubModelId(block, "_post_ends");
        Identifier post = ModelIds.getBlockSubModelId(block, "_post");
        Identifier cap = ModelIds.getBlockSubModelId(block, "_cap");
        Identifier capAlt = ModelIds.getBlockSubModelId(block, "_cap_alt");
        Identifier side = ModelIds.getBlockSubModelId(block, "_side");
        Identifier sideAlt = ModelIds.getBlockSubModelId(block, "_side_alt");

        MultipartBlockStateSupplier supplier = MultipartBlockStateSupplier.create(block)
                .with(BlockStateVariant.create().put(VariantSettings.MODEL, postEnds))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, post))
                .with(When.create().set(Properties.NORTH, true).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, cap))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, true).set(Properties.SOUTH, false).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, cap).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, true).set(Properties.WEST, false),
                        BlockStateVariant.create().put(VariantSettings.MODEL, capAlt))
                .with(When.create().set(Properties.NORTH, false).set(Properties.EAST, false).set(Properties.SOUTH, false).set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, capAlt).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, side))
                .with(When.create().set(Properties.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, side).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideAlt))
                .with(When.create().set(Properties.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideAlt).put(VariantSettings.Y, VariantSettings.Rotation.R90));

        generator.blockStateCollector.accept(supplier);
        generator.registerItemModel(block);
    }

    public static void registerOxidizingEquipmentModel(ItemModelGenerator generator, Item item, Model model) {
        Identifier modelId = ModelIds.getItemModelId(item);
        TextureMap textureMap = TextureMap.layer0(item);

        model.upload(modelId, textureMap, generator.writer, (id, textures) -> {
            JsonObject json = model.createJson(id, textures);

            JsonArray overrides = new JsonArray();

            overrides.add(createOverride(0.25F, ModelIds.getItemSubModelId(item, "_exposed")));
            overrides.add(createOverride(0.50F, ModelIds.getItemSubModelId(item, "_weathered")));
            overrides.add(createOverride(0.75F, ModelIds.getItemSubModelId(item, "_oxidized")));

            json.add("overrides", overrides);
            return json;
        });

        generator.register(item, "_exposed", model);
        generator.register(item, "_weathered", model);
        generator.register(item, "_oxidized", model);
    }

    private static JsonObject createOverride(float oxidationValue, Identifier modelId) {
        JsonObject override = new JsonObject();

        JsonObject predicate = new JsonObject();
        predicate.addProperty("oxidation", oxidationValue);

        override.add("predicate", predicate);
        override.addProperty("model", modelId.toString());

        return override;
    }
}
