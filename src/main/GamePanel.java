package main;

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

import inputs.KeyboardInputs;
import inputs.MouseInputs;


public class GamePanel extends JPanel {
  private MouseInputs mouseInputs;
  private float xDelta = 100, yDelta = 100;
  private BufferedImage image; 
  private final String imageFileName = "assets/players.png";
  
  public GamePanel() {
    mouseInputs = new MouseInputs(this);

    importImage();
    
    setPanelSize();
    addKeyListener(new KeyboardInputs(this));
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);
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

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(image, 0, 0, null); // if in image more than 1 use image.getSubimage(x, y, width, height)
  }
} 
