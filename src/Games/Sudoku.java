package Games;

import Games.Data.Sudoku.Grid;
import Games.Data.Sudoku.Logic;
import Games.Data.Sudoku.SudokuKeyBinds;
import Games.Data.Tetris.ColorChangeManager;
import Games.Engine.Game;
import Games.Engine.KeyBind;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Sudoku extends Game {

    public Grid grid;
    private Logic logic;
    private SudokuKeyBinds keys;
    public JPanel grid_panel;
    private JButton generator_btn, solver_btn, entry_sudoku_btn, check_btn, reset_btn, clear_selection_btn, selected_btn;
    public Color btn_color;
    private boolean selection_locked;

    private int[][] quiz, player_quiz;


    public Sudoku(Window window){
        super(window, "Sudoku");
        this.setBackground(Color.gray);
        this.setLayout(null);

        btn_color = this.back_btn.getBackground();

        // Button Raster
        grid_panel = new JPanel();
        grid_panel.setBackground(Color.white);
        grid_panel.setLocation(70, 25);
        grid_panel.setSize(550, 550);
        grid_panel.setLayout(null);
        grid_panel.setVisible(true);

        grid = new Grid(this, grid_panel);
        this.add(grid_panel);
        // ----

        keys = new SudokuKeyBinds(this);

        logic = new Logic();
        

        // -----------------
        // Menü Buttons
        // Auswahl zurücksetzten
        clear_selection_btn = new JButton("Auswahl zurücksetzten");
        clear_selection_btn.setBounds(640, 20, 200, 25);
        clear_selection_btn.setVisible(true);
        clear_selection_btn.addActionListener(e -> setClear_selection_btn());
        this.add(clear_selection_btn);

        // Generator
        generator_btn = new JButton("Sudoku generieren");
        generator_btn.setBounds(850, 20, 200, 25);
        generator_btn.setVisible(true);
        generator_btn.addActionListener(e -> setGenerator_btn());
        this.add(generator_btn);

        // Löser
        solver_btn = new JButton("Sudoku lösen");
        solver_btn.setBounds(850, 70, 200, 25);
        solver_btn.setVisible(true);
        solver_btn.addActionListener(e -> setSolver_btn());
        this.add(solver_btn);

        // Eigenes Sudoku eintragen
        entry_sudoku_btn = new JButton("Eigenes Sudoku eintragen");
        entry_sudoku_btn.setBounds(850, 120, 200, 25);
        entry_sudoku_btn.setVisible(true);
        entry_sudoku_btn.addActionListener(e -> setEntry_sudoku_btn());
        this.add(entry_sudoku_btn);

        // Überprüfen
        check_btn = new JButton("Sudoku überprüfen");
        check_btn.setBounds(850, 170, 200, 25);
        check_btn.setVisible(true);
        check_btn.addActionListener(e -> setCheck_btn());
        this.add(check_btn);

        // Reset
        reset_btn = new JButton("Sudoku zurücksetzten");
        reset_btn.setBounds(850, 220, 200, 25);
        reset_btn.setVisible(true);
        reset_btn.addActionListener(e -> setReset_btn());
        this.add(reset_btn);

        // Number Buttons
        JButton tmp_number_btn;
        int tmp_number;
        for(int n_y = 0; n_y < 3; n_y++) {
            for(int n_x = 0; n_x < 3; n_x++) {
                tmp_number = n_y*3+n_x+1;
                final int tmp_lmbd = tmp_number;
                tmp_number_btn = new JButton(""+tmp_number);
                tmp_number_btn.setFont(this.font1);
                tmp_number_btn.setBounds(655+n_x*60, 220+n_y*60, 50, 50);
                tmp_number_btn.setVisible(true);
                tmp_number_btn.addActionListener(e -> grid.input_number(tmp_lmbd));
                this.add(tmp_number_btn);
            }
        }

        // Menü Buttons Ende
        // -----------------


        window.pack();
    }

    @Override
    public void update_loop() {
        keys.update();
    }


    // -----------------
    // Button Actions
    public boolean selectButton(JButton button){
        if(!selection_locked) {
            selected_btn = button;
            selected_btn.setBackground(Color.green);
            selection_locked = true;
            return true;
        }
        return false;
    }

    public void setClear_selection_btn() {
        if(selection_locked) {
            selection_locked = false;
            selected_btn.setBackground(clear_selection_btn.getBackground());
            selected_btn = null;
        }
        grid.selected_button.deselectButton();
        grid.selected_button = null;
    }

    public void setGenerator_btn() {
        if(selectButton(generator_btn)) {
            quiz = logic.generate_sudoku(30);
            grid.init_quiz(quiz);
            player_quiz = quiz;
        }
    }

    public void setSolver_btn() {
        if(selectButton(solver_btn)) {

        }
    }

    public void setEntry_sudoku_btn() {
        if(selectButton(entry_sudoku_btn)) {

        }
    }

    public void setCheck_btn() {
        if(selectButton(check_btn)) {

        }
    }

    public void setReset_btn() {
        if(selectButton(reset_btn)) {

        }
    }

    // Button Actions Ende
    // -----------------



}
