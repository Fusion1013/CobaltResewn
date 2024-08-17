package se.fusion1013.render.effect;

import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.MathHelper;

public class FogModifier implements BackgroundRenderer.StatusEffectFogModifier {

    private final StatusEffect effect;
    private final float start;
    private final float end;

    public FogModifier(StatusEffect effect, float start, float end) {
        this.effect = effect;
        this.start = start;
        this.end = end;
    }

    @Override
    public StatusEffect getStatusEffect() {
        return effect;
    }

    @Override
    public void applyStartEndModifier(BackgroundRenderer.FogData fogData, LivingEntity entity, StatusEffectInstance effect, float viewDistance, float tickDelta) {
        if (effect.getFactorCalculationData().isEmpty()) return;

        var delta = effect.getFactorCalculationData().get().lerp(entity, tickDelta);

        float start = MathHelper.lerp(delta, viewDistance, this.start);
        float end = MathHelper.lerp(delta, viewDistance, this.end);
        fogData.fogStart = start;
        fogData.fogEnd = end;
    }

    @Override
    public float applyColorModifier(LivingEntity entity, StatusEffectInstance effect, float f, float tickDelta) {
        return 1;
    }
}
