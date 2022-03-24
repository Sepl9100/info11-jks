package Games.Engine;

import java.awt.image.BufferedImage;

public class Entity {

    private final Game game;
    public Sprite sprite;

    public int x, y = 0;

    public Entity(Game game, int layer, BufferedImage bufferedImage){
        this.game = game;
        this.sprite = new Sprite(game, layer, bufferedImage);
    }

    public void place(int x, int y) {
        this.x = x;
        this.y = y;
        update_pos();
    }

    public void move(int xadd, int yadd) {
        this.x += xadd;
        this.y += yadd;
        update_pos();
    }

    public void update_pos() {
        sprite.set_pos(this.x, this.y);
    }
}
