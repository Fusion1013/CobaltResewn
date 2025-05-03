package se.fusion1013.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.Main;

import java.util.ArrayList;
import java.util.List;

public class DisplayBlockEntity extends BlockEntity {

    public static final String NBT_KEY_PICTURES = "pictures";
    public static final String NBT_KEY_PICTURE_ENTRY = "entry.";
    public static final String NBT_KEY_PICTURE_ID = "picture_id";

    public ArrayList<String> pictures = new ArrayList<>();
    public int pictureId;

    public DisplayBlockEntity(BlockPos pos, BlockState state) {
        super(CobaltBlockEntityTypes.DISPLAY_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        pictures.clear();
        NbtCompound picturesCompound = nbt.getCompound(NBT_KEY_PICTURES);
        int id = 0;
        while (true) {
            String nbtPath = NBT_KEY_PICTURE_ENTRY + id;
            if (!picturesCompound.contains(nbtPath)) break;
            String picturePath = picturesCompound.getString(nbtPath);
            pictures.add(picturePath);
            id++;
        }
        pictureId = nbt.getInt(NBT_KEY_PICTURE_ID);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        NbtCompound compound = new NbtCompound();
        for (int i = 0; i < pictures.size(); i++) {
            String nbtPath = NBT_KEY_PICTURE_ENTRY + i;
            compound.putString(nbtPath, pictures.get(i));
        }
        nbt.put(NBT_KEY_PICTURES, compound);
        nbt.putInt(NBT_KEY_PICTURE_ID, pictureId);
        super.writeNbt(nbt);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public void changeSlideParent(int amount) {
        BlockPos parentPos = findParentPos();
        DisplayBlockEntity parentEntity = (DisplayBlockEntity) world.getBlockEntity(parentPos);
        if (parentEntity != null) parentEntity.changeSlide(amount);
    }

    public void changeSlide(int amount) {
        pictureId+=amount;
        if (pictureId >= pictures.size()) pictureId = 0;
        if (pictureId < 0) pictureId = pictures.size()-1;
        markDirty();
        world.updateListeners(pos, getCachedState(), getCachedState(), 0);
    }

    public String getCurrentParentPicture() {
        BlockPos parentPos = findParentPos();
        DisplayBlockEntity parentEntity = (DisplayBlockEntity) world.getBlockEntity(parentPos);
        if (parentEntity == null) return null;
        return parentEntity.getCurrentPicture();
    }

    public String getCurrentPicture() {
        if (pictures.isEmpty()) return "";
        return pictures.get(pictureId);
    }

    public void setParentPictures(List<String> picturePaths) {
        BlockPos parentPos = findParentPos();
        DisplayBlockEntity parentEntity = (DisplayBlockEntity) world.getBlockEntity(parentPos);
        parentEntity.setPictures(picturePaths);
    }

    public void setPictures(List<String> picturePaths) {
        pictures.clear();
        pictures.addAll(picturePaths);
        pictureId = 0;
        markDirty();
        world.updateListeners(pos, getCachedState(), getCachedState(), 0);
    }

    public void clearPictures() {
        setParentPictures(new ArrayList<>());
    }

    public BlockPos findParentPos() {
        World world = getWorld();
        Direction startBlockDirection = Direction.WEST;

        Direction facing = this.getCachedState().get(Properties.HORIZONTAL_FACING);
        switch (facing) {
            case NORTH:
                startBlockDirection = Direction.EAST;
                break;
            case WEST:
                startBlockDirection = Direction.NORTH;
                break;
            case EAST:
                startBlockDirection = Direction.SOUTH;
                break;
            default:
                break;
        }

        BlockPos foundPos = pos;

        while (true) {
            boolean found = false;

            // Check width direction
            BlockPos checkWidthPos = foundPos.offset(startBlockDirection);
            BlockEntity foundWidthEntity = world.getBlockEntity(checkWidthPos);
            if (foundWidthEntity != null && foundWidthEntity.getType() == this.getType()) {
                foundPos = foundPos.offset(startBlockDirection);
                found = true;
            }

            // Check height direction
            BlockPos checkHeightPos = foundPos.offset(Direction.DOWN);
            BlockEntity foundHeightEntity = world.getBlockEntity(checkHeightPos);
            if (foundHeightEntity != null && foundWidthEntity.getType() == this.getType()) {
                foundPos = foundPos.offset(Direction.DOWN);
                found = true;
            }

            if (!found) break;
        }

        return foundPos;
    }

    /**
     * Determines if the Display is part of a larger group of display blocks.
     * @return DisplayDimensions containing the height and width of the continuous block group
     */
    public DisplayDimensions detectNearbyBlocks() {
        World world = this.getWorld();

        Boolean isMainBlock = true;
        Boolean isPowered = false;
        float width = 1.0F;
        float height = 1.0F;
        float curHeight = 1.0F;

        // default SOUTH
        Direction startBlockDirection = Direction.WEST;
        Direction widthBlockDirection = Direction.EAST;

        Direction facing = this.getCachedState().get(Properties.HORIZONTAL_FACING);
        switch (facing) {
            case NORTH:
                startBlockDirection = Direction.EAST;
                widthBlockDirection = Direction.WEST;
                break;
            case WEST:
                startBlockDirection = Direction.NORTH;
                widthBlockDirection = Direction.SOUTH;
                break;
            case EAST:
                startBlockDirection = Direction.SOUTH;
                widthBlockDirection = Direction.NORTH;
                break;
            case SOUTH:
                startBlockDirection = Direction.WEST;
                widthBlockDirection = Direction.EAST;
                break;
            default:
                break;
        }

        // Check if the block is redstone powered
        int power = world.getReceivedRedstonePower(pos);
        if (power > 0) {
            isPowered = true;
        }

        // Check if I am the start block, there should be nothing to the right
        BlockPos currentPos = this.getPos();
        BlockPos checkPos = currentPos.offset(startBlockDirection);
        BlockEntity foundBlock = world.getBlockEntity(checkPos);

        // if there is a block to the west return
        if (foundBlock != null && foundBlock.getType() == this.getType()) {
            isMainBlock = false;
        }

        checkPos = currentPos.offset(Direction.DOWN);
        foundBlock = world.getBlockEntity(checkPos);

        // if there is a block to the right return
        if (foundBlock != null && foundBlock.getType() == this.getType()) {
            isMainBlock = false;
        }

        if (!isMainBlock) {
            return new DisplayDimensions(0, 0, false, false);
        }

        BlockPos xPos = currentPos;

        // check the x
        while (true) {

            // check the y
            curHeight = 1;
            while (true) {
                checkPos = currentPos.offset(Direction.UP);
                foundBlock = world.getBlockEntity(checkPos);

                if (foundBlock == null || foundBlock.getType() != this.getType()) {
                    currentPos = xPos; // reset the x
                    break;
                }

                curHeight++;
                if (curHeight >= height) {
                    height = curHeight;
                }

                currentPos = foundBlock.getPos();

                power = world.getReceivedRedstonePower(checkPos);
                if (!isPowered && power > 0) {
                    isPowered = true;
                }
            }

            // check the east faces for connected block
            checkPos = currentPos.offset(widthBlockDirection);
            foundBlock = world.getBlockEntity(checkPos);

            if (foundBlock == null || foundBlock.getType() != this.getType()) {
                break; // no more blocks
            }

            width++;
            currentPos = foundBlock.getPos();
            xPos = currentPos;

            power = world.getReceivedRedstonePower(checkPos);
            if (!isPowered && power > 0) {
                isPowered = true;
            }
        }

        return new DisplayDimensions(width, height, isMainBlock, isPowered);
    }

    public class DisplayDimensions {
        public float width = 0.0f;
        public float height = 0.0f;
        public boolean mainBlock = false;
        public boolean isPowered = false;

        public DisplayDimensions(float width, float height, boolean mainBlock, boolean isPowered) {
            this.width = width;
            this.height = height;
            this.mainBlock = mainBlock;
            this.isPowered = isPowered;
        }
    }
}
