package net.frozen1753.copperequipments.mixin;

import net.frozen1753.copperequipments.util.accessor.ForcedOxidationFlagHolder;
import net.frozen1753.copperequipments.util.accessor.ScrapeFlagHolder;
import net.frozen1753.copperequipments.util.accessor.UnwaxFlagHolder;
import net.frozen1753.copperequipments.util.accessor.WaxFlagHolder;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements ScrapeFlagHolder, UnwaxFlagHolder, WaxFlagHolder, ForcedOxidationFlagHolder {
    private boolean playedScrapeThisTick = false;
    private boolean playedUnwaxThisTick = false;
    private boolean playedWaxThisTick = false;
    private boolean playedForcedOxidationThisTick = false;

    @Override
    public boolean hasPlayedScrapeThisTick() {
        return playedScrapeThisTick;
    }

    @Override
    public void setPlayedScrapeThisTick(boolean value) {
        this.playedScrapeThisTick = value;
    }

    @Override
    public boolean hasPlayedUnwaxThisTick() {
        return playedUnwaxThisTick;
    }

    @Override
    public void setPlayedUnwaxThisTick(boolean value) {
        this.playedUnwaxThisTick = value;
    }

    @Override
    public boolean hasPlayedForcedOxidationThisTick() {
        return playedForcedOxidationThisTick;
    }

    @Override
    public void setPlayedForcedOxidationThisTick(boolean value) {
        this.playedForcedOxidationThisTick = value;
    }

    @Override
    public boolean hasPlayedWaxThisTick() {
        return playedWaxThisTick;
    }

    @Override
    public void setPlayedWaxThisTick(boolean value) {
        this.playedWaxThisTick = value;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void resetFlags(CallbackInfo ci) {
        this.playedScrapeThisTick = false;
        this.playedUnwaxThisTick = false;
        this.playedForcedOxidationThisTick = false;
        this.playedWaxThisTick = false;
    }
}

