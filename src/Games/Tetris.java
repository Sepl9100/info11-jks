package Games;

import Games.Data.Tetris.ColorChangeManager;
import Games.Data.Tetris.TileArray;
import Games.Data.Tetris.Tiles.Tile;
import Games.Data.Tetris.Tiles.Tilecolors;
import Games.Engine.Game;
import Games.Engine.Keyboard;
import Games.Engine.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Tetris extends Game {

    private TileArray array;

    private final JPanel start_screen;
    private final JCheckBox wide_mode;
    private final JCheckBox special_mode_cb;

    private Tile activetile;
    private boolean started = false;

    private boolean special_mode;

    private int tilesize = 30;
    private int left_offset = 390;

    public Tetris(Window window){
        super(window, "Tetris");
        this.setBackground(Color.black);

        // Start Screen UI
        start_screen = new JPanel();
        int width = 400;
        int height = 500;
        start_screen.setLocation(PANELWIDTH/2-width/2, PANELHEIGHT/2-height/2);
        start_screen.setSize(width, height);
        start_screen.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        wide_mode = new JCheckBox("Breiter Modus");     // auswählbare optionen
        start_screen.add(wide_mode, gbc);

        special_mode_cb = new JCheckBox("Spezial Modus");
        start_screen.add(special_mode_cb, gbc);

        setLayout(null);
        this.add(start_screen);

        gbc.gridx = 1;
        JButton btn = new JButton("Spiel starten");
        start_screen.add(btn, gbc);
        btn.addActionListener(e -> start_game());

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JLabel lbl = new JLabel("Steuerung:");
        lbl.setFont(font2);
        start_screen.add(lbl, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        lbl = new JLabel("A/S/D Bewegen");
        lbl.setFont(font1);
        start_screen.add(lbl, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        lbl = new JLabel("Q/E Drehen");
        lbl.setFont(font1);
        start_screen.add(lbl, gbc);

        window.pack();
    }

    public void start_game(){
        if (wide_mode.isSelected()){
            array = new TileArray(31, 20);
            left_offset = 75;
        }
        else{
            array = new TileArray();
        }
        started = true;



        activetile = new Tile(3, -1);
        this.remove(start_screen);
    }

    public void display_tile(Tile tile){
        for (int y_index = 0; y_index < tile.arraysize; y_index++){
            for (int x_index = 0; x_index < tile.arraysize; x_index++){
                int block = tile.array[x_index][y_index];
                if (block != 0){
                    g.setColor(Tilecolors.get(block, tick));      // block is color value
                    g.fillRect((tile.x+x_index)*tilesize + left_offset, (tile.y+y_index)*tilesize, tilesize, tilesize);
                    g.setColor(Color.black);
                    g.drawRect((tile.x+x_index)*tilesize + left_offset, (tile.y+y_index)*tilesize, tilesize, tilesize);
                }
            }
        }
    }

    public void display_array(){
        for (int y_index = 0; y_index < array.height; y_index++){
            for (int x_index = 0; x_index < array.width; x_index++){
                int block = array.get_tile(x_index, y_index);
                if (block != 0){
                    g.setColor(Tilecolors.get(block, tick));      // block is color value
                    g.fillRect((x_index)*tilesize + left_offset, (y_index)*tilesize, tilesize, tilesize);
                    g.setColor(Color.black);
                    g.drawRect((x_index)*tilesize + left_offset, (y_index)*tilesize, tilesize, tilesize);
                }
            }
        }
    }

    @Override
    public void update_loop() {
        for (int y = 0; y < PANELHEIGHT; y++){
            g.setColor(ColorChangeManager.get_color(tick*200/(y+200)%255));
            g.fillRect(0, y, 2000, 1);
        }
        //this.setBackground(ColorChangeManager.get_color(tick/2%255));
        if (started) {
            g.setColor(Color.darkGray);
            g.fillRect(left_offset, 0, array.width * tilesize, array.height * tilesize);
            display_tile(activetile);
            display_array();
            int tick_delay = 35;
            if (Keyboard.isKeyPressed(KeyEvent.VK_S)){
                tick_delay = 3;
            }
            if (tick % tick_delay == 0) {
                if (array.check_collision(activetile, 0, 1)) {
                    array.place_matrix(activetile);
                    activetile = new Tile(3, -1);
                }
                activetile.move(0, 1);
            }
            if (a_key_bind.update() && !array.check_collision(activetile, -1, 0)) {
                activetile.move(-1, 0);
            }
            if (d_key_bind.update() && !array.check_collision(activetile, 1, 0)) {
                activetile.move(1, 0);
            }
            if (q_key_bind.update()) {
                activetile.rotateCW();
                if (array.check_collision(activetile, 0, 0)) activetile.rotateCCW();
            }
            if (e_key_bind.update()) {
                activetile.rotateCCW();
                if (array.check_collision(activetile, 0, 0)) activetile.rotateCW();
            }


        }
    }
}
