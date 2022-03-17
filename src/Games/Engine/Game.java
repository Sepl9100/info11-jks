package Games.Engine;

import Games.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Game extends JPanel {

    protected Window window;

    public String name;
    public final int PANELWIDTH = 700;
    public final int PANELHEIGHT = 500;

    public Game(Window window, String name){
        super();
        this.window = window;
        this.name = name;

        window.update_title(name); // display game name on titlebar

        window.add(this);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));

        window.pack();
    }

    public void exit(){
        window.remove(this);
        window.repaint();
        new MainMenu(window);
    }
}
