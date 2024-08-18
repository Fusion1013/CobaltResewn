package se.fusion1013.gui;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;
import se.fusion1013.gui.widget.ToggleImageButton;
import se.fusion1013.items.misc.WalkieTalkieItem;
import se.fusion1013.networking.CobaltClientNetworking;
import se.fusion1013.networking.CobaltNetworkingConstants;
import se.fusion1013.networking.CobaltServerNetworking;
import se.fusion1013.networking.payload.UpdateWalkieTalkiePayloadC2S;

import static se.fusion1013.networking.CobaltNetworkingConstants.*;

@Environment(EnvType.CLIENT)
public class WalkieTalkieScreen extends Screen {

    private static WalkieTalkieScreen instance;

    private final int xSize = 195;
    private final int ySize = 76;

    private int guileft;
    private int guiTop;

    private final ItemStack stack;

    private ToggleImageButton mute;
    private ToggleImageButton activate;
    private Text canal;

    private static final Identifier BG_TEXTURE = Identifier.of(Main.MOD_NAMESPACE, "textures/gui/gui_walkietalkie.png");
    private static final Identifier MUTE_TEXTURE = Identifier.of("voicechat", "textures/icons/microphone_button.png");
    private static final Identifier ACTIVATE_TEXTURE = Identifier.of(Main.MOD_NAMESPACE, "textures/icons/activate.png");

    public WalkieTalkieScreen(ItemStack stack) {
        super(Text.translatable("gui.cobalt.walkietalkie.title"));
        instance = this;
        this.stack = stack;

        MinecraftClient.getInstance().setScreen(this);
    }

    @Override
    protected void init() {
        super.init();

        this.guileft = (this.width - xSize) / 2;
        this.guiTop = (this.height - ySize) / 2;

        mute = new ToggleImageButton(guileft + 6, guiTop + ySize - 6 - 20, MUTE_TEXTURE, button -> sendUpdateWalkieTalkie(2, false), WalkieTalkieItem.isMute(stack));
        this.addDrawableChild(mute);

        activate = new ToggleImageButton(guileft + 28, guiTop + ySize - 26, ACTIVATE_TEXTURE, button -> sendUpdateWalkieTalkie(0, false), WalkieTalkieItem.isActivate(stack));
        this.addDrawableChild(activate);

        this.addDrawableChild(ButtonWidget.builder(Text.literal(">"), button -> sendUpdateWalkieTalkie(1, true)).dimensions(this.width / 2 - 10 + 40, guiTop + 20, 20, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("<"), button -> sendUpdateWalkieTalkie(1, false)).dimensions(this.width / 2 - 10 - 40, guiTop + 20, 20, 20).build());

        canal = Text.literal(String.valueOf(WalkieTalkieItem.getCanal(stack)));
    }

    private void sendUpdateWalkieTalkie(int index, boolean status) {
        CobaltClientNetworking.sendUpdateWalkieTalkie(index, status);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        super.renderBackground(context, mouseX, mouseY, delta);
        context.drawTexture(BG_TEXTURE, guileft, guiTop, 0, 0, xSize, ySize);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawCenteredText(context, this.textRenderer, this.title, this.width / 2, guiTop + 7, 4210752);
        drawCenteredText(context, this.textRenderer, this.canal, this.width / 2, guiTop + 26, 4210752);
    }

    protected void drawCenteredText(DrawContext context, TextRenderer textRenderer, Text text, int centerX, int y, int color) {
        context.drawText(textRenderer, text, centerX - textRenderer.getWidth(text) / 2, y, color, false);
    }

    public void updateButtons(ItemStack stack) {
        mute.setState(WalkieTalkieItem.isMute(stack));
        activate.setState(WalkieTalkieItem.isActivate(stack));
        canal = Text.literal(String.valueOf(WalkieTalkieItem.getCanal(stack)));
    }

    public static WalkieTalkieScreen getInstance() {
        return instance;
    }
}
