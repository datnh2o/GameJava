package game.scene;

import game.GameObject;
import game.KeyEventPress;
import game.Settings;
import game.renderer.Renderer;
import tklibs.AudioUtils;
import tklibs.SpriteUtils;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundWelcome extends GameObject {

    public BackgroundWelcome(){
        renderer = Settings.background;
        position.set(Settings.GAME_HEIGHT/2, Settings.GAME_HEIGHT/2);
        AudioUtils.replay(Settings.bgSound);

    }

    @Override
    public void reset(){
        super.reset();
        AudioUtils.replay(Settings.bgSound);
    }


    @Override
    public void render(Graphics g) {
        super.render(g);
        paint(g);
    }


    private void paint (Graphics g){
        g.drawImage(Settings.btnPlay, Settings.GAME_WIDTH/2 - 50, Settings.GAME_HEIGHT/2 - 50, null);
        g.setFont(new Font("Trattatello", Font.BOLD, 100));
        g.setColor(Color.darkGray);
        g.drawString("STICK", 125, 120);
        g.drawString("HERO", 130, 200);
    }

    int count = 0;

    @Override
    public void run() {
        super.run();
        count++;
//        if (count > 30) renderer = new Renderer("assets/images/background/background.png");
        if (count>30 && KeyEventPress.isAnyKeyPress){
            SceneManager.signNewScene(new SceneStage1());
            count =0;
        }
    }
}
