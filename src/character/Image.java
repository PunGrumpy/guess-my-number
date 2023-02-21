package character;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Image {
    private String character, action;
    private int amount = 10;
    private String imageFileName;
    private BufferedImage image;
    private BufferedImage[] animations;
    
    public Image(String action, String character, int amount) {
        this.character = character;
        this.action = action;
        this.amount = amount;
    }

    private String getPath() {
        return imageFileName = "assets/" + character + "_" + action + ".png";
    }

    private void importImage() {
        try {
            File file = new File(getPath());
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAnimations() {
        animations = new BufferedImage[amount];

        for (int i = 0; i < animations.length; i++) {
            animations[i] = image.getSubimage(i * 120, 0, 72, 80);
        }
    }

    public BufferedImage[] getAnimations() {
        importImage();
        loadAnimations();
        return animations;
    }
}
