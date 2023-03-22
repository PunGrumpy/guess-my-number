package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import utility.ImageLoader;

public class Game extends JFrame {

  private final int WIDTH = 360;
  private final int HEIGHT = WIDTH * 2;

  private transient ImageLoader imageLoader;

  protected JFrame jframe;
  protected JPanel viewPanel;

  public Game() {
    imageLoader = new ImageLoader();

    viewPanel = new JPanel(new BorderLayout());
    this.setTitle("Guess My Number 🔮");
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.add(viewPanel, BorderLayout.CENTER);
    showView(new MenuPanel(this));
    this.setVisible(true);
    this.pack();
    this.setIconImage(
        Toolkit
          .getDefaultToolkit()
          .getImage(imageLoader.getImageUrl("images/icon.png"))
      );
    this.setResizable(false);
    this.setLocationRelativeTo(null);
  }

  public void showView(JPanel jpanel) {
    viewPanel.removeAll();
    viewPanel.add(jpanel, BorderLayout.CENTER);
    viewPanel.revalidate();
    viewPanel.repaint();
  }
}
