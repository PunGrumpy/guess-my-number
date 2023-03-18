package main;

import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
    database.setScore(0);
    database.saveScore();
    
    JLabel title = new JLabel("Guess My Number 🔮");
    title.setForeground(colorScheme.white);
    title.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
    title.setBorder(new EmptyBorder(100, 0, 0, 0));
    title.setAlignmentX(CENTER_ALIGNMENT);
    add(title);

    JLabel highest_label = new JLabel("Highest Score");
    highest_label.setForeground(colorScheme.white);
    highest_label.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
    highest_label.setBorder(new EmptyBorder(50, 0, 0, 0));
    highest_label.setAlignmentX(CENTER_ALIGNMENT);
    add(highest_label);

    JLabel highest_score = new JLabel("Score: " + database.showScore() + "   🏆   High Score: " + database.showHighScore());
    highest_score.setForeground(colorScheme.white);
    highest_score.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    highest_score.setBorder(new EmptyBorder(10, 0, 0, 0));
    highest_score.setAlignmentX(CENTER_ALIGNMENT);
    add(highest_score);

    JLabel play_button = new JLabel(new ImageIcon("asset/play.png"));
    play_button.setBorder(new EmptyBorder(35, 0, 0, 0));
    play_button.setAlignmentX(CENTER_ALIGNMENT);
    play_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    linkPlay(play_button);
    add(play_button);

    JLabel play_label = new JLabel("Click to play 🎮");
    play_label.setForeground(colorScheme.white);
    play_label.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
    play_label.setBorder(new EmptyBorder(35, 0, 0, 0));
    play_label.setAlignmentX(CENTER_ALIGNMENT);
    add(play_label);
  }

  private void linkPlay(JLabel play_button) {
    play_button.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        game.showView(new GamePanel(game));
      }
    });
  }
}
