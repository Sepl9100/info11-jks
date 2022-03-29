package Games.Engine.Kompositum;

public class Queue {
    private Listelement first;

    public Queue() {
        first = new End();
    }

    public void insert(Dataelement content){
        first=first.insert(content);
    }
    
    public int count_nodes() {
        return first.count_nodes();
    }
}
