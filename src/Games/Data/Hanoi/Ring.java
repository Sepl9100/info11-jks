package Games.Data.Hanoi;

import Games.Engine.Entity;
import Games.Engine.Game;
import Games.Engine.Kompositum.Dataelement;

public class Ring extends Dataelement {
    // Size Number. 1-6. 6 = biggest

    public int x, y, number;
    private Game game;
    private Entity ring_entity;

    public Ring(Game game, int x, int y, int number){
        super(number);

        this.x = x;
        this.y = y;
        this.game = game;
        this.number = number;



    }

    public boolean equals(Dataelement de) {
        return (this.number == de.number);
    }

    public int get_number() {
        return this.number;
    }
}
