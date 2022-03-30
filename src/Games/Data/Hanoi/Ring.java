package Games.Data.Hanoi;

import Games.Engine.Entity;
import Games.Engine.Game;
import Games.Engine.Kompositum.Dataelement;

import java.awt.*;

public class Ring extends Dataelement {
    // A Ring is dumb! He does NOT know anything about the game. controlled by stand.
    // Size Number. 1-7. 7 = smallest

    public int x, y, size, number;
    private Game game;
    private Entity ring_entity;

    public Ring(Game game, int x, int y, int size, int number){
        super(number);

        this.x = x;
        this.y = y;
        this.game = game;
        this.number = number;
        this.size = size;

        ring_entity = new Entity(game, 6, null);
        ring_entity.place(x, y);
        ring_entity.sprite.resize(24*size-number*size*4,2*size);
        ring_entity.sprite.color = Color.green;
    }

    public boolean equals(Dataelement de) {
        return (this.number == de.number);
    }

    public int get_number() {
        return this.number;
    }
}
