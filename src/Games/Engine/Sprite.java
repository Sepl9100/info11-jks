package Games.Engine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Sprite {
    public int x;
    public int y;
    public int width = 100;
    public int height = 100;
    public Color color = Color.red;

    private boolean is_image = true;

    private BufferedImage image = null;

    private final Game game;

    public Sprite(Game game, int layer, BufferedImage buffered_image) {
        this.game = game;
        game.spritelist.add_sprite(this, layer);
        image = buffered_image;
        if (image == null){
            is_image = false;
        }
    }

    public void set_pos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){
        if (is_image){
            g.drawImage(image, x, y,width, height, null);
        }
        else{
            g.setColor(color);
            g.fillRect(x,y,width,height);
        }
    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
    }


    public void delete(){
        game.spritelist.remove_sprite(this);
    }

    public void print(){
        System.out.println("SPRITE");
        System.out.println(x);
        System.out.println(y);
        System.out.println(width);
        System.out.println(height);
        System.out.println();
    }
}
