package objects;

import game.Game;
import game.ObjectID;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

/**
 *
 * @author Jiyansh
 */
public class Text extends GameObject {

    private String text;
    private Color color;
    private Font font;

    public Text(int x, int y, Font font, Color color, String text, Game game) {
        super(x, y, getTextWidth(game.getGraphics(), font, text), getTextHeight(game.getGraphics(), font), ObjectID.TEXT);
        this.text = text;
        this.font = font;
        this.color = color;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, x, y + OBJECT_HEIGHT);      //OBJECT_HEIGHT is added because strings are drawn from the lower left corner, not upper left
    }

    public static int getTextHeight(Graphics g, Font font) {
        return g.getFontMetrics(font).getHeight();
    }

    public static int getTextWidth(Graphics g, Font font, String text) {
        return g.getFontMetrics(font).stringWidth(text);
    }

    public static int calculateFontSize(int heightInPixels) {
        return (int) (heightInPixels * Toolkit.getDefaultToolkit().getScreenResolution() / 72.0);
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        return color;
    }

    public Font getFont() {
        return font;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFont(Font font) {
        this.font = font;
    }

}
