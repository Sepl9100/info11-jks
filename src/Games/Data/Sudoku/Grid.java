package Games.Data.Sudoku;

import Games.Engine.Game;
import Games.Sudoku;

import javax.swing.*;
import java.awt.*;

public class Grid {

    public JButton[][] grid_buttons;

    private Game game;
    private JPanel panel;


    public Grid(Game game, JPanel panel) {
        this.game = game;
        this.panel = panel;

        grid_buttons = new JButton[9][9];                       // 9x9 Button Array


        JButton tmp_btn;                                        // temporärer button zum erstellen
        int tmp_btn_x = 0;                                      // tmp_button koordinaten
        int tmp_btn_y = 0;

        // Für jede der 9 Spalten
        for(int y = 0; y < 9; y++){
            tmp_btn_y += 55;


            // Spalte mit 9 Buttons füllen
            for(int x = 0; x < 9; x++){
                tmp_btn_x = x*55+10;

                tmp_btn = new JButton();
                tmp_btn.setFont(game.font1);
                tmp_btn.setText(""+x);
                tmp_btn.setVisible(true);

                tmp_btn.setBounds(tmp_btn_x, tmp_btn_y, 50, 50);


                grid_buttons[y][x] = tmp_btn;
                panel.add(tmp_btn);
            }
        }
    }
}
