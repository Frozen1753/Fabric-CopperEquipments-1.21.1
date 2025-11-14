package net.frozen1753.copperequipments.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.MathHelper;

public class ForcedOxidationParticle extends AbstractSlowingParticle {
    protected ForcedOxidationParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
        super(clientWorld, d, e, f, g, h, i);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void move(double dx, double dy, double dz) {
        this.setBoundingBox(this.getBoundingBox().offset(dx, dy, dz));
        this.repositionFromBoundingBox();
    }

    @Override
    public void tick() {
        super.tick();
        this.velocityX *= 0.8;
        this.velocityY *= 0.8;
        this.velocityZ *= 0.8;
    }

    @Override
    public float getSize(float tickDelta) {
        float f = (this.age + tickDelta) / this.maxAge;
        return this.scale * (1.0F - f * f * 0.5F);
    }

    @Override
    public int getBrightness(float tint) {
        float f = (this.age + tint) / this.maxAge;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        int i = super.getBrightness(tint);
        int j = i & 0xFF;
        int k = i >> 16 & 0xFF;
        j += (int)(f * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            ForcedOxidationParticle oxidationParticle = new ForcedOxidationParticle(clientWorld, d, e, f, g, h, i);
            oxidationParticle.setSprite(this.spriteProvider);

            oxidationParticle.setColor(0.6F, 0.2F, 0.1F); // dark coppery red
            oxidationParticle.setAlpha(0.8F);


            // Smaller initial velocity so it doesn’t shoot off
            oxidationParticle.velocityX *= 0.2;
            oxidationParticle.velocityZ *= 0.2;
            oxidationParticle.velocityY *= 0.2;

            return oxidationParticle;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class SmallFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public SmallFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            ForcedOxidationParticle oxidationParticle = new ForcedOxidationParticle(clientWorld, d, e, f, g, h, i);
            oxidationParticle.setSprite(this.spriteProvider);
            oxidationParticle.scale(0.5F);

            oxidationParticle.setColor(0.6F, 0.2F, 0.1F); // dark coppery red
            oxidationParticle.setAlpha(0.8F);

            // Smaller initial velocity so it doesn’t shoot off
            oxidationParticle.velocityX *= 0.2;
            oxidationParticle.velocityZ *= 0.2;
            oxidationParticle.velocityY *= 0.2;

            return oxidationParticle;
        }
    }
}
