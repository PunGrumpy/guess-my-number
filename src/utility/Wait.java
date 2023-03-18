package utility;

public class Wait {
    public int second;

    public Wait(int second) {
        this.second = second;
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
