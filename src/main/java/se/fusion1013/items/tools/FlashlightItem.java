package se.fusion1013.items.tools;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.PointLight;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import se.fusion1013.Main;
import se.fusion1013.items.CobaltItem;
import se.fusion1013.items.CobaltItemConfiguration;

public class FlashlightItem extends CobaltItem {

    private PointLight light;

    public FlashlightItem(CobaltItem.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (light == null) {
            light = new PointLight()
                    .setPosition(user.getX(), user.getY(), user.getZ())
                    .setBrightness(1.0f)
                    .setRadius(10f)
                    .setColor(1.0f, 1.0f, 1.0f);
            VeilRenderSystem.renderer().getLightRenderer().addLight(light);
        }
        light.setPosition(user.getX(), user.getY(), user.getZ());

        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
