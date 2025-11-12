package net.frozen1753.copperequipments.material;

import net.frozen1753.copperequipments.CopperEquipments;
import net.frozen1753.copperequipments.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    COPPER(ModTags.Blocks.INCORRECT_FOR_COPPER_TOOL, 197, 5.0F, 1.5F, 16, () -> Ingredient.ofItems(Items.COPPER_INGOT));

    private final TagKey<Block> inverseTag;
    private final int durability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(TagKey<Block> inverseTag, int durability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.inverseTag = inverseTag;
        this.durability = durability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override public int getDurability() { return durability; }
    @Override public float getMiningSpeedMultiplier() { return miningSpeed; }
    @Override public float getAttackDamage() { return attackDamage; }
    @Override public int getEnchantability() { return enchantability; }
    @Override public Ingredient getRepairIngredient() { return repairIngredient.get(); }
    @Override public TagKey<Block> getInverseTag() { return inverseTag; }

    public static void registerModToolMaterials() {
        CopperEquipments.LOGGER.info("Registering Tool Material for " + CopperEquipments.MOD_ID);
    }
}
