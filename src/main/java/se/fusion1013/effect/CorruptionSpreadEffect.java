package se.fusion1013.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.Vec3i;
import se.fusion1013.block.CobaltBlocks;

/**
 * Spreads corruption at the {@link LivingEntity}s location.
 */
public class CorruptionSpreadEffect extends StatusEffect {
    public CorruptionSpreadEffect() {
        super(StatusEffectCategory.NEUTRAL, 0xdb05fa);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        var world = entity.getWorld();
        var pos = entity.getBlockPos();
        var posBelow = pos.subtract(new Vec3i(0, 1, 0));
        var blockState = world.getBlockState(posBelow);

        // Only place the block if the block below the entity is a solid block
        if (blockState.getBlock() == CobaltBlocks.SCULK_SPREADER) return false;
        if (!blockState.isSolid() || !blockState.isOpaque()) return false;

        // Place a sculk spreader block underneath the entity
        world.setBlockState(posBelow, CobaltBlocks.SCULK_SPREADER.getDefaultState());
        return true;
    }
}
