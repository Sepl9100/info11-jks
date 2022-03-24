package Games;

import Games.Engine.Game;
import Games.Engine.Sprite;
import Games.Engine.Window;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TicTacToe extends Game {

    private Sprite[] sprites;
    //private Random random;

    public TicTacToe(Window window){
        super(window, "Tic-Tac-Toe");
        this.setBackground(Color.gray);
        window.pack();
    }

    @Override
    public void update_loop() {

    }
}
