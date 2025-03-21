package se.fusion1013.voice.effects;

public class ReverbEffect {
    public static short[] applyReverb(short[] input, int sampleRate, double decay) {
        int delaySamples = (int) (0.06 * sampleRate); // 60ms delay
        short[] output = new short[input.length];

        for (int i = 0; i < input.length; i++) {
            output[i] = input[i];  // Original sound
            if (i >= delaySamples) {
                output[i] += (short) (input[i - delaySamples] * decay);
            }
        }
        return output;
    }
}
