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

    private Tile activetile;

    private int tilesize = 15;

    public Tetris(Window window){
        super(window, "Tetris");
        this.setBackground(Color.green);
        window.pack();
        array = new TileArray();
        array.print();

        activetile = new Tile(5, 5);
    }

    public void display_tile(Tile tile){
        g.setColor(Color.BLUE);
        for (int y_index = 0; y_index < 3; y_index++){
            for (int x_index = 0; x_index < 3; x_index++){
                int block = tile.array[x_index][y_index];
                if (block == 1){
                    g.fillRect((tile.x+x_index)*tilesize, (tile.y+y_index)*tilesize, tilesize, tilesize);
                }
            }
        }
        g.setColor(Color.RED);
        g.fillRect((tile.x)*tilesize, (tile.y)*tilesize, 10, 10);
    }

    public void display_array(){
        for (int y_index = 0; y_index < 20; y_index++){
            for (int x_index = 0; x_index < 10; x_index++){
                int block = array.get_tile(x_index, y_index);
                if (block == 1){
                    g.fillRect((x_index)*tilesize, (y_index)*tilesize, tilesize, tilesize);
                }
            }
        }
    }

    @Override
    public void update_loop() {
        display_tile(activetile);
        display_array();
        if (tick % 10 == 0){
            activetile.move_down();
            if (activetile.y > 16 || array.check_collision(activetile, 0, 1)){
                array.place_matrix(activetile);
                activetile = new Tile(5, 5);
            }
        }

    }
}
