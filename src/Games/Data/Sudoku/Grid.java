package Games.Data.Sudoku;

import Games.Engine.Game;
import Games.Sudoku;

import javax.swing.*;
import java.awt.*;

public class Grid {

    public JButton[][] grid_buttons;

    private Sudoku game;
    public int x_cord, y_cord;

    //  normalerweise wird die Oberklasse GAME als Parameter
    //  verwendet. Da auf das spezielle Layout zugegriffen wird,
    //  ist dies hier nicht möglich.
    public Grid(Sudoku game, int x_cord, int y_cord) {
        this.game = game;
        this.x_cord = x_cord;
        this.y_cord = y_cord;

        game.gbc.gridwidth = 1;
        game.gbc.gridheight = 1;
        game.gbc.ipady = 10;
        game.gbc.ipadx = 30;

        grid_buttons = new JButton[9][9];

        JButton tmp_btn;

        // Für jede der 9 Spalten
        for(int y = 0; y < 9; y++){
            // Spalte mit 9 Buttons füllen
            for(int x = 0; x < 9; x++){
                tmp_btn = new JButton();

                tmp_btn.setVisible(true);
            }
        }
    }
}
