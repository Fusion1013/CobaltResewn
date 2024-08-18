package se.fusion1013.util.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class DamageUtil {
    private static final boolean REDUCE_LINEAR = false;

    public static float getDamageLeft(LivingEntity armorWearer, float damage, DamageSource damageSource, float armor, float armorToughness) {
        if (REDUCE_LINEAR) return Math.max(1, damage - armor);
        else return net.minecraft.entity.DamageUtil.getDamageLeft(armorWearer, damage, damageSource, armor, armorToughness);
    }
}
