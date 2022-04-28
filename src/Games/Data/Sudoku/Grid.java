package Games.Data.Sudoku;

import Games.Engine.Game;
import Games.Sudoku;

import javax.swing.*;
import java.awt.*;

public class Grid {

    public JButton[][] grid_buttons;

    private Sudoku game;
    private GridBagConstraints gbc;
    public int x_cord, y_cord;

    //  normalerweise wird die Oberklasse GAME als Parameter
    //  verwendet. Da auf das spezielle Layout zugegriffen wird,
    //  ist dies hier nicht möglich.
    public Grid(Sudoku game, int x_cord, int y_cord) {
        this.game = game;
        this.x_cord = x_cord;
        this.y_cord = y_cord;

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(1, 1, 1, 1);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.ipady = 10;
        gbc.ipadx = 30;

        grid_buttons = new JButton[9][9];

        JButton tmp_btn;

        // Für jede der 9 Spalten
        for(int y = 0; y < 9; y++){
            gbc.gridy = y;
            // Spalte mit 9 Buttons füllen
            for(int x = 0; x < 9; x++){
                gbc.gridx = x;
                tmp_btn = new JButton();
                tmp_btn.setFont(game.font1);
                tmp_btn.setVisible(true);
                tmp_btn.setText(""+x);
                game.grid_panel.add(tmp_btn, gbc);
                grid_buttons[y][x] = tmp_btn;
            }
        }
    }
}
