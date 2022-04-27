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
        if (Keyboard.isKeyPressed(KeyEvent.VK_T) && !pressed){
            pressed = true;
            run = true;
        }
        if (!Keyboard.isKeyPressed(this.key)){
            pressed = false;
        }
        return run;
    }

    public void run(){
        listener.actionPerformed(new ActionEvent(this, 0, "RUN"));
    }
}
