package Games.Engine;

import Games.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public abstract class Game extends JPanel {

    protected Window window;

    public String name; // Game title
    public final int PANELWIDTH = 700;
    public final int PANELHEIGHT = 500;

    protected int tick;
    protected double fps;
    private long last_fps_check_time;

    public SpriteList spritelist; // 2D Array of all Sprites to be rendered spritelist.list[layer][sprite]

    public Game(Window window, String name){
        super();
        this.window = window;
        this.name = name;

        spritelist = new SpriteList();

        window.update_title(name); // displays game name on titlebar

        window.add(this);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));

        window.pack();
    }

    public void render(Graphics g){
        if (tick % 100 == 0) {
            Date date = new Date();
            System.out.println(fps);
            long difference = date.getTime() - last_fps_check_time;
            double fps_ = 1000 * 100 / difference;
            fps = fps_;
            last_fps_check_time = date.getTime();
        }

        for (Sprite[] layer : spritelist.list) {
            for (Sprite element : layer) {
                if (element != null) {
                    element.draw(g);
                }
            }
        }
    }

    public void update_loop(){

    }

    protected void paintComponent(Graphics g) {
        this.update_loop();
        super.paintComponent(g);
        this.render(g);
        this.repaint();
        this.tick++;
    }

    public void exit(){
        window.remove(this);
        window.repaint();
        this.spritelist = null;
        new MainMenu(window);
    }
}
