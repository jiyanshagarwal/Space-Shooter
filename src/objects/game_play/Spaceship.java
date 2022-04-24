package objects.game_play;

import assets.Assets;
import game.ControlType;
import game.Game;
import game.ObjectID;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import objects.GameObject;
import objects.MovingGameObject;

/**
 *
 * @author Jiyansh
 */
public class Spaceship extends MovingGameObject {

    public static final BufferedImage DEFAULT_IMAGE = Assets.SPACESHIP;
    public static final int DEFAULT_HEALTH = 5;
    public static final ControlType DEFAULT_CONTROL_TYPE = ControlType.ARROW_KEYS;
    public static final HUD DEFAULT_HUD = new HUD(10, Game.FRAME_HEIGHT - 70, 210, 60, DEFAULT_HEALTH);
    public static final Rectangle DEFAULT_MOVEMENT_BOUNDS = new Rectangle(0, 0, Game.FRAME_WIDTH, Game.FRAME_HEIGHT);
    public static final int DEFAULT_DAMAGE = 1;
    public static final int DEFAULT_BULLETS_PER_SECOND = 5;

    public static final int DEFAULT_BULLET_ANGLE = 0;
    public static final int DEFAULT_BULLET_VELOCITY = 10;
    public static final Color DEFAULT_BULLET_COLOR = Color.RED;

    private int dx = 0;
    private int dy = 0;
    private Game game;

    private BufferedImage image;
    private ControlType controls;
    private Rectangle movementBound;
    private int health;
    private HUD hud;
    private int damage;
    private int bulletsPerSecond;

    private int bulletAngle;
    private int bulletVelocity;
    private Color bulletColor;

    private int tickCounter = 0;

    public Spaceship(int x, int y, int velocityX, int velocityY, ObjectID id, Game game) {
        super(x, y, DEFAULT_IMAGE.getWidth(), DEFAULT_IMAGE.getHeight(), Math.abs(velocityX), Math.abs(velocityY), id);
        this.game = game;

        this.image = DEFAULT_IMAGE;
        this.controls = DEFAULT_CONTROL_TYPE;
        this.health = DEFAULT_HEALTH;
        this.hud = DEFAULT_HUD;
        this.movementBound = DEFAULT_MOVEMENT_BOUNDS;
        this.bulletsPerSecond = DEFAULT_BULLETS_PER_SECOND;

        this.bulletAngle = DEFAULT_BULLET_ANGLE;
        this.bulletVelocity = DEFAULT_BULLET_VELOCITY;
        this.bulletColor = DEFAULT_BULLET_COLOR;
    }

    @Override
    public void tick() {
        if (controls.upPressed()) {
            dy = -velocityY;
        } else if (controls.downPressed()) {
            dy = velocityY;
        } else {
            dy = 0;
        }

        if (controls.leftPressed()) {
            dx = -velocityX;
        } else if (controls.rightPressed()) {
            dx = velocityX;
        } else {
            dx = 0;
        }

        tickCounter++;

        if (controls.spacePressed() && tickCounter >= (int) (Game.FPS / bulletsPerSecond)) {
            Bullet bullet1;
            Bullet bullet2;

            if (this.ID == ObjectID.SPACESHIP) {
                bullet1 = new Bullet(x + 6, y, bulletAngle, bulletVelocity, damage, ObjectID.SPACESHIP_BULLET, bulletColor, game);
                bullet2 = new Bullet(x + 27, y, bulletAngle, bulletVelocity, damage, ObjectID.SPACESHIP_BULLET, bulletColor, game);
            } else {
                bullet1 = new Bullet(x + 5, y + OBJECT_HEIGHT, bulletAngle, bulletVelocity, damage, ObjectID.SPACESHIP_2_BULLET, bulletColor, game);
                bullet2 = new Bullet(x + 26, y + OBJECT_HEIGHT, bulletAngle, bulletVelocity, damage, ObjectID.SPACESHIP_2_BULLET, bulletColor, game);
            }

            game.stateHandler.addGameObject(bullet1);
            game.stateHandler.addGameObject(bullet2);
            tickCounter = 0;
        }

        x = clampAt(x += dx, (int) (movementBound.getX()), (int) (movementBound.getWidth() - OBJECT_WIDTH));
        y = clampAt(y += dy, (int) (movementBound.getY()), (int) (movementBound.getHeight() - OBJECT_HEIGHT));

        resolveBulletCollision();
        hud.setHealth(health);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, null);
        hud.render(g);
    }

    private void resolveBulletCollision() {
        for (int i = 0; i < game.stateHandler.getGameObjects().size(); i++) {
            GameObject obj = game.stateHandler.getGameObjects().get(i);

            if (obj.ID == ObjectID.ENEMY_BOT_BULLET) {
                if (this.isCollidingWith(obj)) {
                    game.stateHandler.removeGameObject(obj);
                    health = clampAt(health -= ((Bullet) obj).getDamage(), 0, Integer.MAX_VALUE);
                }
            } else if (this.ID == ObjectID.SPACESHIP && obj.ID == ObjectID.SPACESHIP_2_BULLET) {
                if (this.isCollidingWith(obj)) {
                    game.stateHandler.removeGameObject(obj);
                    health = clampAt(health -= ((Bullet) obj).getDamage(), 0, Integer.MAX_VALUE);
                }
            } else if (this.ID == ObjectID.SPACESHIP_2 && obj.ID == ObjectID.SPACESHIP_BULLET) {
                if (this.isCollidingWith(obj)) {
                    game.stateHandler.removeGameObject(obj);
                    health = clampAt(health -= ((Bullet) obj).getDamage(), 0, Integer.MAX_VALUE);
                }
            }
        }
    }

    //---------------------------------------------[Getters and Setters]----------------------------------------------//
    public int getHealth() {
        return health;
    }

    public BufferedImage getImage() {
        return image;
    }

    public ControlType getControlType() {
        return controls;
    }

    public Rectangle getMovementBound() {
        return movementBound;
    }

    public HUD getHUD() {
        return hud;
    }

    public int getDamage() {
        return damage;
    }

    public int getBulletsPerSecond() {
        return bulletsPerSecond;
    }

    public int getBulletAngle() {
        return bulletAngle;
    }

    public int getBulletVelocity() {
        return bulletVelocity;
    }

    public Color getBulletColor() {
        return bulletColor;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setControlType(ControlType controls) {
        this.controls = controls;
    }

    public void setMovementBound(Rectangle movementBound) {
        this.movementBound = movementBound;
    }

    public void setHUD(HUD hud) {
        this.hud = hud;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setBulletsPerSecond(int bulletsPerSecond) {
        this.bulletsPerSecond = bulletsPerSecond;
    }

    public void setBulletAngle(int bulletAngle) {
        this.bulletAngle = bulletAngle;
    }

    public void setBulletVelocity(int bulletVelocity) {
        this.bulletVelocity = bulletVelocity;
    }

    public void setBulletColor(Color bulletColor) {
        this.bulletColor = bulletColor;
    }
}
