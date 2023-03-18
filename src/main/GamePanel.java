package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Label; // don't forget delete this becase useless
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import utility.Database;
import utility.Music;
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
  
  private JFrame jframe;
  private Database database;
  private RandomNumber randomNumber;
  private ColorScheme colorScheme;
  private Music music = new Music();

  private int SCORE, HIGH_SCORE;
  private int UNKNOW_NUMBER;
  private int EMPTY_COUNTER = 0;

  public GamePanel(Game game) {
    database = new Database();
    colorScheme = new ColorScheme();
    randomNumber = new RandomNumber();
    UNKNOW_NUMBER = randomNumber.GET_RANDOM_NUMBER();

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

    // Label score
    JLabel score_label = new JLabel("Score: " + SCORE);
    score_label.setForeground(colorScheme.white);
    score_label.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    score_label.setBorder(new EmptyBorder(25, 0, 0, 0));
    score_label.setAlignmentX(CENTER_ALIGNMENT);
    add(score_label);

    // Label high score
    JLabel high_score_label = new JLabel("High Score: " + HIGH_SCORE);
    high_score_label.setForeground(colorScheme.white);
    high_score_label.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
    high_score_label.setBorder(new EmptyBorder(25, 0, 0, 0));
    high_score_label.setAlignmentX(CENTER_ALIGNMENT);
    add(high_score_label);

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
        if(guess_label != null) {
          status_value.setText("Please enter your guess!");
          status_value.setForeground(colorScheme.red);
          EMPTY_COUNTER++;
        }

        if(EMPTY_COUNTER == 3) {
          JOptionPane.showMessageDialog(
                null,
                "You have entered an empty input 3 times. The program will now exit.",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE
              );
              System.exit(0);
        }

        System.out.println(UNKNOW_NUMBER);

        // if(Integer.parseInt(guess_label.getText()) > UNKNOW_NUMBER || Integer.parseInt(guess_label.getText()) < 1) {
        //   status_value.setText("Please enter a number between 1 and " + UNKNOW_NUMBER);
        //   status_value.setForeground(colorScheme.red);
        // }

        // if(Integer.parseInt(guess_label.getText()) > UNKNOW_NUMBER) {
        //   status_value.setText("Please enter a number lower than " + UNKNOW_NUMBER);
        //   status_value.setForeground(colorScheme.red);
        // } else if(Integer.parseInt(guess_label.getText()) < UNKNOW_NUMBER) {
        //   status_value.setText("Please enter a number higher than " + UNKNOW_NUMBER);
        //   status_value.setForeground(colorScheme.red);
        // } else {
        //   status_value.setText("Correct! You have guessed the number!");
        //   status_value.setForeground(colorScheme.green);
        //   SCORE++;
        //   score_label.setText("Score: " + SCORE);
        //   if(SCORE > HIGH_SCORE) {
        //     HIGH_SCORE = SCORE;
        //     high_score_label.setText("High Score: " + HIGH_SCORE);
        //   }
        // }
      }
    });
  }
}