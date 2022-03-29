package Games.Data.Snake;

import Games.Engine.Game;
import Games.Engine.Sprite;
import Games.Snake;

import java.awt.image.BufferedImage;

public class SnakeGameSprite extends Sprite {
    private Snake game;

    public SnakeGameSprite(Snake game, int layer, BufferedImage image){
        super(game, layer, image);
        this.width = game.tilesize;
        this.height = game.tilesize;
        this.game = game;
    }

    @Override
    public void set_pos(int x, int y) {
        x = x * game.tilesize;
        y = y * game.tilesize;
        super.set_pos(x, y);
    }
}
