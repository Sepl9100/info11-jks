package Games.Data.Hanoi;

import Games.Engine.Entity;
import Games.Engine.Game;
import Games.Engine.Kompositum.Dataelement;

import java.awt.*;

public class Ring extends Dataelement {
    // A Ring is dumb! He does NOT know anything about the game. controlled by stand.
    // Size Number. 1-6. 6 = smallest

    public int x, y, size, number;
    private Game game;
    public Entity ring_entity;

    public Ring(Game game, int x, int y, int size, int number){
        super(number);

        this.x = x;
        this.y = y;
        this.game = game;
        this.number = number;
        this.size = size;

        ring_entity = new Entity(game, 6, null);
        ring_entity.place(x, y);
        ring_entity.sprite.resize(24*size-number*size*2,2*size);

        if(number == 1) {ring_entity.sprite.color = Color.green;}
        else if(number == 2) {ring_entity.sprite.color = Color.red;}
        else if(number == 3) {ring_entity.sprite.color = Color.blue;}
        else if(number == 4) {ring_entity.sprite.color = Color.yellow;}
        else if(number == 5) {ring_entity.sprite.color = Color.magenta;}
        else if(number == 6) {ring_entity.sprite.color = Color.cyan;}
        else{ring_entity.sprite.color = Color.PINK;}
    }

    public boolean equals(Dataelement de) {
        return (this.number == de.number);
    }

    public int get_number() {
        return this.number;
    }

    public void move_to(int xdest, int ydest) {
        this.x = xdest;
        this.y = ydest;
        this.ring_entity.move_to(xdest, ydest, 1, 8);
    }
}
