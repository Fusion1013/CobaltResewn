package se.fusion1013.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import se.fusion1013.Main;
import se.fusion1013.block.entity.ItemDisplayBlockEntity;
import se.fusion1013.networking.payload.UpdateItemDisplayPayloadC2S;
import se.fusion1013.screen.ItemDisplayScreenHandler;

public class UpdateItemDisplayC2SPacket {

    public static void receive(UpdateItemDisplayPayloadC2S payload, ServerPlayNetworking.Context context) {

        var player = context.player();
        var server = player.server;

        var blockPos = payload.blockPos();

        var offset = payload.offset();
        var offsetFrequency = payload.offsetFrequency();
        var offsetAmplitude = payload.offsetAmplitude();

        var scale = payload.scale();
        var scaleFrequency = payload.scaleFrequency();
        var scaleAmplitude = payload.scaleAmplitude();

        var rotation = payload.rotation();
        var rotationSpeed = payload.rotationSpeed();

        server.execute(() -> {
            var world = player.getWorld();

            var blockEntity = server.getWorld(player.getWorld().getRegistryKey()).getBlockEntity(blockPos);

            if (blockEntity instanceof ItemDisplayBlockEntity itemDisplayBlockEntity) {
                itemDisplayBlockEntity.setOffset(offset);
                itemDisplayBlockEntity.setOffsetFrequency(offsetFrequency);
                itemDisplayBlockEntity.setOffsetAmplitude(offsetAmplitude);

                itemDisplayBlockEntity.setScale(scale);
                itemDisplayBlockEntity.setScaleFrequency(scaleFrequency);
                itemDisplayBlockEntity.setScaleAmplitude(scaleAmplitude);

                itemDisplayBlockEntity.setRotation(rotation);
                itemDisplayBlockEntity.setRotationSpeed(rotationSpeed);

                itemDisplayBlockEntity.markDirty();
                world.updateListeners(blockPos, itemDisplayBlockEntity.getCachedState(), world.getBlockState(blockPos), Block.NOTIFY_LISTENERS);
            }
        });
    }
}
