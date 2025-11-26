package net.frozen1753.copperequipments.mixin;

import net.frozen1753.copperequipments.config.CopperEquipmentsConfigs;
import net.frozen1753.copperequipments.item.custom.CopperItem;
import net.frozen1753.copperequipments.util.ModDataComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Inject(method = "inventoryTick", at = @At("TAIL"))
    private void onInventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (world.isClient) return;
        if (!(stack.getItem() instanceof CopperItem)) return;
        if (CopperItem.isWaxed(stack)) return;

        if ((world.getTime() % CopperEquipmentsConfigs.oxidationIntervalAttempt) != 0) return;
        CopperItem.updateOxidationStage(stack, world, world.getRandom());

        if (!stack.contains(ModDataComponents.CREATION_TIME)) {
            CopperItem.setCreationTime(stack, world);
        }
    }

    @Inject(method = "onCraftByPlayer", at = @At("TAIL"))
    private void onCopperItemCrafted(ItemStack stack, World world, PlayerEntity player, CallbackInfo ci) {
        if (world.isClient) return;
        if (stack.getItem() instanceof CopperItem) {
            if (!stack.contains(ModDataComponents.CREATION_TIME)) {
                CopperItem.setCreationTime(stack, world);
            }
        }
    }
}

