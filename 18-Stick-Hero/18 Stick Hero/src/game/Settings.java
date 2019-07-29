package game;

import game.renderer.Renderer;
import tklibs.AudioUtils;
import tklibs.SpriteUtils;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;

public class Settings {
    //Game
    public static final int GAME_WIDTH = 500;
    public static final int GAME_HEIGHT = 750;
    public  static int score = 0;

    //Background
    public static final int BACKGROUND_WIDTH = 2740;
    public static final int BACKGROUND_HEIGHT = 764;
    //Player
    public static final int PLAYER_WIDTH = 38;
    public static final int PLAYER_HEIGHT = 40;

    //Player
    public static final int ITEM_WIDTH = 38;
    public static final int ITEM_HEIGHT = 40;


    //Rectangle
    public static final int RECTANGLE_HEIGHT = 230;

    //Stick
    public static final int STICK_POSITION_X = 100;
    public static final int STICK_POSITION_Y = 520;

    // loadimage
    public static final Renderer background = new Renderer("assets/images/background/background.png");
    public static final BufferedImage btnPlay = SpriteUtils.loadImage("assets/images/button play.png");
    public static final Renderer item = new Renderer("assets/images/item.png");
    public static final Renderer waiting = new Renderer("assets/images/players/player waiting");
    public static final Renderer tapStick = new Renderer("assets/images/players/player tap stick");
    public static final Renderer kickStick = new Renderer("assets/images/players/player kick stick");
    public static final Renderer running = new Renderer("assets/images/players/player run");
    public static final BufferedImage btnReplay = SpriteUtils.loadImage("assets/images/button replay.png");



    // loadsound
    public static final Clip bgSound = AudioUtils.loadSound("assets/music/bg_country.wav");
    public static final Clip playerDead = AudioUtils.loadSound("assets/music/dead.wav");
    public static final Clip stickSoundGrow = AudioUtils.loadSound("assets/music/stick_grow_loop.wav");
    public static final Clip scoreCount = AudioUtils.loadSound("assets/music/score.wav");
    public static final Clip eatItemSound = AudioUtils.loadSound("assets/music/eating_fruit.wav");
    public static final Clip stickLanding = AudioUtils.loadSound("assets/music/landing.wav");
    public static final Clip stickRotate = AudioUtils.loadSound("assets/music/roll_up_down.wav");




}
