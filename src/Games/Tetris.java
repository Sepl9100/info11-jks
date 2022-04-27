package Games;

import Games.Data.Tetris.TileArray;
import Games.Data.Tetris.Tiles.Tile;
import Games.Data.Tetris.Tiles.Tilecolors;
import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tetris extends Game {

    private TileArray array;

    private Tile activetile;

    private int tilesize = 20;
    private int left_offset = 200;

    public Tetris(Window window){
        super(window, "Tetris");
        this.setBackground(Color.black);
        window.pack();
        array = new TileArray();

        activetile = new Tile(4, 0);
    }

    public void display_tile(Tile tile){

        for (int y_index = 0; y_index < tile.arraysize; y_index++){
            for (int x_index = 0; x_index < tile.arraysize; x_index++){
                int block = tile.array[x_index][y_index];
                if (block != 0){
                    g.setColor(Tilecolors.get(block));      // block is color value
                    g.fillRect((tile.x+x_index)*tilesize + left_offset, (tile.y+y_index)*tilesize, tilesize, tilesize);
                }
            }
        }
        g.setColor(Color.RED);
        g.fillRect((tile.x)*tilesize + left_offset, (tile.y)*tilesize, 10, 10);
    }

    public void display_array(){
        for (int y_index = 0; y_index < 20; y_index++){
            for (int x_index = 0; x_index < 10; x_index++){
                int block = array.get_tile(x_index, y_index);
                if (block != 0){
                    g.setColor(Tilecolors.get(block));      // block is color value
                    g.fillRect((x_index)*tilesize + left_offset, (y_index)*tilesize, tilesize, tilesize);
                }
            }
        }
    }

    @Override
    public void update_loop() {
        g.setColor(Color.darkGray);
        g.fillRect(left_offset, 0, array.width*tilesize, array.height*tilesize);
        display_tile(activetile);
        display_array();
        if (a_key_bind.update() && !array.check_collision(activetile, -1, 0)){
            activetile.move(-1, 0);
        }
        if (d_key_bind.update() && !array.check_collision(activetile, 1, 0)){
            activetile.move(1, 0);
        }
        if (q_key_bind.update()){
            activetile.rotate();
        }
        if (tick % 20 == 0){
            if (array.check_collision(activetile, 0, 1)){
                array.place_matrix(activetile);
                activetile = new Tile(4, 0);
            }
            activetile.move(0, 1);

        }

    }
}
