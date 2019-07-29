package game.player;

import game.GameObject;
import game.KeyEventPress;
import game.Settings;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Stick extends GameObject {
    public static int length;
    public static int rotateDegree = -180;
    public static double rotateSpeed = 1;

    public Stick() {
        this.velocity.set(0,0);
    }

}
