package utility;

import java.net.URL;

public class Loader {

  public class ImageLoader {

    private URL imageUrl = null;

    private void loadImage(String path) {
      try {
        imageUrl = getClass().getClassLoader().getResource(path);
      } catch (Exception e) {
        // e.printStackTrace(); // Make sure this debug feature is deactivated before delivering the code in production.
      }
    }

    public URL getImageUrl(String path) {
      loadImage(path);
      return imageUrl;
    }
  }

  public class SoundLoader {

    private URL soundUrl = null;

    private void loadSound(String path) {
      try {
        soundUrl = getClass().getClassLoader().getResource(path);
      } catch (Exception e) {
        // e.printStackTrace(); // Make sure this debug feature is deactivated before delivering the code in production.
      }
    }

    public URL getSoundUrl(String path) {
      loadSound(path);
      return soundUrl;
    }
  }
}
