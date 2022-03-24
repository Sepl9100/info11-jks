package Games.Engine;

import Games.MainMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public abstract class Game extends JPanel {

    protected Window window;

    protected String name; // Game title
    private final int PANELWIDTH = 1100;
    private final int PANELHEIGHT = 600;

    protected int tick;
    private long last_tick_time;
    protected int fps;
    private long last_fps_check_time;


    protected SpriteList spritelist; // 2D Array of all Sprites to be rendered spritelist.list[layer][sprite]

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
        window.setLocationRelativeTo(null);
    }

    public void render(Graphics g){
        Date date = new Date();
        if (tick % 100 == 0) {                          // Measure FPS every 100th tick
            long difference = date.getTime() - last_fps_check_time;
            if (difference != 0) {
                long fps_ = 1000 * 100 / difference;
                fps = (int)fps_;
            }

            last_fps_check_time = date.getTime();
        }

        if (date.getTime() - last_tick_time < 8){       // Cap FPS to about 150 - 200
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        last_tick_time = date.getTime();


        for (Sprite[] layer : spritelist.list) {
            for (Sprite element : layer) {
                if (element != null) {
                    element.draw(g);
                }
            }
        }
    }

    abstract public void update_loop();

    public BufferedImage load_image(String filename) {
        try {
            InputStream in = getClass().getResourceAsStream(filename);
            return ImageIO.read(in);
        } catch (IOException e) {
            System.out.println("Error loading image.");
        }
        return null;
    }

    protected void paintComponent(Graphics g) {
        this.update_loop();
        super.paintComponent(g);
        this.render(g);
        this.repaint();
        this.tick++;
        g.drawString(Double.toString(fps) + "FPS", 10, 20);
    }

    public void exit(){
        window.remove(this);
        window.repaint();
        this.spritelist = null;
        new MainMenu(window);
    }
}
