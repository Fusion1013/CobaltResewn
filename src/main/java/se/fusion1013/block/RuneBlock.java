package se.fusion1013.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.block.entity.CobaltBlockEntityTypes;
import se.fusion1013.block.entity.MultifaceGrowthBlockWithEntity;
import se.fusion1013.block.entity.PedestalBlockEntity;
import se.fusion1013.block.entity.RuneBlockEntity;

public class RuneBlock extends MultifaceGrowthBlockWithEntity implements BlockEntityProvider {

    public static final MapCodec<RuneBlock> CODEC = createCodec(RuneBlock::new);

    public static final EnumProperty<RuneType> RUNE_TYPE = EnumProperty.of("rune_type", RuneType.class);
    public static final BooleanProperty VISIBLE = BooleanProperty.of("visible");

    public RuneBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(RUNE_TYPE, RuneType.Latin_A).with(VISIBLE, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.isCreative()) return ActionResult.FAIL;

        player.playSound(SoundEvents.BLOCK_BAMBOO_WOOD_BUTTON_CLICK_ON, 1, 1);

        // Get the current rune type
        var runeType = state.get(RUNE_TYPE);
        boolean selectNext = false;

        // Store the previous and next runes
        var previousRuneType = RuneType.Dungeons_Z;
        var newRuneType = RuneType.None;

        // Iterate over all rune types and find the current one.
        // Store the previous and next values.
        for (RuneType type : RuneType.values()) {
            if (selectNext) {
                newRuneType = type;
                break;
            }
            if (type == runeType) selectNext = true;
            else previousRuneType = type;
        }

        // Switch the rune state depending on if the player is sneaking or not
        // TODO: Currently does not work. Make a different interface for switching rune types
        if (player.isSneaking()) world.setBlockState(pos, state.with(RUNE_TYPE, previousRuneType));
        else world.setBlockState(pos, state.with(RUNE_TYPE, newRuneType));

        return ActionResult.SUCCESS;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        // Display particles if the rune is visible
        if (!state.get(VISIBLE)) return;
        if (!world.isClient) return;

        world.addParticle(ParticleTypes.REVERSE_PORTAL, pos.getX() + random.nextFloat(), pos.getY() + random.nextFloat(), pos.getZ() + random.nextFloat(), 0, 0, 0);
    }

    @Override
    protected MapCodec<? extends MultifaceGrowthBlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RuneBlockEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(RUNE_TYPE);
        builder.add(VISIBLE);
    }

    @Override
    public LichenGrower getGrower() {
        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, CobaltBlockEntityTypes.RUNE_BLOCK, world.isClient ? RuneBlockEntity::clientTick : RuneBlockEntity::serverTick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public enum RuneType implements StringIdentifiable {
        None,

        // Latin
        Latin_A, Latin_B, Latin_C, Latin_D, Latin_E, Latin_F, Latin_G, Latin_H,
        Latin_I, Latin_J, Latin_K, Latin_L, Latin_M, Latin_N, Latin_O, Latin_P,
        Latin_Q, Latin_R, Latin_S, Latin_T, Latin_U, Latin_V, Latin_W, Latin_X,
        Latin_Y, Latin_Z,

        // Shapes
        Line_Horizontal, Line_Vertical, Line_L_NE, Line_L_NW, Line_L_SE, Line_L_SW,

        // Dungeons
        Dungeons_A, Dungeons_B, Dungeons_C, Dungeons_D, Dungeons_E, Dungeons_F, Dungeons_G, Dungeons_H,
        Dungeons_I, Dungeons_J, Dungeons_K, Dungeons_L, Dungeons_M, Dungeons_N, Dungeons_O, Dungeons_P,
        Dungeons_Q, Dungeons_R, Dungeons_S, Dungeons_T, Dungeons_U, Dungeons_V, Dungeons_W, Dungeons_X,
        Dungeons_Y, Dungeons_Z

        ;

        @Override
        public String asString() {
            return this.toString().toLowerCase();
        }
    }
}
