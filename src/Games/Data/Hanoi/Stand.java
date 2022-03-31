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

    public int x, y, size;
    private Game game;
    public Sprite rec_bottom, rec_top;
    private Stack stack;

    public Stand(Game game, int x, int y, int size){
        this.x = x;         // cords of the top left corner
        this.y = y;         // from the bottom rectangle
        this.size = size;
        this.game = game;

        rec_bottom = new Sprite(this.game, 5, null);
        rec_bottom.set_pos(x, y);
        rec_bottom.resize(24*size, 2*size);
        rec_bottom.color = Color.white;

        rec_top = new Sprite(this.game, 5, null);
        rec_top.set_pos(x+11*size, y-18*size);
        rec_top.resize(2*size, 18*size);
        rec_top.color = Color.white;

        stack = new Stack();

    }

    public void init_rings() {for(int i = 0; i < 6; i++) {stack.insert(new Ring(game, x, y-2*size*i, size, i+1));}}

    public Ring get_top_ring(){
        Listelement topelement = stack.remove();
        if((!(topelement instanceof End)) && topelement.get_content() instanceof Ring) {
            return (Ring) topelement.get_content();      // return ring from datanode Ring
        }
        else {
            return null;            // returns null if End or Datanode without Ring
        }
    }

}
