package Games;

import Games.Data.Tetris.TileArray;
import Games.Data.Tetris.Tiles.Tile;
import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tetris extends Game {

    private TileArray array;

    private Tile testtile;

    public Tetris(Window window){
        super(window, "Tetris");
        this.setBackground(Color.green);
        window.pack();
        array = new TileArray();
        array.print();

        testtile = new Tile(10, 5);
    }

    public void display_tile(Tile tile){
        g.drawRect(tile.x*10, tile.y*10, 10, 10);
    }

    @Override
    public void update_loop() {
        display_tile(testtile);
        if (tick % 10 == 0){
            testtile.move_down();
        }

    }
}
