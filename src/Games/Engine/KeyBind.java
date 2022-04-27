package Games.Engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class KeyBind {
    public boolean pressed = false;
    private ActionListener listener;
    private int key;

    public KeyBind(){
    }

    public void update(int key, ActionListener listener){
        this.listener = listener;
        this.key = key;
        if (Keyboard.isKeyPressed(KeyEvent.VK_T) && !pressed){
            pressed = true;
            run();
        }
        if (!Keyboard.isKeyPressed(this.key)){
            pressed = false;
        }
    }

    public void run(){
        listener.actionPerformed(new ActionEvent(this, 0, "RUN"));
    }
}
