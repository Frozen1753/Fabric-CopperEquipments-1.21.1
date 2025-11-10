package net.frozen1753.copperequipments.item.custom;

import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;

import java.util.List;

public class CopperAxeItem extends AxeItem implements CopperItem {
    public CopperAxeItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        CopperItem.appendCopperTooltip(stack, tooltip);
        super.appendTooltip(stack, context, tooltip, type);
    }
}
