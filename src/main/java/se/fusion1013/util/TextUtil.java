package se.fusion1013.util;

import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class TextUtil {

    private static final int MAX_TEXT_LENGTH = 50;

    public static List<Text> splitText(Text text) {
        return splitText(text, MAX_TEXT_LENGTH);
    }

    public static List<Text> splitText(Text text, int textLength) {
        List<Text> output = new ArrayList<>();

        var strings = text.getString().split(" ");
        StringBuilder currentString = new StringBuilder();
        for (String s : strings) {
            currentString.append(s).append(" ");
            if (currentString.length() >= textLength) {
                // Append to the text
                output.add(Text.literal(currentString.toString()).setStyle(text.getStyle()));
                currentString = new StringBuilder();
            }
        }
        if (!currentString.isEmpty()) output.add(Text.literal(currentString.toString()).setStyle(text.getStyle()));

        return output;
    }

}
