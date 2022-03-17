package Games.Engine;

import Games.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Game {
    protected Window window;
    protected JPanel surface;
    public Game(Window window){
        this.window = window;

        surface = new JPanel();

        window.add(surface);

        surface.setBackground(Color.green);
        surface.setPreferredSize(new Dimension(700, 500));

        window.pack();

    }

    public void exit(){
        window.remove(surface);
        window.repaint();
        new MainMenu(window);
    }
}
