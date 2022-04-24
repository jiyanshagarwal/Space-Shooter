package assets;

import java.awt.image.BufferedImage;

/**
 *
 * @author Jiyansh
 */
public class Assets {

    private static final SpriteSheet SHEET = new SpriteSheet("/res/Sprite Sheet.png", 50);

    public static final BufferedImage SPACESHIP = SHEET.getAsset(0, 0);
    public static final BufferedImage ENEMY_BOT = SHEET.getAsset(0, 1);
    public static final BufferedImage SPACESHIP_2 = SHEET.getAsset(0, 2);
}
