package Games.Engine.Kompositum;

public abstract class Dataelement {
    private int number;

    public Dataelement(int number){
        this.number = number;
    }


    public abstract boolean equals(Dataelement de);
    public abstract int get_number();
}
