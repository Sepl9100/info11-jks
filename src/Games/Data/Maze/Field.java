package Games.Data.Maze;

import Games.Engine.Kompositum.Dataelement;

public class Field extends Dataelement {
    private final int x_pos;//x-Position des Feldes
    private final int y_pos;//y-Position des Feldes
    private boolean is_drawn;
    private static boolean[] connections;//Verbindungen zu anderen Feldern
    public Field(int y_pos, int x_pos, int number){
        super(number);
        this.x_pos = x_pos;//y_pos setzen
        this.y_pos = y_pos;//x_pos setzen
        this.is_drawn = false;
        connections = new boolean[4];//[Nord, Ost, Süd, West] true = verbindung, null = mauer, false = screen border
   }
   //public void setBorders()
   public void reset_connections(){
       for (boolean connection : this.connections) {//geht durch alle Verbindungen
           connection = false;                      //und negiert diese
       }
   }
   public void set_connection_True(int connection){
        this.connections[connection] = true; //setzt bestimmte Verbindung auf Wahr
   }
    public void setIs_drawn(boolean is_drawn){
        this.is_drawn = is_drawn;
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
    public boolean get_is_drawn(){
        return this.is_drawn;
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
