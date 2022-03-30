package Games.Data.Snake;

import Games.Snake;

import java.awt.image.BufferedImage;

public class SnakeBody extends SnakeGameSprite{

    private SnakeBody next_body;

    public SnakeBody(Snake game, BufferedImage image){
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

    public boolean check_collision(int tilex, int tiley){
        if (tilex == tile_x && tiley == tile_y){
            return true;
        }
        else {
            if (next_body == null) return false;
            return next_body.check_collision(tilex, tiley);
        }
    }

    public void follow_snake(int x, int y){
        if (next_body != null) {
            next_body.follow_snake((int) tile_x, (int) tile_y);
        }
        super.set_pos(x, y);
    }
}
