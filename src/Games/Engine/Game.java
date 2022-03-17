package Games.Engine;

import javax.swing.*;
import java.awt.*;

public abstract class Game {
    private Window window;
    private JPanel surface;
    public Game(Window window){
        this.window = window;
        this.surface = new JPanel();
        this.surface.setBackground(Color.green);
        window.add(this.surface);
    }
}
