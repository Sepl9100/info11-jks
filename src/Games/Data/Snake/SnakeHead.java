package Games.Data.Snake;

import Games.Engine.Game;
import Games.Engine.QueueTask;
import Games.Snake;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SnakeHead extends SnakeGameSprite{

    private SnakeBody next_body;
    public boolean on_drugs;
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
                next_body.follow_snake((int) tile_x, (int) tile_y);     // Snake body pieces recursiv mitbewegen
            }
            SnakeGameSprite apple = game.check_apple(tile_x + x, tile_y + y);       // wenn vor apfel
            if (apple != null){
                this.add_body();        // schlange verlängern
                game.score += 1;
                apple.delete();
                game.apples.remove_sprite(apple);
                game.place_apple();
            }
            SnakeGameSprite drug = game.check_drug(tile_x + x, tile_y + y);         // wenn vor doping
            if (drug != null){
                this.add_body();        // schlange verlängern
                game.score += 10;
                drug.delete();
                game.drugs.remove_sprite(drug);
                game.place_drug();
                this.on_drugs = true;
                new QueueTask(game, 200, e -> this.on_drugs = false); // Task erstellen -> Spieler nach 200 ticks wieder langsam machen
            }
            super.move(x, y);
        }
        else game.game_over_screen();
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
