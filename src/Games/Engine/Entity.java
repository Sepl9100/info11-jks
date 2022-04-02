package Games.Engine;

import java.awt.image.BufferedImage;

public class Entity {

    private final Game game;
    public Sprite sprite;

    public int x, y = 0;
    public boolean destination_reached = true;

    public Entity(Game game, int layer, BufferedImage bufferedImage){
        this.game = game;
        this.sprite = new Sprite(game, layer, bufferedImage);
    }

    public void move_to(int xdest, int ydest, int tick_delay, double speed) {
        this.destination_reached = false;

        new Thread(() -> {
            while((xdest != this.x) || (ydest != this.y)) {
                game.pass();
            }
            this.destination_reached = true;
        }).start();

        int[][] route = line(x, y, xdest, ydest);
        for(int i = 0; i < route.length; i++) {
            final int temp = i;
            new QueueTask(game, (int)((tick_delay*i)/speed), e -> this.place(route[temp][0], route[temp][1]));
        }
    }


    public void place(int x, int y) {
        this.x = x;
        this.y = y;
        update_pos();
    }

    public void move(int xadd, int yadd) {
        this.x += xadd;
        this.y += yadd;
        update_pos();
    }

    public void update_pos() {
        sprite.set_pos(this.x, this.y);
    }


    public int[][] line(int x0, int y0, int x1, int y1)
    {
        int dx, dy, sx, sy, err, err2, array_length;
        int route_index = 0;

        // berchene den unterschied zwischen start und ziel
        dx = Math.abs(x1-x0);
        dy = Math.abs(y1-y0);

        array_length = Math.max(dx, dy)+1;
        int[][] output = new int[array_length][2];

        // wähle die schnellst steigende seite aus
        if(x0 < x1)
        {sx = 1;}
        else
        {sx = -1;}
        if(y0 < y1)
        {sy = 1;}
        else
        {sy = -1;}

        // inital error
        err = dx-dy;
        while(true)
        {
            // speichere die koordinaten im array
            output[route_index][0] = x0;
            output[route_index][1] = y0;

            // return sobald das ziel erreicht ist
            if(x0 == x1 && y0 == y1)
            {return output;}

            // passe den error an
            err2 = 2 * err;

            // entscheide ob in die kurze Richtung gegangen wird
            if(err2 > -dy)
            {
                err = err-dy;
                x0 = x0+sx;
            }
            if(err2 < dx)
            {
                err = err+dx;
                y0 = y0+sy;
            }

            // erhöhe den index
            route_index++;
        }
    }
}
