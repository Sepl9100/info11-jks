package Games;

import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends Game {

    public MainMenu(Window window){
        super(window);

        surface.setBackground(Color.red);

        JButton b = new JButton();
        b.setText("MAIN MENU");
        b.setVisible(true);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                new Snake(window);
            }
        });

        surface.add(b);

        window.pack();
    }

    public void exit(){
        window.remove(surface);
        window.repaint();
    }
}
