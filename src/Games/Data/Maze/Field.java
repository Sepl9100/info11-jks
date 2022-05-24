package Games.Data.Maze;

import Games.Engine.Kompositum.Dataelement;

import java.util.*;

public class Field extends Dataelement {
    private final int x_pos;//x-Position des Feldes
    private final int y_pos;//y-Position des Feldes
    public static boolean[] connections;//Verbindungen zu anderen Feldern
    private final Random random = new Random();
    public Field(int y_pos, int x_pos, int number){
        super(number);
        this.x_pos = x_pos;//ypos setzen
        this.y_pos = y_pos;//xpos setzen
        connections = new boolean[4];//[Nord, Ost, Süd, West] true = verbindung, null = mauer, false = screen border
   }
   //public void setBorders()
   public void reset_connections(){
       for (boolean connection : this.connections) {
           connection = false;
       }
   }
    public int get_x_pos(){
        return this.x_pos;
    }
    public boolean[] get_connections(){
        return this.connections;
    }
    public int get_y_pos(){
        return this.y_pos;
    }
    @Override
    public boolean equals(Dataelement de) {
        return false;
    }

    @Override
    public int get_number() {
        return 0;
    }
}
