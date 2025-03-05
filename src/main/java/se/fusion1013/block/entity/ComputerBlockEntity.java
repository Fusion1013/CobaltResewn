package se.fusion1013.block.entity;

import foundry.veil.api.client.color.Color;
import foundry.veil.api.client.color.ColorTheme;
import foundry.veil.api.client.tooltip.Tooltippable;
import foundry.veil.api.client.tooltip.VeilUIItemTooltipDataHolder;
import foundry.veil.api.client.tooltip.anim.TooltipTimeline;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.block.ComputerBlock;
import se.fusion1013.util.TextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComputerBlockEntity extends BlockEntity implements Tooltippable {

    public static final String NBT_KEY_TITLE = "title";
    public static final String NBT_KEY_TEXT = "text";
    public static final String NBT_KEY_PAGE = "page";

    private String title = "empty";
    private int page = 0;
    private List<String> textList = new ArrayList<>();

    private List<Text> tooltip = new ArrayList<>();

    private ColorTheme theme;

    public ComputerBlockEntity(BlockPos pos, BlockState state) {
        super(CobaltBlockEntityTypes.COMPUTER_BLOCK_ENTITY, pos, state);

        theme = new ColorTheme();
        theme.addColor("background", new Color(38, 38, 38, 255/2));
        theme.addColor("topBorder", new Color(9, 36, 0, 200));
        theme.addColor("bottomBorder", new Color(9, 36, 0, 200));

        reloadTooltip();
    }

    // --- NBT

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        // super.readNbt(nbt);

        title = nbt.getString(NBT_KEY_TITLE);
        page = nbt.getInt(NBT_KEY_PAGE);

        textList.clear();
        NbtList stringList = nbt.getList(NBT_KEY_TEXT, NbtElement.STRING_TYPE);
        for (int i = 0; i < stringList.size(); i++) {
            textList.add(stringList.getString(i));
        }

        reloadTooltip();
    }

    private void reloadTooltip() {
        tooltip.clear();
        tooltip.add(Text.literal(title).formatted(Formatting.GRAY, Formatting.BOLD));

        if (page >= textList.size()) page = 0;
        var textText = Text.literal("Empty");
        if (!textList.isEmpty()) textText = Text.literal(textList.get(page)).formatted(Formatting.GRAY);
        tooltip.addAll(TextUtil.splitText(textText, 30));

        if (textList.size() > 1) {
            int realPage = page+1;
            tooltip.add(Text.empty());
            tooltip.add(Text.literal("Page " + realPage + "/" + textList.size()).formatted(Formatting.GOLD));
        }
    }

    public void nextPage() {
        page++;
        if (page >= textList.size()) page = 0;
        reloadTooltip();
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        // super.writeNbt(nbt);

        nbt.putString(NBT_KEY_TITLE, title);
        nbt.putInt(NBT_KEY_PAGE, page);

        NbtList stringList = new NbtList();
        for (String s : textList) {
            stringList.add(NbtString.of(s));
        }
        nbt.put(NBT_KEY_TEXT, stringList);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    // --- TOOLTIPS

    @Override
    public List<Text> getTooltip() {
        return tooltip;
    }

    @Override
    public boolean isTooltipEnabled() {
        return getCachedState().get(ComputerBlock.ENABLED).booleanValue();
    }

    @Override
    public NbtCompound saveTooltipData() {
        NbtCompound compound = new NbtCompound();
        compound.putBoolean("tooltipEnabled", isTooltipEnabled());
        compound.putInt("tooltipX", pos.getX());
        compound.putInt("tooltipY", pos.getY());
        compound.putInt("tooltipWidth", getTooltipWidth());
        compound.putInt("tooltipHeight", getTooltipHeight());
        compound.putBoolean("worldspace", getWorldspace());

        if (theme != null) {
            NbtCompound themeTag = new NbtCompound();
            for (Map.Entry<String, Color> entry : theme.getColorsMap().entrySet()) {
                String key = entry.getKey() != null ? entry.getKey() : "";
                themeTag.putInt(key, entry.getValue().getRGB());
            }
            compound.put("theme", themeTag);
        }

        return compound;
    }

    @Override
    public void loadTooltipData(NbtCompound tag) {
        if (this.theme != null) {
            this.theme.clear();
        }
        if (tag.contains("theme", NbtCompound.COMPOUND_TYPE)) {
            if (this.theme == null) {
                this.theme = new ColorTheme();
            }

            NbtCompound themeTag = tag.getCompound("theme");
            for (String key : themeTag.getKeys()) {
                this.theme.addColor(key, Color.of(themeTag.getInt(key)));
            }
        }
    }

    @Override
    public void setTooltip(List<Text> tooltip) {
        this.tooltip = tooltip;
    }

    @Override
    public void addTooltip(Text tooltip) {
        this.tooltip.add(tooltip);
    }

    @Override
    public void addTooltip(List<Text> tooltip) {
        this.tooltip.addAll(tooltip);
    }

    @Override
    public void addTooltip(String tooltip) {
        this.tooltip.add(Text.literal(tooltip));
    }

    @Override
    public ColorTheme getTheme() {
        return theme;
    }

    @Override
    public void setTheme(ColorTheme theme) {
        this.theme = theme;
    }

    @Override
    public void setBackgroundColor(int color) {
        theme.addColor("background", Color.of(color));
    }

    @Override
    public void setTopBorderColor(int color) {
        theme.addColor("topBorder", Color.of(color));
    }

    @Override
    public void setBottomBorderColor(int color) {
        theme.addColor("bottomBorder", Color.of(color));
    }

    @Override
    public boolean getWorldspace() {
        return true;
    }

    @Override
    public TooltipTimeline getTimeline() {
        return null;
    }

    @Override
    public ItemStack getStack() {
        return ItemStack.EMPTY;
    }

    @Override
    public int getTooltipWidth() {
        return 0;
    }

    @Override
    public int getTooltipHeight() {
        return 0;
    }

    @Override
    public int getTooltipXOffset() {
        return 0;
    }

    @Override
    public int getTooltipYOffset() {
        return 0;
    }

    @Override
    public List<VeilUIItemTooltipDataHolder> getItems() {
        return new ArrayList<>();
    }

}
