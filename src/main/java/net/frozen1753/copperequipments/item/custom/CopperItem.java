package net.frozen1753.copperequipments.item.custom;

import net.frozen1753.copperequipments.util.ModDataComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

import java.util.List;

public interface CopperItem {
    public static boolean isWaxed(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.WAXED, false);
    }

    public static void setWaxed(ItemStack stack, boolean waxed) {
        stack.set(ModDataComponents.WAXED, waxed);
    }

    public static int getOxidationStage(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.OXIDATION_STAGE, 0);
    }

    public static void setOxidationStage(ItemStack stack, int stage) {
        stack.set(ModDataComponents.OXIDATION_STAGE, stage);
    }

    public static void updateWaxStageFromDamage(ItemStack stack) {
        int damage = stack.getDamage();
        int max = stack.getMaxDamage();
        float ratio = (float) damage / max;

        int stage = Math.min(3, (int) (ratio * 4));
        setOxidationStage(stack, stage);
    }

    static void appendCopperTooltip(ItemStack stack, List<Text> tooltip) {
        boolean waxed = isWaxed(stack);
        int stage = getOxidationStage(stack);

        TextColor waxedColor = TextColor.fromRgb(0xFFC30B);
        TextColor unexposedColor = TextColor.fromRgb(0xD66D48);
        TextColor exposedColor = TextColor.fromRgb(0xAA7D57);
        TextColor weatheredColor = TextColor.fromRgb(0x7E8E67);
        TextColor oxidizedColor = TextColor.fromRgb(0x529F77);

        Text waxedText = Text.translatable("tooltip.copperequipments.waxed")
                .styled(style -> style.withColor(waxedColor));
        Text unexposedText = Text.translatable("tooltip.copperequipments.unexposed")
                .styled(style -> style.withColor(unexposedColor));
        Text exposedText = Text.translatable("tooltip.copperequipments.exposed")
                .styled(style -> style.withColor(exposedColor));
        Text weatheredText = Text.translatable("tooltip.copperequipments.weathered")
                .styled(style -> style.withColor(weatheredColor));
        Text oxidizedText = Text.translatable("tooltip.copperequipments.oxidized")
                .styled(style -> style.withColor(oxidizedColor));

        if (waxed || stage >= 0) {
            MutableText tooltipText = Text.empty();

            if (isWaxed(stack)) {
                tooltipText.append(waxedText).append(" ");
            }

            switch (getOxidationStage(stack)) {
                case 1 -> tooltipText.append(exposedText);
                case 2 -> tooltipText.append(weatheredText);
                case 3 -> tooltipText.append(oxidizedText);
                case 0 -> tooltipText.append(unexposedText);
            }

            tooltip.add(tooltipText);
        }
    }
}

