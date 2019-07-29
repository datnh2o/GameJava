package game.scene;

import game.Background;
import game.GameObject;
import game.player.Player;
import game.player.Stick;

public class SceneStage1 extends Scene {
    @Override
    public void init() {
        GameObject.recycle(Background.class);
        GameObject.recycle(Player.class);
//        GameObject.recycle(Stick.class);
    }

    @Override
    public void clear() {
        GameObject.clearAll();
    }
}
