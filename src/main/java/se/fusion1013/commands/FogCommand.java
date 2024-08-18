package se.fusion1013.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import se.fusion1013.effect.EnvironmentEffect;
import se.fusion1013.networking.CobaltNetworkingConstants;

import static net.minecraft.server.command.CommandManager.*;

/**
 * Allows for switching different fog parameters in game.
 */
public class FogCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(literal("fog")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                .then(literal("start")
                    .then(argument("start", FloatArgumentType.floatArg())
                        .executes(context -> {
                            final float start = FloatArgumentType.getFloat(context, "start");

                            var buffer = PacketByteBufs.create();
                            buffer.writeString("start");
                            buffer.writeFloat(start);

                            // ServerPlayNetworking.send(context.getSource().getPlayer(), CobaltNetworkingConstants.SET_FOG_VALUE_S2C, buffer);

                            context.getSource().sendFeedback(() -> Text.literal("Set fog start to %s".formatted(start)), false);
                            return 0;
                        })))
                .then(literal("end")
                    .then(argument("end", FloatArgumentType.floatArg())
                        .executes(context -> {
                            final float end = FloatArgumentType.getFloat(context, "end");

                            var buffer = PacketByteBufs.create();
                            buffer.writeString("end");
                            buffer.writeFloat(end);

                            // ServerPlayNetworking.send(context.getSource().getPlayer(), CobaltNetworkingConstants.SET_FOG_VALUE_S2C, buffer);

                            context.getSource().sendFeedback(() -> Text.literal("Set fog end to %s".formatted(end)), false);
                            return 0;
                        })))
                .then(literal("color")
                    .then(argument("color", FloatArgumentType.floatArg())
                        .executes(context -> {
                            final float color = FloatArgumentType.getFloat(context, "color");

                            var buffer = PacketByteBufs.create();
                            buffer.writeString("color");
                            buffer.writeFloat(color);

                            // ServerPlayNetworking.send(context.getSource().getPlayer(), CobaltNetworkingConstants.SET_FOG_VALUE_S2C, buffer);

                            context.getSource().sendFeedback(() -> Text.literal("Set fog color to %s".formatted(color)), false);
                            return 0;
                        })))
                .then(literal("padding")
                    .then(argument("padding", IntegerArgumentType.integer())
                        .executes(context -> {
                            final int padding = IntegerArgumentType.getInteger(context, "padding");

                            var buffer = PacketByteBufs.create();
                            buffer.writeString("padding");
                            buffer.writeInt(padding);

                            // ServerPlayNetworking.send(context.getSource().getPlayer(), CobaltNetworkingConstants.SET_FOG_VALUE_S2C, buffer);

                            context.getSource().sendFeedback(() -> Text.literal("Set fog padding to %s".formatted(padding)), false);
                            return 0;
                        })))
        );
    }

}
