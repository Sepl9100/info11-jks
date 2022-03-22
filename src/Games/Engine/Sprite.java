package Games.Engine;

import java.awt.*;

public class Sprite {
    public int x;
    public int y;
    public int width;
    public int height;

    private final Game game;

    public Sprite(Game game) {
        this.game = game;
    }

    public void draw(Graphics g){
        g.drawRect(x,y,width,height);
    }

    public void delete(){

    }

    public void print(){
        System.out.println(x);
        System.out.println(y);
        System.out.println(width);
        System.out.println(height);
    }
}
