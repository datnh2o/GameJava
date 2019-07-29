package game.explosion;

import game.GameObject;
import game.renderer.Renderer;

public class Explosion extends GameObject {
    public Explosion() {
        renderer = new Renderer(
                "assets/images/enemies/explosion",
                true
        );
    }
}
