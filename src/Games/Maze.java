/*
 __    __     ______     ______     ______
/\ "-./  \   /\  __ \   /\___  \   /\  ___\
\ \ \-./\ \  \ \  __ \  \/_/  /__  \ \  __\
 \ \_\ \ \_\  \ \_\ \_\   /\_____\  \ \_____\
  \/_/  \/_/   \/_/\/_/   \/_____/   \/_____/

*/

package Games;

import Games.Data.Maze.Field;
import Games.Data.Tetris.ColorChangeManager;
import Games.Engine.Game;
import Games.Engine.Kompositum.Stack;
import Games.Engine.Sprite;
import Games.Engine.SpriteList;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Maze extends Game {
    private final Stack gen_Stack;
    private final int cell_width = 20;//Zellenseitenlänge in px
    private final int maze_Width = Game.PANELWIDTH/ cell_width;
    private final int maze_Height = Game.PANELHEIGHT/ cell_width;
    private final Field[][] maze;
    private final boolean[][] maze_visited;
    private final Random random = new Random();
    private int step = 0;

    public Maze(Window window){
        super(window, "Maze");
        this.setBackground(Color.black);
        maze = new Field[maze_Height][maze_Width];//Liste aller Felder
        gen_Stack = new Stack();
        maze_visited = new boolean[maze_Height][maze_Width];//Alle besuchten Felder werden hier eingetragen

        JButton regen = new JButton();
        regen.setVisible(true);
        regen.setText("Regen");
        regen.addActionListener(e -> {
            regen_maze();
        });
        this.add(regen);

        init_maze();
        //maze_gen();
        gen_Stack.insert(maze[0][0]);
        window.pack();
    }
    public void init_maze(){//Macht das Feld zum ersten Mal bereit
        for(int y = 0; y < maze_Height; y++){//alle y-Werte und
            for(int x = 0; x < maze_Width; x++){//für alle x-Werte
                maze[y][x] = new Field(y,x, 1);//werden in die Felder geschrieben ; number irrelevant
            }
        }
    }
    public void regen_maze(){     //Macht das Feld erneut bereit
        for(int y = 0; y < maze_Height; y++){
            for(int x = 0; x < maze_Width; x++){
                maze[y][x].reset_connections();     //Felder werden zurückgesetzt
                maze[y][x].setIs_drawn(false);
                maze_visited[y][x] = false;     //Felder werden wieder unbesucht
            }
        }
        this.gen_Stack.insert(maze[random.nextInt(maze_Height)][random.nextInt(maze_Width)]);
        this.spritelist = new SpriteList();
	//maze_gen();
    }

    public void maze_gen(){
        gen_Stack.insert(maze[0][0]);     //Startposition des Labyrinths ist (0, 0)
        Field tmp_data;     //Aktuelles Field wird hier reingeladen
        while(gen_Stack.count_nodes() != 0){     // solange im Stack noch Elemente sind, wird weitergemacht
            maze_step();
        }
    }

    public void maze_step(){
        if(gen_Stack.count_nodes() != 0){
            Field tmp_data = (Field) gen_Stack.get_first().get_content();     //das aktuelle Feld wird zugänglich gemacht
            int[] next_pos = get_next_pos(tmp_data);
            if (next_pos == null) {     //wenn momentaner Weg hier endet
                this.add_maze_path(tmp_data, 5);      //Fügt das Feld im Fenster hinzu; direction = 5 => ohne Folgefeld
                gen_Stack.remove();
                //continue;     //geht zum nächsten Schleifendurchgang
            }else {
                this.add_maze_path(tmp_data, next_pos[2]);
                Field next_Field = maze[next_pos[0]][next_pos[1]];     //Macht das nächste Feld greifbar
                gen_Stack.insert(next_Field);     //Fügt das nächste Feld im gen_Stack hinzu
                //next_Field.set_connection_True(next_pos[2]);     //Braucht man für spätere Pläne...=> irrelevant
                maze_visited[next_pos[0]][next_pos[1]] = true;     //Das nächste Feld wird als besucht markiert
            }
        }
    }

    public int[] get_next_pos(Field current_field){//gibt die Koordinaten für das nächste Feld für das jetzige Feld zurück(bei der Generierung
        int next_dir = get_next_dir(current_field);//Richtung für das nächste Feld
        if(next_dir==5) {
            return null;
        }
        int[] delta_pos;
        delta_pos = get_delta_pos(next_dir);//Veränderung der alten Koordinaten zu den vom neuen Feld
        int[] new_pos = new int[3];
        new_pos[0] = current_field.get_y_pos() + delta_pos[0];
        new_pos[1] = current_field.get_x_pos() + delta_pos[1];
        new_pos[2] = next_dir;
        return new_pos;

    }

    private int[] get_delta_pos(int direction) {//gibt die Veränderung der Koordinaten zur eingegebenen Richtung
        int[] coordinates = new int[2];//[y, x]
        if(direction%2 == 0){//Nord oder Südrichtung
            if(direction < 1){//norden
                coordinates[0] = 1;
            }else{//Süden
                coordinates[0] = -1;
            }
        }else{//Ost- oder Westrichtung
            if(direction < 2){//Osten
                coordinates[1] = 1;
            }else{//Westen
                coordinates[1] = -1;
            }
        }
        return coordinates;
    }

    private int get_next_dir(Field current_pos){//gibt die nächste Richtung zurück, in die das Labyrinth geht
        int[] possible_next;
        possible_next = get_possible_next_dir(current_pos);
        if(possible_next[4]==0) return 5; // wenn es keine möglichkeit gibt weiter zu gehen, wird null zurückgegeben
        int random_next_dir = random.nextInt(possible_next[4]);//in possible_next befindet sich der counter im letzten Array-Eintrag
        return possible_next[random_next_dir];
    }

    private int[] get_possible_next_dir(Field current_field){
        int connection_count = 0; // anfangs keine Verbindungen
        int[] possible_connections = new int[5]; //[first_Next, second_Next, third_Next, (fourth_Next), Number_of_Nexts]
        int[] pos = new int[2];//momentane Position
        pos[0] = current_field.get_y_pos();
        pos[1] = current_field.get_x_pos();
        try {
            if(!(maze_visited[pos[0]+1][pos[1]])) {// wenn das Feld noch nicht besucht ist
                possible_connections[connection_count] = 0;//an der Stelle connection_count wird die Richtung 0 == Nord eingesetzt
                connection_count++;
            }}catch(ArrayIndexOutOfBoundsException e){}
        try{
            if(!(maze_visited[pos[0]][pos[1]+1])) {
                possible_connections[connection_count] = 1;//Richtung 1 == Ost
                connection_count++;
            }}catch(ArrayIndexOutOfBoundsException e){}
        try {
            if(!(maze_visited[pos[0]-1][pos[1]])) {
                possible_connections[connection_count] = 2;//Richtung == Süd
                connection_count++;
            }}catch (ArrayIndexOutOfBoundsException e){}
        try{
            if(!(maze_visited[pos[0]][pos[1]-1])){
                possible_connections[connection_count] = 3;//Richtung == West
                connection_count++;
            }}catch (ArrayIndexOutOfBoundsException e){}
        possible_connections[4] = connection_count;// Anzahl an möglichen Richtungen wird in letztem Listenelement gespeichert
        return possible_connections;
    }

    private void add_maze_path(Field current_Field, int direction){
        int topx = current_Field.get_x_pos() * this.cell_width;//obere rechte x-Koordinate des Feldes
        int topy = current_Field.get_y_pos() * this.cell_width;//    -"-      y-Koordinate    -"-
        //cell_width wird mit relativer Breite des Pfades multipliziert, breite der Zentralen Quadrate
        int center_block_width = 14;
        step++;
        if(!current_Field.get_is_drawn()){
           Sprite sprite = new Sprite(this,1,null);
           sprite.color = Color.BLUE;
           sprite.resize(center_block_width, center_block_width);
           sprite.set_pos(topx + 3,topy + 3);
        }
        if(direction<5) {
            Sprite path = new Sprite(this, 1, null);
            path.color = Color.BLUE;
            int path_length = cell_width - center_block_width;
            if (direction == 2) {//Wenns nach oben geht
                path.set_pos(topx + 3, topy - 3);
                path.resize(center_block_width, path_length);//Zeichnet vom jetzigen direkt zum nächsten Zentrum
            } else if (direction == 1) {//Wenns nach rechts geht
                path.set_pos(topx + 3 + center_block_width, topy + 3);
                path.resize(path_length, center_block_width);
            } else if (direction == 0) {//Wenns nach unten geht
                path.set_pos(topx + 3, topy + 3 + center_block_width);
                path.resize(center_block_width, path_length);
            } else if (direction == 3) {//Wenns nach link s geht
                path.set_pos(topx - 3, topy + 3);
                path.resize(path_length, center_block_width);
            }
        }
        current_Field.setIs_drawn(true);
    }

    @Override
    public void update_loop() {
        if (gen_Stack.count_nodes() != 0) {
            maze_step();
        }
    }
}


