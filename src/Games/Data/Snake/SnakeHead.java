package Games.Data.Snake;

import Games.Engine.Game;
import Games.Snake;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SnakeHead extends SnakeGameSprite{

    private SnakeBody next_body;

    private BufferedImage image_body;

    public SnakeHead(Snake game, BufferedImage image_head, BufferedImage image_body){
        super(game, 6, image_head);
        this.color = Color.GREEN;
        this.image_body = image_body;
    }

    public void add_body(){
        if (next_body == null){
            next_body = new SnakeBody(game, image_body);
            next_body.set_pos((int)this.x, (int)this.y);
        }
        else {
            next_body.add_body();
        }
    }

    public void move(int x, int y) {
        if (!check_collision(tile_x + x, tile_y + y)){
            if (next_body != null) {
                next_body.follow_snake((int) tile_x, (int) tile_y);
            }
            game.check_apple(tile_x + x, tile_y + y);
            super.move(x, y);

        }
        else game.exit();

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
}
