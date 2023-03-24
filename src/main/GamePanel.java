package main;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import utility.ColorScheme;
import utility.Database;
import utility.ImageLoader;
import utility.RandomNumber;
import utility.Sound;
import utility.SoundLoader;

public class GamePanel extends JPanel {

  private final Game game;
  private final String homePath = System.getProperty("user.home");
  private final String scoreFile = homePath + "/guess-my-number/data/SCORE.dat";
  private final String highScoreFile =
    homePath + "/guess-my-number/data/HIGHSCORE.dat";

  private transient Sound music;
  private transient Database database;
  private transient RandomNumber randomNumber;
  private transient ImageLoader imageLoader;
  private transient SoundLoader soundLoader;

  private int SCORE, HIGH_SCORE;
  private int UNKNOW_NUMBER, RANGE_NUMBER;
  private int EMPTY_INPUT = 0;

  public GamePanel(Game game) {
    music = new Sound();
    database = new Database();
    randomNumber = new RandomNumber();
    imageLoader = new ImageLoader();
    soundLoader = new SoundLoader();
    UNKNOW_NUMBER = randomNumber.GET_RANDOM_NUMBER();
    RANGE_NUMBER = randomNumber.GET_RANGE_NUMBER();

    this.game = game;

    SCORE = database.showDatabase(scoreFile);
    HIGH_SCORE = database.showDatabase(highScoreFile);

    BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
    this.setLayout(boxlayout);

    Render();
  }

  private void Render() {
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
      new ImageIcon(imageLoader.getImageUrl("src/resource/question.png"))
    );
    unknown_number.setHorizontalTextPosition(SwingConstants.CENTER);
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

    JPanel gridPlayPanel = new JPanel();
    gridPlayPanel.setMaximumSize(new Dimension(260, 50));
    gridPlayPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
    gridPlayPanel.setLayout(new GridLayout(0, 2));
    gridPlayPanel.setOpaque(false);

    JTextField guess_field = new JTextField();
    guess_field.setTransferHandler(null);
    guess_field.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
    guess_field.setBackground(ColorScheme.white);
    guess_field.setForeground(ColorScheme.indigo);
    guess_field.setHorizontalAlignment(SwingConstants.CENTER);
    guess_field.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    gridPlayPanel.add(guess_field);

    JLabel submit_button = new JLabel("Submit");
    submit_button.setBorder(BorderFactory.createEmptyBorder(0, 15, 10, 0));
    submit_button.setIcon(
      new ImageIcon(imageLoader.getImageUrl("src/resource/submit.png"))
    );
    submit_button.setHorizontalTextPosition(SwingConstants.CENTER);
    submit_button.setAlignmentX(CENTER_ALIGNMENT);
    submit_button.setForeground(ColorScheme.white);
    submit_button.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    submit_button.setAlignmentX(CENTER_ALIGNMENT);
    submit_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    gridPlayPanel.add(submit_button);

    add(gridPlayPanel);

    JLabel continue_button = new JLabel("Continue");
    continue_button.setIcon(
      new ImageIcon(imageLoader.getImageUrl("src/resource/button.png"))
    );
    continue_button.setHorizontalTextPosition(SwingConstants.CENTER);
    continue_button.setForeground(ColorScheme.white);
    continue_button.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    continue_button.setAlignmentX(CENTER_ALIGNMENT);
    continue_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    continue_button.setVisible(false);
    continueGame(continue_button);
    add(continue_button);

    JLabel back_button = new JLabel("Back");
    back_button.setIcon(
      new ImageIcon(imageLoader.getImageUrl("src/resource/button.png"))
    );
    back_button.setHorizontalTextPosition(SwingConstants.CENTER);
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

