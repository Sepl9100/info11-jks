package Games;

import Games.Engine.Game;
import Games.Engine.Sprite;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.*;

public class TicTacToe extends Game {

    // JAN, BRING DAS SCHAWA ZUM FLOWEN!!!!
    private Sprite[] sprites;
    //private Random random;
    private int turns = 0;
    private int[] gameTable = new int[9];
    private JButton[] gameButtons;
    private JLabel topText;

    public TicTacToe(Window window){
        super(window, "Tic-Tac-Toe");
        this.setBackground(Color.gray);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(3,3,3,3);
        gbc.ipadx = 125;
        gbc.ipady = (int) 25;


        topText = new JLabel();
        setTopText();
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
            final int temp = i;
            gameButtons[i].addActionListener(e -> buttonClick(temp));
            gbc.gridx = i % 3 + 1;
            gbc.gridy = (int) i / 3 + 1;

            this.add(gameButtons[i], gbc);
        }

        gbc.gridy = 0;
        gbc.gridx = 5;
        gbc.ipady = 5;
        gbc.ipadx = 2;
        JButton reset = new JButton();
        reset.setVisible(true);
        reset.setText("Reset");
        reset.addActionListener(e -> resetGame());
        this.add(reset, gbc);
        resetGame();
        window.pack();
    }

    public void resetGame(){
        for(int i = 0;i < 9; i++){
            gameTable[i] = 0;
            gameButtons[i].setText("  ");
            setTopText();
        }
    }

    public void buttonClick(int button){
        System.out.println(button);
        if(gameTable[button]==0){
            if(turns%2==0){
                gameButtons[button].setText("x");
                gameTable[button] = 1;
                turns++;
                setTopText();
                System.out.println(turns);

            }else{
                    System.out.println(turns);
                    gameButtons[button].setText("o");
                    gameTable[button] = -1;
                    turns++;
                    setTopText();
                }
        }
    }

    public void setTopText(){
        if(checkWin()==0){
            if(turns % 2 == 0){
                topText.setText("Spieler 1 (X) ist dran!");
            }else{
                topText.setText("Spieler 2 (O) ist dran!");
            }
        }else if(checkWin() > 0){
            topText.setText("Herzlichen Glühstrumpf Spieler 1(x)!");
        }else{
            topText.setText("Herzlichen Glühstrumpf Spieler 2 (O)!");
        }
    }

    private int checkWin(){
        for(int i = 0; i<3; i++){
            if((gameTable[0 + 3 * i] + gameTable[1 + 3 * i] + gameTable[2 + 3 * i]) == 3)return 1;
            if((gameTable[0 + 3 * i] + gameTable[1 + 3 * i] + gameTable[2 + 3 * i]) == -3)return -1;

            if((gameTable[0 + i] + gameTable[3 + i] + gameTable[6 + i]) == 3)return 1;
            if((gameTable[0 + i] + gameTable[3 + i] + gameTable[6 + i]) ==-3)return -1;
        }
        if(gameTable[0] + gameTable[4] + gameTable[8] == 3)return 1;
        if(gameTable[0] + gameTable[4] + gameTable[8] ==-3)return -1;

        if(gameTable[2]+gameTable[4]+gameTable[6]==3)return 1;
        if(gameTable[2]+gameTable[4]+gameTable[6]==-3)return -1;
        else return 0;
    }

    @Override
    public void update_loop() {

    }
}
