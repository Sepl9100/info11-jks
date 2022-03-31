package Games;

import Games.Data.Snake.Apple;
import Games.Data.Snake.AppleList;
import Games.Data.Snake.SnakeGameSprite;
import Games.Data.Snake.SnakeHead;
import Games.Engine.*;
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
import java.util.Random;

public class Snake extends Game {

    private SnakeHead player;
    private int[][] gamearray;
    private AppleList apples;
    public int tilesize = 20;
    private char direction = 'D';
    private char last_direction = 'D';
    private Random random;
    private final int border_x = 50;
    private final int border_y = 20;

    private BufferedImage apple_texture;


    public Snake(Window window){
        super(window, "Snake");
        this.setBackground(Color.black);

        random = new Random();

        apple_texture = load_image("/apple.png");

        apples = new AppleList();
        QueueTask door_open = new QueueTask(this, 100, e -> System.out.println("lel"));

        gamearray = new int[100][100];

        player = new SnakeHead(this, null);
        player.set_pos(3, 3);

        for (int i = 0; i < 50; i++){
            place_apple();
        }
        window.pack();

    }

    public void place_apple(){
        int x = random.nextInt(border_x-1);
        int y = random.nextInt(border_y-1);
        Apple apple = new Apple(this, apple_texture);
        apple.set_pos(x, y);
        apples.add_sprite(apple);
    }

    public void check_apple(int tilex, int tiley){
        for (Apple apple : apples.list){
            if (apple != null) {
                if (apple.tile_x == tilex && apple.tile_y == tiley) {
                    player.add_body();
                    apple.delete();
                    apples.remove_sprite(apple);
                }
            }
        }
    }


    @Override
    public void update_loop() {

        // Snake auf andere seite am rand plazieren

        if (player.tile_x < 0){
            player.set_pos(border_x, player.tile_y);
        }
        if (player.tile_y < 0){
            player.set_pos(player.tile_x, border_y);
        }

        // KEY INPUTS

        if (Keyboard.isKeyPressed(KeyEvent.VK_D)) {
            if (last_direction != 'L') direction = 'R';
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_A)) {
            if (last_direction != 'R') direction = 'L';
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_W)) {
            if (last_direction != 'D') direction = 'U';
        }
        if (Keyboard.isKeyPressed(KeyEvent.VK_S)) {
            if (last_direction != 'U') direction = 'D';
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
            last_direction = direction;
        }
    }

}
