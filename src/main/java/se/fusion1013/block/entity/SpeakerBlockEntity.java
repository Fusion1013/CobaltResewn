package se.fusion1013.block.entity;

import de.maxhenkel.voicechat.api.Position;
import de.maxhenkel.voicechat.api.audiochannel.LocationalAudioChannel;
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.Main;
import se.fusion1013.config.CobaltModConfig;
import se.fusion1013.util.VoiceUtil;
import se.fusion1013.voice.VoiceChatIntegration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpeakerBlockEntity extends BlockEntity {

    private static final List<SpeakerBlockEntity> speakerBlockEntities = new ArrayList<>();

    public static final String NBT_KEY_ACTIVATE = "speaker.activate";
    public static final String NBT_KEY_CANAL = "speaker.canal";

    protected final PropertyDelegate propertyDelegate;

    private boolean activated = true;
    private int canal = 1;

    private final UUID channelId;
    private LocationalAudioChannel channel = null;

    public SpeakerBlockEntity(BlockPos pos, BlockState state) {
        super(CobaltBlockEntityTypes.SPEAKER, pos, state);

        speakerBlockEntities.add(this); // Add the new instance to the list of speakers

        channelId = UUID.randomUUID();

        // TODO: This might not be needed anymore, remove?
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> SpeakerBlockEntity.this.activated ? 1 : 0;
                    case 1 -> SpeakerBlockEntity.this.canal;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> SpeakerBlockEntity.this.activated = value == 1;
                    case 1 -> SpeakerBlockEntity.this.canal = value;
                    default -> {}
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.activated = nbt.getBoolean(NBT_KEY_ACTIVATE);
        this.canal = nbt.getInt(NBT_KEY_CANAL);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putBoolean(NBT_KEY_ACTIVATE, this.activated);
        nbt.putInt(NBT_KEY_CANAL, this.canal);
    }

    @Override
    public boolean onSyncedBlockEvent(int type, int data) {
        return super.onSyncedBlockEvent(type, data);
    }

    /**
     * Returns a list of all {@link SpeakerBlockEntity} within the specified range of the point, on the specified canal.
     * @param canal the canal of the speakers.
     * @param world the world to get speakers from.
     * @param pos the position to search from.
     * @param range the range to search within.
     * @return a list of speakers within range of the position.
     */
    public static List<SpeakerBlockEntity> getSpeakersActivatedInRange(int canal, World world, Vec3d pos, int range) {
        speakerBlockEntities.removeIf(BlockEntity::isRemoved);

        List<SpeakerBlockEntity> list = new ArrayList<>();

        for (SpeakerBlockEntity speaker : speakerBlockEntities) {

            if (!speaker.hasWorld()) continue;

            if (!speaker.canBroadcastToSpeaker(world, pos, speaker, range)) continue;

            if (!speaker.activated) continue;
            if (speaker.canal != canal) continue;

            list.add(speaker);
        }

        return list;
    }

    /**
     * Play a sound from the speaker.
     * @param event the microphone event.
     * @param audioData the audio data to play.
     */
    public void playSound(MicrophonePacketEvent event, byte[] audioData) {

        // Create the position to play the sound from
        Position pos = VoiceChatIntegration.voiceChatAPI.createPosition(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ());

        // If a channel has not yet been created; Attempt to create a new channel to play the sound from.
        if (this.channel == null) {

            // Attempt to create the channel
            if (this.world instanceof ServerWorld serverWorld) {
                this.channel = VoiceChatIntegration.voiceChatAPI.createLocationalAudioChannel(this.channelId, VoiceChatIntegration.voiceChatAPI.fromServerLevel(serverWorld), pos);
            }

            // If the channel still does not exist, do not attempt to play sound from it
            if (this.channel == null) {
                return;
            }

            // Set channel data
            this.channel.setCategory(VoiceChatIntegration.SPEAKER_CATEGORY);
            this.channel.setDistance(CobaltModConfig.speakerDistance + 1F);

            // Filter who should hear the sound
            if (!CobaltModConfig.voiceDuplication) {
                this.channel.setFilter(serverPlayer -> !serverPlayer.getEntity().equals(event.getSenderConnection().getPlayer().getEntity()));
            }
        }

        // Send the audio data to the channel
        this.channel.send(audioData);
    }

    private boolean canBroadcastToSpeaker(World senderWorld, Vec3d senderPos, SpeakerBlockEntity speaker, int range) {
        World receiverWorld = speaker.getWorld();

        if (receiverWorld == null) return false;

        return VoiceUtil.canBroadcastToReceiver(senderWorld, receiverWorld, senderPos, speaker.pos.toCenterPos(), range);
    }
}
