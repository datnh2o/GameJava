package game.player;

import game.*;
import game.physics.BoxCollider;
import game.rectangle.Rec;
import game.renderer.Renderer;
import game.scene.SceneGameOver;
import game.scene.SceneManager;
import tklibs.AudioUtils;
import tklibs.Mathx;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;
import java.util.Set;

public class Player extends GameObject {
    public static Boolean isGameover;
    Stick stick;
    Rec rectangle1, rectangle2;
    Item item;
    public int random;
    public int randomPositionItem;
    public boolean waiting = false;
    public static int score;
    public static int scoreItem;
    public static boolean isUp = true;
    public static int countAfterBeginPlay = 0;


    public Player() {

        rectangle1 = new Rec();
        rectangle1.position.set(Settings.STICK_POSITION_X-rectangle1.width, Settings.STICK_POSITION_Y-2);

        rectangle2 = new Rec();
        random = Mathx.random(150,300);
        if (random %2 == 1) {
            random +=1;
        }
        rectangle2.position.set(random, Settings.STICK_POSITION_Y-2);

        item = new Item();
//        Random rand = new Random();
//        while (randomPositionItem < 120) {
//            randomPositionItem = rand.nextInt((int) (rectangle2.position.x - Settings.ITEM_WIDTH));
        randomPositionItem = Mathx.random(115, (int) rectangle2.position.x - 20);
//        }
        item.position.set(randomPositionItem,Settings.STICK_POSITION_Y + Settings.PLAYER_HEIGHT/2);

        position.set(Settings.STICK_POSITION_X-Settings.PLAYER_WIDTH/2 - 3, Settings.STICK_POSITION_Y-Settings.PLAYER_WIDTH/2 -3);
        this.renderer = new PlayerRenderer(this);
        hitBox = new BoxCollider(this, Settings.PLAYER_WIDTH, Settings.PLAYER_HEIGHT);

        isGameover = false;

        stick = new Stick();
        stick.position.set(Settings.STICK_POSITION_X, Settings.STICK_POSITION_Y);
        stick.anchor.set(0, 0);
    }

    public void checkState() {
        if (this.velocity.x == 0 && this.velocity.y == 0) {
            waiting = true;
        } else {
            waiting = false;
        }
    }


    @Override
    public void render(Graphics g) {
        super.render(g);

        Graphics2D g2d = (Graphics2D) g;
        countAfterBeginPlay++;
        if (countAfterBeginPlay > 30) {
            if (KeyEventPress.isFirePress && stick.rotateDegree == -180) {
                stick.length += 4;
                g2d.setColor(Color.BLACK);
                g2d.fillRect((int) stick.position.x, (int) stick.position.y - stick.length, 3, stick.length);
                stick.rotateDegree = -180;
                if (countAfterBeginPlay % 2 == 0){
//                    stickSoundGrow = AudioUtils.loadSound("assets/music/stick_grow_loop.wav");
                    AudioUtils.replay(Settings.stickSoundGrow);
                }

            }
        }


        if(!KeyEventPress.isFirePress || stick.rotateDegree != -180) {
            calcDegree();
            g2d.setColor(Color.BLACK);
            AffineTransform old = g2d.getTransform();
            g2d.rotate(Math.toRadians(stick.rotateDegree), (int)stick.position.x, (int)stick.position.y);
            g2d.fillRect((int)stick.position.x, (int)stick.position.y, 3, stick.length);
            g2d.setTransform(old);
        }

        g2d.setFont(new Font("Trattatello", Font.PLAIN, 40));
        g2d.setColor(Color.black);
        g2d.drawString("Score : " + getScore(), 30, 70);

        g2d.setFont(new Font("Trattatello", Font.PLAIN, 40));
        g2d.setColor(Color.black);
        g2d.drawString("Item : " + getScoreItem(), 350, 70);

    }


    @Override
    public void run() {
        super.run();
        limitLength();
        checkState();
        this.checkGameOver();
        this.move();
        conditionCheckIsUp();
        checkEatItem();
        System.out.println(Stick.rotateSpeed);
    }

    public static int getScoreItem() {
        return scoreItem;
    }

