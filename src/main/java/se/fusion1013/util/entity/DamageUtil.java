package se.fusion1013.util.entity;

public class DamageUtil {
    private static final boolean REDUCE_LINEAR = true;

    public static float getDamageLeft(float damage, float armor, float armorToughness) {
        if (REDUCE_LINEAR) return Math.max(1, damage - armor);
        else return net.minecraft.entity.DamageUtil.getDamageLeft(damage, armor, armorToughness);
    }
}
