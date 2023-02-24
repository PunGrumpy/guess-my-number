package utility;

import java.security.SecureRandom;

public class RandomNumber {

  private int range = 50;
  private SecureRandom random;

  public RandomNumber() {
    random = new SecureRandom();
  }

  public int GET_RANDOM_NUMBER() {
    return random.nextInt(range) + 1;
  }
}
