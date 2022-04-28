package Games;

import Games.Data.Sudoku.Grid;
import Games.Data.Tetris.ColorChangeManager;
import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;

public class Sudoku extends Game {

    public GridBagConstraints gbc;

    private Grid grid;

    public Sudoku(Window window){
        super(window, "Sudoku");

        this.setBackground(Color.gray);
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(1, 1, 1, 1);
        gbc.ipadx = 125;
        gbc.ipady = 25;

        grid = new Grid(this, 0, 0);
    }

    @Override
    public void update_loop() {
        this.setBackground(ColorChangeManager.get_color(tick/2%255));
    }
}
