import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private Window window;
    private JPanel surface;
    public MainMenu(Window window){
        this.window = window;
        surface = new JPanel();
        surface.setSize(600, 600);
        window.add(surface);

        JButton b = new JButton();
        b.setText("TestKnopfFürLevelAuswahl");


        window.add(b);
    }
}
