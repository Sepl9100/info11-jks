package Games.Data.Sudoku;

import javax.swing.*;
import java.awt.*;

public class SudokuButton {

    public JButton button;
    public int number, x_pos, y_pos;
    public boolean locked, selected;
    private String text;
    private Grid grid;

    public SudokuButton(String text, int x, int y, int width, int height, int number, int x_pos, int y_pos, Grid grid){
        this.grid = grid;
        this.text = text;
        this.number = number;
        this.x_pos = x_pos;
        this.y_pos = y_pos;

        locked = false;
        selected = false;

        button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(e -> selectButton());
    }

    public void lock_number(int number) {
        locked = true;
        setNumber(number);
        button.setBackground(Color.white);
    }

    public void setNumber(int number) {
        this.number = number;
        text = "";
        if(number != 0) {text+=number;}
        button.setText(text);
    }

    public void selectButton() {
        if(!locked) {
            if(!selected) {
                if(grid.selected_button != null) {grid.selected_button.deselectButton();}
                grid.selected_button = this;
                button.setBackground(Color.green);
                selected = true;
            }
        }
    }

    public void deselectButton() {
        if(selected){
            button.setBackground(grid.game.btn_color);
            selected = false;
        }
    }
}
