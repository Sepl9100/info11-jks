package Games;

import Games.Engine.Game;
import Games.Engine.Window;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends Game {

    public MainMenu(Window window){
        super(window, "Hauptmenü");

        this.setBackground(Color.red);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL; //Jeder Button füllt eigene Breite aus

        gbc.gridx = 0;
        gbc.gridy = 0;
        JButton b = new JButton();
        b.setText("Open snake game");
        b.setVisible(true);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                new Snake(window);
            }
        });
        this.add(b, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;

        JButton hanoi = new JButton();
        hanoi.setText("Hanoi Game");
        hanoi.setVisible(true);
        hanoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                new Hanoi(window);
            }
        });
        this.add(hanoi, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JButton tictactoe = new JButton();
        tictactoe.setText("Tic-Tac-Toe Game");
        tictactoe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                new TicTacToe(window);
            }
        });
        this.add(tictactoe, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2; //ButtonBreite = 2
        JButton exit = new JButton();
        exit.setText("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(exit, gbc);

        window.pack();
    }

    @Override
    public void update_loop() {

    }

    public void exit(){
        window.remove(this);
        window.repaint();
    }
}
