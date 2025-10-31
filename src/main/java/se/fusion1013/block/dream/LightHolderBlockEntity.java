package se.fusion1013.block.dream;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import se.fusion1013.block.entity.CobaltBlockEntityTypes;

public class LightHolderBlockEntity extends AbstractDreamLightBlockEntity {

    private static int tick;
    private static int lastTick;

    public LightHolderBlockEntity(BlockPos pos, BlockState state) {
        super(CobaltBlockEntityTypes.LIGHT_HOLDER_BLOCK_ENTITY, pos, state, false);
    }

    public LightHolderBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state, false);
    }

    public static void clientTick(World world, BlockPos pos, BlockState blockState, LightHolderBlockEntity lightHolderBlockEntity) {
        lastTick = tick;
        tick++;
    }

    public static void serverTick(World world, BlockPos pos, BlockState blockState, LightHolderBlockEntity lightHolderBlockEntity) {
    }

    public static int getTick() {
        return tick;
    }

    public static int getLastTick() {
        return lastTick;
    }
}
