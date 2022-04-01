package Games;

import Games.Data.Snake.Apple;
import Games.Data.Snake.AppleList;
import Games.Data.Snake.SnakeHead;
import Games.Engine.*;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Snake extends Game {

    private SnakeHead player;
    private final AppleList apples;
    public int tilesize = 20;
    private char direction = 'D';
    private char last_direction = 'D';
    private final Random random;
    private final int border_x = 54;
    private final int border_y = 29;

    public int score;

    private boolean started = false;
    private final JPanel start_screen;
    private final JCheckBox mode_many_apples;

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

        start_screen = new JPanel();
        int width = 400;
        int height = 500;
        start_screen.setLocation(PANELWIDTH/2-width/2, PANELHEIGHT/2-height/2);
        start_screen.setSize(width, height);
        start_screen.setLayout(new BoxLayout(start_screen, BoxLayout.Y_AXIS));

        mode_many_apples = new JCheckBox("Viele Äpfel Modus");
        start_screen.add(mode_many_apples);

        setLayout(null);
        this.add(start_screen);


        JButton btn = new JButton("Spiel starten");
        start_screen.add(btn);
        btn.addActionListener(e -> start_game());

        window.pack();
    }

    public void start_game(){
        started = true;
        this.remove(start_screen);

        player = new SnakeHead(this, snake_head, snake_body);
        player.set_pos(3, 3);
        int apple_amount = 15;
        if (mode_many_apples.isSelected()) apple_amount = 400;
        for (int i = 0; i < apple_amount; i++){
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
                    score += 1;
                    apple.delete();
                    apples.remove_sprite(apple);
                    place_apple();
                }
            }
        }
    }

    public void game_over_screen(){
        started = false;
        JPanel screen = new JPanel();
        int width = 200;
        int height = 300;
        screen.setLocation(PANELWIDTH/2-width/2, PANELHEIGHT/2-height/2);
        screen.setSize(width, height);
        this.add(screen);
        JLabel label = new JLabel("Du hast Verloren!");
        screen.add(label);

        label = new JLabel("Dein Score: " + score);
        screen.add(label);

        JButton btn = new JButton("Neustarten");
        screen.add(btn);
        btn.addActionListener(e -> {
            exit_to_empty();
            new Snake(this.window);
        });

        window.pack();


    }

    @Override
    public void update_loop() {
        if (started) {

            // Snake auf andere seite am rand plazieren
            if (player.tile_x < 0) {
                player.set_pos(border_x, player.tile_y);
            }
            if (player.tile_y < 0) {
                player.set_pos(player.tile_x, border_y);
            }
            if (player.tile_x > border_x) {
                player.set_pos(0, player.tile_y);
            }
            if (player.tile_y > border_y) {
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

            //snake bewegen
            if (tick % 13 == 0) {
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
}
