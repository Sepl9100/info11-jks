package Games;

import Games.Engine.Game;
import Games.Engine.Sprite;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends Game {

    // JAN, BRING DAS SCHAWA ZUM FLOWEN!!!!
    private Sprite[] sprites;
    //private Random random;
    private int turns = 0;
    private int[] gameTable = new int[9];
    private JButton[] gameButtons;

    public TicTacToe(Window window){
        super(window, "Tic-Tac-Toe");
        this.setBackground(Color.gray);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(3,3,3,3);
        gbc.ipadx = 125;
        gbc.ipady = (int) 25;


        JLabel topText = new JLabel();
        topText.setText("Spieler 1 (X) ist dran!");
        topText.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth= 11;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(topText, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.ipady = 10;
        gbc.ipadx = 30;
        gameButtons = new JButton[9];
        for(int i = 0; i < 9; i++){
            gameButtons[i] = new JButton();
            gameButtons[i].setFont(new Font(Font.DIALOG, Font.PLAIN, 50));
            gameButtons[i].setVisible(true);
            gameButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            gbc.gridx = i % 3 + 1;
            gbc.gridy = (int) i / 3 + 1;

            this.add(gameButtons[i], gbc);
        }

        this.resetGame();
        window.pack();
    }
    public void resetGameTable(){
        for(int i = 0; i < 9; i++){
            gameTable[i] = 0;
        }
    }
    public void resetGame(){
        for(int i = 0;i < 9; i++){
            gameTable[i] = 0;
            gameButtons[i].setText("  ");
        }
    }

    public void buttonClick(int button){
        if(turns % 2 == 0){
            if(gameTable[button] != 0){
                gameButtons[button].setText("x");
                gameTable[button] = 1;
            }
        }else{
            if(gameTable[button] != 0){
                gameButtons[button].setText("o");
                gameTable[button] = -1;
            }
        }
    }

    @Override
    public void update_loop() {

    }
}
