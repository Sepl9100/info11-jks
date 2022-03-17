import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                surface.remove(b);
            }
        });

        surface.add(b);

    }
}
