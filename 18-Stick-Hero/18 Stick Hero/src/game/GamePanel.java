package game;

import game.player.Player;
import game.player.Stick;
import game.scene.SceneManager;
import game.scene.SceneStage1;
import game.scene.SceneWelcome;
import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    Background background;
    Stick stick;
    Player player;


    public GamePanel() {

        SceneManager.signNewScene((new SceneWelcome()));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(int i = 0; i < GameObject.objects.size(); i++) {
            GameObject object = GameObject.objects.get(i);
            if(object.active) {
                object.render(g);
            }
        }
        // TODO: continue editing

    }



    public void gameLoop() {
        long lastTime = 0;
        while (true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTime > 1000 / 55) {
                // run logic
                this.runAll();
                // render
                this.repaint();
                lastTime = currentTime;
            }
        }
    }

    public void runAll() {
        for (int i = 0; i < GameObject.objects.size(); i++) {
            GameObject object = GameObject.objects.get(i);
            // Player, Background, Enemy
            if(object.active) {
                object.run();
                // GameObject.run()
                // >> Player.run()...
            }
        }
    }
}
