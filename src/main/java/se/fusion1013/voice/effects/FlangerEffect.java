package se.fusion1013.voice.effects;

public class FlangerEffect {

    public static short[] applyFlanger(short[] input, int sampleRate, double depth, double rate) {
        int maxDelay = (int) (depth * sampleRate);
        short[] output = new short[input.length];

        for (int i = 0; i < input.length; i++) {
            int delay = (int) (maxDelay * (0.5 + 0.5 * Math.sin(2 * Math.PI * rate * i / sampleRate)));
            int delayedIndex = i - delay;
            if (delayedIndex >= 0) {
                output[i] = (short) ((input[i] + input[delayedIndex]) / 2);
            } else {
                output[i] = input[i];
            }
        }
        return output;
    }

}
