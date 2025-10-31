package se.fusion1013.block.dream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import se.fusion1013.sounds.CobaltSoundEvents;

import java.util.List;

public class DreamLightExtinguishBlock extends Block {

    public DreamLightExtinguishBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.isEmpty()) {
            List<BlockEntity> list = AbstractDreamLightBlockEntity.getDreamLightBlockEntitiesInRange(new Vec3d(pos.getX(), pos.getY(), pos.getZ()), 120);
            boolean foundBlockToExtinguish = false;
            for (BlockEntity entity : list) {
                BlockState otherState = world.getBlockState(entity.getPos());
                if (otherState == null) continue;
                if (!otherState.contains(AbstractDreamLightBlock.LIT)) continue;

                if (entity instanceof AbstractDreamLightBlockEntity dreamLightBlockEntity) {
                    boolean extinguished = dreamLightBlockEntity.extinguish();
                    foundBlockToExtinguish = extinguished || foundBlockToExtinguish;
                }
            }

            if (foundBlockToExtinguish) world.playSound(null, pos, CobaltSoundEvents.DREAM_LIGHT_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 0.9f + world.random.nextFloat() * 0.2f);

            return ItemActionResult.CONSUME;
        }

        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }
}
