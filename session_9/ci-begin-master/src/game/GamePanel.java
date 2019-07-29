package game;

import game.enemy.Enemy;
import game.enemy.EnemySummoner;
import game.player.Player;
import game.player.item.ItemSummoner;
import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    Player player;
    Background background;

    public GamePanel() {
        background = new Background();
        player = new Player();
//        Enemy enemy = new Enemy();
        EnemySummoner es = new EnemySummoner();
        ItemSummoner is = new ItemSummoner();
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
        fillBackgroundMenu(g);
        drawPlayerInfo(g);
    }

    BufferedImage heartImage = SpriteUtils.loadImage("assets/images/heart.png");
    BufferedImage enemyImage = SpriteUtils.loadImage("assets/images/enemies/level0/pink/0.png");
    Font font = new Font("Verdana", Font.BOLD, 32);

    private void drawPlayerInfo(Graphics g) {
        g.setColor(Color.WHITE);
        String mess = player.hp + "";
        g.drawImage(heartImage, 550, 170, null);

        g.setFont(font);
        g.setColor(player.hp >= 3 ? Color.GREEN : Color.RED);
        g.drawString(mess, 600, 200);

        g.drawImage(enemyImage, 550, 220, null);
        g.setColor(Color.WHITE);
        g.drawString(Settings.score + "", 600, 250);
    }

    private void fillBackgroundMenu(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(
                Settings.BACKGROUND_WIDTH,
                0,
                Settings.GAME_WIDTH - Settings.BACKGROUND_WIDTH,
                Settings.GAME_HEIGHT
        );
    }

    public void gameLoop() {
        long lastTime = 0;
        while (true) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTime > 1000 / 60) {
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
                // >> Player.run(), ..
            }
        }
    }
}
