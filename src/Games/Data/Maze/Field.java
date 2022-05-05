package Games.Data.Maze;

import Games.Engine.Kompositum.Dataelement;

import java.util.*;

public class Field extends Dataelement {
    private final int x_pos;//x-Position des Feldes
    private final int y_pos;//y-Position des Feldes
    private final boolean[] connections;//Verbindungen zu anderen Feldern
    private final Random random = new Random();
    public Field(int y_pos, int x_pos, int number){
        super(number);
        this.x_pos = x_pos;//ypos setzen
        this.y_pos = y_pos;//xpos setzen
        connections = new boolean[4];//[Nord, Ost, Süd, West] true = verbindung, null = mauer, false = screen border
   }
   //public void setBorders()
   public int setNext(){
        while(true){
            int next = random.nextInt(0,4);
            if(!connections[next]){
                connections[next] = true;
                return next;
            }
        }
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
