package Games.Engine.Kompositum;

public abstract class Listelement {
    public abstract int count_nodes();
    public abstract Listelement get_next();
    //public abstract Listelement remove(Dataelement search);
    public abstract Datanode insert(Dataelement in);
}
