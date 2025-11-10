package net.frozen1753.copperequipments.item.custom;

import net.frozen1753.copperequipments.util.ModDataComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class CopperArmorItem extends ArmorItem {
    public CopperArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

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

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        String waxed = isWaxed(stack) ? "Waxed" : "";

        String wax_stage = switch (getOxidationStage(stack)) {
            case 0 -> "";
            case 1 -> "Exposed";
            case 2 -> "Weathered";
            default -> "Oxidized";
        };

        if (!(waxed + wax_stage).isBlank()) {
            tooltip.add(Text.literal(waxed + " " + wax_stage));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
