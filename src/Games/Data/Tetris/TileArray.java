package Games.Data.Tetris;

import Games.Data.Tetris.Tiles.Tile;

public class TileArray {
    private int[][] array;
    public int width = 10;
    public int height = 20;

    public TileArray(){
        array = new int[width][height];

    }
    public TileArray(int width, int height){
        this.width = width;
        this.height = height;
        array = new int[width][height];

    }

    public void place_matrix(Tile tile){
        for (int y_index = 0; y_index < tile.arraysize; y_index++){
            for (int x_index = 0; x_index < tile.arraysize; x_index++){
                int block = tile.array[x_index][y_index];
                if (block != 0){
                    array[tile.x + x_index][tile.y + y_index] = block;
                }
            }
        }
        int response = check_for_full_line();
        if (response != -1){
            move_array_down(response);
        };
    }

    public int check_for_full_line() {
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

    public void move_array_down(int startheight){
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
                    if (tile.y + y_index >= 19){
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
