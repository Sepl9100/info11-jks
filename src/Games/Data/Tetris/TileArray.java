package Games.Data.Tetris;

import Games.Data.Tetris.Tiles.Tile;
import Games.Engine.Game;
import Games.Tetris;

public class TileArray {
    private int[][] array;
    public int width = 10;
    public int height = 20;

    private Tetris game;

    public TileArray(Tetris game){
        array = new int[width][height];
        this.game = game;

    }


    public TileArray(Tetris game, int width, int height){
        this.game = game;
        this.width = width;
        this.height = height;
        array = new int[width][height];

    }

    public int place_matrix(Tile tile){            // Tile in TileArray platzieren
        for (int y_index = 0; y_index < tile.arraysize; y_index++){
            for (int x_index = 0; x_index < tile.arraysize; x_index++){
                int block = tile.array[x_index][y_index];
                if (block != 0){
                    array[tile.x + x_index][tile.y + y_index] = block;
                }
            }
        }
        int response = 0;
        int score_multiplier = 0;
        int gained_score = 0;
        while (response != -1){     // solang es eine volle Zeile gibt
            response = check_for_full_line();   // Zeile finden
            if (response != -1){
                score_multiplier += 1;
                gained_score += 1;
                move_array_down(response);      // Array nach unten bewegen
            }
        }
        game.score += gained_score * score_multiplier;
        if (tile.y < 1){
            return 1;       // Game over
        }
        return 0;           // kein game over
    }

    public int check_for_full_line() {      // Ganzes Array auf volle Zeilen überprüfen -> y koordinate zurückgeben
        int fullline = -1;
        for (int y_index = 0; y_index < height; y_index++) {
            boolean is_full = true;
            for (int x_index = 0; x_index < width; x_index++) {
                if (array[x_index][y_index] == 0){
                    is_full = false;
                }
            }
            if (is_full) fullline = y_index;
        }
        return fullline;
    }

    public void move_array_down(int startheight){           // Array ab einer Höhe <startheight> 1 nach unten bewegen
        for (int y_index = startheight-1; y_index >= 1; y_index--) {
            for (int x_index = 0; x_index < width; x_index++) {
                int block = array[x_index][y_index];
                array[x_index][y_index+1] = block;
            }
        }
    }


    public boolean check_collision(Tile tile, int x_move, int y_move){
        for (int y_index = 0; y_index < tile.arraysize; y_index++) {
            for (int x_index = 0; x_index < tile.arraysize; x_index++) {
                int block = tile.array[x_index][y_index];
                if (block != 0){        // Kästchen des Blocks hat kollision
                    if (tile.y + y_index + y_move >= 20){
                        return true;
                    }
                    if (tile.x + x_index + x_move < 0){
                        return true;
                    }
                    if (tile.x + x_index + x_move > width-1){
                        return true;
                    }
                    if (array[tile.x + x_index + x_move][tile.y + y_index + y_move] != 0){
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public int get_tile(int x, int y){
        return array[x][y];
    }

    public void print(){
        for (int[] column : array){
            StringBuilder linestr = new StringBuilder();
            for (int i : column){
                linestr.append(Integer.toString(i));
            }
            System.out.println(linestr);
        }
    }
}
