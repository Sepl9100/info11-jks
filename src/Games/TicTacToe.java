package Games;

import Games.Engine.Game;
import Games.Engine.Sprite;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TicTacToe extends Game {

    private Sprite[] sprites;
    //private Random random;

    public TicTacToe(Window window){
        super(window, "Tic-Tac-Toe");
        this.setBackground(Color.gray);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(3,3,3,3);

        JLabel topText = new JLabel();
        topText.setText("Hier ist der Obere Text \n Hier ist Die zweite Reihe an Text");
        gbc.gridwidth= 11;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(topText, gbc);

        window.pack();
    }

    @Override
    public void update_loop() {

    }
}
