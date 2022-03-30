package Games.Data.Snake;

import Games.Engine.Game;
import Games.Snake;

import java.awt.image.BufferedImage;

public class SnakeHead extends SnakeGameSprite{

    private SnakeBody next_body;

    public SnakeHead(Snake game, BufferedImage image){
        super(game, 6, image);



    }

    public void add_body(){
        if (next_body == null){
            next_body = new SnakeBody(game, image);
        }
        else {
            next_body.add_body();
        }
    }

    public void move(int x, int y) {
        if (next_body != null) {
            next_body.set_pos((int) this.x, (int) this.y);
        }
        super.move(x, y);
    }
}
