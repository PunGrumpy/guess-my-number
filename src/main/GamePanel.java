package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import character.Image;
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
  private BufferedImage[] CharacterAnimation;
  private int animationTick, animationIndex, animationSpeed = 12; // Animation speed is 30 ticks per animation

  public GamePanel() {
    mouseInputs = new MouseInputs(this);

    loadAnimations();

    setPanelSize();
    addKeyListener(new KeyboardInputs(this));
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);
  }

  private void loadAnimations() {
    Image image = new Image("running", "players_knight", 10);
    CharacterAnimation = image.getAnimations();
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
      if (animationIndex >= CharacterAnimation.length) {
        animationIndex = 0;
      }
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    updateAnimationTick();

    g.drawImage(
      CharacterAnimation[animationIndex],
      (int) xDelta,
      (int) yDelta,
      null
    );
  }
}
