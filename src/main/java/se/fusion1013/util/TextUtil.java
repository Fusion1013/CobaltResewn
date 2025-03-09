package se.fusion1013.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class TextUtil {

    private static final int MAX_TEXT_LENGTH = 50;

    public static List<Text> formatText(Text text, boolean split) {
        return formatText(text, split, MAX_TEXT_LENGTH);
    }

    public static List<Text> formatText(Text text, boolean split, int textLength) {
        var formatSplit = text.getString().split("£");
        List<Text> texts = new ArrayList<>();
        boolean first = true;

        MutableText currentText = Text.empty();
        for (String s : formatSplit) {
            if (s.isEmpty()) {
                first = false;
                continue;
            }
            char firstChar = s.charAt(0);
            Formatting format = getFormattingFromChar(firstChar);
            String textString = first ? s : s.substring(1);
            first = false;
            Text textToAdd = Text.literal(textString).formatted(format);

            if (firstChar == 'n' || currentText.getContent().toString().length() > textLength) {
                texts.add(currentText);
                currentText = Text.empty();
            }
            currentText.append(textToAdd);
        }
        if (!currentText.getContent().toString().isEmpty()) texts.add(currentText);
        return texts;
    }

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

    private static Formatting getFormattingFromChar(char c) {
        if (c == '0') return Formatting.BLACK;
        if (c == '1') return Formatting.DARK_BLUE;
        if (c == '2') return Formatting.DARK_GREEN;
        if (c == '3') return Formatting.DARK_AQUA;
        if (c == '4') return Formatting.DARK_RED;
        if (c == '5') return Formatting.DARK_PURPLE;
        if (c == '6') return Formatting.GOLD;
        if (c == '7') return Formatting.GRAY;
        if (c == '8') return Formatting.DARK_GRAY;
        if (c == '9') return Formatting.BLUE;
        if (c == 'a') return Formatting.GREEN;
        if (c == 'b') return Formatting.AQUA;
        if (c == 'c') return Formatting.RED;
        if (c == 'd') return Formatting.LIGHT_PURPLE;
        if (c == 'e') return Formatting.YELLOW;
        if (c == 'f') return Formatting.WHITE;
        return Formatting.WHITE;
    }

}
