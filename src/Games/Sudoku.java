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
    private JButton generator_btn, solver_btn, entry_sudoku_btn, check_btn, reset_btn, clear_selection_btn;
    private JLabel info_label;
    public Color btn_color;
    private boolean quiz_input, pause_loop;

    private int[][] quiz;


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
        // ------------------

        keys = new SudokuKeyBinds(this);

        logic = new Logic();
        

        // -----------------
        // Menü Buttons
        // Auswahl zurücksetzten
        clear_selection_btn = new JButton("Auswahl zurücksetzten");
        clear_selection_btn.setBounds(655, 550, 170, 25);
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
                tmp_number_btn.addActionListener(e -> input_number(tmp_lmbd));
                this.add(tmp_number_btn);
            }
        }

        tmp_number_btn = new JButton("0");
        tmp_number_btn.setFont(this.font1);
        tmp_number_btn.setBounds(655, 400, 170, 50);
        tmp_number_btn.setVisible(true);
        tmp_number_btn.addActionListener(e -> input_number(0));
        this.add(tmp_number_btn);

        info_label = new JLabel("<html>Tastatureingabe der Zahlen ist möglich. " +
                "Die 0 steht für ein leeres Feld. Dies kann bei der Eintragung eines" +
                " Sudokus benutzt werden.</html>");
        info_label.setFont(this.font1);
        info_label.setBounds(655, 380, 260, 250);
        this.add(info_label);

        // Menü Buttons Ende
        // -----------------


        quiz_input = true;

        grid.resetButtons();
        quiz = logic.generate_sudoku(80);
        setReset_btn();

        // -----------------
        window.pack();
    }

    @Override
    public void update_loop() {
        if(!pause_loop){
            keys.update();
        }
    }


    // ----------------
    // Button Actions

    public void setClear_selection_btn() {
        grid.deselectButton();
    }
    public void setGenerator_btn() {
        pause_loop = true;
        String missing_numbers_input = JOptionPane.showInputDialog(this, "Fehlende Zahlen (1-80)", null);
        int missing_numbers;
        try {missing_numbers = Integer.parseInt(missing_numbers_input);}
        catch (NumberFormatException e){missing_numbers = 0;}

        if(missing_numbers > 0 && missing_numbers < 81) {
            setReset_btn();
            quiz = logic.generate_sudoku(missing_numbers);
            grid.init_quiz(quiz);
            grid.unlockButtons(false);
        }
        else {
            JOptionPane.showMessageDialog(this, "Ungültige Eingabe",
                    "Eingabefehler", JOptionPane.WARNING_MESSAGE);
        }
        pause_loop = false;
    }

    public void setSolver_btn() {
        int[][] result = null;
        if(logic.check_solvability(quiz)) {
            result = logic.solve_sudoku(quiz);
        }

        if(result == null) {
            JOptionPane.showMessageDialog(this, "Unlösbares Sudoku",
                    "Sudoku Fehler", JOptionPane.WARNING_MESSAGE);
        }
        else {
            quiz = result;
            grid.init_quiz(quiz);
        }
    }

    public void setEntry_sudoku_btn() {
        if(quiz_input) {
            grid.unlockButtons(true);
            entry_sudoku_btn.setBackground(Color.green);
            quiz_input = false;
        }
        else {
            setClear_selection_btn();
            grid.lockButtons(false);
            entry_sudoku_btn.setBackground(btn_color);
            quiz_input = true;
        }
    }

    public void setCheck_btn() {
        if(logic.check_sudoku(quiz)) {
            JOptionPane.showMessageDialog(this, "Sehr gut! Das ist ein richtiges Sudoku!",
                    "Sudoku überprüft", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Das eingetragene Sudoku ist leider falsch.",
                    "Sudoku überprüft", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setReset_btn() {
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++){
                quiz[y][x] = 0;
            }
        }
        grid.resetButtons();
    }

    // Button Actions Ende
    // -----------------

    public void input_number(int number) {
        if(grid.input_number(number)){
            quiz[grid.selected_button.y_pos][grid.selected_button.x_pos] = number;
        }
    }


}