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
    private BufferedImage snake_body;
    private BufferedImage snake_head;


    public Snake(Window window){
        super(window, "Snake");
        this.setBackground(Color.black);

        random = new Random();

        apple_texture = load_image("/apple.png");
        snake_body = load_image("/snake_body.png");
        snake_head = load_image("/snake_head.png");

        apples = new AppleList();

        JPanel panel = new JPanel();

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        panel.setAlignmentX(RIGHT_ALIGNMENT);
        //this.add(panel, new FlowLayout());

        JButton btn = new JButton("test");
        panel.add(btn);

        start_game();

        window.pack();
    }

    public void start_game(){
        player = new SnakeHead(this, snake_head, snake_body);
        player.set_pos(3, 3);

        for (int i = 0; i < 400; i++){
            place_apple();
        }
    }

    public void place_apple(){
        int x = random.nextInt(border_x+1);
        int y = random.nextInt(border_y+1);
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
        if (player.tile_x > border_x){
            player.set_pos(0, player.tile_y);
        }
        if (player.tile_y > border_y){
            player.set_pos(player.tile_x, 0);
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
