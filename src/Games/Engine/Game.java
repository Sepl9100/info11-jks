package Games.Engine;

import Games.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Game extends JPanel {

    protected Window window;

    public String name; // Game title
    public final int PANELWIDTH = 700;
    public final int PANELHEIGHT = 500;

    public SpriteList spritelist; // muss zu 2D linked list umgebaut werden

    public Game(Window window, String name){
        super();
        this.window = window;
        this.name = name;

        spritelist = new SpriteList();
        Sprite test = new Sprite(this);
        spritelist.add_sprite(test, 1);

        window.update_title(name); // displays game name on titlebar

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
