package se.fusion1013.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CobaltArrowItem extends ArrowItem {

    private final CobaltItem.Settings settings;
    private final CustomEntityFactory<? extends PersistentProjectileEntity> entityFactory;

    public CobaltArrowItem(CobaltItem.Settings settings, CustomEntityFactory<? extends PersistentProjectileEntity> factory) {
        super(settings);
        this.settings = settings;
        this.entityFactory = factory;
    }

    @Override
    public Text getName(ItemStack stack) {
        return settings.applyNameFormatting(super.getName(stack));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        settings.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
        return entityFactory.create(shooter.getX(), shooter.getEyeY() - 0.10000000149011612D, shooter.getZ(), world, stack);
    }

    public CustomEntityFactory<? extends PersistentProjectileEntity> getEntityFactory() {
        return entityFactory;
    }

    public interface CustomEntityFactory<T extends PersistentProjectileEntity> {
        T create(double x, double y, double z, World world, ItemStack stack);
    }
}
