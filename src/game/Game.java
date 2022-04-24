package game;

import game.states.SinglePlayerState;
import game.states.GameStateHandler;
import game.states.GameState;
import game.states.HomeScreenState;
import game.states.TwoPlayerState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Jiyansh
 */
public class Game extends JPanel {

    public final JFrame FRAME;

    public static final String TITLE = "Space Shooter!";
    public static final int FRAME_WIDTH = 900;
    public static final int FRAME_HEIGHT = 600;
    public static final int FPS = 60;
    public static final KeyInputManager KEY_INPUT = new KeyInputManager();
    public static final MouseInputManager MOUSE_INPUT = new MouseInputManager();

    public GameStateHandler stateHandler;
    private GameState state;
    private BufferedImage iconImage;

    public Game() {
        FRAME = new JFrame(TITLE);

        try {
            iconImage = ImageIO.read(Game.class.getResource("/res/Game Icon.png"));
        } catch (IOException e) {
            System.out.println(e);
        }

        initialize();
        changeState(GameStateHandler.HOME_SCREEN);      //Make sure to do this AFTER initialize().
    }

    /**
     * Sets up the frame and adds all the game objects to it.
     */
    private void initialize() {
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setResizable(false);
        FRAME.setIconImage(iconImage);
        FRAME.addKeyListener(KEY_INPUT);
        FRAME.addMouseListener(MOUSE_INPUT);
        FRAME.addMouseMotionListener(MOUSE_INPUT);

        /*Sets up the JPanel that everything will be drawn to. This is added to the JFrame and its Graphics g object is passed to every
        render method to draw with. */
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setMaximumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setBackground(Color.black);

        //Need to add to both the frame and the JPanel in case one or the other isn't focused on. Not doing this will lead to incorrect behavior.
        this.addMouseListener(MOUSE_INPUT);
        this.addMouseMotionListener(MOUSE_INPUT);

        FRAME.add(this);
        FRAME.pack();
        FRAME.setLocationRelativeTo(null);      //Must be called after FRAME.pack() to work.
        FRAME.setVisible(true);
    }

    public void changeState(GameStateHandler stateHandler) {
        if (state != null) {
            state.endState();
        }
        this.stateHandler = stateHandler;

        switch (stateHandler) {
            case HOME_SCREEN:
                state = new HomeScreenState(stateHandler, this);
                break;
            case SINGLE_PLAYER:
                state = new SinglePlayerState(1, stateHandler, this);
                break;
            case TWO_PLAYER:
                state = new TwoPlayerState(stateHandler, this);
                break;
        }

        state.startState();
    }

    private void tick() {
        stateHandler.tick();
        state.tick();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        stateHandler.render(g);
    }

    /**
     * Game loop: Repeatedly renders the game at a specified FPS.
     */
    public void startGame() {
        double timePerTick = 1_000_000_000 / FPS;       //Basically how long to wait (in nanoseconds) between each update of game object positions.

        long lastTime = System.nanoTime();
        long now;
        long delta;

        while (true) {
            now = System.nanoTime();
            delta = now - lastTime;

            if (delta >= timePerTick) {                 //Wait until specified amount of timePerTick has passed until allowing an update. 
                tick();
                lastTime = now;
            }
            repaint();                             //Rendering is done constantly as it does not change the FPS since only tick() updates position.
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
