package Games;

import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DemoGame extends Game {

    public DemoGame(Window window){
        super(window, "DemoGame");
        this.setBackground(Color.orange);

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
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawRect(230,80,10,10);
        g.setColor(Color.RED);
        g.fillRect(230,80,10,10);

        for (int i = 0; i < 20; i++){
            g.fillRect(i*22, 10, 20, 20);
        }
    }

}
