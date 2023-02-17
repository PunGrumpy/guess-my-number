package main;

import javax.swing.JFrame;

public class GameWindow {

  private JFrame jframe;

  public GameWindow(GamePanel gamePanel) {
    jframe = new JFrame("Java Game");

    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jframe.add(gamePanel);
    jframe.setResizable(false); // Prevents the window from being resized
    jframe.pack(); // Adjusts the size of the window to fit the content
    jframe.setVisible(true);
    jframe.setLocationRelativeTo(null); // Centers the window
  }
}
