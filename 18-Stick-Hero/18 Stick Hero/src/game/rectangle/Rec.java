package game.rectangle;

import game.GameObject;
import game.Settings;
import game.physics.BoxCollider;
import game.scene.SceneManager;
import game.scene.SceneStage1;
import tklibs.Mathx;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class Rec extends GameObject {
    public int width;

    public Rec() {
        width = Mathx.random(30, 120);
        if (width %2 ==1){
            width += 1;
        }
        hitBox = new BoxCollider(this, width, Settings.GAME_HEIGHT-Settings.RECTANGLE_HEIGHT+2);
        anchor.set(0, 0);
    }

    public void deactiveRectangle() {
        if(this.position.x + this.width < 30) {
            this.deactive();
            this.deactiveIfNeeded();
        }
    }

    @Override
    public void run() {
        super.run(); // velocity
        this.deactiveRectangle();
    }

    @Override
    public void render (Graphics g) {
        super.render(g);
        if (position.x != 0 && position.y !=0) {
            g.setColor(Color.BLACK);
            g.fillRect((int) this.position.x, (int) this.position.y, width, Settings.GAME_HEIGHT - Settings.STICK_POSITION_Y+2);
        }
    }

    @Override
    public void reset() {
        super.reset(); // active = true
        position.set(Settings.GAME_WIDTH, Settings.GAME_HEIGHT-Settings.RECTANGLE_HEIGHT - 2);
        width = Mathx.random(30, 120);
        if (width %2 ==1){
            width += 1;
        }
        anchor.set(0, 0);
        hitBox = new BoxCollider(this, width, Settings.GAME_HEIGHT-Settings.RECTANGLE_HEIGHT);
    }

    private void deactiveIfNeeded() {
        if(this.position.x + this.width < 30) {
            this.deactive();
        }
    }
}