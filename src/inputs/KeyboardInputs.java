package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.Game;
import main.GamePanel;

public class KeyboardInputs implements KeyListener {

  private GamePanel gamePanel;

  public KeyboardInputs(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  public void keyTyped(KeyEvent e) {
    // When a key is typed
  }

  public void keyReleased(KeyEvent e) {
    // When a key is released
  }

  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_W:
        gamePanel.changeYDelta(-5);
        break;
      case KeyEvent.VK_S:
        gamePanel.changeYDelta(5);
        break;
      case KeyEvent.VK_A:
        gamePanel.changeXDelta(-5);
        break;
      case KeyEvent.VK_D:
        gamePanel.changeXDelta(5);
        break;
      case KeyEvent.VK_ESCAPE:
        break;
    }
  }
}
