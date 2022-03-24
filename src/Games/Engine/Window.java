package Games.Engine;

import Games.Engine.Sprite;
import Games.MainMenu;

import javax.swing.*;

public class Window extends JFrame {

    private final String TITLE = "JKS Spielesammlung";

    public Window(){
        super();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void update_title(String text){
        this.setTitle(TITLE + " - " + text);
    }
}
