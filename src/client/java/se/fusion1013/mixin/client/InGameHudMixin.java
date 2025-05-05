package se.fusion1013.mixin.client;

import dev.emi.trinkets.api.TrinketsApi;
import io.wispforest.owo.mixin.DrawContextMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.joml.Matrix4f;
import org.joml.Vector2i;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import se.fusion1013.items.CobaltItems;
import se.fusion1013.items.components.CobaltComponents;
import se.fusion1013.items.misc.WalkieTalkieItem;
import se.fusion1013.util.FacilityStatus;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Shadow @Final private MinecraftClient client;
    private static final float POWER_MAX = 1296f;
    private static final float PRESSURE_MAX = 12000f;

    @Inject(method = "render", at = @At("RETURN"), cancellable = true)
    public void onRender(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        Vector2i position = new Vector2i(5, 5);
        renderMechanicSpectaclesOverlay(context, position);
        renderWalkieTalkieOverlay(context, position);
    }

    @Unique
    private void renderWalkieTalkieOverlay(DrawContext context, Vector2i position) {
        if (this.client.player == null) return;

        ItemStack mainHandStack = this.client.player.getMainHandStack();
        ItemStack offHandStack = this.client.player.getOffHandStack();

        if (mainHandStack.getItem() == CobaltItems.WALKIE_TALKIE) renderWalkieTalkieOverlay(context, mainHandStack, position);
        if (offHandStack.getItem() == CobaltItems.WALKIE_TALKIE) renderWalkieTalkieOverlay(context, offHandStack, position);
    }

    @Unique
    private void renderWalkieTalkieOverlay(DrawContext context, ItemStack itemStack, Vector2i position) {
        int canal = WalkieTalkieItem.getCanal(itemStack);
        boolean isActivate = WalkieTalkieItem.isActivate(itemStack);
        boolean isMute = WalkieTalkieItem.isMute(itemStack);

        MinecraftClient.getInstance().textRenderer.draw("Speaking on Canal: " + canal, position.x, position.y, 0xFFFFFFFF, true, new Matrix4f(), context.getVertexConsumers(), TextRenderer.TextLayerType.NORMAL, 0x000000, 255);
        position.y += 15;

        if (isMute) {
            MinecraftClient.getInstance().textRenderer.draw("Muted", position.x, position.y, 0xFFFF0000, true, new Matrix4f(), context.getVertexConsumers(), TextRenderer.TextLayerType.NORMAL, 0x000000, 255);
            position.y += 15;
        }
    }

    @Unique
    private void renderMechanicSpectaclesOverlay(DrawContext context, Vector2i position) {
        var possibleTrinketComponent = TrinketsApi.getTrinketComponent(MinecraftClient.getInstance().player);
        if (possibleTrinketComponent.isEmpty()) return;

        var trinketComponent = possibleTrinketComponent.get();
        if (!trinketComponent.isEquipped(CobaltItems.MECHANIC_SPECTACLES)) return;

        var powerPercent = Math.round(FacilityStatus.POWER_CURRENT / POWER_MAX * 100);
        MinecraftClient.getInstance().textRenderer.draw("Power: " + powerPercent + "%", position.x, position.y, getColor(powerPercent), true, new Matrix4f(), context.getVertexConsumers(), TextRenderer.TextLayerType.NORMAL, 0x00000000, 255);

        position.y += 15;

        var pressurePercent = Math.round(FacilityStatus.PRESSURE_CURRENT / PRESSURE_MAX * 100);
        MinecraftClient.getInstance().textRenderer.draw("Pressure: " + pressurePercent + "%", position.x, position.y, getColor(100 - pressurePercent), true, new Matrix4f(), context.getVertexConsumers(), TextRenderer.TextLayerType.NORMAL, 0x00000000, 255);

        position.y += 15;
    }

    @Unique
    private int getColor(float value) {
        if (value < 20) return 0xFFFF0000;
        if (value < 50) return 0xFFFFFF00;
        return 0xFF00FF00;
    }

}
