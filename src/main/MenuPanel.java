package main;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import utility.Database;
import utility.ColorScheme;

public class MenuPanel extends JPanel {
  final private Game game;

  private Database database;
  private ColorScheme colorScheme;

  public MenuPanel(Game game) {
    database = new Database();
    colorScheme = new ColorScheme();
    
    this.game = game;
    this.setBackground(colorScheme.indigo);
    
    BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
    this.setLayout(boxLayout);

    Render();
  }

  private void Render() {
    System.out.println("MenuPanel");
  }
}
