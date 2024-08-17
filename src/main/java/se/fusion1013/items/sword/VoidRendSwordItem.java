package se.fusion1013.items.sword;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import se.fusion1013.items.CobaltItemConfiguration;

public class VoidRendSwordItem extends CobaltSwordItem {

    public VoidRendSwordItem() {
        super(ToolMaterials.STONE, -2+7, -4+2.3f, CobaltItemConfiguration.create(Formatting.DARK_PURPLE), new FabricItemSettings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // sculkFangAbility(world, user);
        lunge(world, user);

        return super.use(world, user, hand);
    }

    // --- ABILITIES

    private void lunge(World world, PlayerEntity user) {
        Vec3d lookDirection = user.getRotationVec(1.0f);
        user.addVelocity(lookDirection.multiply(user.isSneaking() ? -1.5 : 1.25));
    }

    private void sculkFangAbility(World world, PlayerEntity user) {
        float g;
        int delay = 0;

        /*
        int ringDivisions = 1;

        for (int ring = 0; ring < 8; ring++) {
            int spawnCount = (ring + 1) * 8;

            for (int i = 0; i < spawnCount; i++) {
                float dist = ring + ((float) i / spawnCount);
                g = i * (float) (2 * Math.PI) / (float)spawnCount;
                spawnFang(world, user, user.getX() + MathHelper.cos(g) * dist, user.getZ() + MathHelper.sin(g) * dist, user.getY() - 4, user.getY(), delay + (i % (spawnCount / ringDivisions)));
            }

            delay += (spawnCount / ringDivisions);
        }
         */

        /*
        for (int i = 0; i < 128; i++) {
            float dist = 1 + (i / 8f);
            g = i * (float) Math.PI / 4f;
            spawnFang(world, user, user.getX() + MathHelper.cos(g) * dist, user.getZ() + MathHelper.sin(g) * dist, user.getY() - 4, user.getY(), i * 2);
        }

         */

        /*
        for (int i = 0; i < 5; ++i) {
            g = i * (float) Math.PI * 0.4f;
            spawnFang(world, user, user.getX() + MathHelper.cos(g) * 1.5, user.getZ() + MathHelper.sin(g) * 1.5, user.getY() - 4, user.getY(), 0);
        }
        for (int i = 0; i < 8; ++i) {
            g = i * (float) Math.PI * 2 / 8f + 1.2566371f;
            spawnFang(world, user, user.getX() + MathHelper.cos(g) * 2.5, user.getZ() + MathHelper.sin(g) * 2.5, user.getY() - 4, user.getY(), 3);
        }
         */
    }

    private void spawnFang(World world, PlayerEntity user, double fangX, double fangZ, double maxY, double currentY, int warmup) {
        BlockPos targetPos = BlockPos.ofFloored(fangX, currentY, fangZ);
        boolean foundGround = false;
        double groundOffset = 0.0;

        // Loop to find the ground level by checking blocks below the initial position
        do {
            BlockState blockStateBelow;
            VoxelShape collisionShape;

            // Move one block down
            BlockPos posBelow = targetPos.down();

            // Get the block state of the block below the current position
            BlockState currentBlockState = world.getBlockState(posBelow);

            // Check if the block below is solid and can support the fangs on top
            if (!currentBlockState.isSideSolidFullSquare(world, posBelow, Direction.UP)) continue;

            // Check if the current position is not air and has a non-empty collision shape
            if (!world.isAir(targetPos) && !(collisionShape = (blockStateBelow = world.getBlockState(targetPos)).getCollisionShape(world, targetPos)).isEmpty()) {
                groundOffset = collisionShape.getMax(Direction.Axis.Y);
            }

            // Ground found, exit loop
            foundGround = true;
            break;
        } while ((targetPos = targetPos.down()).getY() >= MathHelper.floor(maxY) - 1);

        // If ground was found, spawn fangs entity and emit game event
        if (foundGround) {
            world.spawnEntity(new EvokerFangsEntity(
                    world,
                    fangX, (double) targetPos.getY() + groundOffset, fangZ,
                    0,
                    warmup,
                    user
            ));

            world.emitGameEvent(
                    GameEvent.ENTITY_PLACE,
                    new Vec3d(fangX, (double)targetPos.getY() + groundOffset, fangZ),
                    GameEvent.Emitter.of(user)
            );
        }
    }
}
