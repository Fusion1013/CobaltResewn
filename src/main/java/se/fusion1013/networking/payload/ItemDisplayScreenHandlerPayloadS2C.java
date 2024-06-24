package se.fusion1013.networking.payload;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;
import org.joml.Vector3f;
import se.fusion1013.networking.CobaltNetworkingConstants;
import se.fusion1013.screen.CobaltScreenHandlers;

public record ItemDisplayScreenHandlerPayloadS2C(BlockPos blockPos, Vector3f offset, Vector3f offsetFrequency, Vector3f offsetAmplitude, Vector3f scale, Vector3f scaleFrequency, Vector3f scaleAmplitude, Vector3f rotation, Vector3f rotationSpeed) implements CustomPayload {

    public static final CustomPayload.Id<ItemDisplayScreenHandlerPayloadS2C> ID = new CustomPayload.Id<>(CobaltNetworkingConstants.OPEN_ITEM_DISPLAY_SCREEN_S2C);
    public static final PacketCodec<RegistryByteBuf, ItemDisplayScreenHandlerPayloadS2C> CODEC = new PacketCodec<>() {
        @Override
        public ItemDisplayScreenHandlerPayloadS2C decode(RegistryByteBuf buf) {
            var blockPos = BlockPos.PACKET_CODEC.decode(buf);

            var offset = PacketCodecs.VECTOR3F.decode(buf);
            var offsetFrequency = PacketCodecs.VECTOR3F.decode(buf);
            var offsetAmplitude = PacketCodecs.VECTOR3F.decode(buf);

            var scale = PacketCodecs.VECTOR3F.decode(buf);
            var scaleFrequency = PacketCodecs.VECTOR3F.decode(buf);
            var scaleAmplitude = PacketCodecs.VECTOR3F.decode(buf);

            var rotation = PacketCodecs.VECTOR3F.decode(buf);
            var rotationSpeed = PacketCodecs.VECTOR3F.decode(buf);
            return new ItemDisplayScreenHandlerPayloadS2C(blockPos, offset, offsetFrequency, offsetAmplitude, scale, scaleFrequency, scaleAmplitude, rotation, rotationSpeed);
        }

        @Override
        public void encode(RegistryByteBuf buf, ItemDisplayScreenHandlerPayloadS2C value) {
            BlockPos.PACKET_CODEC.encode(buf, value.blockPos);

            PacketCodecs.VECTOR3F.encode(buf, value.offset);
            PacketCodecs.VECTOR3F.encode(buf, value.offsetFrequency);
            PacketCodecs.VECTOR3F.encode(buf, value.offsetAmplitude);

            PacketCodecs.VECTOR3F.encode(buf, value.scale);
            PacketCodecs.VECTOR3F.encode(buf, value.scaleFrequency);
            PacketCodecs.VECTOR3F.encode(buf, value.scaleAmplitude);

            PacketCodecs.VECTOR3F.encode(buf, value.rotation);
            PacketCodecs.VECTOR3F.encode(buf, value.rotationSpeed);
        }
    };

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
