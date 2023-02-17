package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

public class GamePanel extends JPanel {

  private MouseInputs mouseInputs;
  private float xDelta = 100, yDelta = 100;
  private BufferedImage image, subImage;
  private BufferedImage[] runningAnimations;
  private int animationTick, animationIndex, animationSpeed = 12; // Animation speed is 30 ticks per animation
  private final String imageFileName = "assets/players_knight.png";

  public GamePanel() {
    mouseInputs = new MouseInputs(this);

    importImage();
    loadAnimations();

    setPanelSize();
    addKeyListener(new KeyboardInputs(this));
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);
  }

  private void loadAnimations() {
    runningAnimations = new BufferedImage[10];

    for (int i = 0; i < runningAnimations.length; i++) {
      runningAnimations[i] = image.getSubimage(i * 120, 0, 72, 80);
    }
  }

  private void importImage() {
    try {
      File file = new File(imageFileName);
      image = ImageIO.read(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void setPanelSize() {
    DimensionUIResource screenSize = new DimensionUIResource(1280, 720); // Screen size
    setMinimumSize(screenSize);
    setPreferredSize(screenSize);
    setMaximumSize(screenSize);
  }

  public void changeXDelta(int xDelta) {
    this.xDelta += xDelta;
  }

  public void changeYDelta(int yDelta) {
    this.yDelta += yDelta;
  }

  public void setRectPos(int x, int y) {
    this.xDelta = x;
    this.yDelta = y;
  }

  private void updateAnimationTick() {
    animationTick++;
    if (animationTick >= animationSpeed) {
      animationTick = 0;
      animationIndex++;
      if (animationIndex >= runningAnimations.length) {
        animationIndex = 0;
      }
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    updateAnimationTick();

    g.drawImage(
      runningAnimations[animationIndex],
      (int) xDelta,
      (int) yDelta,
      null
    );
  }
}
