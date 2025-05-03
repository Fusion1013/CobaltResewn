package se.fusion1013.voice;

import de.maxhenkel.voicechat.api.Group;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import de.maxhenkel.voicechat.api.VolumeCategory;
import de.maxhenkel.voicechat.api.events.*;
import de.maxhenkel.voicechat.api.opus.OpusDecoder;
import de.maxhenkel.voicechat.api.opus.OpusEncoder;
import org.jetbrains.annotations.Nullable;
import se.fusion1013.Main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VoiceChatIntegration implements VoicechatPlugin {

    public final static String SPEAKER_CATEGORY = "speakers";

    @Nullable
    public static VoicechatServerApi voiceChatAPI;

    public static OpusEncoder encoder;
    public static OpusDecoder decoder;

    private ExecutorService executorService;

    public VoiceChatIntegration() {
        executorService = Executors.newSingleThreadExecutor(runnable -> {
           Thread thread = new Thread(runnable);
           thread.setName("CobaltVoiceThread");
           thread.setUncaughtExceptionHandler((t, e) -> Main.LOGGER.error("Error in voice chat integration voice thread: " + e));
           thread.setDaemon(true);
           return thread;
        });
    }

    @Override
    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(VoicechatServerStartedEvent.class, this::onServerStarted);
        registration.registerEvent(MicrophonePacketEvent.class, micPacket -> VoiceManager.getInstance().onMicPacket(micPacket));
        registration.registerEvent(LocationalSoundPacketEvent.class, packet -> VoiceManager.getInstance().onLocationalSoundPacket(packet));
    }

    private void onServerStarted(VoicechatServerStartedEvent event) {
        voiceChatAPI = event.getVoicechat();
        encoder = voiceChatAPI.createEncoder();
        decoder = voiceChatAPI.createDecoder();

        voiceChatAPI.groupBuilder()
                .setPersistent(true)
                .setName("global_normal")
                .setPassword("global_normal")
                .setType(Group.Type.NORMAL)
                .build();
        voiceChatAPI.groupBuilder()
                .setPersistent(true)
                .setName("global_isolated")
                .setPassword("global_isolated")
                .setType(Group.Type.ISOLATED)
                .build();
        voiceChatAPI.groupBuilder()
                .setPersistent(true)
                .setName("global_open")
                .setPassword("global_open")
                .setType(Group.Type.OPEN)
                .build();

        VolumeCategory speakers = voiceChatAPI.volumeCategoryBuilder()
                .setId(SPEAKER_CATEGORY)
                .setName("Speakers")
                .setDescription("The volume of all speakers")
                // TODO: .setIcon(getIcon("assets/walkietalkie/textures/block/oxidized_copper_speaker.png"))
                .build();
        voiceChatAPI.registerVolumeCategory(speakers);
    }

    @Override
    public String getPluginId() {
        return Main.MOD_NAMESPACE;
    }
}
