package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import java.util.Random;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {
  private MouseInputs mouseInputs;
  private float xDelta = 100, yDelta = 100;
  private float xDir = 0.003f, yDir = 0.003f;
  private Color color = new Color(160, 160, 160);
  private Random random;
  
  GamePanel() {
    random = new Random();

    mouseInputs = new MouseInputs(this);
    addKeyListener(new KeyboardInputs(this));
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);
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

    updateRectangles();
    g.setColor(color);
    g.fillRect((int)xDelta, (int)yDelta, 200, 50);
    repaint();
  }

  private void updateRectangles() {
    xDelta += xDir;
    if(xDelta > 500 || xDelta < 0) {
      xDir *= -1;
      color = getRandomColor();
    }
    
    yDelta += yDir;
    if(yDelta > 500 || yDelta < 0) {
      yDir *= -1;
      color = getRandomColor();
    }
  }

  private Color getRandomColor() {
    int r = random.nextInt(255);
    int g = random.nextInt(255);
    int b = random.nextInt(255);

    return new Color(r, g, b);
  }
} 
