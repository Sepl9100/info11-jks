package Games;

import Games.Data.Tetris.TileArray;
import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tetris extends Game {

    private TileArray array;

    public Tetris(Window window){
        super(window, "Tetris");
        this.setBackground(Color.green);
        window.pack();
        array = new TileArray();
        array.print();
    }

    @Override
    public void update_loop() {

    }
}
