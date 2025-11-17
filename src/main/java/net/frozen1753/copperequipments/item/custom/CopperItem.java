package net.frozen1753.copperequipments.item.custom;

import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.config.ModConfigs;
import net.frozen1753.copperequipments.util.ModDataComponents;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

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

        System.out.println("[CopperEquipments] DURABILITY_ONLY | damage=" + damage + "/" + max + " | ratio=" + ratio + " | stage=" + stage);
    }

    static long getCreationTime(ItemStack stack, World world) {
        return stack.getOrDefault(ModDataComponents.CREATION_TIME, world.getTime());
    }

    static void setCreationTime(ItemStack stack, World world) {
        stack.set(ModDataComponents.CREATION_TIME, world.getTime());
        System.out.println("[CopperEquipments] Creation time set at tick " + world.getTime());
    }

    static long getAgeTicks(ItemStack stack, World world) {
        long creation = getCreationTime(stack, world);
        return world.getTime() - creation;
    }

    static void setAgeTicks(ItemStack stack, World world, long ageTicks) {
        long creationTime = world.getTime() - ageTicks;
        stack.set(ModDataComponents.CREATION_TIME, creationTime);

        System.out.println("[CopperEquipments] setAgeTicks called | ageTicks=" + ageTicks
                + " | worldTime=" + world.getTime()
                + " | creationTime=" + creationTime);
    }

    static void setAgeTicksForStage(ItemStack stack, World world) {
        int stage = getOxidationStage(stack);
        long maxLifespan = (long) CopperEquipments.CONFIG.oxidation.maxLifespanTick;

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

        System.out.println("[CopperEquipments] TIME_ONLY | ageTicks=" + ageTicks + "/" + maxLifespanTicks + " | pct=" + pct + " | stage=" + stage);
        return stage;
    }

    static void updateOxidationStage(ItemStack stack, World world, Random random) {
        if (isWaxed(stack)) {
            System.out.println("[CopperEquipments] Item was waxed");
            return;
        }

        var method = CopperEquipments.CONFIG.oxidation.itemOxidationMethod;
        int currentStage = getOxidationStage(stack);

        if (method == ModConfigs.OxidationSettings.ItemOxidationMethod.NONE) {
            setOxidationStage(stack, 0);
            System.out.println("[CopperEquipments] NONE | stage forced to 0");
            return;
        }

        long ageTicks = getAgeTicks(stack, world);

        switch (method) {
            case DURABILITY_ONLY -> {
                updateStageFromDamage(stack);
            }
            case TIME_ONLY -> {
                int stage = stageFromTime(ageTicks, (long) CopperEquipments.CONFIG.oxidation.maxLifespanTick);
                setOxidationStage(stack, stage);
            }
            case DURABILITY_AND_TIME -> {
                double durabilityPct = stack.isDamageable()
                        ? 1.0 - (stack.getDamage() / (double) stack.getMaxDamage())
                        : 1.0;

                double Lmax = CopperEquipments.CONFIG.oxidation.maxLifespanTick;
                double timePct = Math.min(1.0, ageTicks / Lmax);

                double alpha = CopperEquipments.CONFIG.oxidation.alphaWeight;
                double beta = CopperEquipments.CONFIG.oxidation.betaWeight;

                double p = alpha * (1.0 - durabilityPct) + beta * timePct;
                p = Math.max(0.0, Math.min(1.0, p));

                double roll = random.nextDouble();
                System.out.println("[CopperEquipments] BOTH | ageTicks=" + ageTicks + "/" + Lmax
                        + " | timePct=" + timePct
                        + " | durabilityPct=" + durabilityPct
                        + " | alpha=" + alpha + " | beta=" + beta
                        + " | probability=" + p + " | roll=" + roll
                        + " | currentStage=" + currentStage);

                if (currentStage < 3 && roll < p) {
                    setOxidationStage(stack, currentStage + 1);
                    System.out.println("[CopperEquipments] BOTH | Stage progressed to " + (currentStage + 1));
                }
            }
        }
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

