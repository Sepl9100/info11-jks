package Games;

import Games.Data.Hanoi.Stand;
import Games.Engine.Game;
import Games.Engine.Window;

import java.awt.*;

public class Hanoi extends Game {

    private Stand test_stand, test_stand2;

    public Hanoi(Window window) {
        super(window, "Hanoi");
        this.setBackground(Color.black);

        test_stand = new Stand(this, 50, 300);
        test_stand2 = new Stand(this, 300, 300);
    }

    @Override
    public void update_loop() {

    }
}
