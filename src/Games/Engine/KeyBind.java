package Games.Engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class KeyBind {
    public boolean pressed = false;
    private ActionListener listener;
    private int key;

    public KeyBind(int key){
        this.key = key;
    }

    public boolean update(){
        boolean run = false;
        if (Keyboard.isKeyPressed(key) && !pressed){        // wenn gerade gedrückt wird aber kurz davor nicht
            pressed = true;
            run = true;
        }
        if (!Keyboard.isKeyPressed(this.key)){      // wenn nicht mehr gedrückt
            pressed = false;
        }
        return run;
    }
}
