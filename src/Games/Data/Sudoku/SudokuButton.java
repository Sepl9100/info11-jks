package Games.Data.Sudoku;

import javax.swing.*;

public class SudokuButton {

    public JButton button;
    public int number, x_pos, y_pos;

    public  SudokuButton(String text, int x, int y, int width, int height, int number, int x_pos, int y_pos){
        button = new JButton(text);
        button.setBounds(x, y, width, height);

        this.number = number;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
    }
}
