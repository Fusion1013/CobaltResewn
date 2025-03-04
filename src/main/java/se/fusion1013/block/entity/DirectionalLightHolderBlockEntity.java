package se.fusion1013.block.entity;

import foundry.veil.api.client.render.light.AreaLight;
import foundry.veil.api.client.render.light.PointLight;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class DirectionalLightHolderBlockEntity extends LightHolderBlockEntity {

    private AreaLight areaLight;
    private PointLight pointLight;

    public DirectionalLightHolderBlockEntity(BlockPos pos, BlockState state) {
        super(CobaltBlockEntityTypes.DIRECTIONAL_LIGHT_HOLDER_BLOCK_ENTITY, pos, state);
    }

    public AreaLight getAreaLight() {
        return areaLight;
    }

    public PointLight getPointLight() {
        return pointLight;
    }

    public void setAreaLight(AreaLight areaLight) {
        this.areaLight = areaLight;
    }

    public void setPointLight(PointLight pointLight) {
        this.pointLight = pointLight;
    }
}
