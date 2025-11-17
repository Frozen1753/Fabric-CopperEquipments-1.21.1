package net.frozen1753.copperequipments.mixin;

import net.frozen1753.copperequipments.item.custom.CopperItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(
            method = "damage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;setDamage(I)V",
                    shift = At.Shift.AFTER
            )
    )
    private void onDamage(int amount, ServerWorld world, @Nullable ServerPlayerEntity player, Consumer<Item> breakCallback, CallbackInfo ci) {
        ItemStack self = (ItemStack)(Object)this;

        if (self.getItem() instanceof CopperItem copper) {
            if (!CopperItem.isWaxed(self)) {
                CopperItem.updateOxidationStage(self, world, world.getRandom());
            }
        }
    }
}

