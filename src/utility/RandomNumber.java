package utility;

import java.security.SecureRandom;

public class RandomNumber {

  private int range = 50;
  private SecureRandom random;

  public RandomNumber() {
    random = new SecureRandom();
  }

  public int Get_Random_Number() {
    return random.nextInt(range) + 1;
  }
}
