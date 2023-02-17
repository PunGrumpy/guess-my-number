package main;

public class Game implements Runnable {

  private GameWindow gameWindow;
  private GamePanel gamePanel;
  private Thread gameThread;
  private final int FPS_SET = 120;

  public Game() {
    gamePanel = new GamePanel();
    gameWindow = new GameWindow(gamePanel);
    gamePanel.requestFocus();
    startGameLoop();
  }

  private void startGameLoop() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  public void run() {
    double timePerFrame = 1_000_000_000.0 / FPS_SET; // nanoseconds
    long lastFrame = System.nanoTime();
    long now = System.nanoTime();
    int frames = 0; // FPS counter
    long lastCheck = System.currentTimeMillis(); // Last time to check FPS

    while (true) {
      now = System.nanoTime();
      if (now - lastFrame >= timePerFrame) {
        gamePanel.repaint();
        lastFrame = now;
        frames++;
      }

      if (System.currentTimeMillis() - lastCheck >= 1000) {
        lastCheck = System.currentTimeMillis();
        System.out.printf("FPS: %d%n", frames);
        frames = 0;
      }
    }
  }
}
