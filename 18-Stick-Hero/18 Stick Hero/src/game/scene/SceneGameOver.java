package game.scene;

import game.GameObject;
import game.player.Player;
import game.player.Stick;

public class SceneGameOver extends Scene {
    @Override
    public void init() {
        GameObject.recycle(BackgroundGameOver.class);
    }

    @Override
    public void clear() {
        GameObject.clearAll();
        Stick.length = 0;
        Stick.rotateDegree = -180;
        Player.score = 0;
        Player.isUp = true;
        Player.scoreItem = 0;
        Player.countAfterBeginPlay = 0;
    }
}
