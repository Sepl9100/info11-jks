package Games;

import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Snake extends Game {

    public Snake(Window window){
        super(window, "Snake");
        this.setBackground(Color.green);

        JButton b = new JButton();
        b.setText("SNAKE GAME EXIT");
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
        g.setColor(Color.BLUE);
        g.fillRect(230,80,10,10);

        for (int i = 0; i < 20; i++){
            g.fillRect(i*22, 10, 20, 20);
        }

        g.fillRoundRect(50, 50, 100, 100, 20, 20);
    }

}
