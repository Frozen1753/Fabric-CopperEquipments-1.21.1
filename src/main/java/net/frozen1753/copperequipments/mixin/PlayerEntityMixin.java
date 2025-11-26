package net.frozen1753.copperequipments.mixin;

import net.frozen1753.copperequipments.util.accessor.*;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.EnumMap;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements ActionFlagHolder {
    private final EnumMap<ActionType, Boolean> flags = new EnumMap<>(ActionType.class);

    @Override
    public boolean hasPlayedFlag(ActionType type) {
        return flags.getOrDefault(type, false);
    }

    @Override
    public void setPlayedFlag(ActionType type, boolean value) {
        flags.put(type, value);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void resetFlags(CallbackInfo ci) {
        for (ActionType type : ActionType.values()) {
            flags.put(type, false);
        }
    }
}