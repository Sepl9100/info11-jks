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

        stand1 = new Stand(this, 50, 300,10);
        stand2 = new Stand(this, 300, 300, 10);
        stand3 = new Stand(this, 550, 300, 10);
    }

    @Override
    public void update_loop() {

    }
}
