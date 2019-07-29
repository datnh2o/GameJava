package game;

import game.player.Player;
import game.renderer.Renderer;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;

public class Background extends GameObject {
//    Clip playerDead;
//    Clip startSound;

    public Background() {
        renderer = Settings.background;
        position.set(0, Settings.GAME_HEIGHT - Settings.BACKGROUND_HEIGHT);
        anchor.set(0, 0);
//        startSound = Settings.bgsound;
//        AudioUtils.replay(startSound);
        AudioUtils.replay(Settings.bgSound);
    }

    public void backgroundMove() {
        position.x--;
        if (position.x <= Settings.GAME_WIDTH - Settings.BACKGROUND_WIDTH + 1) {
            position.x = -150;
        }
    }

    int count = 1;
    int countPlayMusic = 1;
    @Override
    public void run() {
        super.run();

        countPlayMusic ++;
        if (countPlayMusic > 55*31) { // So frame mot giay nhan so giay cua music
            AudioUtils.replay(Settings.bgSound);
            countPlayMusic = 0;
        }

        Player player = GameObject.findPlayer();
        if (player != null) {
            if (player.waiting && player.position.x > Settings.STICK_POSITION_X - Settings.PLAYER_WIDTH / 2 - 2) {
                this.backgroundMove();
            }

            if (player.position.y > 750) {
                count++;
                if (count % 4 == 1) {
                    position.x += 5;
                }
                else if (count % 4 == 2) {
                    position.y += 5;
                } else if (count % 4 == 3) {
                    position.x -= 5;
                } else {
                    position.y -= 5;
                }
                if (player.position.y <= 1200){
                    System.out.println("Have");
                    AudioUtils.replay(Settings.playerDead);
                }
            }
        }
    }

    public void reset() {
        super.reset();
        AudioUtils.replay(Settings.bgSound);
        AudioUtils.replay(Settings.playerDead);
    }
}
