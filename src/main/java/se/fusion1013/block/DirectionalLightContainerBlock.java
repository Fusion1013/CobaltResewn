package se.fusion1013.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import se.fusion1013.block.entity.DirectionalLightHolderBlockEntity;
import se.fusion1013.items.CobaltItems;
import se.fusion1013.sounds.CobaltSoundEvents;

import java.awt.*;

public class DirectionalLightContainerBlock extends AbstractLightContainerBlock {

    public static final MapCodec<DirectionalLightContainerBlock> CODEC = createCodec(DirectionalLightContainerBlock::new);

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final EnumProperty<LensType> LENS_TYPE = EnumProperty.of("lens_type", LensType.class);

    private static final int MAX_DISTANCE = 16;

    public DirectionalLightContainerBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(LightContainerBlock.LIT, false).with(FACING, Direction.NORTH).with(LENS_TYPE, LensType.NONE));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING, LENS_TYPE);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);

        if (!state.get(LIT)) return;
        if (state.get(LENS_TYPE) == LensType.NONE) return;

        Vec3d centerPos = pos.toCenterPos();

        // Display laser particles if it has a lens
        for (int i = 0; i < MAX_DISTANCE; i++) {
            Vec3d newPos = centerPos.offset(state.get(FACING), i);
            LensType lensType = state.get(LENS_TYPE);
            Color color = lensType.color;
            world.addParticle(new DustParticleEffect(new Vector3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f), 1.0f), newPos.getX(), pos.getY() + (1/16f)*6, newPos.getZ(), 0, 0, 0);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (hand == Hand.OFF_HAND) return super.onUse(state, world, pos, player, hand, hit);
        if (player.isSneaking()) return super.onUse(state, world, pos, player, hand, hit);

        // Try to insert a lens
        ItemStack heldItem = player.getStackInHand(hand);
        for (LensType lensType : LensType.values()) {
            if (lensType == LensType.NONE) continue;

            if (heldItem.getItem() == lensType.item) {
                insertLens(lensType, world, pos, state, player, hand);
                return ActionResult.SUCCESS;
            }
        }

        // If hand is empty & block has lens, lens
        if (heldItem.isEmpty() && state.get(LENS_TYPE) != LensType.NONE) {
            Item oldLensItem = state.get(LENS_TYPE).item;

            world.setBlockState(pos, state.with(LENS_TYPE, LensType.NONE), DirectionalLightContainerBlock.NOTIFY_LISTENERS);
            player.setStackInHand(hand, oldLensItem.getDefaultStack());

            if (!world.isClient) {
                world.playSound(null, pos, CobaltSoundEvents.LIGHT_HOLDER_LENS_REMOVE, SoundCategory.BLOCKS, 1, 1);
            }

            return ActionResult.SUCCESS;
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    private void insertLens(LensType lensType, World world, BlockPos pos, BlockState state, PlayerEntity player, Hand hand) {
        if (state.get(LENS_TYPE) == lensType) return; // Do not insert the same lens

        player.getStackInHand(hand).setCount(0);

        // If there is already a lens in the block, add it to player inventory
        if (state.get(LENS_TYPE) != LensType.NONE) {
            Item oldLensItem = state.get(LENS_TYPE).item;
            player.setStackInHand(hand, oldLensItem.getDefaultStack());
        }

        world.setBlockState(pos, state.with(LENS_TYPE, lensType), DirectionalLightContainerBlock.NOTIFY_LISTENERS);

        if (!world.isClient) {
            world.playSound(null, pos, CobaltSoundEvents.LIGHT_HOLDER_LENS_INSERT, SoundCategory.BLOCKS, 1, 1);
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(LightContainerBlock.LIT, false);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DirectionalLightHolderBlockEntity(pos, state);
    }

    public enum LensType implements StringIdentifiable {
        NONE("none", null, Color.WHITE),
        CLEAR("clear", CobaltItems.LENS, Color.CYAN),
        RED("red", CobaltItems.RED_LENS, new Color(230, 44, 44)),
        GREEN("green", CobaltItems.GREEN_LENS, new Color(44, 219, 61)),
        BLUE("blue", CobaltItems.BLUE_LENS, new Color(59, 123, 227));

        public final String id;
        public final Item item;
        public final Color color;

        LensType(String id, Item item, Color color) {
            this.id = id;
            this.item = item;
            this.color = color;
        }

        @Override
        public String asString() {
            return id;
        }
    }
}
