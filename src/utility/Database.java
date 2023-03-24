package utility;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Database {

  private int score, highScore;
  private String scoreFile = "data/SCORE.dat";
  private String highScoreFile = "data/HIGH_SCORE.dat";

  public Database() {
    createFile();
    isFileEmpty();
    this.score = 0;
    this.highScore = 0;
  }

  private void createFile() {
    try {
      File file = new File(scoreFile);
      if (!file.exists()) {
        file.getParentFile().mkdirs();
        file.createNewFile();
        file.setReadable(false, true);
      }
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }

    try {
      File file = new File(highScoreFile);
      if (!file.exists()) {
        file.getParentFile().mkdirs();
        file.createNewFile();
        file.setReadable(false, true);
      }
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }

  private void isFileEmpty() {
    try {
      File file = new File(scoreFile);
      Scanner scan = new Scanner(file);
      if (!scan.hasNextInt()) {
        setDefault();
      }
      scan.close();
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }

    try {
      File file = new File(highScoreFile);
      Scanner scan = new Scanner(file);
      if (!scan.hasNextInt()) {
        setDefault();
      }
      scan.close();
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }

  private void setDefault() {
    try {
      File file = new File(scoreFile);
      Scanner scan = new Scanner(file);
      if (!scan.hasNextInt()) {
        score = 0;
        saveScore();
      }
      scan.close();
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }

    try {
      File file = new File(highScoreFile);
      Scanner scan = new Scanner(file);
      if (!scan.hasNextInt()) {
        highScore = 0;
        saveHighScore();
      }
      scan.close();
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void setHighScore(int highScore) {
    this.highScore = highScore;
  }

  public int showScore() {
    try {
      File file = new File(scoreFile);
      Scanner scan = new Scanner(file);
      score = scan.nextInt();
      scan.close();
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }

    return score;
  }

  public int showHighScore() {
    try {
      File file = new File(highScoreFile);
      Scanner scan = new Scanner(file);
      highScore = scan.nextInt();
      scan.close();
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }

    return highScore;
  }

  public void saveScore() {
    try {
      File file = new File(scoreFile);
      PrintWriter output = new PrintWriter(file);
      output.println(score);
      output.close();
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }

  public void saveHighScore() {
    try {
      File file = new File(highScoreFile);
      PrintWriter output = new PrintWriter(file);
      output.println(highScore);
      output.close();
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }
}
