package inputs;

import model.Player;
import ui.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Keyboard implements KeyListener {
    private final GamePanel panel;
    private final Player player;



    public Keyboard(GamePanel gamePanel) {
        this.panel = gamePanel;
        player = gamePanel.getGame().getPlayer();
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }



    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setJumping(true);
                break;
            case KeyEvent.VK_A:
                player.setMoving(true);
                player.setLeft(true);
                break;
            case KeyEvent.VK_S:
                player.setMoving(true);
                player.setDown(true);
                break;
            case KeyEvent.VK_D:
                player.setMoving(true);
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setMoving(false);
                player.setAttacking(true);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                break;
            case KeyEvent.VK_A:
                player.setLeft(false);
                player.setMoving(false);
                break;
            case KeyEvent.VK_S:
                player.setDown(false);
                player.setMoving(false);
                player.setJumping(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                player.setMoving(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setDown(false);
                player.setUp(false);
                player.setLeft(false);
                player.setRight(false);
                break;
        }
    }
}
