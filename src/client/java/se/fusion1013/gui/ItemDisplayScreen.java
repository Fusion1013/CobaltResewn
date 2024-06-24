package se.fusion1013.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.joml.Vector3f;
import se.fusion1013.gui.widget.VectorWidget;
import se.fusion1013.networking.payload.UpdateItemDisplayPayloadC2S;
import se.fusion1013.screen.ItemDisplayScreenHandler;

@Environment(EnvType.CLIENT)
public class ItemDisplayScreen extends HandledScreen<ItemDisplayScreenHandler> {

    private static final int GLOBAL_WIDTH_OFFSET = 150;

    // GUI Components
    private VectorWidget vectorWidgetOffset;
    private VectorWidget vectorWidgetOffsetFrequency;
    private VectorWidget vectorWidgetOffsetAmplitude;

    private VectorWidget vectorWidgetScale;
    private VectorWidget vectorWidgetScaleFrequency;
    private VectorWidget vectorWidgetScaleAmplitude;

    private VectorWidget vectorWidgetRotation;
    private VectorWidget vectorWidgetRotationSpeed;

    private ButtonWidget doneButton;
    private ButtonWidget updateButton;

    // Fields
    private BlockPos blockPos;

    private Vector3f offset;
    private Vector3f offsetFrequency;
    private Vector3f offsetAmplitude;

    private Vector3f scale;
    private Vector3f scaleFrequency;
    private Vector3f scaleAmplitude;

    private Vector3f rotation;
    private Vector3f rotationSpeed;

    public ItemDisplayScreen(ItemDisplayScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

        blockPos = handler.getBlockPos();

        offset = handler.getOffset();
        offsetFrequency = handler.getOffsetFrequency();
        offsetAmplitude = handler.getOffsetAmplitude();

        scale = handler.getScale();
        scaleFrequency = handler.getScaleFrequency();
        scaleAmplitude = handler.getScaleAmplitude();

        rotation = handler.getRotation();
        rotationSpeed = handler.getRotationSpeed();
    }

    @Override
    protected void init() {
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;

        int widthCenter = this.width / 2 - GLOBAL_WIDTH_OFFSET;
        int heightCenter = this.height / 2;

        // Create vector widgets
        this.vectorWidgetOffset = new VectorWidget(widthCenter - 75, heightCenter - 120, 130, 20, Text.translatable("vector_offset"), this.textRenderer, this.offset);
        this.vectorWidgetOffsetFrequency = new VectorWidget(widthCenter - 75, heightCenter - 100, 130, 20, Text.translatable("vector_offset_frequency"), this.textRenderer, this.offsetFrequency);
        this.vectorWidgetOffsetAmplitude = new VectorWidget(widthCenter - 75, heightCenter - 80, 130, 20, Text.translatable("vector_offset_amplitude"), this.textRenderer, this.offsetAmplitude);

        this.vectorWidgetScale = new VectorWidget(widthCenter - 75, heightCenter - 40, 130, 20, Text.translatable("vector_scale"), this.textRenderer, this.scale);
        this.vectorWidgetScaleFrequency = new VectorWidget(widthCenter - 75, heightCenter - 20, 130, 20, Text.translatable("vector_scale_frequency"), this.textRenderer, this.scaleFrequency);
        this.vectorWidgetScaleAmplitude = new VectorWidget(widthCenter - 75, heightCenter, 130, 20, Text.translatable("vector_scale_amplitude"), this.textRenderer, this.scaleAmplitude);

        this.vectorWidgetRotation = new VectorWidget(widthCenter - 75, heightCenter + 40, 130, 20, Text.translatable("vector_rotation"), this.textRenderer, this.rotation);
        this.vectorWidgetRotationSpeed = new VectorWidget(widthCenter - 75, heightCenter + 60, 130, 20, Text.translatable("vector_rotation_speed"), this.textRenderer, this.rotationSpeed);

        this.addDrawableChild(this.vectorWidgetOffset);
        this.addDrawableChild(this.vectorWidgetOffsetFrequency);
        this.addDrawableChild(this.vectorWidgetOffsetAmplitude);

        this.addDrawableChild(this.vectorWidgetScale);
        this.addDrawableChild(this.vectorWidgetScaleFrequency);
        this.addDrawableChild(this.vectorWidgetScaleAmplitude);

        this.addDrawableChild(this.vectorWidgetRotation);
        this.addDrawableChild(this.vectorWidgetRotationSpeed);

        this.doneButton = ButtonWidget.builder(Text.translatable("done"), button -> {
            this.offset = vectorWidgetOffset.getVector();
            this.offsetFrequency = vectorWidgetOffsetFrequency.getVector();
            this.offsetAmplitude = vectorWidgetOffsetAmplitude.getVector();

            this.scale = vectorWidgetScale.getVector();
            this.scaleFrequency = vectorWidgetScaleFrequency.getVector();
            this.scaleAmplitude = vectorWidgetScaleAmplitude.getVector();

            this.rotation = vectorWidgetRotation.getVector();
            this.rotationSpeed = vectorWidgetRotationSpeed.getVector();
            sendUpdateItemDisplayBlock(); // Send update to the block
            this.close();
        })
                .dimensions(widthCenter - 50, heightCenter + 100, 100, 20)
                .build();

        this.updateButton = ButtonWidget.builder(Text.translatable("update"), button -> {
                    this.offset = vectorWidgetOffset.getVector();
                    this.offsetFrequency = vectorWidgetOffsetFrequency.getVector();
                    this.offsetAmplitude = vectorWidgetOffsetAmplitude.getVector();

                    this.scale = vectorWidgetScale.getVector();
                    this.scaleFrequency = vectorWidgetScaleFrequency.getVector();
                    this.scaleAmplitude = vectorWidgetScaleAmplitude.getVector();

                    this.rotation = vectorWidgetRotation.getVector();
                    this.rotationSpeed = vectorWidgetRotationSpeed.getVector();
                    sendUpdateItemDisplayBlock(); // Send update to the block
                })
                .dimensions(widthCenter + 120, heightCenter + 100, 100, 20)
                .build();

        this.addDrawableChild(this.doneButton);
        this.addDrawableChild(this.updateButton);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);

        int widthCenter = this.width / 2 - GLOBAL_WIDTH_OFFSET;
        int heightCenter = this.height / 2 + 5;

        int xPos = widthCenter - 120;

        context.drawCenteredTextWithShadow(textRenderer, Text.literal("Offset"), xPos, heightCenter - 120, 0xffffff);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("Offset Frequency"), xPos, heightCenter - 100, 0xffffff);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("Offset Amplitude"), xPos, heightCenter - 80, 0xffffff);

        context.drawCenteredTextWithShadow(textRenderer, Text.literal("Scale"), xPos, heightCenter - 40, 0xffffff);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("Scale Frequency"), xPos, heightCenter - 20, 0xffffff);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("Scale Amplitude"), xPos, heightCenter, 0xffffff);

        context.drawCenteredTextWithShadow(textRenderer, Text.literal("Rotation"), xPos, heightCenter + 40, 0xffffff);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal("Rotation Speed"), xPos, heightCenter + 60, 0xffffff);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
        // super.renderBackground(context, mouseX, mouseY, delta);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        // TODO
    }

    private void sendUpdateItemDisplayBlock() {
        ClientPlayNetworking.send(new UpdateItemDisplayPayloadC2S(
                blockPos,
                offset,
                offsetFrequency,
                offsetAmplitude,
                scale,
                scaleFrequency,
                scaleAmplitude,
                rotation,
                rotationSpeed)
        );
    }
}
