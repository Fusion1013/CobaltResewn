package se.fusion1013.mixin.item;

import com.google.common.collect.Multimap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.struct.InjectorGroupInfo;

import java.util.List;
import java.util.Map;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot);

    @Inject(method = "getTooltip", at = @At(value = "HEAD", shift = At.Shift.BY, by = 5), locals = LocalCapture.CAPTURE_FAILHARD)
    public void getTooltip(@Nullable PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> cir, List<Text> list) {
        // appendModifiers(player, context, cir, list);
    }

    @Unique
    private void appendModifiers(@Nullable PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> cir, List<Text> list) {
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = getAttributeModifiers(EquipmentSlot.MAINHAND);
        if (modifiers.isEmpty()) return;

        double attackDamage = 0;
        double attackSpeed = 0;

        for (Map.Entry<EntityAttribute, EntityAttributeModifier> entry : modifiers.entries()) {
            EntityAttributeModifier entityAttributeModifier = entry.getValue();
            double value = entityAttributeModifier.getValue();
            if (player == null) continue;

            if (entry.getKey() == EntityAttributes.GENERIC_ATTACK_DAMAGE) {
                value += player.getAttributeBaseValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                value += EnchantmentHelper.getAttackDamage((ItemStack) (Object) this, EntityGroup.DEFAULT);
                attackDamage += value;
            }

            if (entry.getKey() == EntityAttributes.GENERIC_ATTACK_SPEED) {
                value += player.getAttributeBaseValue(EntityAttributes.GENERIC_ATTACK_SPEED);
                attackSpeed += value;
            }
        }

        list.add(Text.translatable("item.cobalt.damage_prefix").formatted(Formatting.GRAY).append(Text.literal("+" + Math.round(attackDamage * 10) / 10f).formatted(Formatting.RED)));
        list.add(Text.translatable("item.cobalt.attack_speed_prefix").formatted(Formatting.GRAY).append(Text.literal("+" + Math.round(attackSpeed * 10) / 10f).formatted(Formatting.RED)));
    }
    
}
