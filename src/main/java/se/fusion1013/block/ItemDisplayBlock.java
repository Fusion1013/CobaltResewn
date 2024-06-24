package se.fusion1013.block;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.Main;
import se.fusion1013.block.entity.CobaltBlockEntityTypes;
import se.fusion1013.block.entity.ItemDisplayBlockEntity;
import se.fusion1013.networking.CobaltNetworkingConstants;

public class ItemDisplayBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final MapCodec<ItemDisplayBlock> CODEC = createCodec(ItemDisplayBlock::new);

    public ItemDisplayBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;

        Inventory blockEntity = (Inventory) world.getBlockEntity(blockPos);

        if (!player.getMainHandStack().isEmpty()) {
            // Player is holding something, set the item in the display block
            blockEntity.setStack(0, player.getMainHandStack().copy());
            blockEntity.markDirty();
            world.updateListeners(blockPos, blockState, world.getBlockState(blockPos), Block.NOTIFY_LISTENERS);

            // Play item insert sound and display particles
            world.playSound(null, blockPos, SoundEvents.BLOCK_DECORATED_POT_INSERT, SoundCategory.BLOCKS, 1.0f, 0.7f);
            if (world instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(ParticleTypes.DUST_PLUME, blockPos.getX() + .5f, blockPos.getY() + .9f, blockPos.getZ() + .5f, 7, 0, 0, 0, 0);
            }
        } else {
            // Player is not holding anything, try to open the GUI

            // This will call the createScreenHandlerFactory method from BlockWithEntity, which will return our blockEntity casted to
            // a namedScreenHandlerFactory. If your block class does not extend BlockWithEntity, it needs to implement createScreenHandlerFactory.
            NamedScreenHandlerFactory screenHandlerFactory = blockState.createScreenHandlerFactory(world, blockPos);

            if (screenHandlerFactory != null) {
                // With this call the server will request the client to open the appropriate ScreenHandler
                player.openHandledScreen(screenHandlerFactory);
            }

            // TODO: Remove
            // var buffer = PacketByteBufs.create();
            // buffer.writeBlockPos(blockPos);
            // ServerPlayNetworking.send((ServerPlayerEntity) player, CobaltNetworkingConstants.OPEN_ITEM_DISPLAY_SCREEN_S2C, buffer);
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, CobaltBlockEntityTypes.ITEM_DISPLAY_BLOCK_ENTITY, world.isClient ? ItemDisplayBlockEntity::clientTick : ItemDisplayBlockEntity::serverTick);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ItemDisplayBlockEntity(pos, state);
    }
}
