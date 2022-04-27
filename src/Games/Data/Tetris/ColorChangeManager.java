package Games.Data.Tetris;

import java.awt.*;

public class ColorChangeManager {

    public ColorChangeManager(){

    }

    public static Color get_color(double value){
        double multiplicator = value/255;
        value = multiplicator*40;
        double frequency = 0.3;


        int r = (int) (Math.sin(frequency * value+0)*127+128);
        int g = (int) (Math.sin(frequency * value+2*Math.PI/3)*127+128);
        int b = (int) (Math.sin(frequency * value+4*Math.PI/3)*127+128);


        return new Color(r, g, b);

    }
}
