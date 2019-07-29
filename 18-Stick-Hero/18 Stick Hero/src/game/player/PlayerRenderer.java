package game.player;

import game.GameObject;
import game.Settings;
import game.renderer.Renderer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class PlayerRenderer extends Renderer {
//    Renderer waiting = new Renderer("assets/images/players/player waiting");
//    Renderer tapstick = new Renderer("assets/images/players/player tap stick");
//    Renderer kickstick = new Renderer("assets/images/players/player kick stick");
//    Renderer running = new Renderer("assets/images/players/player run");
    Renderer current;
    Player player;
    public PlayerRenderer(Player player) {
        current = Settings.waiting;
        this.player = player;
    }

    public PlayerRenderer(String url) {
        super(url);
    }

    public PlayerRenderer(String url, boolean isOnce) {
        super(url, isOnce);
    }

    @Override
    public void render(Graphics g, GameObject master) {

        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        if (!player.waiting && !player.getIsUp()) {
            g2d.translate(0, (player.position.y + Settings.PLAYER_HEIGHT/2) *2);
            g2d.scale(1, -1);
        }

        if (player.waiting && Stick.length != 0 && Stick.rotateDegree == -180) {
            current = Settings.tapStick;
        } else if (player.waiting && Stick.length != 0 && Stick.rotateDegree > -180 && Stick.rotateDegree < -90){
            current = Settings.kickStick;
        } else if (!player.waiting && Stick.length != 0 && Stick.rotateDegree == -90) {
            current = Settings.running;
        } else {
            current = Settings.waiting;
        }

        current.render(g2d, master);

        g2d.setTransform(old);
    }
}
