package Games.Data.Tetris;

public class TileArray {
    private int[][] array;

    public TileArray(){
        array = new int[10][20];
        for (int i = 0; i < 10; i++){
            array[i][0] = 1;
        }

    }

    public void place_matrix(int x, int y, int[][] matrix){

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
