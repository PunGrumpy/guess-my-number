package utility;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {

  public void soundEffect(String soundName) {
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
        new File(soundName)
      );
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      FloatControl gainControl = (FloatControl) clip.getControl(
        FloatControl.Type.MASTER_GAIN
      );
      gainControl.setValue(-20.0f);
      clip.start();
    } catch (
      UnsupportedAudioFileException | LineUnavailableException | IOException e
    ) {
      System.out.printf("Error: %s%n", e);
    }
  }
}
