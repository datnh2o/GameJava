package game.player.item;

import game.GameObject;
import game.Settings;
import game.physics.BoxCollider;
import game.player.Player;

public abstract class Item extends GameObject {
    public Item() {
        hitBox = new BoxCollider(this, 12, 12);
    }

    @Override
    public void run() {
        super.run();
        deactiveIfNeeded();
    }

    private void deactiveIfNeeded() {
        if(this.position.y > Settings.GAME_HEIGHT) {
            this.deactive();
        }
    }

//    public void powerUp(Player player) {
//
//    }
    public abstract void powerUp(Player player);
}
