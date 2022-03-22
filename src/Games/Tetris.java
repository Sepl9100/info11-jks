package Games;

import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tetris extends Game {

    public Tetris(Window window){
        super(window, "Tetris");
        this.setBackground(Color.green);
        window.pack();
    }

    @Override
    public void update_loop() {

    }
}
