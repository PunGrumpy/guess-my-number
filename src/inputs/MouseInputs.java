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

  @Override
  public void mouseDragged(MouseEvent e) {
    // When the mouse is dragged
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    // When the mouse is moved
    gamePanel.setRectPos(e.getX(), e.getY());
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // When the mouse is clicked
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // When the mouse is pressed
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // When the mouse is released
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // When the mouse enters the window
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // When the mouse exits the window
  }
}
