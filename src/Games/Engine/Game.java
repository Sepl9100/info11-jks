package Games.Engine;

import Games.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Game {
    protected Window window;
    protected JPanel surface;

    public String name;
    public final int PANELWIDTH = 700;
    public final int PANELHEIGHT = 500;


    public Game(Window window, String name){
        this.window = window;
        this.name = name;

        window.update_title(name); // display game name on titlebar

        surface = new JPanel();
        window.add(surface);
        surface.setBackground(Color.black);
        surface.setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));
        window.pack();

    }

    public void exit(){
        window.remove(surface);
        window.repaint();
        new MainMenu(window);
    }
}
