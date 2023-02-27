package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import utility.Database;
import utility.RandomNumber;

class ColorScheme {

  protected Color red = new Color(255, 0, 0);
  protected Color green = new Color(0, 255, 0);
  protected Color white = new Color(250, 250, 250);
  protected Color indigo = new Color(64, 66, 88); // rgb(64, 66, 88)
  protected Color blueGray = new Color(71, 78, 104); // rgb(71, 78, 104)
  protected Color ligthBlueGray = new Color(80, 87, 122); // rgb(80, 87, 122)
  protected Color brigthBlueGray = new Color(107, 114, 142); // rgb(107, 114, 142)
}

public class GamePanel extends JPanel {

  private JFrame jframe;
  private Database database;
  private RandomNumber randomNumber;
  private ColorScheme colorScheme;

  private int SCORE, HIGH_SCORE;
  private int UNKNOW_NUMBER;

  public GamePanel(Game game) {
    jframe = game.jframe;
    database = new Database();
    colorScheme = new ColorScheme();
    randomNumber = new RandomNumber();
    UNKNOW_NUMBER = randomNumber.GET_RANDOM_NUMBER();

    SCORE = database.showScore();
    HIGH_SCORE = database.showHighScore();

    BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
    this.setLayout(boxlayout);

    this.setBackground(colorScheme.indigo);
    jframe.getContentPane().setBackground(colorScheme.indigo);

    Render();

    jframe.setLocationRelativeTo(null);
  }

  private void Render() {
    // Title
    Label label_0 = new Label("Random Number Between 1 and 50");
    label_0.setForeground(colorScheme.white);
    label_0.setBounds(160, 40, 800, 20);
    label_0.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    jframe.add(label_0);

    // Label input
    Label label_1 = new Label("Enter your guess: ");
    label_1.setForeground(colorScheme.white);
    label_1.setBounds(170, 100, 230, 30);
    label_1.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    jframe.add(label_1);

    // Field input
    TextField text_1 = new TextField();
    text_1.setBackground(colorScheme.brigthBlueGray);
    text_1.setBounds(400, 100, 80, 30);
    text_1.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    jframe.add(text_1);

    // Button input
    JButton button_1 = new JButton("Guess");
    button_1.setBounds(290, 150, 100, 50);
    button_1.setBorder(new LineBorder(colorScheme.blueGray, 2));
    button_1.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    jframe.add(button_1);

    // Button action
    Label label_2 = new Label("Check: ");
    label_2.setForeground(colorScheme.white);
    label_2.setBounds(210, 220, 100, 30);
    label_2.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    jframe.add(label_2);

    // Check input
    TextField text_2 = new TextField();
    text_2.setBackground(colorScheme.brigthBlueGray);
    text_2.setBounds(320, 220, 150, 30);
    text_2.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    jframe.add(text_2);
    text_2.setText("-");

    // Score label
    Label label_3 = new Label("Score: ");
    label_3.setForeground(colorScheme.white);
    label_3.setBounds(220, 260, 100, 30);
    label_3.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    jframe.add(label_3);

    // Highscore field
    TextField text_3 = new TextField();
    text_3.setBackground(colorScheme.brigthBlueGray);
    text_3.setBounds(320, 260, 150, 30);
    text_3.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    jframe.add(text_3);
    text_3.setText(SCORE + "");

    // Highscore label
    Label label_4 = new Label("High Score: ");
    label_4.setForeground(colorScheme.white);
    label_4.setBounds(170, 300, 130, 30);
    label_4.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    jframe.add(label_4);

    // Highscore field
    TextField text_4 = new TextField();
    text_4.setBackground(colorScheme.brigthBlueGray);
    text_4.setBounds(320, 300, 150, 30);
    text_4.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    jframe.add(text_4);
    text_4.setText(HIGH_SCORE + "");

    // Reset button
    JButton button_2 = new JButton("Reset");
    button_2.setBounds(290, 370, 100, 50);
    button_2.setBorder(new LineBorder(colorScheme.blueGray, 2));
    button_2.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    jframe.add(button_2);

    // Exit button
    JButton button_3 = new JButton("Exit");
    button_3.setBounds(290, 430, 100, 50);
    button_3.setBorder(new LineBorder(colorScheme.blueGray, 2));
    button_3.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    jframe.add(button_3);

    button_1.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (
            Integer.parseInt(text_1.getText()) > 50 ||
            Integer.parseInt(text_1.getText()) < 1
          ) {
            text_2.setText("Invalid");
            text_2.setForeground(colorScheme.red);
            return;
          }

          if (Integer.parseInt(text_1.getText()) > UNKNOW_NUMBER) {
            text_2.setText("Too High");
          } else if (Integer.parseInt(text_1.getText()) < UNKNOW_NUMBER) {
            text_2.setText("Too Low");
          } else {
            text_2.setText("Correct");
            text_2.setForeground(colorScheme.green);
            SCORE++;
            database.setScore(SCORE);
            database.saveScore();
            UNKNOW_NUMBER = randomNumber.GET_RANDOM_NUMBER();
            text_2.setForeground(colorScheme.white);
            text_3.setText(String.valueOf(SCORE));
            if (SCORE > HIGH_SCORE) {
              HIGH_SCORE = SCORE;
              database.setHighScore(HIGH_SCORE);
              database.saveHighScore();
              text_4.setText(String.valueOf(HIGH_SCORE));
            }
          }
        }
      }
    );

    button_2.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          text_3.setText("-");
          SCORE = 0;
          UNKNOW_NUMBER = randomNumber.GET_RANDOM_NUMBER();
          text_2.setText("-");
          text_1.setText("");
        }
      }
    );

    button_3.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          int exit = javax.swing.JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to exit?",
            "Exit",
            JOptionPane.YES_NO_OPTION
          );
          if (exit == javax.swing.JOptionPane.YES_OPTION) {
            System.exit(0);
          }
        }
      }
    );
  }
}
