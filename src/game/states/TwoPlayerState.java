package game.states;

import assets.Assets;
import game.ControlType;
import game.Game;
import game.ObjectID;
import java.awt.Color;
import java.awt.Rectangle;
import objects.game_play.HUD;
import objects.game_play.Spaceship;

/**
 *
 * @author Jiyansh
 */
public class TwoPlayerState extends GameState {

    public TwoPlayerState(GameStateHandler state, Game game) {
        super(state, game);
    }

    @Override
    public void startState() {
        int health = 6;

        Rectangle bounds = new Rectangle(0, 400, Game.FRAME_WIDTH, Game.FRAME_HEIGHT);
        Rectangle bounds2 = new Rectangle(0, 0, Game.FRAME_WIDTH, 200);

        HUD hud = new HUD(10, Game.FRAME_HEIGHT - 70, 210, 60, health);
        HUD hud2 = new HUD(10, 10, 210, 60, health);

        Spaceship spaceship = new Spaceship(450, 500, 10, 5, ObjectID.SPACESHIP, game);
        Spaceship spaceship2 = new Spaceship(450, 500, 10, 5, ObjectID.SPACESHIP_2, game);

        spaceship.setControlType(ControlType.ARROW_KEYS);
        spaceship.setMovementBound(bounds);
        spaceship.setHealth(health);
        spaceship.setHUD(hud);
        spaceship.setDamage(1);
        spaceship.setBulletsPerSecond(6);
        spaceship.setBulletAngle(90);
        spaceship.setBulletVelocity(20);
        spaceship.setBulletColor(Color.YELLOW);

        spaceship2.setImage(Assets.SPACESHIP_2);
        spaceship2.setControlType(ControlType.WASD);
        spaceship2.setMovementBound(bounds2);
        spaceship2.setHealth(health);
        spaceship2.setHUD(hud2);
        spaceship2.setDamage(1);
        spaceship2.setBulletsPerSecond(6);
        spaceship2.setBulletAngle(270);
        spaceship2.setBulletVelocity(20);
        spaceship2.setBulletColor(Color.GREEN);

        GameStateHandler.TWO_PLAYER.addGameObject(spaceship);
        GameStateHandler.TWO_PLAYER.addGameObject(spaceship2);
    }

    @Override
    public void tick() {
    }
}
