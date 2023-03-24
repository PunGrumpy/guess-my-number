package utility;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Database {

  private final String homePath = System.getProperty("user.home");
  private final String scoreFile = homePath + "/guess-my-number/data/SCORE.dat";
  private final String highScoreFile = homePath + "/guess-my-number/data/HIGHSCORE.dat";
  
  private int score, highScore;

  public Database() {
    createFile(scoreFile);
    createFile(highScoreFile);
    isFileEmpty(scoreFile);
    isFileEmpty(highScoreFile);
    this.score = 0;
    this.highScore = 0;
  }

  private void createFile(String filePath) {
    try {
      File file = new File(filePath);
      if (!file.exists()) {
        file.getParentFile().mkdirs();
        file.createNewFile();
        file.setReadable(false, true);
      }
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }

  private void isFileEmpty(String filePath) {
    try {
      File file = new File(filePath);
      Scanner scan = new Scanner(file);
      if (!scan.hasNextInt()) {
        setDefault(filePath);
      }
      scan.close();
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }

  private void setDefault(String filePath) {
    try {
      File file = new File(filePath);
      Scanner scan = new Scanner(file);
      if (!scan.hasNextInt()) {
        if(filePath.equals(scoreFile)) {
          score = 0;
        } else if(filePath.equals(highScoreFile)) {
          highScore = 0;
        }
        saveDatabase(filePath);
      }
      scan.close();
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }

  public int showDatabase(String filePath) {
    try {
      File file = new File(filePath);
      Scanner scan = new Scanner(file);
      score = scan.nextInt();
      scan.close();
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }

    return score;
  }

  public void saveDatabase(String filePath) {
    try {
      File file = new File(filePath);
      PrintWriter output = new PrintWriter(file);
      if(filePath.equals(scoreFile)) {
        output.println(score);
      } else if(filePath.equals(highScoreFile)) {
        output.println(highScore);
      }
      output.close();
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
}