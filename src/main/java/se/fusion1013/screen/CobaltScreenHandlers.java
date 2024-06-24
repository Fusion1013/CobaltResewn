package se.fusion1013.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import se.fusion1013.Main;
import se.fusion1013.networking.payload.ItemDisplayScreenHandlerPayloadS2C;

public class CobaltScreenHandlers {

    public static ScreenHandlerType<ItemDisplayScreenHandler> ITEM_DISPLAY_SCREEN_HANDLER = new ExtendedScreenHandlerType<>(ItemDisplayScreenHandler::new, ItemDisplayScreenHandlerPayloadS2C.CODEC);

    static {
        ITEM_DISPLAY_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Main.MOD_NAMESPACE, "item_display"), ITEM_DISPLAY_SCREEN_HANDLER);
    }

    public static void registerAll() {}

}
