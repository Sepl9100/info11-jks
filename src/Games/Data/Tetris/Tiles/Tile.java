package Games.Data.Tetris.Tiles;

import java.util.Random;

public class Tile {
    public int[][] array;

    public int x, y;
    public int arraysize;

    private Random random;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        random = new Random();
        int rnd = random.nextInt(TileArrays.arrays.length);
        array = TileArrays.arrays[rnd];
        arraysize = array.length;

        give_color();
    }

    public void rotate(){
        int[][] newarray = new int[arraysize][arraysize];
        for (int y = 0; y < array.length; y++){
            for (int x = 0; x < array.length; x++){
                int item = array[x][y];
                newarray[arraysize-1-y][x] = item;
            }
        }
        array = newarray;
    }

    public void give_color(){
        int color = random.nextInt(Tilecolors.colorcount)+1;
        for (int y = 0; y < array.length; y++){
            for (int x = 0; x < array.length; x++){
                int item = array[x][y];
                if (item != 0) {
                    array[x][y] = color;
                }
            }
        }
    }

    public void move(int x, int y){
        this.x += x;
        this.y += y;
    }

}
