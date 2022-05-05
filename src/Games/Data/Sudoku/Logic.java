package Games.Data.Sudoku;

import java.util.Random;

public class Logic {

    private static Random random;

    public Logic() {

    }

    public int[][] generate_sudoku() {
        int[][] tmp_quiz = new int[9][9];
        int[] nmb_list = new int[9];

        for(int nmb = 0; nmb < 9; nmb++) {nmb_list[nmb] = nmb;}
        nmb_list = shuffle(nmb_list);



        for(int i = 0; i < 9; i++) {
            tmp_quiz[i][i] = nmb_list[i];
        }

        return tmp_quiz;
    }




    public int[] shuffle(int[] array) {
        if (random == null) {random = new Random();}
        for (int i = array.length; i > 1; i--) {
            int rnd = random.nextInt(i);
            int tmp = array[i-1];
            array[i-1] = array[rnd];
            array[rnd] = tmp;
        }
        return array;
    }
}
