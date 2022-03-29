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

    private SnakeGameSprite player;
    private int[][] gamearray;
    public int tilesize = 40;



    public Snake(Window window){
        super(window, "Snake");
        this.setBackground(Color.black);

        BufferedImage apple_texture = load_image("/apple.png");

        gamearray = new int[100][100];

        player = new SnakeGameSprite(this, 6, null);


        for (int i = 0; i < 5; i++){
            SnakeGameSprite tp = new SnakeGameSprite(this, 5, apple_texture);
            tp.set_pos(i, 5);
        }
        window.pack();
    }


    @Override
    public void update_loop(){

        double steps = 1.9;

        if (Keyboard.isKeyPressed(KeyEvent.VK_D)){
            player.move(1, 0);
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_A)){
            player.move(-1, 0);
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_W)){
            player.move(0, -1);
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_S)){
            player.move(0, 1);
        }
    }

}
