package Games;

import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tetris extends Game {

    public Tetris(Window window){
        super(window, "Tetris");
        this.setBackground(Color.green);
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
