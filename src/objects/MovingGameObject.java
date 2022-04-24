package objects;

import game.ObjectID;

/**
 *
 * @author Jiyansh
 */
public abstract class MovingGameObject extends GameObject {

    protected int velocityX, velocityY;

    public MovingGameObject(int x, int y, int width, int height, int velocityX, int velocityY, ObjectID id) {
        super(x, y, width, height, id);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }
    
    public double getVelocity() {
        return Math.sqrt(Math.pow(getVelocityX(), 2) + Math.pow(getVelocityY(), 2));
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
}
