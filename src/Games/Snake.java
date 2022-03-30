package Games;

import Games.Data.Snake.Apple;
import Games.Data.Snake.AppleList;
import Games.Data.Snake.SnakeGameSprite;
import Games.Data.Snake.SnakeHead;
import Games.Engine.Game;
import Games.Engine.Keyboard;
import Games.Engine.Sprite;
import Games.Engine.Window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;

public class Snake extends Game {

    private SnakeHead player;
    private int[][] gamearray;
    private AppleList apples;
    public int tilesize = 30;
    public char direction = 'D';



    public Snake(Window window){
        super(window, "Snake");
        this.setBackground(Color.black);

        BufferedImage apple_texture = load_image("/apple.png");

        apples = new AppleList();

        gamearray = new int[100][100];

        player = new SnakeHead(this, null);
        player.set_pos(3, 3);
        //player.add_body();


        for (int i = 0; i < 5; i++){
            Apple tp = new Apple(this, apple_texture);
            tp.set_pos(i, 5);
            apples.add_sprite(tp);
        }
        window.pack();

        apples.print();
    }


    @Override
    public void update_loop() {

        double steps = 1.9;

        if (Keyboard.isKeyPressed(KeyEvent.VK_D)) {
            direction = 'R';
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_A)) {
            direction = 'L';
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_W)) {
            direction = 'U';
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_S)) {
            direction = 'D';
        }

        if (tick % 20 == 0) {
            if (direction == 'R') {
                player.move(1, 0);
            } else if (direction == 'L') {
                player.move(-1, 0);
            } else if (direction == 'U') {
                player.move(0, -1);
            } else if (direction == 'D') {
                player.move(0, 1);
            }
        }
    }

}
