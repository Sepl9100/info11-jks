package Games;

import Games.Engine.Game;
import Games.Engine.Sprite;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        sprites = new Sprite[1000];
        for (int i = 0; i < 500; i++){
            sprites[i] = new Sprite(this, 0, "img.png");
            sprites[i].x = 200;
            sprites[i].y = 200;
        }

    }

    @Override
    public void update_loop() {
        for (Sprite element : sprites){
            if (element != null) {
                element.x += random.nextInt(-1, 2);
                element.y += random.nextInt(-1, 2);
            }
        }
    }
}
