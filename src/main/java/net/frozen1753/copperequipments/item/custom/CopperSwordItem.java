package net.frozen1753.copperequipments.item.custom;

import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class CopperSwordItem extends SwordItem implements CopperItem {
    public CopperSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        CopperItem.appendCopperTooltip(stack, tooltip);
        super.appendTooltip(stack, context, tooltip, type);
    }
}
