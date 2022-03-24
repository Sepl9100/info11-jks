package Games;

import Games.Data.Hanoi.Stand;
import Games.Engine.Game;
import Games.Engine.Window;

import java.awt.*;

public class Hanoi extends Game {

    private Stand test_stand;

    public Hanoi(Window window) {
        super(window, "Hanoi");
        this.setBackground(Color.black);

        test_stand = new Stand(this, 50, 50);
    }

    @Override
    public void update_loop() {

    }
}