    public void checkGameOver(){
        if (Stick.length < (rectangle2.position.x - stick.position.x) && Stick.rotateDegree == -90) {
            isGameover = true;

        }
        else if (Stick.length > (rectangle2.position.x + rectangle2.width - stick.position.x) && Stick.rotateDegree == -90) {
            isGameover = true;

        }

        if (!isUp && (int)this.position.x >= rectangle2.position.x - Settings.PLAYER_WIDTH/2 - 1){
            isGameover = true;

        }



    }

    int count = 0;
    public void move(){

        if (isGameover && isUp && position.y == 498){
            if (Stick.length != 0 && stick.rotateDegree == -90 && this.position.x < Settings.STICK_POSITION_X + stick.length){
                this.velocity.set(2,0);
            }

            if (this.position.x > Settings.STICK_POSITION_X + stick.length && stick.rotateDegree == -90) {
                count ++;
                this.velocity.set(0, 1 + count);
            }
        }
        else if (isGameover && isUp && position.y != 498) {
            if (this.position.x > Settings.STICK_POSITION_X + stick.length && stick.rotateDegree == -90) {
                count ++;
                this.velocity.set(0, 1 + count);
            }
        }

        if (isGameover && !isUp) {

                if (this.position.x > Settings.STICK_POSITION_X + stick.length && stick.rotateDegree == -90) {
                    count++;
            }

            if (Stick.length != 0) {
                if (this.position.x >= rectangle2.position.x - Settings.PLAYER_WIDTH/2 && this.position.x <= rectangle2.position.x + rectangle2.width + Settings.PLAYER_WIDTH/2) {
                    this.velocity.set(0, 1 + count);
                    count++;
                }
                if (this.position.x > Settings.STICK_POSITION_X + stick.length && stick.rotateDegree == -90) {
                    count++;
                    this.velocity.set(0, 1 + count);
                }
            }
        }


        if (!isGameover){
            if (Stick.length != 0 && stick.rotateDegree == -90 && this.position.x < Settings.STICK_POSITION_X + stick.length){
                this.velocity.set(2,0);
            }
            if (Stick.length != 0 && stick.rotateDegree == -90 && this.position.x > rectangle2.position.x - Settings.PLAYER_WIDTH/2 -1){
                isUp = true;
            }

            if (this.position.x == rectangle2.position.x + rectangle2.width - Settings.PLAYER_WIDTH/2 -3){
                this.velocity.set(0,0);
                if (rectangle2.position.x + rectangle2.width > Settings.STICK_POSITION_X){
                    rectangle2.position.x -= 2;
                    item.position.x -= 2;
                    this.position.x -= 2;
                    rectangle1.position.x -= 2;
                    stick.position.x -= 2;
                }

                if (position.x == Settings.STICK_POSITION_X - Settings.PLAYER_WIDTH/2 - 3){
                    score ++;

//                    scoreCount = AudioUtils.loadSound("assets/music/score.wav");
                    AudioUtils.replay(Settings.scoreCount);

                    stick.deactive();
                    stick = GameObject.recycle(Stick.class);
                    stick.position.set(Settings.STICK_POSITION_X,Settings.STICK_POSITION_Y);
                    Stick.length = 0;
                    Stick.rotateDegree = -180;

                    Rec rectangle1 = GameObject.recycle(Rec.class);
                    rectangle1.reset();
                    rectangle1.width = rectangle2.width;
                    rectangle1.position.x = Settings.STICK_POSITION_X - rectangle2.width;

                    rectangle2.deactive();
                    Rec rectangle2 = GameObject.recycle(Rec.class);
                    rectangle2.reset();
                    random = Mathx.random(150,300);
                    if (random %2 == 1) {
                        random +=1;
                    }
                    rectangle2.position.set(random, Settings.STICK_POSITION_Y - 2);
//                    randomPositionItem = Mathx.random(150,(int) rectangle2.position.x - Settings.ITEM_WIDTH/2);
//                    if (randomPositionItem %2 == 1) {
//                        randomPositionItem +=1;
//                    }

                    item.deactive();
                    Item item = GameObject.recycle(Item.class);
//                    item.reset();
//                    Random rand = new Random();
//                    while (randomPositionItem < 120) {
//                        randomPositionItem = rand.nextInt((int) (rectangle2.position.x - Settings.ITEM_WIDTH));
//                    }
                    randomPositionItem = Mathx.random(115, (int) rectangle2.position.x - 20);
                    item.position.set(randomPositionItem,Settings.STICK_POSITION_Y + Settings.PLAYER_HEIGHT/2);

                }

            }

        }

        deactiveIfNeeded();

    }

