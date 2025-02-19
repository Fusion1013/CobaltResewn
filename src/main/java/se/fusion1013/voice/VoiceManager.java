package se.fusion1013.voice;

import de.maxhenkel.voicechat.api.Group;
import de.maxhenkel.voicechat.api.VoicechatConnection;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import de.maxhenkel.voicechat.api.audiochannel.StaticAudioChannel;
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import se.fusion1013.Main;
import se.fusion1013.block.entity.SpeakerBlockEntity;
import se.fusion1013.config.CobaltModConfig;
import se.fusion1013.items.CobaltItems;
import se.fusion1013.items.misc.WalkieTalkieItem;
import se.fusion1013.util.VoiceUtil;
import se.fusion1013.util.item.ItemUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VoiceManager {

    private static VoiceManager INSTANCE;

    public static final String GLOBAL_GROUP_NAME = "global";

    private static final RadioFilter radioFilter = new RadioFilter();

    private VoiceManager() {
    }

    /// region Voice chat listeners

    public void onMicPacket(MicrophonePacketEvent event) {
        VoicechatConnection senderConnection = event.getSenderConnection();

        if (senderConnection == null) return;
        if (!(senderConnection.getPlayer().getPlayer() instanceof PlayerEntity senderPlayer)) return;

        // Send mic packet to walkie talkie
        sendWalkieTalkieSound(event, senderConnection, senderPlayer);

        // Sends audio from players not in the global group, to the global group
        sendMicPacketToGlobalGroup(event, senderConnection, senderPlayer);
    }

    private void testStatic(MicrophonePacketEvent event, VoicechatConnection senderConnection) {
        var api = event.getVoicechat();
        api.sendStaticSoundPacketTo(senderConnection, event.getPacket().staticSoundPacketBuilder().opusEncodedData(getFilteredAudio(radioFilter, event, 1)).build());
    }

    private void sendSpeakerSound(MicrophonePacketEvent event, PlayerEntity senderPlayer, int canal, byte[] audioData) {
        var senderWalkie = ItemUtil.getHeldItemOfType(senderPlayer, CobaltItems.WALKIE_TALKIE);
        if (senderWalkie == null) return;

        // Send the audio data to all activated speakers in range
        SpeakerBlockEntity.getSpeakersActivatedInRange(canal, senderPlayer.getWorld(), senderPlayer.getPos(), WalkieTalkieItem.getRange(senderWalkie))
                .forEach(speakerBlockEntity -> speakerBlockEntity.playSound(event, audioData));
    }

    private byte[] getFilteredAudio(IAudioFilter filter, MicrophonePacketEvent event, double volume) {
        // Add static to the sound
        byte[] opusEncodedData = event.getPacket().getOpusEncodedData();
        if (CobaltModConfig.applyRadioEffect) {
            short[] rawData = VoiceChatIntegration.decoder.decode(opusEncodedData);
            opusEncodedData = VoiceChatIntegration.encoder.encode(filter.apply(rawData, volume));
        }
        return opusEncodedData;
    }

    private void sendWalkieTalkieSound(MicrophonePacketEvent event, VoicechatConnection senderConnection, PlayerEntity senderPlayer) {

        // Get the held walkie talkie
        var senderWalkie = ItemUtil.getHeldItemOfType(senderPlayer, CobaltItems.WALKIE_TALKIE);
        if (senderWalkie == null) return;

        // Check if the walkie talkie is activated and not muted
        if (!WalkieTalkieItem.isActivate(senderWalkie) || WalkieTalkieItem.isMute(senderWalkie)) return;

        int senderCanal = WalkieTalkieItem.getCanal(senderWalkie);

        var filteredAudio = getFilteredAudio(radioFilter, event, 1.8d);

        // Send mic packet to all nearby speaker blocks
        sendSpeakerSound(event, senderPlayer, senderCanal, filteredAudio);

        var api = event.getVoicechat();

        // Iterate over all players on the server
        for (PlayerEntity receiverPlayer : senderPlayer.getServer().getPlayerManager().getPlayerList()) {
            if (receiverPlayer.getUuid().equals(senderPlayer.getUuid())) continue; // Do not send to self
            // TODO: Check cross dimensional support

            // Check if the receiving player is holding a walkie talkie
            ItemStack receiverWalkie = ItemUtil.getHeldItemOfType(receiverPlayer, CobaltItems.WALKIE_TALKIE);
            if (receiverWalkie == null) continue;

            // Check if the walkie talkie is in range and set to the correct canal
            int receiverRange = WalkieTalkieItem.getRange(receiverWalkie);
            int receiverCanal = WalkieTalkieItem.getCanal(receiverWalkie);

            if (receiverCanal != senderCanal) continue;
            if (!VoiceUtil.canBroadcastToReceiver(senderPlayer.getWorld(), receiverPlayer.getWorld(), senderPlayer.getPos(), receiverPlayer.getPos(), receiverRange)) continue;

            // Check if the receiving player is connected to the voice chat
            VoicechatConnection connection = api.getConnectionOf(receiverPlayer.getUuid());
            if (connection == null) continue;

            // Send the voice event to the receiving player
            api.sendStaticSoundPacketTo(connection, event.getPacket().staticSoundPacketBuilder().opusEncodedData(filteredAudio).build());
        }
    }

    private void sendMicPacketToGlobalGroup(MicrophonePacketEvent event, VoicechatConnection senderConnection, PlayerEntity senderPlayer) {

        // If the player is already in the global group, ignore
        var playerGroup = senderConnection.getGroup();
        if (playerGroup != null && playerGroup.getName().toLowerCase().contains(GLOBAL_GROUP_NAME.toLowerCase())) return;

        VoicechatServerApi api = event.getVoicechat();

        var globalGroup = getGroupFromPartialName(api, GLOBAL_GROUP_NAME);
        if (globalGroup == null) return;

        // Iterate over every player on the server
        for (PlayerEntity receiverPlayer : senderPlayer.getServer().getPlayerManager().getPlayerList()) {
            if (receiverPlayer.getUuid().equals(senderPlayer.getUuid())) continue; // Do not send to self

            // Check if the receiving player is actually connected to the voice chat
            VoicechatConnection connection = api.getConnectionOf(receiverPlayer.getUuid());
            if (connection == null) continue;

            // Only send audio to players in the global group
            var receiverGroup = connection.getGroup();
            if (receiverGroup == null) continue;
            if (!receiverGroup.getName().toLowerCase().contains(GLOBAL_GROUP_NAME.toLowerCase())) continue;

            api.sendStaticSoundPacketTo(connection, event.getPacket().staticSoundPacketBuilder().build());
        }
    }

    private Group getGroupFromPartialName(VoicechatServerApi api, String name) {
        for (Group group : api.getGroups()) {
            if (group.getName().toLowerCase().contains(name.toLowerCase())) return group;
        }
        return null;
    }

    /// endregion

    public static VoiceManager getInstance() {
        if (INSTANCE == null) INSTANCE = new VoiceManager();
        return INSTANCE;
    }

}
