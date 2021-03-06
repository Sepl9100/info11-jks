package Games.Engine;

import Games.MainMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.util.Date;

public abstract class Game extends JPanel {

    protected Window window;
    protected Graphics g;

    protected String name; // Game title
    public static int PANELWIDTH = 1100;
    public static int PANELHEIGHT = 600;

    protected int tick;
    protected int fps = 90;
    protected double accurate_fps;
    protected int target_fps = fps;
    protected double frame_delay = 0;
    private long last_fps_check_time;

    protected boolean debug = false;

    public Font font1, font2, font3;

    protected JButton back_btn;
    protected final KeyBind t_key_bind = new KeyBind(KeyEvent.VK_T);
    protected final KeyBind a_key_bind = new KeyBind(KeyEvent.VK_A);
    protected final KeyBind d_key_bind = new KeyBind(KeyEvent.VK_D);
    protected final KeyBind q_key_bind = new KeyBind(KeyEvent.VK_Q);
    protected final KeyBind e_key_bind = new KeyBind(KeyEvent.VK_E);

    protected SpriteList spritelist; // 2D Array of all Sprites to be rendered spritelist.list[layer][sprite]
    protected TaskQueue task_queue;

    public Game(Window window, String name){
        super();
        this.window = window;
        this.name = name;

        task_queue = new TaskQueue();
        spritelist = new SpriteList();

        font1 = new Font("SegoeUI", Font.PLAIN, 16);
        font2 = new Font("SegoeUI", Font.BOLD, 32);
        font3 = new Font(Font.DIALOG, Font.PLAIN, 50);

        window.update_title(name); // displays game name on titlebar

        window.add(this);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));

        if (!name.equals("Hauptmenü")) {
            back_btn = new JButton();
            back_btn.setText("<");
            back_btn.setVisible(true);

            back_btn.addActionListener(e -> exit());
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.add(back_btn, BorderLayout.LINE_START);
        }
        window.pack();
        window.setLocationRelativeTo(null);
    }

    public void render(Graphics g){
        // kill zombie tasks
        for (int i = 0; i <= task_queue.filled_until+2; i++){
            QueueTask task = task_queue.list[i];
            if (task != null && task.isDead) {
                task.kill();
            }
        }
        // update all alive tasks
        for (int i = 0; i <= task_queue.filled_until+1; i++){
            QueueTask task = task_queue.list[i];
            if (task != null && !task.isDead) {
                task.update();
            }
        }

        // FPS berechnen
        Date date = new Date();
        if (tick % 10 == 0) {                          // Measure FPS every 100th tick
            long difference = date.getTime() - last_fps_check_time;
            if (difference != 0) {
                accurate_fps = 1000 * 10 / (double)difference;
                fps = (int)accurate_fps;
            }

            last_fps_check_time = date.getTime();
        }

        // fps beschränken (neue methode)
        if (fps > target_fps){
            double difference = accurate_fps - target_fps;
            frame_delay += 0.001*(difference);
        }
        if (fps < target_fps && frame_delay > 0){
            double difference = target_fps - accurate_fps;
            frame_delay -= 0.001*(difference);
        }
        try {
            if (frame_delay > 0){
                Thread.sleep((long)frame_delay);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Sprites zeichnen
        if (spritelist != null) {
            for (Sprite[] layer : spritelist.list) {
                for (Sprite element : layer) {
                    if (element != null) {
                        element.draw(g);
                    }
                }
            }
        }
    }

    abstract public void update_loop();

    public BufferedImage load_image(String filename) {
        System.out.println("Loading image " + filename);
        try {
            InputStream in = getClass().getResourceAsStream(filename);
            if (in == null){
                System.out.println("ERROR loading image " + filename);
            }
            assert in != null;
            return ImageIO.read(in);
        } catch (IOException e) {
            System.out.println("Error loading image.");
        }
        return null;
    }

    protected void paintComponent(Graphics g) {
        this.g = g;
        super.paintComponent(g);
        if (t_key_bind.update()){
            debug = !debug;
        }
        this.update_loop();
        this.render(g);
        if (debug) {    // Debug info
            g.setColor(Color.white);
            g.drawString(Double.toString(fps) + " FPS ", 100, 20);
            g.drawString(Double.toString(frame_delay) + " FRAME DELAY (MS)", 100, 40);
            g.drawString("SpriteListLenght: " + String.valueOf(spritelist.filled_until), 100, 60);
            g.drawString("TaskListLenght: " + String.valueOf(task_queue.filled_until), 100, 80);
        }
        this.repaint();
        this.tick++;
    }


    public void exit(){
        window.remove(this);
        window.repaint();
        this.spritelist = null;
        new MainMenu(window);
    }

    public void exit_to_empty(){
        window.remove(this);
        window.repaint();
        this.spritelist = null;
    }

    public void pass(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
