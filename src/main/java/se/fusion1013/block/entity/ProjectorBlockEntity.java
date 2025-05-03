package se.fusion1013.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.SingleStackInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.Main;
import se.fusion1013.block.ProjectorBlock;
import se.fusion1013.registries.CobaltRegistryKeys;
import se.fusion1013.tags.CobaltTags;

public class ProjectorBlockEntity extends BlockEntity implements SingleStackInventory {

    private ItemStack slideReelStack;

    public ProjectorBlockEntity(BlockPos pos, BlockState state) {
        super(CobaltBlockEntityTypes.PROJECTOR_BLOCK_ENTITY, pos, state);
        slideReelStack = ItemStack.EMPTY;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("SlideReelItem", 10)) {
            slideReelStack = ItemStack.fromNbt(nbt.getCompound("SlideReelItem"));
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        if (!slideReelStack.isEmpty()) {
            nbt.put("SlideReelItem", slideReelStack.writeNbt(new NbtCompound()));
        }
        super.writeNbt(nbt);
    }

    private void updateState(@Nullable Entity entity, boolean hasSlideReel) {
        if (world.getBlockState(getPos()) == getCachedState()) {
            world.setBlockState(getPos(), getCachedState().with(ProjectorBlock.HAS_SLIDE_REEL, hasSlideReel), 2);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, getPos(), GameEvent.Emitter.of(entity, getCachedState()));
        }
    }

    @Override
    public ItemStack getStack() {
        return slideReelStack;
    }

    @Override
    public ItemStack decreaseStack(int count) {
        ItemStack itemStack = slideReelStack;
        slideReelStack = ItemStack.EMPTY;
        if (!itemStack.isEmpty()) {
            updateState(null, false);
        }
        return itemStack;
    }

    @Override
    public void setStack(ItemStack stack) {
        if (stack.isIn(CobaltTags.IS_SLIDE_REEL) && world != null) {
            slideReelStack = stack;
            updateState(null, true);
        } else if (stack.isEmpty()) {
            decreaseStack(1);
        }
    }

    @Override
    public BlockEntity asBlockEntity() {
        return this;
    }

    public void dropSlideReel() {
        if (world == null || world.isClient) return;
        if (slideReelStack.isEmpty()) return;

        BlockPos blockPos = getPos();
        ItemStack stack = slideReelStack;
        emptyStack();
        Vec3d vec3d = Vec3d.add(blockPos, 0.5, 1.1, 0.5).addRandom(world.random, 0.01f);
        ItemStack stackCopy = stack.copy();
        ItemEntity itemEntity = new ItemEntity(world, vec3d.getX(), vec3d.getY(), vec3d.getZ(), stackCopy, 0, 0, 0);
        itemEntity.setToDefaultPickupDelay();
        world.spawnEntity(itemEntity);
    }
}
