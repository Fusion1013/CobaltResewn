package se.fusion1013.block.dream;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.ActionResult;

public interface DreamLightLitCallback {

    Event<DreamLightLitCallback> EVENT = EventFactory.createArrayBacked(DreamLightLitCallback.class,
            (listeners) -> (blockEntity) -> {
                for (DreamLightLitCallback listener : listeners) {
                    ActionResult result = listener.dreamLightLit(blockEntity);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            }
    );

    ActionResult dreamLightLit(AbstractDreamLightBlockEntity entity);
}
