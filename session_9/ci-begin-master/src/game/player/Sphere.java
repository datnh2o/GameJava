package game.player;

import game.GameObject;
import game.renderer.Renderer;

public class Sphere extends GameObject {
    public Sphere() {
        renderer = new Renderer("assets/images/sphere");
    }

    @Override
    public void run() {
        super.run();
        // this.fire()
    }
}
