package main;

import javax.swing.JFrame;

public class GameWindow {
  private JFrame jframe;

  public GameWindow(GamePanel gamePanel) {
    jframe = new JFrame("Java Game");

    jframe.setSize(500, 500);
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jframe.add(gamePanel);
    jframe.setVisible(true);
  }
}
