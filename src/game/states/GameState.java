package game.states;

import game.Game;

/**
 *
 * @author Jiyansh
 */
public abstract class GameState {
    protected final Game game;
    protected final GameStateHandler state;
    
    public GameState(GameStateHandler state, Game game) {
        this.state = state;
        this.game = game;
    }
    
    public abstract void startState();
    
    public void endState() {
        state.removeAllGameObjects();
    }
    
    public abstract void tick();
}
