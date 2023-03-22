package utility;

import java.net.URL;

public class ImageLoader {

  private URL imageUrl = null;

  private void loadImage(String path) {
    try {
      imageUrl = getClass().getClassLoader().getResource(path);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public URL getImageUrl(String path) {
    loadImage(path);
    return imageUrl;
  }
}
