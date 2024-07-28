package se.fusion1013.mixin.client.render;

import com.google.common.collect.Lists;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import se.fusion1013.effect.CobaltEffects;
import se.fusion1013.render.effect.EnvironmentFogModifier;
import se.fusion1013.render.effect.FogModifier;

import java.util.List;

@Mixin(targets = {"net.minecraft.client.render.BackgroundRenderer"})
public class BackgroundRendererMixin {

    @Shadow @Final private static final List<BackgroundRenderer.StatusEffectFogModifier> FOG_MODIFIERS = Lists.newArrayList(
            new EnvironmentFogModifier(),
            new FogModifier(CobaltEffects.LIGHT_FOG, 80, 160),
            new FogModifier(CobaltEffects.MEDIUM_FOG, 50, 100),
            new FogModifier(CobaltEffects.HEAVY_FOG, 10, 50),
            new BackgroundRenderer.DarknessFogModifier(),
            new BackgroundRenderer.BlindnessFogModifier()
    );

}
