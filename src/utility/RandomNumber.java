package utility;

import java.util.Random;

public class RandomNumber {

  private Random random;
  private int range = 50;

  public RandomNumber() {
    random = new Random();
  }

  public int GetRandomNumber() {
    return random.nextInt(range) + 1;
  }
}
