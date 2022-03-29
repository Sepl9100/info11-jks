package Games.Engine.Kompositum;

public class Stack {
    private Listelement first;

    public Stack(){
        first = new End();
    }

    public void insert(Dataelement content){
        first = new Datanode(first, content);
    }

    public Listelement remove(){
        Listelement oldFirst = first;
        first = first.get_next();
        return oldFirst;
    }
}
