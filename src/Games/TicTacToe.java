package Games;

import Games.Data.Tetris.ColorChangeManager;
import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;

public class TicTacToe extends Game {

    //JAN, BRING DAS SCHAWA ZUM FLOWEN!!!!
    //private Random random;
    private int turns = 0;
    private final int[] gameTable = new int[9];
    private final JButton[] gameButtons;
    private final JLabel topText;
    private boolean gameActive;

    public TicTacToe(Window window){
        super(window, "Tic-Tac-Toe");
        //grundsätzliche Layout Definitionen
        this.setBackground(Color.gray);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(3,3,3,3);
        gbc.ipadx = 125;
        gbc.ipady = 25;

        //Kopf der Spiele-GUI
        topText = new JLabel();
        setTopText();
        topText.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth= 11;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(topText, gbc);

        //Spielfeld mit den Buttons erstellen
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.ipady = 10;
        gbc.ipadx = 30;
        gameButtons = new JButton[9];
        for(int i = 0; i < 9; i++){
            gameButtons[i] = new JButton();
            gameButtons[i].setFont(font3);
            gameButtons[i].setVisible(true);
            final int temp = i;
            gameButtons[i].addActionListener(e -> buttonClick(temp));
            gbc.gridx = i % 3 + 1;
            gbc.gridy = i / 3 + 1;

            this.add(gameButtons[i], gbc);
        }

        //Reset Button hinzufügen
        gbc.gridy = 0;
        gbc.gridx = 5;
        gbc.ipady = 5;
        gbc.ipadx = 2;
        JButton reset = new JButton();
        reset.setVisible(true);
        reset.setText("Reset");
        reset.addActionListener(e -> resetGame());
        this.add(reset, gbc);
        //Spielfeld spielbereit machen
        resetGame();
        window.pack();
    }

    public void resetGame(){
        for(int i = 0;i < 9; i++){
            gameTable[i] = 0;
            gameButtons[i].setText("  ");
            setTopText();
            gameActive = true;
        }
    }

    public void buttonClick(int button){
        if(gameActive){
            if(gameTable[button]==0){
                if(turns%2==0){//Spieler X am Zug
                    gameButtons[button].setText("x");
                    gameTable[button] = 1;
                    turns++;
                    setTopText();
                }else{//Spieler O am Zug
                        gameButtons[button].setText("o");
                        gameTable[button] = -1;
                        turns++;
                        setTopText();
                    }
            }
        }
    }

    public void setTopText(){
        int test = checkWin();
        if(test==0){
            if(turns % 2 == 0){
                topText.setText("Spieler 1 (X) ist dran!");
            }else{
                topText.setText("Spieler 2 (O) ist dran!");
            }
        }else{
            gameActive = false;
            if(test > 0){
                topText.setText("Herzlichen Glühstrumpf Spieler 1(x)!");
            }else{
                topText.setText("Herzlichen Glühstrumpf Spieler 2 (O)!");
            }
        }
    }

    private int checkWin(){
        for(int i = 0; i<3; i++){
            if(gameTable[3 * i] + gameTable[1 + 3 * i] + gameTable[2 + 3 * i] == 3)return 1;//Waagrechte Reihen
            if(gameTable[3 * i] + gameTable[1 + 3 * i] + gameTable[2 + 3 * i] == -3)return -1;

            if(gameTable[i] + gameTable[3 + i] + gameTable[6 + i] == 3)return 1;//Sekrechte Reihen
            if(gameTable[i] + gameTable[3 + i] + gameTable[6 + i] ==-3)return -1;
        }
        if(gameTable[0] + gameTable[4] + gameTable[8] == 3)return 1;//Diagonale r->l
        if(gameTable[0] + gameTable[4] + gameTable[8] ==-3)return -1;

        if(gameTable[2]+gameTable[4]+gameTable[6]==3)return 1;//Diagonale l->r
        if(gameTable[2]+gameTable[4]+gameTable[6]==-3)return -1;
        else return 0;
    }

    @Override
    public void update_loop() {

    }
}
