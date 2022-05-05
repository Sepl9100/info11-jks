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
    private JButton generator_btn, solver_btn, entry_sudoku_btn, check_btn, reset_btn, clear_selection_btn;

    public Sudoku(Window window){
        super(window, "Sudoku");
        this.setBackground(Color.gray);
        this.setLayout(null);

        // Button Raster
        grid_panel = new JPanel();
        grid_panel.setBackground(Color.white);
        grid_panel.setLocation(70, 25);
        grid_panel.setSize(550, 550);
        grid_panel.setLayout(null);
        grid_panel.setVisible(true);

        grid = new Grid(this, grid_panel);
        this.add(grid_panel);


        // Menü Buttons
        // Auswahl zurücksetzten
        clear_selection_btn = new JButton("Auswahl zurücksetzten");
        clear_selection_btn.setBounds(640, 20, 200, 25);
        clear_selection_btn.setVisible(true);
        this.add(clear_selection_btn);
        // Generator
        generator_btn = new JButton("Sudoku generieren");
        generator_btn.setBounds(850, 20, 200, 25);
        generator_btn.setVisible(true);
        this.add(generator_btn);
        // Löser
        solver_btn = new JButton("Sudoku lösen");
        solver_btn.setBounds(850, 70, 200, 25);
        solver_btn.setVisible(true);
        this.add(solver_btn);
        // Eigenes Sudoku eintragen
        entry_sudoku_btn = new JButton("Eigenes Sudoku eintragen");
        entry_sudoku_btn.setBounds(850, 120, 200, 25);
        entry_sudoku_btn.setVisible(true);
        this.add(entry_sudoku_btn);
        // Überprüfen
        check_btn = new JButton("Sudoku überprüfen");
        check_btn.setBounds(850, 170, 200, 25);
        check_btn.setVisible(true);
        this.add(check_btn);
        // Reset
        reset_btn = new JButton("Sudoku zurücksetzten");
        reset_btn.setBounds(850, 220, 200, 25);
        reset_btn.setVisible(true);
        this.add(reset_btn);
        // Menü Buttons Ende


        window.pack();
    }

    @Override
    public void update_loop() {

    }
}
