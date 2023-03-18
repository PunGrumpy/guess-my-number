package main;

import java.awt.Dimension;
import java.awt.Toolkit;
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
    jframe.setIconImage(Toolkit.getDefaultToolkit().getImage("asset/icon.png"));
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jframe.add(new GamePanel(this));
    jframe.setResizable(false); // Prevents the window from being resized
    jframe.pack(); // Adjusts the size of the window to fit the content
    jframe.setVisible(true);
    jframe.setLocationRelativeTo(null); // Centers the window
  }

  public void restartGame() {
    jframe.dispose();
    new Game();
  }

  public void exitGame() {
    jframe.dispose();
  }
  
}
