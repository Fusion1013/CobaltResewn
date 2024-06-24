package se.fusion1013.networking.payload;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;
import org.joml.Vector3f;
import se.fusion1013.networking.CobaltNetworkingConstants;

public record UpdateItemDisplayPayloadC2S(BlockPos blockPos, Vector3f offset, Vector3f offsetFrequency, Vector3f offsetAmplitude, Vector3f scale, Vector3f scaleFrequency, Vector3f scaleAmplitude, Vector3f rotation, Vector3f rotationSpeed) implements CustomPayload {

    public static final CustomPayload.Id<UpdateItemDisplayPayloadC2S> ID = new CustomPayload.Id<>(CobaltNetworkingConstants.UPDATE_ITEM_DISPLAY_C2S);
    public static final PacketCodec<RegistryByteBuf, UpdateItemDisplayPayloadC2S> CODEC = new PacketCodec<>() {
        @Override
        public UpdateItemDisplayPayloadC2S decode(RegistryByteBuf buf) {
            var blockPos = BlockPos.PACKET_CODEC.decode(buf);

            var offset = PacketCodecs.VECTOR3F.decode(buf);
            var offsetFrequency = PacketCodecs.VECTOR3F.decode(buf);
            var offsetAmplitude = PacketCodecs.VECTOR3F.decode(buf);

            var scale = PacketCodecs.VECTOR3F.decode(buf);
            var scaleFrequency = PacketCodecs.VECTOR3F.decode(buf);
            var scaleAmplitude = PacketCodecs.VECTOR3F.decode(buf);

            var rotation = PacketCodecs.VECTOR3F.decode(buf);
            var rotationSpeed = PacketCodecs.VECTOR3F.decode(buf);
            return new UpdateItemDisplayPayloadC2S(blockPos, offset, offsetFrequency, offsetAmplitude, scale, scaleFrequency, scaleAmplitude, rotation, rotationSpeed);
        }

        @Override
        public void encode(RegistryByteBuf buf, UpdateItemDisplayPayloadC2S value) {
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
