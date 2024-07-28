package se.fusion1013.render.effect;

import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.MathHelper;
import se.fusion1013.effect.CobaltEffects;

public class EnvironmentFogModifier implements BackgroundRenderer.StatusEffectFogModifier {
    @Override
    public StatusEffect getStatusEffect() {
        return CobaltEffects.ENVIRONMENT_EFFECT;
    }

    @Override
    public void applyStartEndModifier(BackgroundRenderer.FogData fogData, LivingEntity entity, StatusEffectInstance effect, float viewDistance, float tickDelta) {
        if (effect.getFactorCalculationData().isEmpty()) return;

        if (EnvironmentEffectValues.ResetTimer) {
            effect.getFactorCalculationData().get().factorCurrent = 0;
            effect.getFactorCalculationData().get().factorStart = 0;
            effect.getFactorCalculationData().get().factorPreviousFrame = 0;
            effect.getFactorCalculationData().get().effectChangedTimestamp = 0;
            effect.getFactorCalculationData().get().paddingDuration = EnvironmentEffectValues.PaddingDuration;
            EnvironmentEffectValues.ResetTimer = false;
        }

        var delta = effect.getFactorCalculationData().get().lerp(entity, tickDelta);

        float start = MathHelper.lerp(delta, EnvironmentEffectValues.StartLerpFrom, EnvironmentEffectValues.FogStart);
        float end = MathHelper.lerp(delta, EnvironmentEffectValues.EndLerpFrom, EnvironmentEffectValues.FogEnd);
        fogData.fogStart = start;
        fogData.fogEnd = end;
    }

    @Override
    public float applyColorModifier(LivingEntity entity, StatusEffectInstance effect, float f, float tickDelta) {
        var delta = effect.getFactorCalculationData().get().lerp(entity, tickDelta);
        return MathHelper.lerp(delta, EnvironmentEffectValues.ColorLerpFrom, EnvironmentEffectValues.Color);
    }
}
