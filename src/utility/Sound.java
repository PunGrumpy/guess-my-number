package utility;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

  public void soundEffect(URL soundName) {
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
        soundName
      );
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      FloatControl gainControl = (FloatControl) clip.getControl(
        FloatControl.Type.MASTER_GAIN
      );
      gainControl.setValue(-20.0f);
      clip.start();
    } catch (
      UnsupportedAudioFileException
      | LineUnavailableException
      | NullPointerException
      | IOException e
    ) {
      e.printStackTrace();
    }
  }
}
