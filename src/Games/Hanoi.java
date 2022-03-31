package Games;

import Games.Data.Hanoi.Stand;
import Games.Engine.Game;
import Games.Engine.Window;

import java.awt.*;

public class Hanoi extends Game {

    private Stand stand1, stand2, stand3;

    public Hanoi(Window window) {
        super(window, "Hanoi");
        this.setBackground(Color.black);

        stand1 = new Stand(this, 60, 350,12);
        stand2 = new Stand(this, stand1.x + stand1.rec_bottom.width+60, 350, 12);
        stand3 = new Stand(this, stand2.x + stand2.rec_bottom.width+60, 350, 12);

        stand1.init_rings();

        stand1.get_top_ring();
        stand2.get_top_ring();
    }

    @Override
    public void update_loop() {

    }
}
