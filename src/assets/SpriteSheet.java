package assets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Loads assets from a sprite sheet of given dimensions.
 * @author Jiyansh
 */
public class SpriteSheet {

    private BufferedImage spriteSheet;
    private int boxSize;

    public SpriteSheet(String location, int boxSize) {
        try {
            spriteSheet = ImageIO.read(SpriteSheet.class.getResource(location));
        } catch (IOException e) {
            System.out.println(e);
        }
        
        this.boxSize = boxSize;
    }
    
    /**
     * Gets an asset from a specified row an column.
     * @param row the row starting from 0.
     * @param column the column starting from 0.
     * @return the image asset at that location.
     */
    public BufferedImage getAsset(int row, int column) {
        return spriteSheet.getSubimage(column * boxSize, row * boxSize, boxSize, boxSize);
    }
}
