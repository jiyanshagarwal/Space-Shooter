package objects.game_play;

import game.Game;
import game.ObjectID;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import objects.MovingGameObject;

/**
 *
 * @author Jiyansh
 */
public class Bullet extends MovingGameObject {

    public static final int BULLET_WIDTH = 20;
    public static final int BULLET_HEIGHT = 5;
    
    private Game game;    
    private Color color;
    private int damage;
    private int angle;

    /**
     * 
     * @param x Starting x coordinate.
     * @param y Starting y coordinate.
     * @param angle The angle at which the bullet will be fired.
     * @param velocity The velocity of the bullet. Note that the sign of this value does not matter. The bullet will be fired in the direction of the
     * angle.
     * @param damage The bullet's damage.
     * @param id Indicates if this is an enemy or spaceship bullet.
     * @param color The bullet color
     * @param game The game that the bullet is part of.
     */
    public Bullet(int x, int y, int angle, int velocity, int damage, ObjectID id, Color color, Game game) {
        super(x, y, BULLET_WIDTH, BULLET_HEIGHT, (int) (velocity * Math.cos(Math.toRadians(Math.abs(angle)))), 
                (int) -(velocity * Math.sin(Math.toRadians(Math.abs(angle)))), id);
        this.damage = damage;
        this.angle = angle;
        this.color = color;
        this.game = game;
    }

    @Override
    public void tick() {        
        x = clampAt(x += velocityX, 0, Game.FRAME_WIDTH);
        y = clampAt(y += velocityY, 0, Game.FRAME_HEIGHT);
        
        if (y <= 5 || y >= Game.FRAME_HEIGHT - 5 ||
                x <= 5 || x >= Game.FRAME_WIDTH - 5) {            
            game.stateHandler.removeGameObject(this);
        }              
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();
        
        g2d.setColor(color);
        g2d.rotate(Math.toRadians(angle), x + (OBJECT_WIDTH / 2), y + (OBJECT_HEIGHT / 2));
        g2d.fillRect(x, y, OBJECT_WIDTH, OBJECT_HEIGHT);
        
        g2d.setTransform(originalTransform);
    }
    
    public int getDamage() {
        return damage;
    }
}
