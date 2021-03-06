package Games.Data.Sudoku;

import java.util.Arrays;
import java.util.Random;

public class Logic {

    private static Random random;

    public Logic() {

    }


    // Diese Methode generiert ein zufälliges Sudoku mit
    // der gewünschten Menge an fehlenden Zahlen
    // Missing Numbers 1-80 !
    public int[][] generate_sudoku(int missing_numbers) {
        int[][] tmp_quiz = new int[9][9];
        int[] nmb_list = new int[9];

        // generate a diagonal line with random number from 1-9
        for(int nmb = 0; nmb < 9; nmb++) {nmb_list[nmb] = nmb+1;}
        nmb_list = shuffle(nmb_list);
        for(int i = 0; i < 9; i++) {tmp_quiz[i][i] = nmb_list[i];}
        nmb_list = shuffle(nmb_list);
        for(int x = 1; x < 9; x++) {
            if(check_number(tmp_quiz, 0, x, nmb_list[x])) {
                tmp_quiz[0][x] = nmb_list[x];
            }
        }
        tmp_quiz = solve_sudoku(tmp_quiz);
        if(tmp_quiz == null){
            tmp_quiz = generate_sudoku(missing_numbers);
        }

        // Hier haben wir ein vollständig gelöstes Sudoku
        // jetzt werden wie im Parameter angegeben,
        // x-viele Zahlen gelöscht

        int[] nmb_del_list = new int[80];
        for(int nmb = 0; nmb < 80; nmb++) {nmb_del_list[nmb] = nmb+1;}
        nmb_del_list = shuffle(nmb_del_list);
        for(int i = 0; i < missing_numbers; i++) {tmp_quiz[nmb_del_list[i]/9][nmb_del_list[i]%9] = 0;}

        return tmp_quiz;
    }

    public int[][] solve_sudoku(int[][] quiz) {
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                if(quiz[y][x] == 0) {
                    for(int check_nmb = 1; check_nmb < 10; check_nmb++) {
                        if(check_number(quiz, y, x, check_nmb)) {
                            quiz[y][x] = check_nmb;
                            if(solve_sudoku(quiz) != null) {return quiz;}
                            quiz[y][x] = 0;
                        }
                    }
                    return null;
                }
            }
        }
        return quiz;
    }

    public boolean check_number(int[][] quiz, int y, int x, int check_number) {
        // Überprüfung ob die Zahl in der Zeile schon vorkommt
        for(int i = 0; i < 9; i++) {
            if(quiz[y][i] == check_number) {return false;}
        }
        // Überprüfung ob die Zahl in der Spalte schon vorkommt
        for(int i = 0; i < 9; i++) {
            if(quiz[i][x] == check_number) {return false;}
        }

        // Überprüfung des 3x3 Teilkästchens
        int x0 = (x/3)*3;
        int y0 = (y/3)*3;
        for(int i = 0; i<3; i++) {
            for(int j = 0; j<3; j++) {
                if(quiz[y0+i][x0+j] == check_number) {return false;}
            }
        }

        // Zahl kommt weder in der Zeile, der Spalte oder dem 3x3 Kästchen vor.
        return true;
    }

    public boolean check_sudoku(int[][] quiz) {
        int tmp_number;

        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {

                tmp_number = quiz[y][x];
                quiz[y][x] = 0;
                if(!check_number(quiz, y, x, tmp_number)) {
                    quiz[y][x] = tmp_number;
                    return false;   // ungültiges sudoku
                }
                quiz[y][x] = tmp_number;
            }
        }
        return true;    // gültiges sudoku
    }

    public boolean check_solvability(int[][] quiz) {
        int[][] tmp_quiz;
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(quiz[i][j] != 0) {
                    tmp_quiz = copy_quiz(quiz);
                    tmp_quiz[i][j] = 0;
                    if (!check_number(tmp_quiz, i, j, quiz[i][j])) {
                        return false;   // not solvable
                    }
                }
            }
        }
        return true;    // solvable
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

    public int[][] copy_quiz(int[][] quiz) {
        int[][] result = new int[9][9];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                result[i][j] = quiz[i][j];
            }
        }
        return result;
    }

    public void print_quiz(int[][] quiz) {
        for(int y = 0; y < 9; y++) {
            System.out.println("");
            for(int x = 0; x < 9; x++) {
                System.out.print(""+quiz[y][x]+" ");
            }
        }
        System.out.println("");
    }
}
