package net.frozen1753.copperequipments.item;
import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.material.ModToolMaterials;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

import javax.tools.Tool;
import java.util.List;

public class ModTools {
    public static final Item COPPER_SWORD = registerItem("copper_sword",
            new SwordItem(ModToolMaterials.COPPER,
                    new Item.Settings()
                            .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                                    SwordItem.createAttributeModifiers(ModToolMaterials.COPPER, 3, -2.4F))
            )
    );

    public static final Item COPPER_PICKAXE = registerItem("copper_pickaxe",
            new PickaxeItem(ModToolMaterials.COPPER,
                    new Item.Settings()
                            .component(DataComponentTypes.TOOL,
                                    new ToolComponent(
                                            List.of(ToolComponent.Rule.of(BlockTags.PICKAXE_MINEABLE, ModToolMaterials.COPPER.getMiningSpeedMultiplier())),
                                            1.0F, 1
                                    )
                            )
                            .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                                    MiningToolItem.createAttributeModifiers(ModToolMaterials.COPPER, 1, -2.8F))
            )
    );

    public static final Item COPPER_SHOVEL = registerItem("copper_shovel",
            new ShovelItem(ModToolMaterials.COPPER,
                    new Item.Settings()
                            .component(DataComponentTypes.TOOL,
                                    new ToolComponent(
                                            List.of(ToolComponent.Rule.of(BlockTags.SHOVEL_MINEABLE, ModToolMaterials.COPPER.getMiningSpeedMultiplier())),
                                            1.0F, 1
                                    )
                            )
                            .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                                    MiningToolItem.createAttributeModifiers(ModToolMaterials.COPPER, 1.5F, -3.0F))
            )
    );

    public static final Item COPPER_AXE = registerItem("copper_axe",
            new AxeItem(ModToolMaterials.COPPER,
                    new Item.Settings()
                            .component(DataComponentTypes.TOOL,
                                    new ToolComponent(
                                            List.of(ToolComponent.Rule.of(BlockTags.AXE_MINEABLE, ModToolMaterials.COPPER.getMiningSpeedMultiplier())),
                                            1.0F, 1
                                    )
                            )
                            .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                                    MiningToolItem.createAttributeModifiers(ModToolMaterials.COPPER, 6.5F, -3.1F))
            )
    );

    public static final Item COPPER_HOE = registerItem("copper_hoe",
            new HoeItem(ModToolMaterials.COPPER,
                    new Item.Settings()
                            .component(DataComponentTypes.TOOL,
                                    new ToolComponent(
                                            List.of(ToolComponent.Rule.of(BlockTags.HOE_MINEABLE, ModToolMaterials.COPPER.getMiningSpeedMultiplier())),
                                            1.0F, 1
                                    )
                            )
                            .component(DataComponentTypes.ATTRIBUTE_MODIFIERS,
                                    MiningToolItem.createAttributeModifiers(ModToolMaterials.COPPER, -1.5F, -1.5F))
            )
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CopperEquipments.MOD_ID, name), item);
    }

    public static void registerModTools() {
        CopperEquipments.LOGGER.info("Registering Copper Tools");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(COPPER_SWORD);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(COPPER_SHOVEL);
            entries.add(COPPER_PICKAXE);
            entries.add(COPPER_AXE);
            entries.add(COPPER_HOE);
        });
    }
}
