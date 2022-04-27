package Games.Data.Tetris.Tiles;

import java.util.Random;

public class Tile {
    public int[][] array;

    public int x, y;
    public int arraysize = 4;

    private Random random;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        random = new Random();

        array = new int[][]{
                {0, 0, 1, 1},
                {0, 1, 1, 1},
                {0, 0, 1, 1},
                {1, 1, 1, 1}
        };

        give_color();
        System.out.println(array);
    }

    public void rotate(){
        int[][] newarray = new int[arraysize][arraysize];
        for (int y = 0; y < array.length; y++){
            for (int x = 0; x < array.length; x++){
                int item = array[x][y];
                newarray[y][x] = item;
            }
        }
        array = newarray;
    }

    public void give_color(){
        int color = random.nextInt(3)+1;
        System.out.println(color);
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
