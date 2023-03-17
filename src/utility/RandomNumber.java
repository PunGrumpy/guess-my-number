package utility;

import java.security.SecureRandom;

public class RandomNumber {

  private int range;
  private SecureRandom random;
  private int[] ranges = { 1000, 10000, 100000, 1000000 };

  public RandomNumber() {
    random = new SecureRandom();
    range = ranges[random.nextInt(ranges.length)];
  }

  public int GET_RANDOM_NUMBER() {
    return random.nextInt(range) + 1;
  }

  public int GET_RANGE_NUMBER() {
    return range;
  }
}
