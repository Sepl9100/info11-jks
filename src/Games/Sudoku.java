package Games;

import Games.Data.Sudoku.Grid;
import Games.Data.Tetris.ColorChangeManager;
import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;

public class Sudoku extends Game {

    private Grid grid;
    public JPanel grid_panel;

    public Sudoku(Window window){
        super(window, "Sudoku");
        this.setBackground(Color.gray);
        this.setLayout(null);

        // Button Raster
        grid_panel = new JPanel();
        grid_panel.setBackground(Color.white);
        grid_panel.setLocation(50, 25);
        grid_panel.setSize(550, 550);
        grid_panel.setLayout(null);
        grid_panel.setVisible(true);

        grid = new Grid(this, grid_panel);
        this.add(grid_panel);

        // Menü Buttons

        // Generator

        // Löser

        // Eigenes Sudoku eintragen

        // Überprüfen

        // Reset

        // Menü Buttons Ende

        window.pack();
    }

    @Override
    public void update_loop() {

    }
}
