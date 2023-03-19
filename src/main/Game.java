package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Game extends JFrame {

  protected JFrame jframe;
  protected JPanel viewPanel;
  private Dimension screenSize = new Dimension(350, 660);

  public Game() {
    viewPanel = new JPanel(new BorderLayout());
    this.setTitle("Guess My Number 🔮");
    this.setPreferredSize(screenSize);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.add(viewPanel, BorderLayout.CENTER);
    showView(new MenuPanel(this));
    this.setVisible(true);
    this.pack();
    this.setIconImage(Toolkit.getDefaultToolkit().getImage("asset/icon.png"));
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
