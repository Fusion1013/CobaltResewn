package se.fusion1013.block.dream;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.block.Block.NOTIFY_ALL_AND_REDRAW;

public abstract class AbstractDreamLightBlockEntity extends BlockEntity {

    private static final List<AbstractDreamLightBlockEntity> DREAM_LIGHT_BLOCK_ENTITIES = new ArrayList<>();

    private boolean canExtinguish;

    public AbstractDreamLightBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, boolean canExtinguish) {
        super(type, pos, state);
        DREAM_LIGHT_BLOCK_ENTITIES.add(this);
        this.canExtinguish = canExtinguish;
    }

    public boolean extinguish() {
        if (!canExtinguish) return false;
        if (world == null) return false;
        world.setBlockState(getPos(), world.getBlockState(getPos()).with(AbstractDreamLightBlock.LIT, false), NOTIFY_ALL_AND_REDRAW);
        return true;
    }

    public static List<BlockEntity> getDreamLightBlockEntitiesInRange(Vec3d pos, int range) {
        DREAM_LIGHT_BLOCK_ENTITIES.removeIf(BlockEntity::isRemoved);
        List<BlockEntity> entities = new ArrayList<>();
        for (AbstractDreamLightBlockEntity entity : DREAM_LIGHT_BLOCK_ENTITIES) {
            if (!entity.hasWorld()) continue;
            if (!entity.getPos().isWithinDistance(pos, range)) continue;
            entities.add(entity);
        }
        return entities;
    }

}
