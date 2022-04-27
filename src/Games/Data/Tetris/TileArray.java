package Games.Data.Tetris;

import Games.Data.Tetris.Tiles.Tile;

public class TileArray {
    private int[][] array;

    public TileArray(){
        array = new int[10][20];

    }

    public void place_matrix(Tile tile){
        for (int y_index = 0; y_index < 3; y_index++){
            for (int x_index = 0; x_index < 3; x_index++){
                int block = tile.array[x_index][y_index];
                if (block != 0){
                    array[tile.x + x_index][tile.y + y_index] = block;
                }
            }
        }
    }

    public boolean check_collision(Tile tile, int x_move, int y_move){
        for (int y_index = 0; y_index < 3; y_index++) {
            for (int x_index = 0; x_index < 3; x_index++) {
                int block = tile.array[x_index][y_index];
                if (block != 0){        // Kästchen des Blocks hat kollision
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
