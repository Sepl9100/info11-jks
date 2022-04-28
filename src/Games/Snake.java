package Games;

import Games.Data.Snake.*;
import Games.Data.Tetris.ColorChangeManager;
import Games.Engine.*;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Snake extends Game {

    private SnakeHead player;
    private final SnakeSpriteList apples;
    private final SnakeSpriteList drugs;
    public int tilesize = 20;
    private char direction = 'D';
    private char last_direction = 'D';
    private final Random random;
    private final int border_x = 54;
    private final int border_y = 29;

    private int tickdelay = 15;

    public int score;

    private boolean started = false;
    private final JPanel start_screen;
    private final JCheckBox mode_many_apples;

    private BufferedImage apple_texture;
    private BufferedImage snake_body;
    private BufferedImage snake_head;
    private BufferedImage pill;


    public Snake(Window window){
        super(window, "Snake");
        this.setBackground(Color.black);

        random = new Random();


        apple_texture = load_image("/Games/Data/Snake/images/apple.png");
        snake_body = load_image("/Games/Data/Snake/images/snake_body.png");
        snake_head = load_image("/Games/Data/Snake/images/snake_head.png");
        pill = load_image("/Games/Data/Snake/images/pill.png");

        apples = new SnakeSpriteList();
        drugs = new SnakeSpriteList();

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
        if (mode_many_apples.isSelected()) apple_amount = 400;      // Viele Äpfel Modus

        // Anfänglich apple_amount viele Äpfel platzieren
        for (int i = 0; i < apple_amount; i++){
            place_apple();
        }

        // Anfänglich Pillen platzieren
        for (int i = 0; i < 5; i++){
            place_drug();
        }
    }

    public void place_apple(){
        int x = random.nextInt(border_x+1);
        int y = random.nextInt(border_y+1);
        Apple apple = new Apple(this, apple_texture);
        apple.set_pos(x, y);
        apples.add_sprite(apple);
    }

    public void place_drug(){
        int x = random.nextInt(border_x+1);
        int y = random.nextInt(border_y+1);
        Drug drug = new Drug(this, pill);
        drug.color = Color.blue;
        drug.set_pos(x, y);
        drugs.add_sprite(drug);     // Zur liste hinzufügen
    }

    public void check_apple(int tilex, int tiley){
        for (SnakeGameSprite apple : apples.list){
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

    public void check_drug(int tilex, int tiley){
        for (SnakeGameSprite drug : drugs.list){
            if (drug != null) {
                if (drug.tile_x == tilex && drug.tile_y == tiley) {
                    player.add_body();
                    score += 10;
                    drug.delete();
                    drugs.remove_sprite(drug);
                    place_drug();
                    player.on_drugs = true;
                    new QueueTask(this, 200, e -> player.on_drugs = false);
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
        //this.setBackground(ColorChangeManager.get_color(tick/2%255));
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

            // Tasten input
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
            int tick_delay = tickdelay;
            if (player.on_drugs) tick_delay = 2;
            if (tick % tick_delay == 0 ) {
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
