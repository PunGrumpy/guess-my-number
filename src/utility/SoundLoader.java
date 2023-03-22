package utility;

import java.net.URL;

public class SoundLoader {

  private URL soundUrl = null;

  private void loadSound(String path) {
    try {
      soundUrl = getClass().getClassLoader().getResource(path);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public URL getSoundUrl(String path) {
    loadSound(path);
    return soundUrl;
  }
}
