package se.fusion1013.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtFloat;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import se.fusion1013.Main;
import se.fusion1013.screen.ItemDisplayScreenHandler;

public class ItemDisplayBlockEntity extends CustomSingleStackInventoryBlockEntity implements ExtendedScreenHandlerFactory<ItemDisplayBlockEntity.ItemDisplayBlockEntityData> {

    // Offset
    public static final String NBT_KEY_OFFSET = "offset";
    public static final String NBT_KEY_OFFSET_FREQUENCY = "offset_frequency";
    public static final String NBT_KEY_OFFSET_AMPLITUDE = "offset_amplitude";

    public static final String NBT_KEY_SCALE = "scale";
    public static final String NBT_KEY_SCALE_FREQUENCY = "scale_frequency";
    public static final String NBT_KEY_SCALE_AMPLITUDE = "scale_amplitude";

    public static final String NBT_KEY_ROTATION = "rotation";
    public static final String NBT_KEY_ROTATION_SPEED = "rotation_speed";

    private Vector3f offset = new Vector3f();
    private Vector3f offsetFrequency = new Vector3f();
    private Vector3f offsetAmplitude = new Vector3f();

    private Vector3f scale = new Vector3f();
    private Vector3f scaleFrequency = new Vector3f();
    private Vector3f scaleAmplitude = new Vector3f();

    private Vector3f rotation = new Vector3f();
    private Vector3f rotationSpeed = new Vector3f();

    private static int tick;
    private static int lastTick;

    public ItemDisplayBlockEntity(BlockPos pos, BlockState state) {
        super(CobaltBlockEntityTypes.ITEM_DISPLAY_BLOCK_ENTITY, pos, state);
    }

    public static void clientTick(World world, BlockPos pos, BlockState blockState, ItemDisplayBlockEntity itemDisplayBlockEntity) {
        lastTick = tick;
        tick++;
    }

    public static void serverTick(World world, BlockPos pos, BlockState blockState, ItemDisplayBlockEntity itemDisplayBlockEntity) {

    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);

        offset = getVector(nbt, NBT_KEY_OFFSET);
        offsetAmplitude = getVector(nbt, NBT_KEY_OFFSET_AMPLITUDE);
        offsetFrequency = getVector(nbt, NBT_KEY_OFFSET_FREQUENCY);

        scale = getVector(nbt, NBT_KEY_SCALE);
        scaleAmplitude = getVector(nbt, NBT_KEY_SCALE_AMPLITUDE);
        scaleFrequency = getVector(nbt, NBT_KEY_SCALE_FREQUENCY);

