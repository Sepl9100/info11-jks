package Games;

import Games.Engine.Game;
import Games.Engine.Kompositum.Stack;
import Games.Engine.Window;

import java.awt.*;

public class Maze extends Game {
    private Stack genStack;
    private boolean[][] m_visited;
    private int straightness = 1;

    public Maze(Window window){
        super(window, "Maze");
        this.setBackground(Color.black);


        window.pack();
    }

    @Override
    public void update_loop() {

    }
}

