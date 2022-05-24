/*
 __    __     ______     ______     ______
/\ "-./  \   /\  __ \   /\___  \   /\  ___\
\ \ \-./\ \  \ \  __ \  \/_/  /__  \ \  __\
 \ \_\ \ \_\  \ \_\ \_\   /\_____\  \ \_____\
  \/_/  \/_/   \/_/\/_/   \/_____/   \/_____/

*/

package Games;

import Games.Data.Maze.Field;
import Games.Engine.Game;
import Games.Engine.Kompositum.Stack;
import Games.Engine.SpriteList;
import Games.Engine.Window;

import java.awt.*;
import java.util.Random;

public class Maze extends Game {
    private final Stack gen_Stack;
    private final int path_Width = 3;
    private final int maze_Width = Game.PANELWIDTH/ path_Width *10;
    private final int maze_Height = Game.PANELHEIGHT/ path_Width *10;
    private final SpriteList m_graphic = new SpriteList();
    private final Field[][] maze;
    private final boolean[][] m_visited;
    //private final int straightness = 1;
    private final Random random = new Random();

    public Maze(Window window){
        super(window, "Maze");
        this.setBackground(Color.black);
        maze = new Field[maze_Height][maze_Width];
        gen_Stack = new Stack();
        m_visited = new boolean[maze_Height][maze_Width]; //Alle besuchten Felder werden hier eingetragen
        init_maze();
        maze_gen();
        window.pack();
    }
    public void init_maze(){//Macht das Feld bereit
        for(int y = 0; y < maze_Height; y++){//alle y-Werte und
            for(int x = 0; x < maze_Width; x++){//für alle x-Werte
                maze[y][x] = new Field(y,x, 1);//werden in die Felder geschrieben
            }
        }
    }
    public void reset_maze(){
        for(int y = 0; y < maze_Height; y++){
            for(int x = 0; x < maze_Width; x++){
                maze[y][x].reset_connections();//Felder werden zurückgesetzt
                m_visited[y][x] = false;//Felder werden wieder unbesucht

            }
        }
    }

    public void maze_gen(){
        gen_Stack.insert(maze[0][0]);//Startposition des Labyrinths ist (0, 0)
        Field tmp_data;//Aktuelles Field wird hier reingeladen
        int tmpX ,tmpY;//Momentane Position beim Generieren
        while(gen_Stack.count_nodes() != 0){// solange im Stack noch Elemente sind, wird weitergemacht
            //System.out.println(genStack.get_first().get_content().getClass());//Debugging
            tmp_data = (Field) gen_Stack.get_first().get_content();//das aktuelle Feld wird zugänglich gemacht
            int[] next_pos = get_next_pos(tmp_data);

            gen_Stack.remove();
        }

    }

    public int[] get_next_pos(Field current_field){//gibt die Koordinaten für das nächste Feld für das jetzige Feld zurück(bei der Generierung
        int next_dir = get_next_dir(current_field);//Richtung für das nächste Feld
        int[] delta_pos;
        delta_pos = get_delta_pos(next_dir);//Veränderung der alten Koordinaten zu den vom neuen Feld
        int[] new_pos = new int[2];
        new_pos[0] = current_field.get_y_pos() + delta_pos[0];
        new_pos[1] = current_field.get_x_pos() + delta_pos[1];
        return new_pos;

    }

    private int[] get_delta_pos(int direction) {//gibt die Veränderung der Koordinaten zur eingegebenen Richtung
        int[] coords = new int[2];//[y, x]
        if(direction%2 == 0){//Nord oder Südrichtung
            coords[1] = 0;//Delta x = 0
            if(direction < 1){//norden
                coords[0] = 1;
            }else{//Süden
                coords[0] = -1;
            }
        }else{//Ost- oder Westrichtung
            coords[0] = 0;//Delta y = 0
            if(direction < 2){//Osten
                coords[1] = 1;
            }else{//Westen
                coords[1] = -1;
            }
        }
        return coords;
    }

    private int get_next_dir(Field current_pos){//gibt die nächste Richtung zurück, in die das Labyrinth geht
        while(true){
            int tmp_next_dir = random.nextInt(4);//Generiert eine zufällige Richtung
            if(!current_pos.connections[tmp_next_dir]){//Überprüft die Richtung, damit diese noch nicht besetzt ist
                return tmp_next_dir;//gibt das überprüfte Ergenbis zurück
            }
        }
    }

    @Override
    public void update_loop() {

    }
}

