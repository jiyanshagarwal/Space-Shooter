package objects.game_play;

import assets.Assets;
import game.Game;
import game.ObjectID;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import objects.GameObject;
import objects.MovingGameObject;

/**
 *
 * @author Jiyansh
 */
public class EnemyBot extends MovingGameObject {

    private static final BufferedImage IMAGE = Assets.ENEMY_BOT;
    private final int DEFAULT_HEALTH;
    
    private int health;
    private int bulletsPerSecond;
    private int tickCounter = 0;
    private Game game;
    private HUD hud;

    public EnemyBot(int x, int y, int velocityX, int velocityY, int health, int bulletsPerSecond, Game game) {
        super(x, y, IMAGE.getWidth(), IMAGE.getHeight(), velocityX, velocityY, ObjectID.ENEMY_BOT);
        this.game = game;
        this.DEFAULT_HEALTH = health;
        this.health = DEFAULT_HEALTH;
        this.bulletsPerSecond = bulletsPerSecond;
        hud = new HUD(x - 25, y - 30, 100, 20, health);
    }

    @Override
    public void tick() {
        x = clampAt(x += velocityX, 0, Game.FRAME_WIDTH - OBJECT_WIDTH);
        y = clampAt(y += velocityY, 0, Game.FRAME_HEIGHT - OBJECT_HEIGHT);
        
        if (x <= 0 || x >= Game.FRAME_WIDTH - OBJECT_WIDTH) {
            velocityX = -velocityX;
        }
        if (y <= 0 || y >= Game.FRAME_HEIGHT - OBJECT_HEIGHT) {
            velocityY = -velocityY;
        }
        
        tickCounter++;
        
        if (tickCounter >= (int) (Game.FPS / bulletsPerSecond)) {
            game.stateHandler.addGameObject(new Bullet(x + 11, y + OBJECT_HEIGHT, 270, 20, 1, ObjectID.ENEMY_BOT_BULLET, Color.RED, game));
            game.stateHandler.addGameObject(new Bullet(x + 34, y + OBJECT_HEIGHT, 270, 20, 1, ObjectID.ENEMY_BOT_BULLET, Color.RED, game));
            tickCounter = 0;
        }
        
        resolveBulletCollision();
        hud.setHealth(health);
        hud.setX(x - 25);
        hud.setY(y - 30);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(IMAGE, x, y, null);
        hud.render(g);
    }
    
    private void resolveBulletCollision() {
        for (int i = 0; i < game.stateHandler.getGameObjects().size(); i++) {
            GameObject obj = game.stateHandler.getGameObjects().get(i);

            if (obj.ID == ObjectID.SPACESHIP_BULLET) {
                if (this.isCollidingWith(obj)) {
                    game.stateHandler.removeGameObject(obj);
                    health = clampAt(health -= 1, 0, DEFAULT_HEALTH);
                }
            }
        }
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
}
