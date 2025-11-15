package net.frozen1753.copperequipments.mixin;

import net.frozen1753.copperequipments.util.accessor.ScrapeFlagHolder;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements ScrapeFlagHolder {
    private boolean playedScrapeThisTick = false;

    public boolean hasPlayedScrapeThisTick() {
        return playedScrapeThisTick;
    }

    public void setPlayedScrapeThisTick(boolean value) {
        this.playedScrapeThisTick = value;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void resetScrapeFlag(CallbackInfo ci) {
        ((PlayerEntityMixin)(Object)this).setPlayedScrapeThisTick(false);
    }
}

