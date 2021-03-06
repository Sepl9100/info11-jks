package Games;

import Games.Data.Tetris.ColorChangeManager;
import Games.Engine.Game;
import Games.Engine.Window;
import javax.swing.*;
import java.awt.*;

public class MainMenu extends Game {

    public MainMenu(Window window){
        super(window, "Hauptmenü");

        this.setBackground(Color.gray);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); //Margins/Abstände um die Buttons
        gbc.fill = GridBagConstraints.HORIZONTAL; //Jeder Button füllt eigene maximale Breite aus

        gbc.gridx = 0;
        gbc.gridy = 0;
        //Snake Button
        JButton snake = new JButton();
        snake.setText("Snake (Konstantin)");
        snake.setVisible(true);
        snake.addActionListener(e -> {
                exit();
            new Snake(window);
        });
        this.add(snake, gbc);

        gbc.gridx = 1;
        //Tetris Button
        JButton tetris = new JButton();
        tetris.setText("Tetris (Konstantin)");
        tetris.setVisible(true);
        tetris.addActionListener(e -> {
            exit();
            new Tetris(window);
        });
        this.add(tetris, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        //Hanoi Button
        JButton hanoi = new JButton();
        hanoi.setText("Hanoi (Sebastian)");
        hanoi.setVisible(true);
        hanoi.addActionListener(e -> {
            exit();
            new Hanoi(window);
        });
        this.add(hanoi, gbc);

        gbc.gridx = 1;
        //Sudoku Button
        JButton sudoku = new JButton();
        sudoku.setText("Sudoku (Sebastian)");
        sudoku.setVisible(true);
        sudoku.addActionListener(e -> {
            exit();
            new Sudoku(window);
        });
        this.add(sudoku, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        //maze Button
        JButton maze = new JButton();
        maze.setText("Maze (Jan)");
        maze.setVisible(true);
        maze.addActionListener(e -> {
            exit();
            new Maze(window);
        });
        this.add(maze, gbc);

        gbc.gridx++;
        //TicTacToe Button
        JButton tictactoe = new JButton();
        tictactoe.setText("Tic-Tac-Toe (Jan)");
        tictactoe.addActionListener(e -> {
            exit();
            new TicTacToe(window);
        });
        this.add(tictactoe, gbc);

        gbc. gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; //ButtonBreite = 2
        //exit Button
        JButton exit = new JButton();
        exit.setText("Exit");
        exit.addActionListener(e -> System.exit(0));
        this.add(exit, gbc);

        window.pack();
    }

    @Override
    public void update_loop() {
        this.setBackground(ColorChangeManager.get_color(tick/20%255));      // Hintergrund Farb Wechsel

    }

    public void exit(){
        window.remove(this);
        window.repaint();
    }
}
