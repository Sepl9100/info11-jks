package Games.Data.Sudoku;

import javax.swing.*;
import java.awt.*;

public class SudokuButton {

    public JButton button;
    public int number, x_pos, y_pos;
    public boolean locked;
    private String text;
    private Grid grid;

    public SudokuButton(String text, int x, int y, int width, int height, int number, int x_pos, int y_pos, Grid grid){
        button = new JButton(text);
        button.setBounds(x, y, width, height);

        this.grid = grid;
        this.text = text;
        this.number = number;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
    }

    public void lock_number(int number) {
        locked = true;
        this.number = number;
        text = ""+number;
        button.setText(text);
        button.setBackground(Color.white);
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
