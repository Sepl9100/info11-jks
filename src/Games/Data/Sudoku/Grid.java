package Games.Data.Sudoku;

import Games.Engine.Game;
import Games.Engine.Sprite;
import Games.Sudoku;

import javax.swing.*;
import java.awt.*;

public class Grid {

    public SudokuButton[][] grid_buttons;

    public Sudoku game;
    private JPanel panel;
    public SudokuButton selected_button;


    public Grid(Sudoku game, JPanel panel) {
        this.game = game;
        this.panel = panel;

        grid_buttons = new SudokuButton[9][9];                       // 9x9 Button Array
        selected_button = null;

        // Grid Generation
        SudokuButton tmp_btn;                                        // temporärer button zum erstellen
        int tmp_btn_x;                                               // tmp_button koordinaten
        int tmp_btn_y = -45;

        // Für jede der 9 Spalten mit 9 Buttons füllen
        for(int y = 0; y < 9; y++){
            tmp_btn_y += 55;
            tmp_btn_x = -45;
            if(y%3 == 0) {tmp_btn_y += 10;}

            for(int x = 0; x < 9; x++){
                tmp_btn_x += 55;
                if(x%3 == 0) {tmp_btn_x += 10;}

                tmp_btn = new SudokuButton("", tmp_btn_x, tmp_btn_y, 50, 50, 0, x, y, this);
                tmp_btn.button.setFont(game.font1);
                tmp_btn.button.setVisible(true);


                grid_buttons[y][x] = tmp_btn;
                panel.add(tmp_btn.button);
            }
        }
    }

    public void init_quiz(int[][] quiz) {
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                if(quiz[y][x] != 0) {
                    grid_buttons[y][x].lock_number(quiz[y][x]);
                }
            }
        }
    }

    public boolean input_number(int number) {
        if (selected_button != null) {
            selected_button.setNumber(number);
            return true;
        }
        else return false;
    }

    public void resetButtons() {
        SudokuButton tmp_btn;
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++){
                tmp_btn = grid_buttons[y][x];
                tmp_btn.lock_number(0);
            }
        }
    }

    public void lockButtons(boolean all) {
        SudokuButton tmp_btn;
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++){
                tmp_btn = grid_buttons[y][x];
                if(all) {tmp_btn.lock_number(tmp_btn.number);}
                else if(tmp_btn.number != 0) {tmp_btn.lock_number(tmp_btn.number);}
            }
        }
    }

    public void unlockButtons(boolean all) {
        SudokuButton tmp_btn;
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++){
                tmp_btn = grid_buttons[y][x];
                if(tmp_btn.number == 0 || all){
                    tmp_btn.locked = false;
                    tmp_btn.button.setBackground(game.btn_color);
                }
            }
        }
    }

    public void deselectButton() {
        if(selected_button != null) {
            selected_button.deselectButton();
            selected_button = null;
        }
    }
}
