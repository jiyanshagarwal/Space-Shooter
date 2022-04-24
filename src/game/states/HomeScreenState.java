package game.states;

import game.Game;
import java.awt.Color;
import java.awt.Font;
import objects.Button;
import objects.Text;

/**
 *
 * @author Jiyansh
 */
public class HomeScreenState extends GameState {
    
    private Button singlePlayerButton;
    private Button twoPlayerButton;

    public HomeScreenState(GameStateHandler state, Game game) {
        super(state, game);
    }

    @Override
    public void startState() {
        //Making the title
        String title = Game.TITLE;
        Font titleFont = new Font(Font.MONOSPACED, Font.BOLD, 50);
        int titleX = (Game.FRAME_WIDTH - Text.getTextWidth(game.getGraphics(), titleFont, title)) / 2;
        int titleY = 50;

        GameStateHandler.HOME_SCREEN.addGameObject(new Text(titleX, titleY, titleFont, Color.CYAN, title, game));

        //Making the buttons
        int buttonWidth = 200;
        int buttonHeight = 50;

        int buttonX = (Game.FRAME_WIDTH - buttonWidth) / 2;
        int buttonY = titleY + Text.getTextHeight(game.getGraphics(), titleFont) + 200;    //Must add the height of the title itself + a gap of 50.
        int button2Y = buttonY + buttonHeight + 50;

        singlePlayerButton = new Button(buttonX, buttonY, buttonWidth, buttonHeight, "Single Player", game);
        twoPlayerButton = new Button(buttonX, button2Y, buttonWidth, buttonHeight, "Two Player", game);
        
        GameStateHandler.HOME_SCREEN.addGameObject(singlePlayerButton);
        GameStateHandler.HOME_SCREEN.addGameObject(twoPlayerButton);
    }

    @Override
    public void tick() {
        if (singlePlayerButton != null && twoPlayerButton != null) {
            if (singlePlayerButton.isClicked()) {
                game.changeState(GameStateHandler.SINGLE_PLAYER);
            }
            else if (twoPlayerButton.isClicked()) {
                game.changeState(GameStateHandler.TWO_PLAYER);
            }
        }
    }
}
