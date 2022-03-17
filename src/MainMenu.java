import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;

public class MainMenu {
    private Window window;
    private JPanel surface;
    public MainMenu(Window window){
        this.window = window;
        surface = new JPanel();
        window.add(surface);
        surface.setSize(700, 500);
        
        surface.setBackground(Color.green);

        JButton b = new JButton();
        b.setText("TestKnopfFürLevelAuswahl");
        b.setVisible(true);

        surface.add(b);

    }
}
