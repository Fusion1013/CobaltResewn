package se.fusion1013.slidereel;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import se.fusion1013.Main;

import java.util.List;

public class SlideReel {

    public static final Codec<SlideReel> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("title").forGetter(SlideReel::getTitle),
            Codec.STRING.listOf().fieldOf("pictures").forGetter(SlideReel::getPictures)
    ).apply(instance, SlideReel::new));

    public String title;
    public List<String> pictures;

    public SlideReel(String title, List<String> pictures) {
        this.title = title;
        this.pictures = pictures;

        Main.LOGGER.info("Created new Slide Reel called '" + title + "' + and " + pictures.size() + " pictures");
    }

    public String getTitle() {
        return title;
    }

    public List<String> getPictures() {
        return pictures;
    }
}
