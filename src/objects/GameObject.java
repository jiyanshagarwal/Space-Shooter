package objects;

import game.ObjectID;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Jiyansh
 */
public abstract class GameObject {

    protected int x, y;
    public final int OBJECT_WIDTH, OBJECT_HEIGHT;
    public final ObjectID ID;

    public GameObject(int x, int y, int width, int height, ObjectID id) {
        this.x = x;
        this.y = y;
        OBJECT_WIDTH = width;
        OBJECT_HEIGHT = height;
        this.ID = id;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public Rectangle getHitBox() {
        return new Rectangle(x, y, OBJECT_WIDTH, OBJECT_HEIGHT);
    }

    public boolean isCollidingWith(GameObject obj) {
        return this.getHitBox().intersects(obj.getHitBox());
    }

    public int clampAt(int num, int min, int max) {
        if (num < max) {
            if (num >= min) {
                return num;
            }
            return min;
        }
        return max;
    }

    public int getXLocation() {
        return x;
    }

    public int getYLocation() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
