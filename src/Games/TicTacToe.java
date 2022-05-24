/*
 ______   __     ______     ______   ______     ______     ______   ______     ______
/\__  _\ /\ \   /\  ___\   /\__  _\ /\  __ \   /\  ___\   /\__  _\ /\  __ \   /\  ___\
\/_/\ \/ \ \ \  \ \ \____  \/_/\ \/ \ \  __ \  \ \ \____  \/_/\ \/ \ \ \/\ \  \ \  __\
   \ \_\  \ \_\  \ \_____\    \ \_\  \ \_\ \_\  \ \_____\    \ \_\  \ \_____\  \ \_____\
    \/_/   \/_/   \/_____/     \/_/   \/_/\/_/   \/_____/     \/_/   \/_____/   \/_____/

*/
package Games;

//import Games.Data.Tetris.ColorChangeManager;
import Games.Engine.Game;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;

public class TicTacToe extends Game {

    //JAN, BRING DAS SCHAWA ZUM FLOWEN!!!! ~Sebastian Reichl
    //private Random random;
    private int turns = 0;
    private final int[] gameTable = new int[9];
    private final JButton[] gameButtons;
    private final JLabel topText;
    private boolean gameActive;

    public GridBagConstraints gbc;
    public TicTacToe(Window window){
        super(window, "Tic-Tac-Toe");
        //grundsätzliche Layout Definitionen
        this.setBackground(Color.gray);
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();//"Regeln" des Layouts beim Hinzufügen von Objekten
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(3,3,3,3);//Abstände zwischen den Rastern
        gbc.ipadx = 125;//Padding zu den Rastern (vergrößerung zusätzlich zum Inhalt)
        gbc.ipady = 25;

        //Kopf der Spiele-GUI
        topText = new JLabel();
        set_top_text();
        topText.setHorizontalAlignment(SwingConstants.CENTER);//Zentrierung vom Text
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth= 11;//füllt 11 Spalten aus
        gbc.fill = GridBagConstraints.HORIZONTAL;//füllt volle Breite der Spalten aus
        this.add(topText, gbc);

        //Spielfeld mit den Buttons erstellen
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.ipady = 10;
        gbc.ipadx = 30;
        gameButtons = new JButton[9];
        for(int i = 0; i < 9; i++){
            gameButtons[i] = new JButton();
            gameButtons[i].setFont(font3);//font3 aus der Game Klasse
            gameButtons[i].setVisible(true);
            final int tmp = i;
            gameButtons[i].addActionListener(e -> button_click(tmp));//Java wollte tmp als final int haben
            gbc.gridx = i % 3 + 1;//Spaltendefinierung
            gbc.gridy = i / 3 + 1;//Zeilendefinierung
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
        reset.addActionListener(e -> reset_game());
        this.add(reset, gbc);

        reset_game();//Spielfeld spielbereit machen
        window.pack();
    }

    public void reset_game(){
        for(int i = 0;i < 9; i++){
            gameTable[i] = 0;
            gameButtons[i].setText("  ");
            set_top_text();
            gameActive = true;
        }
    }

    public void button_click(int button){
        if(gameActive){
            if(gameTable[button]==0){
                if(turns%2==0){//Spieler X am Zug
                    gameButtons[button].setText("x");
                    gameTable[button] = 1;
                    turns++;
                    set_top_text();
                }else{//Spieler O am Zug
                        gameButtons[button].setText("o");
                        gameTable[button] = -1;
                        turns++;
                        set_top_text();
                    }
            }
        }
    }

    public void set_top_text(){
        int test = check_win();
        if(test==0){//==kein gewinner
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

    private int check_win(){
        for(int i = 0; i<3; i++){//1 = Spieler 1; -1 = Spieler 2
            if(gameTable[3 * i] + gameTable[1 + 3 * i] + gameTable[2 + 3 * i] == 3)return 1;//Waagrechte Reihen
            if(gameTable[3 * i] + gameTable[1 + 3 * i] + gameTable[2 + 3 * i] == -3)return -1;

            if(gameTable[i] + gameTable[3 + i] + gameTable[6 + i] == 3)return 1;//Sekrechte Reihen
            if(gameTable[i] + gameTable[3 + i] + gameTable[6 + i] ==-3)return -1;
        }
        if(gameTable[0] + gameTable[4] + gameTable[8] == 3)return 1;//Diagonale r->l
        if(gameTable[0] + gameTable[4] + gameTable[8] ==-3)return -1;

        if(gameTable[2]+gameTable[4]+gameTable[6]==3)return 1;//Diagonale l->r
        if(gameTable[2]+gameTable[4]+gameTable[6]==-3)return -1;

        else return 0;//noch kein Gewinner
    }

    @Override
    public void update_loop() {

    }
}
