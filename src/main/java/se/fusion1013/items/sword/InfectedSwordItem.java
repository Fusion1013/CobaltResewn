package se.fusion1013.items.sword;

import se.fusion1013.entity.damage.CobaltDamageTypes;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.items.CobaltItemConfiguration;

import java.util.List;

public class InfectedSwordItem extends CobaltSwordItem {

    private final float m_healthReduction;
    private final int m_cooldown;
    private final InfectedAction m_infectedAction;

    public InfectedSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, Formatting nameFormatting, float healthReduction, int cooldown, InfectedAction infectedAction) {
        super(toolMaterial, attackDamage, attackSpeed, CobaltItemConfiguration.create(nameFormatting), settings);

        this.m_healthReduction = healthReduction;
        this.m_cooldown = cooldown;
        this.m_infectedAction = infectedAction;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var cooldownManager = new ItemCooldownManager();

        if (cooldownManager.isCoolingDown(this)) return super.use(world, user, hand);
        if (user.hasStatusEffect(StatusEffects.STRENGTH)) return super.use(world, user, hand);

        // Reduce player health
        user.damage(CobaltDamageTypes.of(world, CobaltDamageTypes.INFECTION), m_healthReduction);

        // Apply effect
        m_infectedAction.Perform(world, user, hand);

        // Start item cooldown
        cooldownManager.set(this, m_cooldown);

        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("item.cobalt.infected_adventure_sword.ability").formatted(Formatting.GOLD));
    }

    public interface InfectedAction {
        void Perform(World world, PlayerEntity user, Hand hand);
    }
}
