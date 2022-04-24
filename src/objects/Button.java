package objects;

import game.Game;
import game.ObjectID;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jiyansh
 */
public class Button extends GameObject {

    public static final Font DEFAULT_FONT = new Font(Font.MONOSPACED, Font.BOLD, 20);
    public static final int DEFAULT_BORDER_WIDTH = 2;
    public static final Color DEFAULT_BORDER_COLOR = Color.DARK_GRAY;
    public static final Color DEFAULT_BACKGROUND_COLOR = Color.LIGHT_GRAY;
    public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;

    private String text;
    private BufferedImage image;
    private Color backgroundColor;
    private Color borderColor;
    private Color textColor;
    private int borderWidth = -1;
    private Font font;
    private Game game;
    
    
    private boolean hover = false;
    private boolean clicked = false;

    public Button(int x, int y, int width, int height, String text, Game game) {
        super(x, y, width, height, ObjectID.BUTTON);
        this.text = text;
        this.game = game;
    }

    @Override
    public void tick() {
        if (mouseInBounds()) {
            if (game.MOUSE_INPUT.isleftPressed()) {
                clicked = true;
            } else {
                hover = true;
            }
        } else {
            hover = false;
            clicked = false;
        }
    }

    @Override
    public void render(Graphics g) {
        //Border
        if (borderColor == null) {
            g.setColor(DEFAULT_BORDER_COLOR);
        } else {
            g.setColor(borderColor);
        }

        if (borderWidth < 0) {
            g.fillRect(x, y, OBJECT_WIDTH + 2 * DEFAULT_BORDER_WIDTH, OBJECT_HEIGHT + 2 * DEFAULT_BORDER_WIDTH);
        } else {
            g.fillRect(x, y, OBJECT_WIDTH + 2 * borderWidth, OBJECT_HEIGHT + 2 * borderWidth);
        }

        //Background
        if (backgroundColor == null) {
            if (clicked) {
                g.setColor(DEFAULT_BACKGROUND_COLOR.darker());
            } else if (hover) {
                g.setColor(DEFAULT_BACKGROUND_COLOR.brighter());
            } else {
                g.setColor(DEFAULT_BACKGROUND_COLOR);
            }
        } else if (clicked) {
            g.setColor(backgroundColor.darker());
        } else if (hover) {
            g.setColor(backgroundColor.brighter());
        } else {
            g.setColor(backgroundColor);
        }

        if (borderWidth < 0) {
            g.fillRect(x + DEFAULT_BORDER_WIDTH, y + DEFAULT_BORDER_WIDTH, OBJECT_WIDTH, OBJECT_HEIGHT);
        } else {
            g.fillRect(x + borderWidth, y + borderWidth, OBJECT_WIDTH, OBJECT_HEIGHT);
        }

        if (image != null) {
            g.drawImage(image, x, y, null);
        }

        //Text
        if (font == null) {            
            g.setFont(DEFAULT_FONT);
        } else {
            g.setFont(font);
        }

        if (textColor == null) {
            g.setColor(DEFAULT_TEXT_COLOR);
        } else {
            g.setColor(textColor);
        }
        
        int stringX = x + (OBJECT_WIDTH - g.getFontMetrics().stringWidth(text)) / 2;
        int stringY = y + ((OBJECT_HEIGHT - g.getFontMetrics().getHeight()) / 2) + g.getFontMetrics().getAscent();
        
        g.drawString(text, stringX, stringY);
    }

    public boolean mouseInBounds() {
        return this.getHitBox().contains(game.MOUSE_INPUT.getMouseX(), game.MOUSE_INPUT.getMouseY());
    }
    
    //---------------------------------------------[Getters and Setters]----------------------------------------------//
    
    public boolean isClicked() {
        return clicked;
    }
    
    public String getText() {
        return text;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Font getFont() {
        return font;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }
}
