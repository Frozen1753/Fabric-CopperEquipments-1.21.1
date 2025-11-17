package net.frozen1753.copperequipments.mixin;

import net.frozen1753.copperequipments.util.accessor.ScrapeFlagHolder;
import net.frozen1753.copperequipments.util.accessor.UnwaxFlagHolder;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements ScrapeFlagHolder, UnwaxFlagHolder {
    private boolean playedScrapeThisTick = false;
    private boolean playedUnwaxThisTick = false;

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

    @Inject(method = "tick", at = @At("HEAD"))
    private void resetFlags(CallbackInfo ci) {
        this.playedScrapeThisTick = false;
        this.playedUnwaxThisTick = false;
    }
}

