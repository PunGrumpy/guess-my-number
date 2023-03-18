package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import utility.Wait;
import utility.Music;
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

  private final Game game;
  
  private Database database;
  private RandomNumber randomNumber;
  private ColorScheme colorScheme;
  private Music music = new Music();

  private int SCORE, HIGH_SCORE;
  private int UNKNOW_NUMBER, RANGE_NUMBER;
  private int EMPTY_COUNTER = 0;

  public GamePanel(Game game) {
    database = new Database();
    colorScheme = new ColorScheme();
    randomNumber = new RandomNumber();
    UNKNOW_NUMBER = randomNumber.GET_RANDOM_NUMBER();
    RANGE_NUMBER = randomNumber.GET_RANGE_NUMBER();

    System.out.println("Unknow number: " + UNKNOW_NUMBER);

    this.game = game;
    this.setBackground(colorScheme.indigo);

    SCORE = database.showScore();
    HIGH_SCORE = database.showHighScore();

    BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
    this.setLayout(boxlayout);

    Render();
  }

  private void Render() {
    JPanel gridPanel;
    ColorScheme colorScheme = new ColorScheme();
    
    // Title
    JLabel title = new JLabel("Random Number Between 1 and " + randomNumber.GET_RANGE_NUMBER());
    title.setForeground(colorScheme.white);
    title.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
    title.setBorder(new EmptyBorder(20, 0, 0, 0));
    title.setAlignmentX(CENTER_ALIGNMENT);
    add(title);

    // Label input guess
    JLabel guess_label = new JLabel("Enter your guess: ");
    guess_label.setForeground(colorScheme.white);
    guess_label.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    guess_label.setBorder(new EmptyBorder(25, 0, 0, 0));
    guess_label.setAlignmentX(CENTER_ALIGNMENT);
    add(guess_label);

    gridPanel = new JPanel();
    gridPanel.setBackground(colorScheme.indigo);
    gridPanel.setMaximumSize(new Dimension(300, 75));
    gridPanel.setBorder(BorderFactory.createEmptyBorder(25, 20, 0, 0));
    gridPanel.setLayout(new GridLayout(0, 2));
    gridPanel.setOpaque(false); // make the panel transparent

    // TextField input guess
    JTextField guess_field = new JTextField();
    guess_field.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    guess_field.setBackground(colorScheme.white);
    guess_field.setForeground(colorScheme.indigo);
    guess_field.setHorizontalAlignment(JTextField.CENTER);
    guess_field.setBorder(new EmptyBorder(0, 15, 0, 15));
    gridPanel.add(guess_field);
    
    // Button submit guess
    JButton submit_button = new JButton("Submit");
    submit_button.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    submit_button.setBackground(colorScheme.white);
    submit_button.setForeground(colorScheme.indigo);
    submit_button.setBorder(new EmptyBorder(0, 15, 0, 15));
    submit_button.setAlignmentX(CENTER_ALIGNMENT);
    gridPanel.add(submit_button);

    add(gridPanel);

    // Status label
    JLabel status_label = new JLabel("Status: ");
    status_label.setForeground(colorScheme.white);
    status_label.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    status_label.setBorder(new EmptyBorder(25, 0, 0, 0));
    status_label.setAlignmentX(CENTER_ALIGNMENT);
    add(status_label);

    // Status value
    JLabel status_value = new JLabel("Waiting for input...");
    status_value.setForeground(colorScheme.white);
    status_value.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    status_value.setBorder(new EmptyBorder(25, 0, 0, 0));
    status_value.setAlignmentX(CENTER_ALIGNMENT);
    add(status_value);

    // Score Label
    JLabel score_label = new JLabel("Score: ");
    score_label.setForeground(colorScheme.white);
    score_label.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    score_label.setBorder(new EmptyBorder(25, 0, 0, 0));
    score_label.setAlignmentX(CENTER_ALIGNMENT);
    add(score_label);

    // Score Field
    JTextField score_field = new JTextField();
    score_field.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    score_field.setBackground(colorScheme.white);
    score_field.setForeground(colorScheme.indigo);
    score_field.setHorizontalAlignment(JTextField.CENTER);
    score_field.setBorder(new EmptyBorder(0, 15, 0, 15));
    score_field.setEditable(false);
    score_field.setText(SCORE + "");
    add(score_field);

    // High score Label
    JLabel high_score_label = new JLabel("High Score: ");
    high_score_label.setForeground(colorScheme.white);
    high_score_label.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    high_score_label.setBorder(new EmptyBorder(25, 0, 0, 0));
    high_score_label.setAlignmentX(CENTER_ALIGNMENT);
    add(high_score_label);

    // High score Field
    JTextField high_score_field = new JTextField();
    high_score_field.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    high_score_field.setBackground(colorScheme.white);
    high_score_field.setForeground(colorScheme.indigo);
    high_score_field.setHorizontalAlignment(JTextField.CENTER);
    high_score_field.setBorder(new EmptyBorder(0, 15, 0, 15));
    high_score_field.setEditable(false);
    high_score_field.setText(HIGH_SCORE + "");
    add(high_score_field);

    // Button back
    JButton back_button = new JButton("Back");
    back_button.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    back_button.setBackground(colorScheme.white);
    back_button.setForeground(colorScheme.indigo);
    back_button.setBorder(new EmptyBorder(10, 0, 10, 0));
    back_button.setAlignmentX(CENTER_ALIGNMENT);
    // linkMenu(back_button);
    add(back_button);

    // Button reset
    JButton reset_button = new JButton("Reset");
    reset_button.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    reset_button.setBackground(colorScheme.white);
    reset_button.setForeground(colorScheme.indigo);
    reset_button.setBorder(new EmptyBorder(10, 0, 10, 0));
    reset_button.setAlignmentX(CENTER_ALIGNMENT);
    add(reset_button);

    submit_button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(guess_field.getText().equals("")) {
          System.out.println("Empty");
          status_value.setText("Please enter your guess!");
          status_value.setForeground(colorScheme.red);
          EMPTY_COUNTER++;

          if(EMPTY_COUNTER == 3) {
            status_value.setText("You have entered an empty guess 3 times!");
            status_value.setForeground(colorScheme.red);
            guess_field.setEnabled(false);
            submit_button.setEnabled(false);
            
            new Wait(3);

            int restart = JOptionPane.showConfirmDialog(null, "Do you want to restart the game?", "Restart Game", JOptionPane.YES_NO_OPTION);
            if(restart == JOptionPane.YES_OPTION) {
              game.restartGame();
            } else {
              game.exitGame();
            }
          }
        }

        try {
          Integer.parseInt(guess_field.getText());
        } catch (NumberFormatException ex) {
          status_value.setText("Please enter a number!");
          status_value.setForeground(colorScheme.red);
          return;
        }
        
        if(Integer.parseInt(guess_field.getText()) > RANGE_NUMBER || Integer.parseInt(guess_field.getText()) < 1) {
          status_value.setText("Valid range is 1 to " + RANGE_NUMBER);
          status_value.setForeground(colorScheme.red);
        }

        if(Integer.parseInt(guess_field.getText()) > UNKNOW_NUMBER) {
          status_value.setText("Too High");
          music.soundEffect("asset/incorrect.wav");
        } else if(Integer.parseInt(guess_field.getText()) < UNKNOW_NUMBER) {
          status_value.setText("Too Low");
          music.soundEffect("asset/incorrect.wav");
        } else {
          status_value.setText("Correct ✅");
          music.soundEffect("asset/correct.wav");
          SCORE++;
          database.setScore(SCORE);
          database.saveScore();
          UNKNOW_NUMBER = randomNumber.GET_RANDOM_NUMBER();
          status_value.setText("Waiting for input...");
          status_value.setForeground(colorScheme.white);
          score_field.setText(String.valueOf(SCORE));
          guess_field.setText("");

          if(SCORE > HIGH_SCORE) {
            HIGH_SCORE = SCORE;
            database.setHighScore(HIGH_SCORE);
            database.saveHighScore();
            high_score_field.setText(String.valueOf(HIGH_SCORE));
          }
        }
      }
    });
  }
}