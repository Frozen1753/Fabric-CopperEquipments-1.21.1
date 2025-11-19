package net.frozen1753.copperequipments.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;

public class ModFunctions {
    public static int getEnchantmentLevel(ItemStack stack, RegistryKey<Enchantment> key) {
        // Get the enchantments component from the stack
        ItemEnchantmentsComponent comp =
                stack.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);

        // Iterate through the RegistryEntries present
        for (RegistryEntry<Enchantment> entry : comp.getEnchantments()) {
            if (entry.matchesKey(key)) {
                // Ask the component for the level of this entry
                return comp.getLevel(entry);
            }
        }
        return 0;
    }
}