    private void calcDegree() {
        if (stick.length != 0) {
            if (stick.rotateDegree < -90) {
                stick.rotateDegree += stick.rotateSpeed / 15;
                stick.rotateSpeed += 3;

                if (stick.rotateSpeed % 52 == 0){
//                    stickRotate = AudioUtils.loadSound("assets/music/roll_up_down.wav");
                    AudioUtils.replay(Settings.stickRotate);
                }

                if (stick.rotateDegree >= -94 && stick.rotateDegree <= -91){
//                    stickLanding = AudioUtils.loadSound("assets/music/landing.wav");
                    AudioUtils.replay(Settings.stickLanding);
                }
            } else {
                stick.rotateDegree = -90;
                stick.rotateSpeed =1;
            }
        }
    }

    public static int getScore() {
        return score;
    }

    public void conditionCheckIsUp(){
        if (Stick.length != 0 && Stick.length < rectangle2.position.x - Settings.STICK_POSITION_X) {
            if (position.x > Settings.STICK_POSITION_X && position.y == Settings.STICK_POSITION_Y-Settings.PLAYER_WIDTH/2 -3) {
                checkIsUp();
            }
        } else if (Stick.length != 0 && Stick.length >= rectangle2.position.x - Settings.STICK_POSITION_X
                && Stick.length <= rectangle2.position.x + rectangle2.width - Settings.STICK_POSITION_X){
            if (position.x > Settings.STICK_POSITION_X && position.y == Settings.STICK_POSITION_Y-Settings.PLAYER_WIDTH/2 -3
                    && position.x < rectangle2.position.x - Settings.PLAYER_WIDTH/2 - 1) {
                checkIsUp();
            }
        } else if (Stick.length != 0 && Stick.length > rectangle2.position.x + rectangle2.width - Settings.STICK_POSITION_X) {
            if (position.x > Settings.STICK_POSITION_X && position.y == Settings.STICK_POSITION_Y-Settings.PLAYER_WIDTH/2 -3) {
                checkIsUp();
            }
        }
    }

    int counttime = 0;
    public void checkIsUp(){
        counttime ++;
        if (!waiting && KeyEventPress.isFirePress && counttime > 7){
            isUp = !isUp;
            counttime = 0;
        }
    }


    public static boolean getIsUp(){
        return isUp;
    }

    private void deactiveIfNeeded() {
        if(position.y > 1500) {
            this.deactive();
        }
    }
    private void limitLength() {
        Stick.length = (int) Mathx.clamp(Stick.length, 0, 390);
    }

    private void checkEatItem() {
        if (!isUp && position.x >= item.position.x - Settings.ITEM_WIDTH && position.x <= item.position.x + Settings.ITEM_WIDTH && item.active){
//            eatItemSound = AudioUtils.loadSound("assets/music/eating_fruit.wav");
            AudioUtils.replay(Settings.eatItemSound);
            scoreItem ++;
            item.deactive();
        }
        if (isGameover && position.y != 498 && position.x >= item.position.x - Settings.ITEM_WIDTH && position.x <= item.position.x + Settings.ITEM_WIDTH && item.active){
            AudioUtils.replay(Settings.eatItemSound);
            scoreItem ++;
            item.deactive();
        }
    }

    @Override
    public void reset(){
    super.reset();
    AudioUtils.replay(Settings.eatItemSound);
    AudioUtils.replay(Settings.stickLanding);
    AudioUtils.replay(Settings.scoreCount);
    AudioUtils.replay(Settings.stickSoundGrow);
    AudioUtils.replay(Settings.stickRotate);
    }

    @Override
    public void deactive() {
        super.deactive();
        SceneManager.signNewScene(new SceneGameOver());

    }
}
