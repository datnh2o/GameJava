package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.List;

/**
 * Created by huynq on 7/4/17.
 */
public class Program {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        GamePanel panel = new GamePanel();
        panel.setBackground(Color.CYAN);
        panel.setPreferredSize(new Dimension(Settings.GAME_WIDTH, Settings.GAME_HEIGHT));

        window.add(panel); // location
        window.pack();
        window.setTitle("Game Stick Hero");
//        window.setSize(800, 600);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        KeyAdapter keyHandler = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                KeyEventPress.isAnyKeyPress = true;

                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    KeyEventPress.isFirePress = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                KeyEventPress.isAnyKeyPress = false;

                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    KeyEventPress.isFirePress = false;
                }
            }
        };
        window.addKeyListener(keyHandler);
        // mouse

        window.setVisible(true);

        panel.gameLoop();

        // ctrl + ? : comment/uncomment
        // alt + enter : sua loi code
        // (fn +) shift + f6 : doi ten
        // alt + ctrl + l : format code
    }
}
