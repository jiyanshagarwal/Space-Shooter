package game.states;

import game.ControlType;
import game.Game;
import game.ObjectID;
import java.awt.Color;
import java.awt.Rectangle;
import objects.GameObject;
import objects.game_play.EnemyBot;
import objects.game_play.HUD;
import objects.game_play.Spaceship;

/**
 * This class is for designing custom single player mode.
 *
 * @author Jiyansh
 */
public class SinglePlayerState extends GameState {

    private final int difficulty;

    public SinglePlayerState(int difficulty, GameStateHandler state, Game game) {
        super(state, game);
        this.difficulty = difficulty;
    }

    @Override
    public void startState() {
        int spaceshipHealth = 6;
        Rectangle spaceshipBounds = new Rectangle(0, 400, Game.FRAME_WIDTH, Game.FRAME_HEIGHT);
        HUD hud = new HUD(10, Game.FRAME_HEIGHT - 70, 210, 60, spaceshipHealth);
        
        Spaceship spaceship = new Spaceship(450, 500, 10, 5, ObjectID.SPACESHIP, game);
        
        spaceship.setControlType(ControlType.ARROW_KEYS);
        spaceship.setMovementBound(spaceshipBounds);
        spaceship.setHealth(spaceshipHealth);
        spaceship.setHUD(hud);
        spaceship.setDamage(1);
        spaceship.setBulletsPerSecond(6);
        spaceship.setBulletAngle(90);
        spaceship.setBulletVelocity(20);
        spaceship.setBulletColor(Color.YELLOW);
        
        GameStateHandler.SINGLE_PLAYER.addGameObject(spaceship);
        GameStateHandler.SINGLE_PLAYER.addGameObject(new EnemyBot(0, 100, 10, 0, 5, 3, game));
        GameStateHandler.SINGLE_PLAYER.addGameObject(new EnemyBot(0, 170, 5, 0, 5, 3, game));
    }

    @Override
    public void tick() {
        for (int i = 0; i < GameStateHandler.SINGLE_PLAYER.getGameObjects().size(); i++) {
            GameObject obj = GameStateHandler.SINGLE_PLAYER.getGameObjects().get(i);

            if (obj.ID == ObjectID.SPACESHIP) {
                if (((Spaceship) obj).getHealth() == 0) {
                    //endState();
                    System.out.println("Enermy Wins!");
                }
            } else if (obj.ID == ObjectID.ENEMY_BOT) {
                if (((EnemyBot) obj).getHealth() == 0) {
                    //endState();
                    System.out.println("Spaceship Wins!");
                }
            }
        }
    }
}
