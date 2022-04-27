package Games.Data.Tetris.Tiles;

public class Tile {
    public int[][] array;

    public int x, y;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        array = new int[][]{
                {0, 0, 1},
                {0, 1, 1},
                {0, 0, 1}
        };
    }

    public void move_down(){
        this.y += 1;
    }
}
