package se.fusion1013.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.Main;
import se.fusion1013.block.entity.DisplayBlockEntity;
import se.fusion1013.block.entity.ProjectorBlockEntity;
import se.fusion1013.items.misc.SlideReelItem;
import se.fusion1013.registries.CobaltRegistryKeys;
import se.fusion1013.slidereel.SlideReel;
import se.fusion1013.tags.CobaltTags;

public class ProjectorBlock extends BlockWithEntity {

    public static final MapCodec<ProjectorBlock> CODEC = createCodec(ProjectorBlock::new);
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final IntProperty DISTANCE_TO_SCREEN = IntProperty.of("distance_to_screen", 0, 20);
    public static final BooleanProperty HAS_SLIDE_REEL = BooleanProperty.of("has_slide_reel");

    protected ProjectorBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(HAS_SLIDE_REEL, false));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);

        // Find the screen
        Direction direction = state.get(FACING);
        for (int i = 0; i < 20; i++) {
            BlockPos possibleScreenPosition = pos.offset(direction, i);
            BlockState possibleScreenState = world.getBlockState(possibleScreenPosition);
            if (possibleScreenState.getBlock() != CobaltBlocks.DISPLAY_BLOCK) continue;

            // Found a display block, set the target distance
            world.setBlockState(pos, state.with(DISTANCE_TO_SCREEN, i), 2);
            return;
        }

        // Could not find valid display, break the block
        if (placer.isPlayer()) {
            PlayerEntity player = (PlayerEntity) placer;
            world.breakBlock(pos, !player.isCreative());
        } else {
            world.breakBlock(pos, true);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(HAS_SLIDE_REEL)) {
            ProjectorBlockEntity entity = (ProjectorBlockEntity) world.getBlockEntity(pos);
            entity.dropSlideReel();
            DisplayBlockEntity displayBlockEntity = getDisplayBlockEntity(state, world, pos);
            displayBlockEntity.clearPictures();

            return ActionResult.success(world.isClient);
        }

        ItemStack heldItem = player.getStackInHand(hand);

        if (heldItem.isIn(CobaltTags.IS_SLIDE_REEL)) {
            ProjectorBlockEntity entity = (ProjectorBlockEntity) world.getBlockEntity(pos);
            entity.setStack(heldItem);
            ItemStack heldCopy = heldItem.copy();
            heldCopy.decrement(1);
            player.setStackInHand(hand, heldCopy);

            if (heldItem.getItem() instanceof SlideReelItem slideReelItem && !world.isClient) {
                final DynamicRegistryManager registryManager = world.getRegistryManager();

                Registry<SlideReel> slideReelRegistry = registryManager.get(CobaltRegistryKeys.SLIDE_REEL);
                var slideReel = slideReelRegistry.getEntry(slideReelItem.getSlideReel());

                DisplayBlockEntity displayBlockEntity = getDisplayBlockEntity(state, world, pos);
                if (slideReel.isPresent() && displayBlockEntity != null) {
                    displayBlockEntity.setParentPictures(slideReel.get().value().pictures);
                }
            }

            return ActionResult.success(world.isClient);
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {

        if (world instanceof ServerWorld serverWorld) {
            Direction facing = state.get(FACING);
            Direction right = facing.rotateYClockwise();
            Direction left = facing.rotateYCounterclockwise();

            if (world.getEmittedRedstonePower(pos.offset(left), left) > 0) previousSlide(state, serverWorld, pos);
            else if (world.getEmittedRedstonePower(pos.offset(right), right) > 0) nextSlide(state, serverWorld, pos);
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    private void nextSlide(BlockState state, World world, BlockPos pos) {
        DisplayBlockEntity entity = getDisplayBlockEntity(state, world, pos);
        if (entity == null) return;
        entity.changeSlideParent(1);
    }

    private void previousSlide(BlockState state, World world, BlockPos pos) {
        DisplayBlockEntity entity = getDisplayBlockEntity(state, world, pos);
        if (entity == null) return;
        entity.changeSlideParent(-1);
    }

    private DisplayBlockEntity getDisplayBlockEntity(BlockState state, World world, BlockPos pos) {
        Direction direction = state.get(FACING);
        int distance = state.get(DISTANCE_TO_SCREEN);

        BlockPos screenPosition = pos.offset(direction, distance);
        BlockState screenState = world.getBlockState(screenPosition);
        if (screenState.getBlock() != CobaltBlocks.DISPLAY_BLOCK) return null;

        return (DisplayBlockEntity) world.getBlockEntity(screenPosition);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(DISTANCE_TO_SCREEN);
        builder.add(HAS_SLIDE_REEL);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ProjectorBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
