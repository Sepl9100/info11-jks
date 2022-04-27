package Games.Data.Tetris.Tiles;

import java.awt.*;

public class Tilecolors {

    public static int colorcount = 6;

    public static Color get(int colornumber){
        if (colornumber == 1){
            return Color.decode("#e6be50");     // orange
        } else if (colornumber == 2){
            return Color.decode("#67e67c");     // green
        } else if (colornumber == 3){
            return Color.decode("#4b5cf2");     // blue
        } else if (colornumber == 4){
            return Color.decode("#eb584d");     // red
        } else if (colornumber == 5){
            return Color.decode("#b74aed");     // pink / purple
        } else if (colornumber == 6){
            return Color.decode("#2df7f1");     // cyan
        }

        return Color.white;
    }

}
