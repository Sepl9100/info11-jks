package Games.Data.Sudoku;

import Games.Engine.Game;

import javax.swing.*;
import java.awt.*;

public class Grid {

    public JButton[] grid_buttons;

    public Grid(Game game, int x, int y) {

        grid_buttons = new JButton[81];
        for(int i = 0; i < 80; i++) {
            grid_buttons[i] = new JButton(""+i);


        }
    }
}
