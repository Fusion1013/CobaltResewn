package se.fusion1013.voice;

import se.fusion1013.voice.effects.FlangerEffect;
import se.fusion1013.voice.effects.ReverbEffect;
import uk.me.berndporr.iirj.Butterworth;

public class UnderwaterFilter implements IAudioFilter {

    private static final int SAMPLE_RATE = 48000;

    private final Butterworth LOWPASS = new Butterworth();

    public UnderwaterFilter() {
        LOWPASS.lowPass(6, SAMPLE_RATE, 800);
    }

    @Override
    public short[] apply(short[] rawData, double volumeModifier) {
        short[] filteredAudio = applyButterworth(rawData);
        short[] reverbAudio = ReverbEffect.applyReverb(filteredAudio, SAMPLE_RATE, 0.5);
        return FlangerEffect.applyFlanger(reverbAudio, SAMPLE_RATE, 0.005, 0.2);
    }

    private short[] applyButterworth(short[] rawData) {
        short[] output = new short[rawData.length];
        for (int i = 0; i < rawData.length; i++) {
            output[i] = (short) LOWPASS.filter(rawData[i]);
        }
        return output;
    }
}
