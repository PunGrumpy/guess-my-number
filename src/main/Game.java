package main;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Game extends JFrame {

  protected JFrame jframe;
  private Dimension screenSize = new Dimension(700, 600);

  public Game() {
    jframe = new JFrame("Java Game");
    jframe.setTitle("Guess My Number");
    jframe.setPreferredSize(screenSize);
    jframe.setMinimumSize(screenSize);
    jframe.setMaximumSize(screenSize);
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jframe.add(new GamePanel(this));
    jframe.setAlwaysOnTop(false); // Prevent exit panel stay behind the game
    jframe.setResizable(false); // Prevents the window from being resized
    jframe.pack(); // Adjusts the size of the window to fit the content
    jframe.setVisible(true);
  }
}
