package Games.Data.Tetris.Tiles;

public class TileArrays {


    public static int[][] b1 = new int[][]{
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 0, 0},
    };
    public static int[][] b2 = new int[][]{
            {0, 1, 0},
            {0, 1, 0},
            {1, 1, 0},
    };
    public static int[][] b3 = new int[][]{
            {1, 1, 0},
            {0, 1, 0},
            {0, 1, 0},
    };
    public static int[][] b4 = new int[][]{
            {1, 1},
            {1, 1},
    };
    public static int[][] b5 = new int[][]{
            {1, 0, 0},
            {1, 1, 0},
            {0, 1, 0},
    };
    public static int[][] b6 = new int[][]{
            {0, 1, 0},
            {1, 1, 0},
            {0, 1, 0},
    };
    public static int[][] b7 = new int[][]{
            {0, 1, 0},
            {1, 1, 0},
            {1, 0, 0},
    };

    public static int[][][] arrays = new int[][][]{
            b1, b2, b3, b4, b5, b6, b7
    };
}