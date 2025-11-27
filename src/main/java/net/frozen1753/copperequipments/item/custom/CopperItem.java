package net.frozen1753.copperequipments.item.custom;

import net.frozen1753.copperequipments.config.CopperEquipmentsConfigs;
import net.frozen1753.copperequipments.util.ModDataComponents;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public interface CopperItem {
    static boolean isWaxed(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.WAXED, false);
    }

    static void setWaxed(ItemStack stack, boolean waxed) {
        stack.set(ModDataComponents.WAXED, waxed);
    }

    static int getOxidationStage(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.OXIDATION_STAGE, 0);
    }

    static void setOxidationStage(ItemStack stack, int stage) {
        stack.set(ModDataComponents.OXIDATION_STAGE, stage);
    }

    static void updateStageFromDamage(ItemStack stack) {
        int damage = stack.getDamage();
        int max = stack.getMaxDamage();
        float ratio = (float) damage / max;

        int stage = Math.min(3, (int) (ratio * 4));
        setOxidationStage(stack, stage);
    }

    static long getCreationTime(ItemStack stack, World world) {
        return stack.getOrDefault(ModDataComponents.CREATION_TIME, world.getTime());
    }

    static void setCreationTime(ItemStack stack, World world) {
        stack.set(ModDataComponents.CREATION_TIME, world.getTime());
    }

    static long getAgeTicks(ItemStack stack, World world) {
        long creation = getCreationTime(stack, world);
        return world.getTime() - creation;
    }

    static void setAgeTicks(ItemStack stack, World world, long ageTicks) {
        long creationTime = world.getTime() - ageTicks;
        stack.set(ModDataComponents.CREATION_TIME, creationTime);
    }

    static void setAgeTicksForStage(ItemStack stack, World world) {
        int stage = getOxidationStage(stack);
        long maxLifespan = (long) CopperEquipmentsConfigs.maxLifespanTick;

        long ageForStage;
        switch (stage) {
            case 0 -> ageForStage = 0;
            case 1 -> ageForStage = (long)(0.25 * maxLifespan);
            case 2 -> ageForStage = (long)(0.50 * maxLifespan);
            case 3 -> ageForStage = (long)(0.75 * maxLifespan);
            default -> ageForStage = 0;
        }

        setAgeTicks(stack, world, ageForStage);
    }

    static int stageFromTime(long ageTicks, long maxLifespanTicks) {
        double pct = Math.min(1.0, (double) ageTicks / maxLifespanTicks);
        int stage;
        if (pct < 0.25) stage = 0;
        else if (pct < 0.50) stage = 1;
        else if (pct < 0.75) stage = 2;
        else stage = 3;

        return stage;
    }

    static void updateOxidationStage(ItemStack stack, World world, Random random) {
        if (isWaxed(stack)) {
            return;
        }

        int currentStage = getOxidationStage(stack);
        if (currentStage > 2) {
            return;
        }

        var method = CopperEquipmentsConfigs.itemOxidationMethod;
        if (method == CopperEquipmentsConfigs.ItemOxidationMethod.NONE) {
            return;
        }

        long ageTicks = getAgeTicks(stack, world);

        switch (method) {
            case DURABILITY_ONLY -> updateStageFromDamage(stack);
            case TIME_ONLY -> {
                int stage = stageFromTime(ageTicks, (long) CopperEquipmentsConfigs.maxLifespanTick);
                setOxidationStage(stack, stage);
            }
            case DURABILITY_AND_TIME -> {
                double durabilityPct = stack.isDamageable()
                        ? 1.0 - (stack.getDamage() / (double) stack.getMaxDamage())
                        : 1.0;

                double Lmax = CopperEquipmentsConfigs.maxLifespanTick;
                double timePct = Math.min(1.0, ageTicks / Lmax);

                double alpha = CopperEquipmentsConfigs.alphaWeight;
                double beta = CopperEquipmentsConfigs.betaWeight;

                double threshold = (1 + currentStage) / 4.0;
                double durabilityFactor = 1.0 / (1 + Math.exp(-50 * ((1 - durabilityPct) - threshold)));

                double p = alpha * durabilityFactor + beta * timePct;
                p = Math.max(0.0, Math.min(1.0, p));

                double roll = random.nextDouble();

                if (roll < p) {
                    setOxidationStage(stack, currentStage + 1);
                }
            }
        }
    }

    static void appendCopperTooltip(ItemStack stack, List<Text> tooltip) {
        boolean waxed = isWaxed(stack);
        int stage = getOxidationStage(stack);

        CopperEquipmentsConfigs.TooltipMode mode = CopperEquipmentsConfigs.tooltipMode;

        // if mode = NO_TOOLTIP
        if (mode == CopperEquipmentsConfigs.TooltipMode.NO_TOOLTIP) {
            return;
        }

        // colors
        TextColor waxedColor = TextColor.fromRgb(0xFFC30B);
        TextColor unexposedColor = TextColor.fromRgb(0xD66D48);
        TextColor exposedColor = TextColor.fromRgb(0xAA7D57);
        TextColor weatheredColor = TextColor.fromRgb(0x7E8E67);
        TextColor oxidizedColor = TextColor.fromRgb(0x529F77);

        // texts
        Text waxedText = Text.translatable("tooltip.copperequipments.waxed")
                .styled(style -> style.withColor(waxedColor));
        Text oxidationText = switch (stage) {
            case 0 -> Text.translatable("tooltip.copperequipments.unexposed").styled(s -> s.withColor(unexposedColor));
            case 1 -> Text.translatable("tooltip.copperequipments.exposed").styled(s -> s.withColor(exposedColor));
            case 2 -> Text.translatable("tooltip.copperequipments.weathered").styled(s -> s.withColor(weatheredColor));
            case 3 -> Text.translatable("tooltip.copperequipments.oxidized").styled(s -> s.withColor(oxidizedColor));
            default -> null;
        };

        // build list of components to display depending on mode
        List<Text> components = new ArrayList<>();
        switch (mode) {
            case WAXED_ONLY -> {
                if (waxed) components.add(waxedText);
            }
            case OXIDATION_ONLY -> {
                if (oxidationText != null) components.add(oxidationText);
            }
            case WAXED_AND_OXIDATION -> {
                if (waxed) components.add(waxedText);
                if (oxidationText != null) components.add(oxidationText);
            }
            case OXIDATION_AND_WAXED -> {
                if (oxidationText != null) components.add(oxidationText);
                if (waxed) components.add(waxedText);
            }
        }

        boolean lines = CopperEquipmentsConfigs.tooltipTwoLines;

        // display depending on lines setting
        if (!lines && components.size() > 1) {
            // merge into one line
            MutableText merged = Text.empty();
            for (int i = 0; i < components.size(); i++) {
                merged.append(components.get(i));
                if (i < components.size() - 1) merged.append(" ");
            }
            tooltip.add(merged);
        } else {
            // each component on its own line
            tooltip.addAll(components);
        }
    }
}

