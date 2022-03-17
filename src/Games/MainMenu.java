package Games;

import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends Game {

    public MainMenu(Window window){
        super(window, "Hauptmenü");

        this.setBackground(Color.red);

        JButton b = new JButton();
        b.setText("Open snake game");
        b.setVisible(true);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                new Snake(window);
            }
        });
        this.add(b);

        JButton e = new JButton();
        e.setText("Open demo game");
        e.setVisible(true);
        e.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                new DemoGame(window);
            }
        });
        this.add(e);

        window.pack();
    }

    public void exit(){
        window.remove(this);
        window.repaint();
    }
}
