package Games.Data.Tetris;

public class TileArray {
    private int[][] array;

    public TileArray(){
        array = new int[10][20];
        for (int i = 0; i < 10; i++){
            array[i][0] = 1
        }

    }

    public void print(){
        for (int[] line : array){
            StringBuilder linestr = new StringBuilder();
            for (int i : line){
                linestr.append(Integer.toString(i));
            }
            System.out.println(linestr);
        }
    }
}
