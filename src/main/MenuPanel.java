package main;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import utility.ColorScheme;
import utility.Database;
import utility.ImageLoader;

public class MenuPanel extends JPanel {

  private final Game game;

  private transient Database database;
  private transient ImageLoader imageLoader;

  public MenuPanel(Game game) {
    database = new Database();
    imageLoader = new ImageLoader();

    this.game = game;

    BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
    this.setLayout(boxLayout);

    Render();
  }

  private void Render() {
    database.setScore(0);
    database.saveScore();

    JLabel title = new JLabel("Guess My Number 🔮");
    title.setForeground(ColorScheme.black);
    title.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
    title.setBorder(new EmptyBorder(100, 0, 0, 0));
    title.setAlignmentX(CENTER_ALIGNMENT);
    add(title);

    JLabel highest_label = new JLabel("Highest Score");
    highest_label.setForeground(ColorScheme.black);
    highest_label.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
    highest_label.setBorder(new EmptyBorder(50, 0, 0, 0));
    highest_label.setAlignmentX(CENTER_ALIGNMENT);
    add(highest_label);

    JLabel highest_score = new JLabel(
      "Score: " +
      database.showScore() +
      "   🏆   High Score: " +
      database.showHighScore()
    );
    highest_score.setForeground(ColorScheme.black);
    highest_score.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    highest_score.setBorder(new EmptyBorder(10, 0, 0, 0));
    highest_score.setAlignmentX(CENTER_ALIGNMENT);
    add(highest_score);

    JLabel play_button = new JLabel(
      new ImageIcon(imageLoader.getImageUrl("src/resource/play.png"))
    );
    play_button.setBorder(new EmptyBorder(35, 0, 0, 0));
    play_button.setAlignmentX(CENTER_ALIGNMENT);
    play_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    linkPlay(play_button);
    add(play_button);

    JLabel play_label = new JLabel("Click to play 🎮");
    play_label.setForeground(ColorScheme.black);
    play_label.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
    play_label.setBorder(new EmptyBorder(35, 0, 0, 0));
    play_label.setAlignmentX(CENTER_ALIGNMENT);
    add(play_label);
  }

  private void linkPlay(JLabel play_button) {
    play_button.addMouseListener(
      new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
          game.showView(new GamePanel(game));
        }
      }
    );
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(
      new ImageIcon(imageLoader.getImageUrl("src/resource/background_menu.png"))
        .getImage(),
      0,
      0,
      getWidth(),
      getHeight(),
      null
    );
  }
}
