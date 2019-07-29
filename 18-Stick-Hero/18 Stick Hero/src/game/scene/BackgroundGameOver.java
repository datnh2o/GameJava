package game.scene;

import game.GameObject;
import game.KeyEventPress;
import game.Settings;
import game.player.Player;
import game.renderer.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundGameOver extends GameObject {
    public BackgroundGameOver(){
        renderer = Settings.background;
        position.set(Settings.GAME_HEIGHT/2, Settings.GAME_HEIGHT/2);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        paint(g);
    }


    private void paint (Graphics g){
        g.drawImage(Settings.btnReplay, Settings.GAME_WIDTH/2 - 50, Settings.GAME_HEIGHT/2 + 150, null);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setFont(new Font("Trattatello", Font.BOLD, 85));
        g2d.setColor(Color.darkGray);
        g2d.drawString("GAME OVER", 35, 120);

        g2d.setFont(new Font("Trattatello", Font.PLAIN, 60));
        g2d.drawString("SCORE : " + Player.getScore(), 130, 250);

        g2d.setFont(new Font("Trattatello", Font.PLAIN, 60));
        g2d.drawString("ITEM : " + Player.getScoreItem(), 145, 350);
    }

    @Override
    public void run() {
        super.run();
        if (KeyEventPress.isAnyKeyPress){
            SceneManager.signNewScene(new SceneStage1());
        }
    }
}