        rotation = getVector(nbt, NBT_KEY_ROTATION);
        rotationSpeed = getVector(nbt, NBT_KEY_ROTATION_SPEED);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);

        putVector(offset, nbt, NBT_KEY_OFFSET);
        putVector(offsetAmplitude, nbt, NBT_KEY_OFFSET_AMPLITUDE);
        putVector(offsetFrequency, nbt, NBT_KEY_OFFSET_FREQUENCY);

        putVector(scale, nbt, NBT_KEY_SCALE);
        putVector(scaleAmplitude, nbt, NBT_KEY_SCALE_AMPLITUDE);
        putVector(scaleFrequency, nbt, NBT_KEY_SCALE_FREQUENCY);

        putVector(rotation, nbt, NBT_KEY_ROTATION);
        putVector(rotationSpeed, nbt, NBT_KEY_ROTATION_SPEED);
    }

    private static void putVector(Vector3f vec, NbtCompound nbt, String key) {
        NbtList list = new NbtList();

        list.add(0, NbtFloat.of(vec.x));
        list.add(1, NbtFloat.of(vec.y));
        list.add(2, NbtFloat.of(vec.z));

        nbt.put(key, list);
    }

    private static Vector3f getVector(NbtCompound nbt, String key) {
        var compound = nbt.getList(key, NbtList.FLOAT_TYPE);
        return new Vector3f(
            compound.getFloat(0),
            compound.getFloat(1),
            compound.getFloat(2)
        );
    }

    // These methods are from the NamedScreenHandlerFactory interface
    // createMenu creates the ScreenHandler itself
    // getDisplayName will provide its name which is normally shown at the top

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ItemDisplayScreenHandler(syncId, playerInventory);
    }

    // This method gets called on the server when it requests the client to open the screenHandler.
    // The contents you write into the packetByteBuf will automatically be transferred in a packet to the client
    // and the ScreenHandler constructor with the packetByteBuf argument gets called on the client.
    //
    // The order you insert things here is the same as you need to extract them. You do not need to reverse the order.
    @Override
    public ItemDisplayBlockEntityData getScreenOpeningData(ServerPlayerEntity player) {
        var data = new ItemDisplayBlockEntityData();

        data.xOffset = offset.x;
        data.yOffset = offset.y;
        data.zOffset = offset.z;

        data.xOffsetFrequency = offsetFrequency.x;
        data.yOffsetFrequency = offsetFrequency.y;
        data.zOffsetFrequency = offsetFrequency.z;

        data.xOffsetAmplitude = offsetAmplitude.x;
        data.yOffsetAmplitude = offsetAmplitude.y;
        data.zOffsetAmplitude = offsetAmplitude.z;

        data.xScale = scale.x;
        data.yScale = scale.y;
        data.zScale = scale.z;

        data.xScaleFrequency = scaleFrequency.x;
        data.yScaleFrequency = scaleFrequency.y;
        data.zScaleFrequency = scaleFrequency.z;

        data.xScaleAmplitude = scaleAmplitude.x;
        data.yScaleAmplitude = scaleAmplitude.y;
        data.zScaleAmplitude = scaleAmplitude.z;

        data.xRotation = rotation.x;
        data.yRotation = rotation.y;
        data.zRotation = rotation.z;

        data.xRotationSpeed = rotationSpeed.x;
        data.yRotationSpeed = rotationSpeed.y;
        data.zRotationSpeed = rotationSpeed.z;

        return data;
    }

    public Vector3f getOffset() {
        return offset;
    }

    public Vector3f getOffsetFrequency() {
        return offsetFrequency;
    }

    public Vector3f getOffsetAmplitude() {
        return offsetAmplitude;
    }

    public Vector3f getScale() {
        return scale;
    }

    public Vector3f getScaleFrequency() {
        return scaleFrequency;
    }

    public Vector3f getScaleAmplitude() {
        return scaleAmplitude;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getRotationSpeed() {
        return rotationSpeed;
    }

    public void setOffset(Vector3f offset) {
        this.offset = offset;
    }

    public void setOffsetFrequency(Vector3f offsetFrequency) {
        this.offsetFrequency = offsetFrequency;
    }

    public void setOffsetAmplitude(Vector3f offsetAmplitude) {
        this.offsetAmplitude = offsetAmplitude;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public void setScaleFrequency(Vector3f scaleFrequency) {
        this.scaleFrequency = scaleFrequency;
    }

    public void setScaleAmplitude(Vector3f scaleAmplitude) {
        this.scaleAmplitude = scaleAmplitude;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public void setRotationSpeed(Vector3f rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public static int getTick() {
        return tick;
    }

    public static int getLastTick() {
        return lastTick;
    }

    public static class ItemDisplayBlockEntityData {
        public float xOffset;
        public float yOffset;
        public float zOffset;

        public float xOffsetFrequency;
        public float yOffsetFrequency;
        public float zOffsetFrequency;

        public float xOffsetAmplitude;
        public float yOffsetAmplitude;
        public float zOffsetAmplitude;

        public float xScale;
        public float yScale;
        public float zScale;

        public float xScaleFrequency;
        public float yScaleFrequency;
        public float zScaleFrequency;

        public float xScaleAmplitude;
        public float yScaleAmplitude;
        public float zScaleAmplitude;

        public float xRotation;
        public float yRotation;
        public float zRotation;

        public float xRotationSpeed;
        public float yRotationSpeed;
        public float zRotationSpeed;
    }
}
