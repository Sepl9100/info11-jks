package Games.Data.Tetris.Tiles;

import java.awt.*;

public class Tilecolors {

    public static Color get(int colornumber){
        if (colornumber == 1){
            return Color.red;
        } else if (colornumber == 2){
            return Color.green;
        } else if (colornumber == 3){
            return Color.blue;
        }
        return Color.white;
    }

}
