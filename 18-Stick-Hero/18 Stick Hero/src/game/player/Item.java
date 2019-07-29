package game.player;

import game.GameObject;
import game.Settings;
import game.physics.BoxCollider;
import game.renderer.Renderer;

public class Item extends GameObject {
    public Item() {
        renderer = Settings.item;
        anchor.set(0.5, 0.5);
    }

    @Override
    public void deactive() {
        super.deactive();
    }
}
