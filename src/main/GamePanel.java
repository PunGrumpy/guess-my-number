package main;

import javax.swing.JPanel;
import java.awt.Graphics;

public class GamePanel extends JPanel {
  GamePanel() {}

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.fillRect(100, 100, 200, 50);
  }
} 
