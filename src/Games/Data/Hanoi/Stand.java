package Games.Data.Hanoi;

import Games.Engine.Game;
import Games.Engine.Sprite;
import Games.Engine.Window;

import java.awt.*;

public class Stand {

    public int x, y;
    private Game game;
    private Sprite rec_bottom, rec_top;

    public Stand(Game game, int x, int y){
        this.x = x;         // cords of the top left corner
        this.y = y;         // from the bottom rectangle
        this.game = game;

        rec_bottom = new Sprite(this.game, 5, null);
        rec_bottom.set_pos(x, y);
        rec_bottom.resize(240, 20);
        rec_bottom.color = Color.white;

        rec_top = new Sprite(this.game, 5, null);
        rec_top.set_pos(x+110, y-240);
        rec_top.resize(20, 240);
        rec_top.color = Color.white;

    }
}
