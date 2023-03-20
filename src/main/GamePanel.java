package main;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import utility.ColorScheme;
import utility.Database;
import utility.Music;
import utility.RandomNumber;

public class GamePanel extends JPanel {

  private final Game game;

  private Database database;
  private RandomNumber randomNumber;
  private Music music = new Music();

  private int SCORE, HIGH_SCORE;
  private int UNKNOW_NUMBER, RANGE_NUMBER;

  public GamePanel(Game game) {
    database = new Database();
    randomNumber = new RandomNumber();
    UNKNOW_NUMBER = randomNumber.GET_RANDOM_NUMBER();
    RANGE_NUMBER = randomNumber.GET_RANGE_NUMBER();

    this.game = game;
    this.setBackground(ColorScheme.indigo);

    SCORE = database.showScore();
    HIGH_SCORE = database.showHighScore();

    BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
    this.setLayout(boxlayout);

    Render();
  }

  private void Render() {
    JPanel gridPanel;

    JLabel title = new JLabel("Random The Number");
    title.setForeground(ColorScheme.white);
    title.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
    title.setBorder(new EmptyBorder(20, 0, 0, 0));
    title.setAlignmentX(CENTER_ALIGNMENT);
    add(title);

    JLabel subtitle = new JLabel("Between 1 and " + RANGE_NUMBER);
    subtitle.setForeground(ColorScheme.white);
    subtitle.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    subtitle.setBorder(new EmptyBorder(10, 0, 0, 0));
    subtitle.setAlignmentX(CENTER_ALIGNMENT);
    add(subtitle);

    JLabel unknown_number = new JLabel("?");
    unknown_number.setIcon(
      new ImageIcon(
        getClass().getClassLoader().getResource("asset/question.png")
      )
    );
    unknown_number.setHorizontalTextPosition(JLabel.CENTER);
    unknown_number.setForeground(ColorScheme.white);
    unknown_number.setFont(new Font(Font.DIALOG, Font.BOLD, 120));
    unknown_number.setBorder(new EmptyBorder(20, 0, 0, 0));
    unknown_number.setAlignmentX(CENTER_ALIGNMENT);
    add(unknown_number);

    JLabel status_value = new JLabel("Input a number");
    status_value.setForeground(ColorScheme.white);
    status_value.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
    status_value.setBorder(new EmptyBorder(20, 0, 20, 0));
    status_value.setAlignmentX(CENTER_ALIGNMENT);
    add(status_value);

    gridPanel = new JPanel();
    gridPanel.setMaximumSize(new Dimension(260, 50));
    gridPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
    gridPanel.setLayout(new GridLayout(0, 2));
    gridPanel.setOpaque(false);

    JTextField guess_field = new JTextField();
    guess_field.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
    guess_field.setBackground(ColorScheme.white);
    guess_field.setForeground(ColorScheme.indigo);
    guess_field.setHorizontalAlignment(JTextField.CENTER);
    guess_field.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    gridPanel.add(guess_field);

    JLabel submit_button = new JLabel("Submit");
    submit_button.setBorder(BorderFactory.createEmptyBorder(0, 15, 10, 0));
    submit_button.setIcon(
      new ImageIcon(getClass().getClassLoader().getResource("asset/submit.png"))
    );
    submit_button.setHorizontalTextPosition(JLabel.CENTER);
    submit_button.setForeground(ColorScheme.white);
    submit_button.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    submit_button.setAlignmentX(CENTER_ALIGNMENT);
    submit_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    gridPanel.add(submit_button);

    add(gridPanel);

    JLabel continue_button = new JLabel("Continue");
    continue_button.setIcon(
      new ImageIcon(getClass().getClassLoader().getResource("asset/button.png"))
    );
    continue_button.setHorizontalTextPosition(JLabel.CENTER);
    continue_button.setForeground(ColorScheme.white);
    continue_button.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    continue_button.setAlignmentX(CENTER_ALIGNMENT);
    continue_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    continue_button.setVisible(false);
    continueGame(continue_button);
    add(continue_button);

    JLabel back_button = new JLabel("Back");
    back_button.setIcon(
      new ImageIcon(getClass().getClassLoader().getResource("asset/button.png"))
    );
    back_button.setHorizontalTextPosition(JLabel.CENTER);
    back_button.setForeground(ColorScheme.white);
    back_button.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    back_button.setAlignmentX(CENTER_ALIGNMENT);
    back_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    back_button.setVisible(true);
    linkMenu(back_button);
    add(back_button);

    JLabel score_label = new JLabel(
      "Score: " + SCORE + "   🏆   High Score: " + HIGH_SCORE
    );
    score_label.setForeground(ColorScheme.white);
    score_label.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    score_label.setBorder(new EmptyBorder(25, 0, 0, 0));
    score_label.setAlignmentX(CENTER_ALIGNMENT);
    add(score_label);

    submit_button.addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          changeStatusValue(
            gridPanel,
            status_value,
            guess_field,
            unknown_number,
            submit_button,
            continue_button,
            back_button,
            score_label
          );
        }
      }
    );

    guess_field.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          changeStatusValue(
            gridPanel,
            status_value,
            guess_field,
            unknown_number,
            submit_button,
            continue_button,
            back_button,
            score_label
          );
        }
      }
    );
  }

  public void linkMenu(JLabel back_button) {
    back_button.addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          int dialogResult = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to go back to the menu?",
            "Warning",
            JOptionPane.YES_NO_OPTION
          );
          if (dialogResult == JOptionPane.YES_OPTION) {
            game.showView(new MenuPanel(game));
          }
        }
      }
    );
  }

  public void continueGame(JLabel continue_button) {
    continue_button.addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          game.showView(new GamePanel(game));
        }
      }
    );
  }

  private void changeStatusValue(
    JPanel gridPanel,
    JLabel status_value,
    JTextField guess_field,
    JLabel unknown_number,
    JLabel submit_button,
    JLabel continue_button,
    JLabel back_button,
    JLabel score_label
  ) {
    if (guess_field.getText().equals("")) {
      status_value.setText("Please enter a number!");
      return;
    }

    status_value.setFont(new Font(Font.DIALOG, Font.BOLD, 25));

    if (guess_field.getText().equals("cheat")) {
      status_value.setText("The answer is: " + UNKNOW_NUMBER);
      // Copy to clipboard
      StringSelection stringSelection = new StringSelection(
        String.valueOf(UNKNOW_NUMBER)
      );
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      clipboard.setContents(stringSelection, null);
      guess_field.setText("");
      return;
    }

    if (guess_field.getText().equals("I love suntana")) {
      status_value.setText("Please give me more grade");
      music.soundEffect(
        getClass().getClassLoader().getResource("asset/correct.wav")
      );
      unknown_number.setText(String.valueOf(""));
      Image originalImg = new ImageIcon(
        getClass().getClassLoader().getResource("asset/suntana.png")
      )
        .getImage();
      Image resizedImg = originalImg.getScaledInstance(
        200,
        225,
        Image.SCALE_SMOOTH
      );
      unknown_number.setIcon(new ImageIcon(resizedImg));
      unknown_number.setForeground(ColorScheme.gold);
      gridPanel.setVisible(false);
      submit_button.setVisible(false);
      continue_button.setVisible(true);
      back_button.setVisible(true);
      SCORE++;
      score_label.setText(
        "Score: " + SCORE + "   🏆   High Score: " + HIGH_SCORE
      );
      database.setScore(SCORE);
      database.saveScore();
      if (SCORE > HIGH_SCORE) {
        database.setHighScore(SCORE);
        database.saveHighScore();
      }
      return;
    }

    try {
      Integer.parseInt(guess_field.getText());
    } catch (NumberFormatException e) {
      status_value.setText("Please enter a number!");
      return;
    }

    status_value.setFont(new Font(Font.DIALOG, Font.BOLD, 25));

    if (
      Integer.parseInt(guess_field.getText()) > RANGE_NUMBER ||
      Integer.parseInt(guess_field.getText()) < 1
    ) {
      status_value.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
      status_value.setText(
        "Please enter a number between 1 and " + RANGE_NUMBER
      );
      return;
    }

    status_value.setFont(new Font(Font.DIALOG, Font.BOLD, 25));

    if (Integer.parseInt(guess_field.getText()) == UNKNOW_NUMBER) {
      status_value.setText("Correct! You win!");
      music.soundEffect(
        getClass().getClassLoader().getResource("asset/correct.wav")
      );
      unknown_number.setText(String.valueOf("🏆"));
      unknown_number.setForeground(ColorScheme.gold);
      gridPanel.setVisible(false);
      submit_button.setVisible(false);
      continue_button.setVisible(true);
      back_button.setVisible(true);
      SCORE++;
      score_label.setText(
        "Score: " + SCORE + "   🏆   High Score: " + HIGH_SCORE
      );
      database.setScore(SCORE);
      database.saveScore();
      if (SCORE > HIGH_SCORE) {
        database.setHighScore(SCORE);
        database.saveHighScore();
      }
    } else if (Integer.parseInt(guess_field.getText()) > UNKNOW_NUMBER) {
      status_value.setText("Too high!");
      guess_field.setText("");
      music.soundEffect(
        getClass().getClassLoader().getResource("asset/incorrect.wav")
      );
    } else if (Integer.parseInt(guess_field.getText()) < UNKNOW_NUMBER) {
      status_value.setText("Too low!");
      guess_field.setText("");
      music.soundEffect(
        getClass().getClassLoader().getResource("asset/incorrect.wav")
      );
    }
  }
}
