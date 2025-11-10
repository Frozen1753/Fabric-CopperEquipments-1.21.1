package net.frozen1753.copperequipments.item.custom;

import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class CopperPickaxeItem extends PickaxeItem implements CopperItem {
    public CopperPickaxeItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        CopperItem.appendCopperTooltip(stack, tooltip);
        super.appendTooltip(stack, context, tooltip, type);
    }
}
