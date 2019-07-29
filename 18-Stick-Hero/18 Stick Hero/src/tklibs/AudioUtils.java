package tklibs;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioUtils {

    public static Clip loadSound(String audioUrl) {
        File soundFile = new File(audioUrl);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void replay(Clip clip){
        clip.setFramePosition(0);
        clip.start();
    }

    public static void play(Clip clip){
        clip.start();
    }

    public static void pause(Clip clip){
        clip.stop();
    }

    public static void loop(Clip clip, int numberLoop){
        clip.loop(numberLoop);
    }

    public static void loopForever(Clip clip){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}
