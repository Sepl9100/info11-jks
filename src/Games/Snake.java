package Games;

import Games.Data.Snake.SnakeGameSprite;
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

    private Sprite test_;
    private int[][] gamearray;
    public int tilesize = 10;

    public Snake(Window window){
        super(window, "Snake");
        this.setBackground(Color.green);

        BufferedImage test_texture = load_image("/img.png");

        gamearray = new int[100][100];

        SnakeGameSprite tp = new SnakeGameSprite(this, 5, null);
        tp.set_pos(5, 5);


        Sprite test = new Sprite(this, 5, test_texture);
        test_ = test;
        test.x = 10;
        test.y = 20;
        test.width = 200;
        window.pack();
    }


    @Override
    public void update_loop(){

        double steps = 1.9;

        if (Keyboard.isKeyPressed(KeyEvent.VK_D)){
            test_.x += steps;
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_A)){
            test_.x -= steps;
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_W)){
            test_.y -= steps;
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_S)){
            test_.y += steps;
        }
    }

}
