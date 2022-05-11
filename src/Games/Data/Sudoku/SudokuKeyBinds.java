package Games.Data.Sudoku;

import Games.Engine.Game;
import Games.Engine.KeyBind;
import Games.Sudoku;

import java.awt.event.KeyEvent;

public class SudokuKeyBinds {

    private Sudoku game;

    private final KeyBind key_1 = new KeyBind(KeyEvent.VK_1);
    private final KeyBind key_2 = new KeyBind(KeyEvent.VK_2);
    private final KeyBind key_3 = new KeyBind(KeyEvent.VK_3);
    private final KeyBind key_4 = new KeyBind(KeyEvent.VK_4);
    private final KeyBind key_5 = new KeyBind(KeyEvent.VK_5);
    private final KeyBind key_6 = new KeyBind(KeyEvent.VK_6);
    private final KeyBind key_7 = new KeyBind(KeyEvent.VK_7);
    private final KeyBind key_8 = new KeyBind(KeyEvent.VK_8);
    private final KeyBind key_9 = new KeyBind(KeyEvent.VK_9);

    private final KeyBind key_n1 = new KeyBind(KeyEvent.VK_NUMPAD1);
    private final KeyBind key_n2 = new KeyBind(KeyEvent.VK_NUMPAD2);
    private final KeyBind key_n3 = new KeyBind(KeyEvent.VK_NUMPAD3);
    private final KeyBind key_n4 = new KeyBind(KeyEvent.VK_NUMPAD4);
    private final KeyBind key_n5 = new KeyBind(KeyEvent.VK_NUMPAD5);
    private final KeyBind key_n6 = new KeyBind(KeyEvent.VK_NUMPAD6);
    private final KeyBind key_n7 = new KeyBind(KeyEvent.VK_NUMPAD7);
    private final KeyBind key_n8 = new KeyBind(KeyEvent.VK_NUMPAD8);
    private final KeyBind key_n9 = new KeyBind(KeyEvent.VK_NUMPAD9);

    public SudokuKeyBinds(Sudoku game) {
        this.game = game;

    }
}
