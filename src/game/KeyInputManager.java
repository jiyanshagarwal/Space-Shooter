package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Jiyansh
 */
public class KeyInputManager extends KeyAdapter {

    private boolean UP = false;
    private boolean DOWN = false;
    private boolean LEFT = false;
    private boolean RIGHT = false;

    private boolean W = false;
    private boolean A = false;
    private boolean S = false;
    private boolean D = false;

    private boolean M = false;
    private boolean ONE = false;

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:
                UP = true;
                break;
            case KeyEvent.VK_DOWN:
                DOWN = true;
                break;
            case KeyEvent.VK_LEFT:
                LEFT = true;
                break;
            case KeyEvent.VK_RIGHT:
                RIGHT = true;
                break;
            case KeyEvent.VK_M:
                M = true;
                break;
            case KeyEvent.VK_W:
                W = true;
                break;
            case KeyEvent.VK_A:
                A = true;
                break;
            case KeyEvent.VK_S:
                S = true;
                break;
            case KeyEvent.VK_D:
                D = true;
                break;
            case KeyEvent.VK_1:
                ONE = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:
                UP = false;
                break;
            case KeyEvent.VK_DOWN:
                DOWN = false;
                break;
            case KeyEvent.VK_LEFT:
                LEFT = false;
                break;
            case KeyEvent.VK_RIGHT:
                RIGHT = false;
                break;
            case KeyEvent.VK_M:
                M = false;
                break;
            case KeyEvent.VK_W:
                W = false;
                break;
            case KeyEvent.VK_A:
                A = false;
                break;
            case KeyEvent.VK_S:
                S = false;
                break;
            case KeyEvent.VK_D:
                D = false;
                break;
            case KeyEvent.VK_1:
                ONE = false;
                break;
        }
    }

    public boolean upPressed() {
        return UP;
    }

    public boolean downPressed() {
        return DOWN;
    }

    public boolean leftPressed() {
        return LEFT;
    }

    public boolean rightPressed() {
        return RIGHT;
    }

    public boolean wPressed() {
        return W;
    }

    public boolean aPressed() {
        return A;
    }

    public boolean sPressed() {
        return S;
    }

    public boolean dPressed() {
        return D;
    }

    public boolean mPressed() {
        return M;
    }
    
    public boolean onePressed() {
        return ONE;
    }
}
