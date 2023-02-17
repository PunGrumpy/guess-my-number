package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {

  private GamePanel gamePanel;

  public MouseInputs(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  public void mouseDragged(MouseEvent e) {
    // When the mouse is dragged
  }

  public void mouseMoved(MouseEvent e) {
    // When the mouse is moved
    gamePanel.setRectPos(e.getX(), e.getY());
  }

  public void mouseClicked(MouseEvent e) {
    // When the mouse is clicked
  }

  public void mousePressed(MouseEvent e) {
    // When the mouse is pressed
  }

  public void mouseReleased(MouseEvent e) {
    // When the mouse is released
  }

  public void mouseEntered(MouseEvent e) {
    // When the mouse enters the window
  }

  public void mouseExited(MouseEvent e) {
    // When the mouse exits the window
  }
}
