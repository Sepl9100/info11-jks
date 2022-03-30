package Games.Data.Snake;

import Games.Engine.Game;
import Games.Engine.Sprite;
import Games.Snake;

import java.awt.image.BufferedImage;

public class SnakeGameSprite extends Sprite {
    protected Snake game;
    public int tile_x = 0;
    public int tile_y = 0;

    public SnakeGameSprite(Snake game, int layer, BufferedImage image){
        super(game, layer, image);
        this.width = game.tilesize;
        this.height = game.tilesize;
        this.game = game;
    }

    @Override
    public void set_pos(int x, int y) {
        tile_x = x;
        tile_y = y;
        x = x * game.tilesize;
        y = y * game.tilesize;
        super.set_pos(x, y);
    }

    public void move(int x, int y){
        tile_x += x;
        tile_y += y;
        super.set_pos((int)this.x + x*game.tilesize, (int)this.y + y*game.tilesize);
    }
}
