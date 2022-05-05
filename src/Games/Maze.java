package Games;

import Games.Data.Maze.Field;
import Games.Engine.Game;
import Games.Engine.Kompositum.Stack;
import Games.Engine.Window;

import java.awt.*;

public class Maze extends Game {
    private Stack genStack;
    private final int spriteSize = 20;
    private final int x_val = Game.PANELWIDTH/spriteSize;
    private final int y_val = Game.PANELHEIGHT/spriteSize;
    private Field[][] maze;
    private final boolean[][] m_visited;

    private final int straightness = 1;

    public Maze(Window window){
        super(window, "Maze");
        this.setBackground(Color.black);
        m_visited = new boolean[y_val][x_val]; //alle Felder sind null und noch nicht besucht
        initMaze();
        mazeGen();
        window.pack();
    }
    public void initMaze(){
        for(int y = 0; y < y_val; y++){
            for(int x = 0; x < x_val; x++){
                maze[y][x] = new Field(y,x, 1);
            }
        }
    }
    public void mazeGen(){
        genStack.insert(maze[0][0]);


    }
    @Override
    public void update_loop() {

    }
}

