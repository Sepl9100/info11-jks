package Games.Data.Hanoi;

import Games.Engine.Game;
import Games.Engine.Sprite;
import Games.Engine.Window;

import java.awt.*;

public class Stand {

    public int x, y, size;
    private Game game;
    private Sprite rec_bottom, rec_top;

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
        rec_top.set_pos(x+110, y-240);
        rec_top.resize(2*size, 24*size);
        rec_top.color = Color.white;

    }
}
