package se.fusion1013.mixin.client;

import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import se.fusion1013.items.CobaltItems;
import se.fusion1013.util.FacilityStatus;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    private static final float POWER_MAX = 1296f;
    private static final float PRESSURE_MAX = 12000f;

    @Inject(method = "render", at = @At("RETURN"), cancellable = true)
    public void onRender(DrawContext context, float tickDelta, CallbackInfo ci) {

        var possibleTrinketComponent = TrinketsApi.getTrinketComponent(MinecraftClient.getInstance().player);
        if (possibleTrinketComponent.isEmpty()) return;

        var trinketComponent = possibleTrinketComponent.get();
        if (!trinketComponent.isEquipped(CobaltItems.MECHANIC_SPECTACLES)) return;

        var powerPercent = Math.round(FacilityStatus.POWER_CURRENT / POWER_MAX * 100);
        MinecraftClient.getInstance().textRenderer.draw("Power: " + powerPercent + "%", 5, 5, getColor(powerPercent), true, new Matrix4f(), context.getVertexConsumers(), TextRenderer.TextLayerType.NORMAL, 0x00000000, 255);

        var pressurePercent = Math.round(FacilityStatus.PRESSURE_CURRENT / PRESSURE_MAX * 100);
        MinecraftClient.getInstance().textRenderer.draw("Pressure: " + pressurePercent + "%", 5, 20, getColor(100 - pressurePercent), true, new Matrix4f(), context.getVertexConsumers(), TextRenderer.TextLayerType.NORMAL, 0x00000000, 255);
    }

    private int getColor(float value) {
        if (value < 20) return 0xFFFF0000;
        if (value < 50) return 0xFFFFFF00;
        return 0xFF00FF00;
    }

}
