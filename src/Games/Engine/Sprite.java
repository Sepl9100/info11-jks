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

    public Sprite(Game game, int layer, String image_file) {
        this.game = game;
        game.spritelist.add_sprite(this, layer);

        if (image_file == null){
            is_image = false;
        }

        if (is_image){
            try {
                image = load_image("/" + image_file);
            }
            catch(Exception e){
                System.out.println("Problem opening file");
            }
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

    private BufferedImage load_image(String filename) {
        try {
            InputStream in = getClass().getResourceAsStream(filename);
            return ImageIO.read(in);
        } catch (IOException e) {
            System.out.println("Error loading image.");
        }
        return null;
    }

    public void delete(){

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