    JPanel gridBottomPanel = new JPanel();
    gridBottomPanel.setMaximumSize(new Dimension(260, 50));
    gridBottomPanel.setAlignmentX(CENTER_ALIGNMENT);
    gridBottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 78, 0, 23));
    gridBottomPanel.setLayout(new GridLayout(0, 2));
    gridBottomPanel.setOpaque(false);

    JLabel hint_button = new JLabel();
    ImageIcon hintImage = new ImageIcon(
      imageLoader.getImageUrl("src/resource/hint.png")
    );
    hint_button.setIcon(
      new ImageIcon(
        hintImage.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)
      )
    );
    hint_button.setHorizontalTextPosition(SwingConstants.CENTER);
    hint_button.setForeground(ColorScheme.white);
    hint_button.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    hint_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    hint_button.setVisible(true);
    hintAnswer(hint_button, status_value, score_label);
    gridBottomPanel.add(hint_button);

    JLabel source_code = new JLabel();
    ImageIcon sourceImage = new ImageIcon(
      imageLoader.getImageUrl("src/resource/github.png")
    );
    source_code.setIcon(
      new ImageIcon(
        sourceImage.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)
      )
    );
    source_code.setCursor(new Cursor(Cursor.HAND_CURSOR));
    source_code.setHorizontalTextPosition(SwingConstants.CENTER);
    source_code.setForeground(ColorScheme.white);
    source_code.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    source_code.setVisible(true);
    sourceCode(source_code);
    gridBottomPanel.add(source_code);

    add(gridBottomPanel);

    submit_button.addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          changeStatusValue(
            gridPlayPanel,
            status_value,
            guess_field,
            unknown_number,
            submit_button,
            continue_button,
            back_button,
            hint_button,
            source_code,
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
            gridPlayPanel,
            status_value,
            guess_field,
            unknown_number,
            submit_button,
            continue_button,
            back_button,
            hint_button,
            source_code,
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

  private void sourceCode(JLabel source_code) {
    source_code.addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          try {
            java.awt.Desktop
              .getDesktop()
              .browse(
                new java.net.URI("https://github.com/PunGrumpy/guess-my-number")
              );
          } catch (java.io.IOException | java.net.URISyntaxException ex) {
            ex.printStackTrace();
          }
        }
      }
    );
  }

  private void hintAnswer(
    JLabel hint_button,
    JLabel status_value,
    JLabel score_label
  ) {
    hint_button.addMouseListener(
      new MouseAdapter() {
        String hint = String.valueOf(Integer.toBinaryString(UNKNOW_NUMBER));

        @Override
        public void mouseClicked(MouseEvent e) {
          int dialogResult = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to use a hint?\nYou will lose 1 score.",
            "Warning",
            JOptionPane.YES_NO_OPTION
          );
          if (dialogResult == JOptionPane.YES_OPTION) {
            if (SCORE >= 1) {
              SCORE--;
              database.setScore(SCORE);
              database.saveDatabase(scoreFile);
              status_value.setText("I'll give you a hint: " + hint);
              status_value.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
              score_label.setText(
                "Score: " + SCORE + "   🏆   High Score: " + HIGH_SCORE
              );
            } else {
              status_value.setText("Not enough score!");
            }
          }
        }
      }
    );
  }

  private void changeStatusValue(
    JPanel gridPlayPanel,
    JLabel status_value,
    JTextField guess_field,
    JLabel unknown_number,
    JLabel submit_button,
    JLabel continue_button,
    JLabel back_button,
    JLabel hint_button,
    JLabel source_code,
    JLabel score_label
  ) {
    status_value.setFont(new Font(Font.DIALOG, Font.BOLD, 25));

    if (guess_field.getText().toLowerCase().equals("cheat")) {
      status_value.setText("The answer is: " + UNKNOW_NUMBER);
      guess_field.setText(String.valueOf(UNKNOW_NUMBER));
      return;
    }

    if (guess_field.getText().equals("I love Suntana")) {
      status_value.setText("Please give me more grade");
      music.soundEffect(
        getClass().getClassLoader().getResource("src/resource/correct.wav")
      );
      unknown_number.setText(String.valueOf(""));
      ImageIcon suntanaImage = new ImageIcon(
        imageLoader.getImageUrl("src/resource/suntana.png")
      );
      unknown_number.setIcon(
        new ImageIcon(
          suntanaImage
            .getImage()
            .getScaledInstance(200, 205, Image.SCALE_SMOOTH)
        )
      );
      gridPlayPanel.setVisible(false);
      submit_button.setVisible(false);
      continue_button.setVisible(true);
      back_button.setVisible(true);
      hint_button.setVisible(false);
      source_code.setVisible(false);
      SCORE++;
      score_label.setText(
        "Score: " + SCORE + "   🏆   High Score: " + HIGH_SCORE
      );
      database.setScore(SCORE);
      database.saveDatabase(scoreFile);
      if (SCORE > HIGH_SCORE) {
        database.setHighScore(SCORE);
        database.saveDatabase(highScoreFile);
      }
      return;
    }

    try {
      Integer.parseInt(guess_field.getText());
    } catch (NumberFormatException e) {
      EMPTY_INPUT++;
      status_value.setText("Please enter a number! [" + EMPTY_INPUT + "/5]");
      guess_field.setText("");

      if (EMPTY_INPUT == 5) {
        JOptionPane.showMessageDialog(
          null,
          "Why you still input empty or string?"
        );

        try {
          Thread.sleep(1000);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }

        try {
          Desktop
            .getDesktop()
            .browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
          EMPTY_INPUT = 0;
          game.showView(new MenuPanel(game));
        } catch (IOException | URISyntaxException ex) {
          ex.printStackTrace();
        }
      }

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
      guess_field.setText("");
      return;
    }

    status_value.setFont(new Font(Font.DIALOG, Font.BOLD, 25));

    if (Integer.parseInt(guess_field.getText()) == UNKNOW_NUMBER) {
      status_value.setText("Correct! You win!");
      music.soundEffect(soundLoader.getSoundUrl("src/resource/correct.wav"));
      unknown_number.setText(String.valueOf("🏆"));
      unknown_number.setForeground(ColorScheme.gold);
      gridPlayPanel.setVisible(false);
      submit_button.setVisible(false);
      continue_button.setVisible(true);
      back_button.setVisible(true);
      hint_button.setVisible(false);
      source_code.setVisible(false);
      SCORE++;
      database.setScore(SCORE);
      database.saveDatabase(scoreFile);
      if (SCORE > HIGH_SCORE) {
        HIGH_SCORE = SCORE;
        database.setHighScore(SCORE);
        database.saveDatabase(highScoreFile);
      }
      score_label.setText(
        "Score: " + SCORE + "   🏆   High Score: " + HIGH_SCORE
      );
    } else if (Integer.parseInt(guess_field.getText()) > UNKNOW_NUMBER) {
      status_value.setText(guess_field.getText() + " is too high!");
      guess_field.setText("");
      music.soundEffect(soundLoader.getSoundUrl("src/resource/incorrect.wav"));
    } else if (Integer.parseInt(guess_field.getText()) < UNKNOW_NUMBER) {
      status_value.setText(guess_field.getText() + " is too low!");
      guess_field.setText("");
      music.soundEffect(soundLoader.getSoundUrl("src/resource/incorrect.wav"));
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(
      new ImageIcon(imageLoader.getImageUrl("src/resource/background_game.png"))
        .getImage(),
      0,
      0,
      getWidth(),
      getHeight(),
      null
    );
  }
}
