package se.fusion1013.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import org.joml.Vector3i;
import se.fusion1013.block.entity.ForgeBlockEntity;
import se.fusion1013.items.CobaltItems;

public class ForgeBlock extends BlockWithEntity implements BlockEntityProvider {

    private static final MapCodec<ForgeBlock> CODEC = createCodec(ForgeBlock::new);

    // Pedestal offsets
    private static final Vec3i PEDESTAL_0_OFFSET = new Vec3i(3, -1, 3);
    private static final Vec3i PEDESTAL_1_OFFSET = new Vec3i(-3, -1, 3);
    private static final Vec3i PEDESTAL_2_OFFSET = new Vec3i(3, -1, -3);
    private static final Vec3i PEDESTAL_3_OFFSET = new Vec3i(-3, -1, -3);


    // TODO: Add a CRAFTING blockstate


    public ForgeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Check if player is holding forge hammer
        ItemStack mainHandStack = player.getMainHandStack();
        if (mainHandStack.getItem() != CobaltItems.SwordItems.FORGE_HAMMER) return ActionResult.FAIL;

        // Summon lightning
        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightning.setPosition(pos.toCenterPos());
        world.spawnEntity(lightning);

        // Play sounds
        player.playSound(SoundEvents.ITEM_TRIDENT_THUNDER, SoundCategory.BLOCKS, 1, 1);

        // Throw nearby players back and up in the air
        // TODO

        return ActionResult.SUCCESS;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);

        if (!hasPedestals(world, pos)) return;

        // Display particles above pedestals
        spawnPedestalParticles(world, pos, PEDESTAL_0_OFFSET);
        spawnPedestalParticles(world, pos, PEDESTAL_1_OFFSET);
        spawnPedestalParticles(world, pos, PEDESTAL_2_OFFSET);
        spawnPedestalParticles(world, pos, PEDESTAL_3_OFFSET);
    }

    private void spawnPedestalParticles(World world, BlockPos pos, Vec3i offset) {
        Vec3d particlePos = pos.add(offset).add(0, 1, 0).toCenterPos();
        world.addParticle(ParticleTypes.END_ROD, particlePos.getX(), particlePos.getY(), particlePos.getZ(), 0, 0, 0);
    }

    private boolean hasPedestals(World world, BlockPos pos) {
        if (world.getBlockState(pos.add(PEDESTAL_0_OFFSET)).getBlock() != CobaltBlocks.PEDESTAL_BLOCK) return false;
        if (world.getBlockState(pos.add(PEDESTAL_1_OFFSET)).getBlock() != CobaltBlocks.PEDESTAL_BLOCK) return false;
        if (world.getBlockState(pos.add(PEDESTAL_2_OFFSET)).getBlock() != CobaltBlocks.PEDESTAL_BLOCK) return false;
        if (world.getBlockState(pos.add(PEDESTAL_3_OFFSET)).getBlock() != CobaltBlocks.PEDESTAL_BLOCK) return false;

        return true;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ForgeBlockEntity(pos, state);
    }
}
