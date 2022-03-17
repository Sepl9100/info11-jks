package Games;

import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Snake extends Game {

    public Snake(Window window){
        super(window, "Snake");
        surface.setBackground(Color.green);

        JButton b = new JButton();
        b.setText("SNAKE GAME EXIT");
        b.setVisible(true);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });

        surface.add(b);

        window.pack();
    }

}
