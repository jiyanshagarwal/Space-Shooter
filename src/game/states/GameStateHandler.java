package game.states;

import java.awt.Graphics;
import java.util.ArrayList;
import objects.GameObject;

/**
 *
 * @author Jiyansh
 */
public enum GameStateHandler {
    HOME_SCREEN, 
    SINGLE_PLAYER, 
    TWO_PLAYER, 
    SETTINGS, 
    PAUSED;
    
    private final ArrayList<GameObject> GAME_OBJECTS;
    
    GameStateHandler() {
        GAME_OBJECTS = new ArrayList<>();
    }
    
    public void addGameObject(GameObject obj) {
        GAME_OBJECTS.add(obj);
    }

    public void removeGameObject(GameObject obj) {
        GAME_OBJECTS.remove(obj);
    }
    
    public void removeAllGameObjects() {
        GAME_OBJECTS.clear();
    }

    public ArrayList<GameObject> getGameObjects() {
        return GAME_OBJECTS;
    }
    
    public void tick() {
        //Do not use an enhanced for loop because then bullets can't be removed on the fly! It will throw a java.util.concurrentmodificationexception
        for (int i = 0; i < GAME_OBJECTS.size(); i++) {
            GAME_OBJECTS.get(i).tick();
        }
    }
    public void render(Graphics g) {
        //Do not use an enhanced for loop because then bullets can't be removed on the fly! It will throw a java.util.concurrentmodificationexception
        for (int i = 0; i < GAME_OBJECTS.size(); i++) {
            GAME_OBJECTS.get(i).render(g);
        }
    }
}
