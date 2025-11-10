package net.frozen1753.copperequipments.util.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;

public interface ItemDurabilityChangeCallback {
    Event<ItemDurabilityChangeCallback> EVENT = EventFactory.createArrayBacked(ItemDurabilityChangeCallback.class,
            (listeners) -> (stack, amount, world, player) -> {
                for (ItemDurabilityChangeCallback listener : listeners) {
                    listener.onDurabilityChanged(stack, amount, world, player);
                }
            }
    );

    void onDurabilityChanged(ItemStack stack, int amount, ServerWorld world, @Nullable ServerPlayerEntity player);
}

