package objects.game_play;

import game.ObjectID;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import objects.GameObject;

/**
 *
 * @author Jiyansh
 */
public class HUD extends GameObject {
    
    public final int FONT_SIZE;
    private final int DEFAULT_HEALTH;
    private final int LIFE_BAR_LENGTH;
    private final int BORDER;

    private int health;

    /**
     * Note that the height is the total height, including the text. The health bar will only take up 1/2 of the total height. The text will take
     * up 1/3 of the height
     * @param x the horizontal position of the display
     * @param y the vertical position of the display
     * @param width width of display
     * @param height height of display. 
     * @param defaultHealth the starting or full health level.
     */
    public HUD(int x, int y, int width, int height, int defaultHealth) {
        super(x, y, width, height, ObjectID.HUD);

        FONT_SIZE = (int) ((height / 3.0) * Toolkit.getDefaultToolkit().getScreenResolution() / 72.0);        
        BORDER = (int) (((height / 2.0) - ((height / 2.0) / 1.5)) / 2);
        LIFE_BAR_LENGTH = (width - 2 * BORDER) / defaultHealth;
        
        DEFAULT_HEALTH = defaultHealth;
        health = DEFAULT_HEALTH;        
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, OBJECT_WIDTH, (int) (OBJECT_HEIGHT / 2.0));

        if (health >= 0) {
            g.setColor(new Color((int) ((1 - (1.0 * health / DEFAULT_HEALTH)) * 255), (int) ((1.0 * health / DEFAULT_HEALTH) * 255), 0));
            g.fillRect(x + BORDER, y + BORDER, LIFE_BAR_LENGTH * health, (int) (OBJECT_HEIGHT / 2.0) - 2 * BORDER);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, FONT_SIZE));
        g.drawString("Lives: " + health, x, y + OBJECT_HEIGHT);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
