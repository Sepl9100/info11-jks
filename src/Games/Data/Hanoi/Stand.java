package Games.Data.Hanoi;

import Games.Engine.Game;
import Games.Engine.Kompositum.Datanode;
import Games.Engine.Kompositum.End;
import Games.Engine.Kompositum.Listelement;
import Games.Engine.Kompositum.Stack;
import Games.Engine.Sprite;
import Games.Engine.Window;

import java.awt.*;

public class Stand {

    public int x, y, size, number;
    private Game game;
    public Sprite rec_bottom, rec_top;
    public Stack stack;
    public boolean moving_ring = false;

    public Stand(Game game, int x, int y, int size, int number){
        this.x = x;         // x koordinate ecke unten links
        this.y = y;         // y koordinate des unteren rechtecks
        this.size = size;
        this.game = game;
        this.number = number;

        // unteres rechteck
        rec_bottom = new Sprite(this.game, 5, null);
        rec_bottom.set_pos(x, y);
        rec_bottom.resize(24*size, 2*size);
        rec_bottom.color = Color.white;

        // oberes rechteck
        rec_top = new Sprite(this.game, 5, null);
        rec_top.set_pos(x+11*size, y-18*size);
        rec_top.resize(2*size, 18*size);
        rec_top.color = Color.white;

        // Stand hat einen Stack in dem die Ringe gespeichert werden
        stack = new Stack();

    }

    public void init_rings(int rings) {                     // Ringe dem Stack hinzufügen
        for(int i = 0; i < rings; i++) {
            stack.insert(new Ring(game, x+(size*(i+1)), y-2*size*i-2*size, size, i+1));
        }
    }

    public Ring get_top_ring(boolean remove){
        Listelement topelement;

        if(remove) {topelement = stack.remove();}
        else {topelement = stack.get_first();}

        if((!(topelement instanceof End)) && topelement.get_content() instanceof Ring) {
            return (Ring) topelement.get_content();      // return Ring from Datanode content
        }
        else {
        return null;                                    // returns null if End or Datanode without Ring
        }
    }

    public int get_height() {
        Ring ring = get_top_ring(false);
        if(ring instanceof Ring) {return ring.y;}
        else {return this.y;}
    }

    public void add_ring(Ring newRing) {new Thread(() -> {add_ring_thread(newRing);}).start();}

    private void add_ring_thread(Ring newRing) {
        moving_ring = true;
        newRing.move_to(newRing.x, 10);
        while(!newRing.ring_entity.destination_reached) {game.pass();}

        newRing.move_to(this.x+(size*newRing.number), 10);
        while(!newRing.ring_entity.destination_reached) {game.pass();}

        newRing.move_to(this.x+(size*newRing.number), get_height()-2*size);
        while(!newRing.ring_entity.destination_reached) {game.pass();}

        stack.insert(newRing);
        moving_ring = false;
    }

    public void clear() {
        while(stack.count_nodes() > 0) {
            get_top_ring(true).ring_entity.sprite.delete();
        }
    }
}
