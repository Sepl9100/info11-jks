package Games.Engine;

import Games.Engine.Sprite;
import Games.MainMenu;

import javax.swing.*;

public class Window extends JFrame {

    public Window(){
        super("JKS Spielesammlung");
        this.setVisible(true);
        this.setResizable(false);
        //this.pack();
        this.setLocationRelativeTo(null);
    }

    public void draw(Sprite sprite){

    }
}
