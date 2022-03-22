package Games;

import Games.Engine.Game;
import Games.Engine.Sprite;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DemoGame extends Game {

    private Sprite[] sprites;
    private Random random;

    public DemoGame(Window window){
        super(window, "DemoGame");
        this.setBackground(Color.orange);

        random = new Random();

        JButton b = new JButton();
        b.setText("DEMO GAME EXIT");
        b.setVisible(true);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });

        this.add(b);
        window.pack();

        BufferedImage test_texture = load_image("/img.png");

        sprites = new Sprite[5000];
        for (int i = 0; i < 4500; i++){
            sprites[i] = new Sprite(this, 0, test_texture);
            sprites[i].x = 200;
            sprites[i].y = 200;
        }

    }

    @Override
    public void update_loop() {
        for (Sprite element : sprites){
            if (element != null) {
                element.x += random.nextInt(3)-1;
                element.y += random.nextInt(3)-1;
            }
        }
    }
}
